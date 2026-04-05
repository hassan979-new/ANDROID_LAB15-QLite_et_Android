package com.example.lab15.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab15.R;
import com.example.lab15.classes.Etudiant;
import com.example.lab15.service.EtudiantService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder> implements Filterable {

    private List<Etudiant> etudiants;
    private Context context;

    private List<Etudiant> etudiantsFiltre;
    private NewFilter mFilter;

    public EtudiantAdapter(Context context, List<Etudiant> etudiants){
        this.context = context;
        this.etudiants = etudiants;
        this.etudiantsFiltre = new ArrayList<>(this.etudiants);
        this.mFilter = new NewFilter();
    }


    @NonNull
    @Override
    public EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.etudiant_item, parent, false);
        return  new EtudiantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, int position) {
        Etudiant e = etudiantsFiltre.get(position);
        holder.nom.setText(e.getNom());
        holder.prenom.setText(e.getPrenom());

        holder.itemView.setOnClickListener(v -> {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
            View popup = LayoutInflater.from(context)
                    .inflate(R.layout.activity_main, null);
            builder.setView(popup);

            EditText etNom = popup.findViewById(R.id.nom);
            EditText etPrenom = popup.findViewById(R.id.prenom);
            Button btnValider = popup.findViewById(R.id.bn);
            Button btnSupprimer = popup.findViewById(R.id.delete);

            etNom.setText(e.getNom());
            etPrenom.setText(e.getPrenom());

            android.app.AlertDialog dialog = builder.create();
            dialog.show();

            btnValider.setOnClickListener(v1 -> {
                String newNom = etNom.getText().toString().trim();
                String newPrenom = etPrenom.getText().toString().trim();

                e.setNom(newNom);
                e.setPrenom(newPrenom);

                EtudiantService es = new EtudiantService(context);
                es.update(e);

                notifyItemChanged(position);
                dialog.dismiss();
            });

            btnSupprimer.setOnClickListener(v1 -> {
                EtudiantService es = new EtudiantService(context);
                es.delete(e);

                etudiants.remove(e);
                etudiantsFiltre.remove(e);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, etudiantsFiltre.size());

                dialog.dismiss();
            });

        });
    }

    @Override
    public int getItemCount() {
        return etudiantsFiltre.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    static class EtudiantViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nom, prenom;

        EtudiantViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.etd_img);
            nom = itemView.findViewById(R.id.tv_nom);
            prenom = itemView.findViewById(R.id.tv_prenom);
        }
    }

    private class NewFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Etudiant> filtered =new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filtered.addAll(etudiants);
            }else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Etudiant e : etudiants){
                    if (e.getNom().toLowerCase().contains(pattern) || e.getPrenom().toLowerCase().contains(pattern) || String.valueOf(e.getId()).equals(pattern)){
                        filtered.add(e);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            etudiantsFiltre.clear();
            etudiantsFiltre.addAll((List<Etudiant>) results.values);
            notifyDataSetChanged();
        }
    }
}
