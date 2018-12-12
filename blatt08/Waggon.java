public class Waggon{


	// Enums als public static, damit von Test darauf zugegriffen werden kann
	public static enum Klasse {ERSTEKLASSE, ZWEITEKLASSE};
	public static enum ToiletteStatus {FREI, BESETZT, DEFEKT};

	// benoetigte Attribute
	private int gesamtSitzplaetze;
	private int reserviertSitzplaetze;
	private int freieSitzplaetze;

	private boolean doppelStockWaggon;


	private Klasse klasse;
	private ToiletteStatus toiletteStatus;
	private Waggon kupplung;

	private boolean lichtAn = false;





	// -----------------------------------------



	/**
	*	Der Konstrutor ohne uebergebene Parameter erstellt einen Standardwaggon mit 300 Plaetzen der 2. Klasse
	*
	*	@return none
	*/

	public Waggon(){

		// Ein Standard-Waggon wird eingerichtet
		gesamtSitzplaetze = 300;
		reserviertSitzplaetze = 0;
		freieSitzplaetze = 300;

		doppelStockWaggon = false;

		klasse = Klasse.ZWEITEKLASSE;

		kupplung = null;

		// Per Zufall wird definiert, ob Klo defekt (mit 30 Prozent defekt)
		double kloZufall = Math.random();
		toiletteStatus = kloZufall <= 0.7 ? ToiletteStatus.FREI : ToiletteStatus.DEFEKT;

	}


	/**
	*	Es koennen dem Konstruktor die Parameter zur initialisierung uebergeben werden
	*
	*	@param gesSitzplaetze 	Die Gesamtzahl der Sitzplaetze als Integer
	*	@param resSitzplaetze 	Zahl der reservierten Sitzplaetze als Integer
	*	@param frSitzplaetze 	Anzahl der freien Sitzplaetze als Integer
	*	@param doppelStock 		Boolean, ob es sich Doppelstock-Waggon handelt
	*	@param klas 			Art der Waggon-Klasse als Wert des Enums 'Klasse'
	*	@param kuppl 			der in Fahrtrichtung naechste Waggon, als Objekt der Klasse Waggon
	*
	*	@return none
	*/

	public Waggon(int gesSitzplaetze, int resSitzplaetze, int frSitzplaetze, boolean doppelStock, Klasse klas, Waggon kuppl){
			
		// ueberpruefung, ob zuviele freie und reservierte Sitzplaetze
		if (resSitzplaetze + frSitzplaetze <= gesSitzplaetze) {
			
			// Die Attribute werden mit den uebergebenen Parametern initialisiert
			gesamtSitzplaetze = gesSitzplaetze;
			reserviertSitzplaetze = resSitzplaetze;
			freieSitzplaetze = frSitzplaetze;

			doppelStockWaggon = doppelStock;
			klasse = klas;

			kupplung = kuppl;

			// Bestimmung des Toilettestatus
			double kloZufall = Math.random();
			toiletteStatus = kloZufall <= 0.7 ? ToiletteStatus.FREI : ToiletteStatus.DEFEKT;
		

		}else{
			throw new ExceptionInInitializerError("Falsche Sitzplatzzahl uebergeben!");
		}
	}




	/**
	*	Rueckgabe der Selbstinfo fuer den println()-Befehl mit Ausgabe aller Attribute
	* 	Mit rekursiver Ueberpruefung und Aufruf des angehaengen Waggons
	*
	*	@return 	gibt die Selbstinfo und eventuelle Infos der nachfolgenden Zuege aus
	*/

	public String toString(){

		// Selbstinfo mit Ausgabe aller Attribute
		String selbstInfo = "Sitzplaetze [GES/RES/FREI]: [" + gesamtSitzplaetze + "/" + reserviertSitzplaetze + "/" + freieSitzplaetze + "]"
								+ ", Doppelstock: " + doppelStockWaggon + ", Klasse: " + klasse + ", Toilettenstatus: " 
								+ toiletteStatus + ", Licht an?: " + lichtAn;

		// Wenn nachfolgender Waggon: rekursiver Aufruf
		if (kupplung == null) {
			return selbstInfo;
		}else{
			String anhaengend = kupplung.toString();

			// Konkatenation der Infos der nachfolgenden Zuege
			return selbstInfo + "\nAnhaengend: " + anhaengend; 
		}
	}



	/**
	*	Rekursive Zaehlung der freien Sitzplaetze des Gespanns
	*
	*	@return 	die Anzahl der eigenen Sitzplaetze, evtl. addiert mit der rekursiven Zahl
	*				der nachfolgenden Waggons, wenn ein Nachfolger vorhanden ist
	*/

	public int countFreeSeats(){

		if (kupplung == null) {
			return freieSitzplaetze;
		}else{
			// Wenn Nachfolger vorhanden: Addition der eigenen Sitzplaetze mit den nachfolgenden Waggons
			return kupplung.countFreeSeats() + freieSitzplaetze;
		}
	}



	/**
	*	Schaltet das Licht des aktuellen und der nachfolgenden Waggons rekursiv entweder an oder aus
	*
	*	@param licht 	true, wenn das Licht angeschaltet werden soll, false, wenn ausgeschaltet
	*
	*/

	public void toggleLight(boolean licht){
		lichtAn = licht;
		if (kupplung != null) {
			kupplung.toggleLight(licht);
		}
	}


	/**
	*	Simuliert eine Kontrolle, d.h. alle nicht defekten Toiletten sind besetzt
	*	Rekusriver Aufruf der nachfolgenden Waggons
	*/

	public void kontrolle(){

		// Toilette besetzt, wenn nicht defekt
		if (toiletteStatus != ToiletteStatus.DEFEKT) {
			toiletteStatus = ToiletteStatus.BESETZT;
		}
		// Rekursion
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

		// Sitzplatzmanagement
		reserviertSitzplaetze = 0;
		freieSitzplaetze = gesamtSitzplaetze;

		// Defekte Toilette mit 50 prozentiger Wahrscheinlichkeit repariert
		if (toiletteStatus == ToiletteStatus.DEFEKT) {
			toiletteStatus = Math.random() >= 0.5 ? ToiletteStatus.FREI : ToiletteStatus.DEFEKT;
		
		// Besetzte Toiletten werden auch frei
		}else if (toiletteStatus == ToiletteStatus.BESETZT) {
			toiletteStatus = ToiletteStatus.FREI;
		}

		// Rekursion
		if (kupplung != null) {
			kupplung.endhalt();
		}
		
	}





} // Ende Waggon