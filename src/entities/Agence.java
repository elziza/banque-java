package entities;

public class Agence {
    // attribut static
    private static int nbre;
    // attribut instance
    private int id;//0
    private String numero;//null
    private String adresse;//null
    private String tel;//nul 
                        //bool=false

    public Agence(int id) {
        this.id = id;
    }

    public Agence(int id, String numero, String adresse, String tel) {
        this.id = id;
        this.numero = numero;
        this.adresse = adresse;
        this.tel = tel;
    }

    // constructeur par defaut              
     public Agence() {
        nbre++;
        id=nbre;
        numero="AG_"+nbre;
       }
                        
   // surcharge ou polymorphisme parametrique
   // surcharge d'insertion
    public Agence(String adresse, String tel) {
    this.adresse = adresse;
    this.tel = tel;
    nbre++;
    id=nbre;
    numero="AG_"+nbre;
       }
    


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    //serialisation objet--> Chaine
    @Override
    public String toString() {
        return "Agence [adresse=" + adresse + ", id=" + id + ", numero=" + numero + ", tel=" + tel + "]";
    }

}
