public class Test{


	// Methode baut einen Zug
	public static void main(String[] args){

		// Deklaration der benoetigten Referenzattribute
		Train zug;
		Waggon wagen1;
		Waggon wagen2;
		Waggon wagen3;
		Waggon wagen4;


		// Erstellen der Objekte nach Abb. 2
		wagen4 = new Waggon(50, 17, 3, true, Waggon.Klasse.ERSTEKLASSE, null);
		wagen3 = new Waggon(100, 32, 11, true, Waggon.Klasse.ZWEITEKLASSE, wagen4);
		wagen2 = new Waggon(100, 12, 64, true, Waggon.Klasse.ZWEITEKLASSE, wagen3);
		wagen1 = new Waggon(50, 24, 3, true, Waggon.Klasse.ERSTEKLASSE, wagen2);

		zug = new Train(412, 250, Train.Antriebsart.ELEKTRISCH, 13500, wagen1);


		// Ausgabetest
		System.out.println(zug);
		System.out.println();


		// Sitzplatzcount
		System.out.println("Freie Sitzplaetze: " + zug.countFreeSeats());
		System.out.println();


		// Lichttest
		zug.toggleLight(true);
		System.out.println(zug);
		System.out.println();


		// Kontrolle
		zug.kontrolle();
		System.out.println(zug);
		System.out.println();


		// Test des Endhaltes
		zug.endhalt();
		System.out.println(zug);
		System.out.println();

	}


}	// Ende Test