public class Train{

	// Enums als public static, damit von Test darauf zugegriffen werden kann
	public static enum Antriebsart {DIESEL, ELEKTRISCH};


	// benoetigte Attribute
	private int baureihe;
	private int hoechstgeschwindigkeit;

	private Antriebsart antriebsArt;
	private int anzahlPS;

	private Waggon kupplung;





	// -------------------------------------------------




	/**
	*	Konstruktor ohne Parameteruebergabe initialisiert einen ICE der Baureihe 412
	*/

	public Train(){

		// Als Standard wird ein ICE der Baureihe 412 'initialisiert' 
		baureihe = 412;
		hoechstgeschwindigkeit = 230;

		antriebsArt = Antriebsart.ELEKTRISCH;
		anzahlPS = 6640;

		// standardmaessig kein Waggon gekoppelt
		kupplung = null;

	}


	/**
	*	Kontruktor mit Uebergabemoeglichkeit aller Parameter zur Initialisierung
	*
	*	@param baur 					die Baureihennummer als Integer
	*	@param hoechgeschwindigkeit 	die Hoechstgeschwindigkeit in km/h als Integer
	*	@param antrArt 					die Art des Antriebs als Wert des Enums Antriebsart
	*	@param anzPS 					die Anzahl der PS als Integer
	*	@param kupp 					von Fahrtrichtung aus nachfolgender Waggon als Objekt der Klasse Waggon
	*/

	public Train(int baur, int hoechgeschwindigkeit, Antriebsart antrArt, int anzPS, Waggon kupp){

		// Initialisierung mit gegebenen Parametern
		baureihe = baur;
		hoechstgeschwindigkeit = hoechgeschwindigkeit;
		
		antriebsArt = antrArt;
		anzahlPS = anzPS;

		kupplung = kupp;

	}


	/**
	*	Rueckgabe der Selbstinfo fuer den println()-Befehl mit Ausgabe aller Attribute
	* 	Mit rekursiver Ueberpruefung und Aufruf des angehaengen Waggons
	*
	*	@return 	gibt die Selbstinfo und eventuelle Infos der nachfolgenden Zuege aus
	*/

	public String toString(){

		String selbstInfo = "Baureihe: " + baureihe + ", Hoechstgeschwindigkeit: " + hoechstgeschwindigkeit + ", Antriebsart: " + antriebsArt
							+ ", Anzahl PS: " + anzahlPS;

		if (kupplung == null) {
			return selbstInfo;
		}else{
			return selbstInfo + "\nAnhaengend: " + kupplung.toString();
		}
	

	}


	/**
	*	Rekursive Zaehlung der freien Sitzplaetze des Gespanns
	*
	*	@return 	die Anzahl der Sitzplaetze der angehaengten Waggons per Rekursion
	*				0, wenn kein Waggon angehaengt
	*/

	public int countFreeSeats(){
		if (kupplung == null) {

			// null, wenn kein Waggon angehaengt
			return 0;
		}else{
			return kupplung.countFreeSeats();
		}
	}


	/**
	*	Schaltet das Licht der nachfolgenden Waggons rekursiv entweder an oder aus
	*
	*	@param licht 	true, wenn das Licht angeschaltet werden soll, false, wenn ausgeschaltet
	*
	*/

	public void toggleLight(boolean licht){
		if (kupplung != null) {
			kupplung.toggleLight(licht);
		}
	}



	/**
	*	Simuliert eine Kontrolle, d.h. alle nicht defekten Toiletten sind besetzt
	*	Rekusriver Aufruf der nachfolgenden Waggons
	*/

	public void kontrolle(){
		if (kupplung != null) {
			kupplung.kontrolle();
		}
	}



	/**
	*	Simuliert den Endhalt, d.h. reservierte Sitzplaetze werden geloescht und alle Personen steigen aus.
	*	Jede Defekte Toilette wird mit einer Wahrscheinlichkeit von 50 Prozent repariert
	*	Rekursiver Aufruf der nachfolgenden Waggons
	*/

	public void endhalt(){
		if (kupplung != null) {
			kupplung.endhalt();
		}
	}



} // Ende Train