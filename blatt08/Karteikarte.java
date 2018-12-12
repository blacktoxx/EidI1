class Karteikarte{

    private String vorderseite;
    private String rueckseite;


    //Konstruktor
    public Karteikarte(String vorderseite, String rueckseite){
        this.vorderseite = vorderseite;
        this.rueckseite = rueckseite;
    }


    public static void main(String[] args) {
        
        Karteikarte k1 = new Karteikarte("kompakte Menge",
                "Eine Menge heißt _kompakt_, wenn sie abgeschlossen und beschränkt ist\n Beispiel: [0,1]");

        Karteikarte k2 = new Karteikarte("kompakte Menge",
                "Eine Menge heißt _kompakt_, wenn sie abgeschlossen und beschränkt ist\n Beispiel: [0,1]");

        Karteikarte k3 = new Karteikarte("Eine Menge heißt _kompakt_, wenn sie abgeschlossen und beschränkt ist\n Beispiel: [0,1]", "kompakte Menge");

        Karteikarte k4 = new Karteikarte("Wilde Moehre",
                "Die Wilde Möhre ist ein Doldenblütler und ein Elternteil der Karotte.");

        System.out.println(k1);
        System.out.println(k1.equals(k2));
        System.out.println(k1.equals(k3));
        System.out.println(k1.equals(k4));
    }



    /*
    *   b) gibt zuerst den Klassennamen, von welcher das Objekt abstammt und dann ein @, gefolgt von einem
    *   hexadezimalen Hash-Code, welcher das Objekt repraesentiert.
    *   Das ist die Standardfunktion toString(), welche jedes Objekt von der Klasse 'Object' erbt. 
    *
    *   c) um dies nun zu 'beheben' und eine besser lesbare Ausgabe zu erhalten, muss die Methode toString()
    *   selbsttaendig in anderer Form in der Klasse definiert werden, bspw. durch Ausgabe eines Strings mit Namen
    */



    public String toString(){

        // Vorder- und Rueckseite werden einfach als String ausgegeben, um leichter identifizierbar zu sein
        return vorderseite + "\n" + rueckseite;
    }


    // Uebergebenes Objekt ist vom Typ Karteikarte
    public boolean equals(Karteikarte obj){

        //
        if ((vorderseite.equals(obj.vorderseite) && rueckseite.equals(obj.rueckseite)) || (vorderseite.equals(obj.rueckseite)) || (obj.vorderseite.equals(rueckseite))) {
            return true;
        }else{
            return false;
        }
    }

}