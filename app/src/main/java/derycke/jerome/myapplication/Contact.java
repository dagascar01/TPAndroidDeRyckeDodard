package derycke.jerome.myapplication;

import android.os.Parcel;
import android.os.Parcelable;



public class Contact implements Parcelable {

    private int id;
    private String nom;
    private String prenom;
    private String groupe;
    private String num;
    private String mail;
    private String adresse;


    public Contact(int id, String n, String p, String nu,String ml, String adr, String grp){
        this.id=id;
        this.nom = n;
        this.prenom = p;
        this.num = nu;
        this.mail = ml;
        this.adresse = adr;
        this.groupe = grp;
    }

    public Contact(String n, String p, String nu,String ml, String adr, String grp){
        this.nom = n;
        this.prenom = p;
        this.num = nu;
        this.mail = ml;
        this.adresse = adr;
        this.groupe = grp;
    }

    protected Contact(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        prenom = in.readString();
        num = in.readString();
        mail = in.readString();
        adresse = in.readString();
        groupe = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(num);
        dest.writeString(mail);
        dest.writeString(adresse);
        dest.writeString(groupe);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGroupe() {
        return groupe;
    }

    public String getNum() { return num; }

    public void setPrenom(String p) {
        this.prenom = p;
    }

    public String getMail() {
        return mail;
    }

    public String getAdresse() {
        return adresse;
    }


    public void setId(int id) {
        this.id = id;
    }
}
