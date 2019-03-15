package derycke.jerome.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "contact.db";

    private static final int DATABASE_VERSION = 4;

    private SQLiteDatabase db;

    private static String TABLE_CONTACT = "CONTACT";

    private static String CREATE_TABLE_CONTACT = "create table "
            + TABLE_CONTACT + "("
            + "id integer PRIMARY KEY AUTOINCREMENT,"
            + "nom varchar(15) NOT NULL,"
            + "prenom varchar(15) NOT NULL,"
            + "num varchar(10) NOT NULL, "
            + "mail varchar NOT NULL, "
            + "adresse varchar NOT NULL,"
            + "groupe varchar NOT NULL" +")";

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        onCreate(db);
    }

    public long insert(Contact contact){
        ContentValues contactToInsert  = new ContentValues();
        contactToInsert.put("nom",contact.getNom());
        contactToInsert.put("prenom",contact.getPrenom());
        contactToInsert.put("num",contact.getNum());
        contactToInsert.put("mail",contact.getMail());
        contactToInsert.put("adresse",contact.getAdresse());
        contactToInsert.put("groupe",contact.getGroupe());
        return db.insert(TABLE_CONTACT, null, contactToInsert);
    }

    public void deleteContact(String nom, String prenom){
        this.getWritableDatabase().delete(TABLE_CONTACT,"nom ='"+nom+"' AND prenom ='"+prenom+"'",null );
    }


    public ArrayList<Contact> recup_contact() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String[] string_contact = new String[]{"id","nom", "prenom", "num", "mail", "adresse" , "groupe"};

        Cursor cursorResults = db.query(false, TABLE_CONTACT, string_contact, null, null, null, null, null, null, null);

        if(null!= cursorResults) {
            if (cursorResults.moveToFirst()) {
                do {
                    Contact c = new Contact(cursorResults.getInt(0),cursorResults.getString(1),cursorResults.getString(2), cursorResults.getString(3)
                            , cursorResults.getString(4), cursorResults.getString(5), cursorResults.getString(6));
                    contacts.add(c);
                } while (cursorResults.moveToNext());
            }
        }
        cursorResults.close();
        return contacts ;
    }

}
