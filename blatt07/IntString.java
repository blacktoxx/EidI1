public class IntString {

	public static void main(String[] args) {
		
		// Testing

		// isIntString
		System.out.println("[isIntString]");
		System.out.println(isIntString("asdf"));	// false
		System.out.println(isIntString("1215"));	// true
		System.out.println(isIntString("124asd"));	// false
		System.out.println(isIntString("asg123"));	// false
		System.out.println();


		// isDigit
		System.out.println("[isDigit]");
		System.out.println(isDigit('2'));	// true
		System.out.println(isDigit('0'));	// true
		System.out.println(isDigit('A'));	// false
		System.out.println(isDigit('#'));	// false
		System.out.println();


		// add
		System.out.println("[add]");
		System.out.println(add("14","12"));		// "26"
		System.out.println(add("151","0"));		// "151"
		System.out.println(add("1151","91"));	// "1242"
		System.out.println(add("99","99"));		// "198"
		System.out.println(add("999","99"));	// "1098"
		System.out.println(add("99999","99"));	// "100098"
		System.out.println(add("4","20"));		// "24" 
		System.out.println(add("asd","12"));	// "NaN"
		System.out.println(add("14","asdga"));	// "NaN"
		System.out.println(add("sag","eag"));	// "NaN"
		System.out.println();


		// mul
		System.out.println("[mul]");
		System.out.println(mul("1","4"));		// "4"
		System.out.println(mul("10","4"));		// "40"
		System.out.println(mul("4","10"));		// "40"
		System.out.println(mul("5","21"));		// "105"
		System.out.println(mul("121","158"));	// "19118"
		System.out.println(mul("11","11"));		// "121"
		System.out.println(mul("124","asd"));	// "NaN"
		System.out.println(mul("asg","4"));		// "NaN"
		System.out.println(mul("asdg","asga"));	// "NaN"
	}




	/**
	*	gibt einen Integer als String zurueck
	*
	*	@param zahl 	Zahl, welche umgewandelt werden soll
	*	@return 		in String umgewandelte Zahl
	*/

	public static String intToIntString(int zahl){

		// Methode der Klasse String, welche einen uebergebenen Wert in String konvertiert
		return String.valueOf(zahl);
	}



	/**
	*	Ueberprueft, ob ein Zeichen eine Zahl darstellt
	*	
	*	@param zeichen 	das zu ueberpruefende Zeichen
	*	@return 		'true' wenn Darstellung einer Zahl, ansonsten 'false'
	*/

	public static boolean isDigit(char zeichen){
		
		// wenn char im Bereich der Zahlen
		if (zeichen >= '0' && zeichen <= '9') {
			return true;
		}else{
			return false;
		}
	}



	/**
	*	Gibt die in Integer konvertierte Ziffer eines char's zurueck
	*
	*	@param zeichen 	Char, welcher konvertiert werden soll
	*	@return 		wenn char eine Zahlendarstellung: Konvertieren in Integer, ansonsten 
	*					Rueckgabe von 0
	*/

	public static int digitCharToInt(char zeichen){
		if (isDigit(zeichen)) {
			return zeichen - '0';			
		}else{
			// throw new Exception("KONVERTIERUNGSFEHLER charToInt");
			return 0;
		}
	}



	/**
	*	Ueberprueft, ob ein String nicht-null, nicht-leer oder keine anderen Zeichen als Zahlen enthaelt.
	*	Wenn ja, wird 'true' zurueckgegeben, ansonsten 'false'
	*
	*	@param zeichenkette 	die zu untersuchende Zeichenkette
	*	@return 				'true', wenn nicht-null, nicht-leer und keine anderen Zeichen als Zahlen,
	*							ansonsten 'false'
	*/

	public static boolean isIntString(String zeichenkette){
		if ((zeichenkette != null) && (!zeichenkette.equals(""))) {
		
			// Die zeichenkette wird durchlaufen und auf digitChars geprueft
			for (int i = 0; i < zeichenkette.length(); i++) {

					// Sobald Zeichen keine Zahl ist, wird 'false' zurueckgegeben
					if (!isDigit(zeichenkette.charAt(i))) {
						return false;
					}
				}	
		}else{
			return false;
		}
		return true;
	}



	/**
	*	Invertiert den uebergebenen String (nicht gefragt)
	*
	*	@param string 	die Zeichenkette, welche invertiert werden soll
	*	@return 		die invertierte Zeichenkette
	*/

	public static String invertString(String string){

		String stringInverted = "";
		for (int i = string.length() - 1; i >= 0; i-- ) {
			stringInverted += string.charAt(i);
		}
		return stringInverted;
	}


	/**
	*	Addiert zwei Zahlen, welche als String uebergeben werden
	*
	*	@param zahl1 	die erste Zahl, welche addiert werden soll als String
	*	@param zahl2 	die zweite Zahl, welche addiert werden soll als String
	*
	*	@return 		Summe der beiden Zahlen als String
	*/

	public static String add(String zahl1, String zahl2){

		// Ueberpruefung auf Wohldefiniertheit
		if (!isIntString(zahl1) || (!isIntString(zahl2))){
			return "NaN";
		}

		String ergebnis = "";
		int uebertrag = 0;

		// die Zahlen werden nach Anzahl der Stellen sortiert
		String zahlLaenger = zahl1.length() >= zahl2.length() ? zahl1 : zahl2;
		String zahlKuerzer = zahlLaenger == zahl1 ? zahl2 : zahl1;


		// Zaehlvariablen werden hier bereits deklariert, damit in speateren Schleife wiederverwendet werden koennen
		int l = zahlLaenger.length() - 1;
		int k = zahlKuerzer.length() - 1;

		for (; k >= 0; l--, k--) {

			// Addition der jeweiligen Stellen und des Uebertrags
			int digit = digitCharToInt(zahlLaenger.charAt(l)) + digitCharToInt(zahlKuerzer.charAt(k)) + uebertrag;

			// Ueberpruefung, ob Uebertrag benoetigt wird
			if (digit >= 10) {
				digit = digit % 10;
				uebertrag = 1;
			}else{
				uebertrag = 0;
			}

			// Das Ergebnisdigit wird dem Ergebnis angefuegt
			ergebnis += intToIntString(digit);
		}

		// der 'ueberstehende' Teil der laengeren Zahl wird noch angehaengt
		for (; l >= 0; l--) {
			int digit = digitCharToInt(zahlLaenger.charAt(l)) + uebertrag;

			// Es wird nochmal auf Uebertrag geprueft, fuer Zahlen wie 999
			if (digit >= 10) {
				digit = digit % 10;
				uebertrag = 1;
			}else{
				uebertrag = 0;
			}
			ergebnis += intToIntString(digit);
			
		}

		// Fall, dass zum Schluss noch ein uebertrag bleibt, der angefuegt werden muss
		if (l == -1 && uebertrag == 1) {
			ergebnis += intToIntString(uebertrag);
		}

		// Das Ergebnis muss noch invertiert werden, da beim String von hinten nach vorne addiert wird
		return invertString(ergebnis);
	}




	/**
	*	Multiplikation zweier Zahlen, welche als Strings uebergeben werden
	*
	*	@param zahl1 	die erste Zahl, welche multipliziert werden soll als String
	*	@param zahl2 	die zweite Zahl, welche multipliziert werden soll als String
	*
	*	@return 		die Multiplikation der beiden Zahlen als String
	*/

	public static String mul(String zahl1, String zahl2){

		// Ueberpruefung auf Wohldefiniertheit
		if (!isIntString(zahl1) || (!isIntString(zahl2))){
			return "NaN";
		}

		// ergebnis muss als 0 initialisiert werden, da darauf spaeter addiert wird
		String ergebnis = "0";

		int z1 = zahl1.length() - 1;
		int z2 = zahl2.length() - 1;

		// Es werden die Ziffern von hinten nach vorne durchgegangen
		for (int i = z1; i >= 0; i--) {
			for (int j = z2; j >= 0; j--) {

				char charZahl1 = zahl1.charAt(i);
				char charZahl2 = zahl2.charAt(j);

				// die zwei Stellen werden einfach multipliziert und spaeter noch die zugehoerigen Nullen angefuegt
				int digit = digitCharToInt(charZahl1) * digitCharToInt(charZahl2);
				String zwischenErgebnis = intToIntString(digit);

				// Hinzufuegen der Nullen am Schluss
				for (int k = 0; k < (z1 + z2) - (i + j); k++) {
					zwischenErgebnis += intToIntString(0);
				}

				ergebnis = add(ergebnis, zwischenErgebnis);
			}
		}

		return ergebnis;
	}



} // Ende IntString