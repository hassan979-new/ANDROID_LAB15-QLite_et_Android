package com.example.lab15.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab15.classes.Etudiant;
import com.example.lab15.util.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class EtudiantService {

    private static final String TABLE_NAME = "etudiant";

    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String[] COLUMNS = {KEY_ID, KEY_NOM, KEY_PRENOM};

    private final MySQLiteHelper helper;

    public EtudiantService(Context context){
        this.helper =new MySQLiteHelper(context);
    }


    public void create(Etudiant e){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOM,e.getNom());
        values.put(KEY_PRENOM, e.getPrenom());

        db.insert(TABLE_NAME,null,values);
        Log.d("insert",e.getNom());

        db.close();

    }

    public void update(Etudiant e){
        SQLiteDatabase db = this.helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, e.getId());
        values.put(KEY_NOM, e.getNom());
        values.put(KEY_PRENOM, e.getPrenom());

        db.update(TABLE_NAME, values, "id = ?", new String[]{e.getId()+""});
        db.close();
    }

    public Etudiant findById(int id){
        Etudiant e = null;
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()){
            e = new Etudiant();
            e.setId(cursor.getInt(0));
            e.setNom(cursor.getString(1));
            e.setPrenom(cursor.getString(2));
        }
        cursor.close();
        db.close();
        return e;
    }

    public void delete(Etudiant e){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE_NAME,"id = ?",new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public List<Etudiant> findAll(){
        List<Etudiant> etudiants = new ArrayList<>();
        String req = "select * from "+TABLE_NAME;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(req, null);
        if (cursor.moveToFirst()){
            do {
                Etudiant e = new Etudiant();
                e.setId(cursor.getInt(0));
                e.setNom(cursor.getString(1));
                e.setPrenom(cursor.getString(2));
                etudiants.add(e);
                Log.d("id = ", e.getId() + "");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return etudiants;
    }
}
