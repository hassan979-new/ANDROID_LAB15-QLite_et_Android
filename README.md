# Android Java – Gestion des Étudiants avec RecyclerView & SQLite

## 📖 Description
Ce projet Android Java illustre la mise en place d’une **application de gestion des étudiants** utilisant **RecyclerView** pour l’affichage et **SQLite** pour la persistance des données.  
L’application permet d’ajouter, modifier, supprimer et rechercher des étudiants grâce à une interface interactive.

---

## 📂 Structure du projet

```
app/
└── src/
└── main/
├── java/com/example/lab15/
│   ├── adapter/
│   │   └── EtudiantAdapter.java
│   ├── classes/
│   │   └── Etudiant.java
│   ├── service/
│   │   └── EtudiantService.java
│   ├── util/
│   │   └── MySQLiteHelper.java
│   └── RecycleView.java
└── res/
├── layout/
│   ├── activity_list.xml
│   └── etudiant_item.xml
├── menu/
│   └── menu.xml
```
---
## 📲 Installation de l’APK

Pour installer l’APK de ce projet Android :

1. **Compiler l’application**  
   - Ouvrir le projet dans **Android Studio**.  
   - Aller dans le menu **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
   - <img width="960" height="540" alt="image" src="https://github.com/user-attachments/assets/f8013d07-8ca0-44f1-ba68-70147e031e22" />
   - Le fichier APK sera généré dans le dossier :  
     ```
     app/build/outputs/apk/debug/app-debug.apk
     ```
2. **Transférer l’APK sur votre appareil Android**  
   - Copier le fichier `app-debug.apk` sur votre smartphone (via câble USB, Bluetooth,...).  

3. **Autoriser les sources inconnues**  
   - Sur votre appareil Android, aller dans **Paramètres > Sécurité**.  
   - Activer l’option **Installer des applications depuis des sources inconnues**.  

4. **Installer l’APK**  
   - Ouvrir le fichier `app-debug.apk` sur votre smartphone.  
   - Suivre les instructions pour installer l’application.  

5. **Lancer l’application**  

---

## ⚙️ Fonctionnalités
- **EtudiantAdapter**  
  - Gère l’affichage des étudiants dans un `RecyclerView`.  
  - Permet la modification et suppression via une boîte de dialogue.  
  - Implémente `Filterable` pour la recherche par nom, prénom ou ID.  

- **Etudiant**  
  - Classe modèle représentant un étudiant avec `id`, `nom`, `prenom`.  

- **EtudiantService**  
  - Fournit les opérations CRUD (Create, Read, Update, Delete).  
  - Utilise `SQLiteDatabase` pour stocker et gérer les étudiants.  
  - Méthodes : `create`, `update`, `findById`, `delete`, `findAll`.  

- **MySQLiteHelper**  
  - Classe utilitaire pour la création et mise à jour de la base SQLite.  
  - Crée la table `etudiant` avec colonnes `id`, `nom`, `prenom`.  

- **RecycleView Activity**  
  - Affiche la liste des étudiants via `RecyclerView`.  
  - Intègre une `Toolbar` avec `SearchView` pour filtrer les étudiants.  

---

## 🖥️ Exemple d’exécution

https://github.com/user-attachments/assets/059dad18-50a8-48f7-b593-2aba6a6905e5

---

## 💡 Concepts pratiqués
- Utilisation de **RecyclerView** et **Adapter personnalisé**.  
- Persistance des données avec **SQLite**.  
- Gestion des opérations CRUD via un service dédié.  
- Filtrage dynamique avec `Filterable`.  
- Intégration d’une `Toolbar` et d’un `SearchView`.  
- Interaction utilisateur avec `AlertDialog` et formulaires.  

---

## 🧑‍💻 Auteur
👤 **Agouram Hassan**  
📱 Développement Android Java  
🎓 Instructor : **Mr. LACHGAR**  
📅 5 Avril 2026
