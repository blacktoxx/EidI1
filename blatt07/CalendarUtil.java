public class CalendarUtil {


	// Definition der Enums
	enum Months {JANUAR, FEBRUAR, MAERZ, APRIL, MAI, JUNI, JULI, AUGUST, SEPTEMBER, OKTOBER, NOVEMBER, DEZEMBER};
	enum Weekdays {SONNTAG, MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG};



	public static void main(String[] args){

		// Methoden-Testing

		// getNthWeekday
		System.out.println("[getNthWeekday]");
		System.out.println(getNthWeekday(1));	// Montag
		System.out.println(getNthWeekday(5));	// Freitag
		System.out.println(getNthWeekday(7));	// Sonntag
		System.out.println(getNthWeekday(14));	// Sonntag
		System.out.println();


		// isLeapYear
		System.out.println("[isLeapYear]");
		System.out.println(isLeapYear(1800));	// false
		System.out.println(isLeapYear(1900));	// false
		System.out.println(isLeapYear(2000));	// true
		System.out.println(isLeapYear(2100));	// false
		System.out.println();


		// getDaysInMonth
		System.out.println("[getDaysInMonth]");
		System.out.println(getDaysInMonth(Months.JANUAR,2018)); 	// 31
		System.out.println(getDaysInMonth(Months.SEPTEMBER,2018)); 	// 30
		System.out.println(getDaysInMonth(Months.FEBRUAR,2018)); 	// 28
		System.out.println(getDaysInMonth(Months.FEBRUAR,2000)); 	// 29
		System.out.println();


		// dayInYear
		System.out.println("[dayInYear]");
		System.out.println(dayInYear(1, Months.JANUAR, 2018));		// 1
		System.out.println(dayInYear(31, Months.DEZEMBER, 2000));	// 366
		System.out.println(dayInYear(31, Months.DEZEMBER, 2018));	// 365
		System.out.println();


		// daysBetween
		System.out.println("[daysBetween]");
		System.out.println(daysBetween(1,1,2018,1,1,2018)); // 0
		System.out.println(daysBetween(1,1,2018,6,1,2018)); // 5
		System.out.println(daysBetween(1,1,2018,1,2,2018)); // 31
		System.out.println(daysBetween(1,1,2018,1,1,2019)); // 365
		System.out.println(daysBetween(1,1,2000,1,1,2018)); // 6575
		System.out.println(daysBetween(2,3,2000,1,3,2001)); // 364
		System.out.println(daysBetween(2,2,2001,1,2,2000)); // 367
		System.out.println();
		

		// getWeekdayFromDate
		System.out.println("[getWeekdayFromDate]");
		System.out.println(getWeekdayFromDate(27, Months.NOVEMBER, 2018));	// Dienstag
		System.out.println(getWeekdayFromDate(4, Months.JULI, 1998));		// Samstag
		System.out.println(getWeekdayFromDate(1, Months.SEPTEMBER, 2002)); 	// Sonntag
		System.out.println(getWeekdayFromDate(15, Months.JANUAR, 1997)); 	// Mittwoch
		System.out.println(getWeekdayFromDate(29, Months.FEBRUAR, 2000)); 	// Dienstag
		System.out.println(getWeekdayFromDate(17, Months.SEPTEMBER, 2020)); // Donnerstag
	
	}



	/**
	*	Bekommt eine Zahl uebergeben und gibt den dazugehoerigen Wochentag aus
	*	
	*	@param tag 	die Nummer des gesuchten Tages
	*
	*	@return 	den zur Nummer gehoerenden Tag aus dem Enum 'Weekdays'
	*/

	public static Weekdays getNthWeekday(int tag){

		// fuer tag > 6
		tag = tag % Weekdays.values().length;

		// der n-te Wochentag wird zurueckgegeben
		return Weekdays.values()[tag];
	}



	/**
	*	Gibt an, ob das uebergebene Jahr ein Schaltjahr ist
	*
	*	@param jahr  das zu ueberpruefende Jahr als Integer
	*
	*	@return 	 'true', wenn Schaltjahr, ansonsten 'false'
	*/

	public static boolean isLeapYear(int jahr){

		if (jahr % 400 == 0) {
			return true;
		}
		else if ((jahr % 4 == 0) && (jahr % 100 != 0)) {
			return true;
		}
		return false;

	}


	/**
	*	Gibt die Anzahl der Tage eines Monats, abhaengig vom Jahr
	*
	*	@param monat 	der Monat, wessen Anzahl der Tage zurueckgegeben werden soll
	*	@param jahr 	das zugehoerige Jahr fuer evtlle Schaltjahre
	*
	*	@return  		
	*/

	public static int getDaysInMonth(Months monat, int jahr){

		int indexMonat = monat.ordinal();

		// Monate Januar - Juli
		if (indexMonat < 7) {

			// Februar wird gesondert berücksichtigt
			if (monat != Months.FEBRUAR) {

				// Wenn gerader Index 31 Tage
				return indexMonat % 2 == 0 ? 31 : 30;

			}else {
				return isLeapYear(jahr) ? 29 : 28;
			}
		// Monate August - Dezember					
		}else{
			return indexMonat % 2 == 0 ? 30 : 31;
		}
	}



	/**
	*	Berechnet, um den wievielten Tag im Jahr es sich bei dem uebergebenen Datum handelt
	*
	*	@param tag 		gesuchter Tag als Integer
	*	@param monat 	Monat des gesuchten Tages als Wert des Enums
	*	@param jahr 	gesuchtes Jahr als Integer
	*
	*	@return 		Integer mit der Position des Tages im Jahr
	*/

	public static int dayInYear(int tag, Months monat, int jahr){

		int anzahlTage = 0;

		for (int i = 0; i < monat.ordinal(); i++) {

			// die vorhergegangenen, abgeschlossenen Monate werden addiert
			anzahlTage += getDaysInMonth(Months.values()[i], jahr);
		}
		// zuletzt noch die 'Nummer' des Tages addiert
		return anzahlTage + tag;

	}




	/**
	*	Gibt die Anzahl der Tage zwischen zwei uebergebenen Daten. Wenn Datum2 vor Datum1 liegt,
	* 	ruft die Methode sich selbst mit vertauschen Parametern auf
	*
	*	@param tag1 	Tag des ersten Datums als Integer
	*	@param monat1 	Monat des ersten Datums als Integer
	*	@param jahr1 	Jahr des ersten Datums als Integer
	* 	@param tag2 	Tag des zweiten Datums als Integer
	*	@param monat2	Monat des zweiten Datums als Integer
	*	@param jahr2 	Jahr des zweiten Datums als Integer
	*
	*	@return 		die Anzahl der Tage zwischen den beiden Daten
	*/

	public static int daysBetween(int tag1, int monat1, int jahr1, int tag2, int monat2, int jahr2) {

		int tagDifferenz = 0;

		// vom Index muss 1 abgezogen werden, da Arrays bei 0 anfangen
		int tagImJahr1 = dayInYear(tag1, Months.values()[monat1 - 1], jahr1);
		int tagImJahr2 = dayInYear(tag2, Months.values()[monat2 - 1], jahr2);

		// Wenn das uebergebene Datum2 frueher als Datum1 ist
		if ((jahr1 == jahr2 && tagImJahr2 < tagImJahr1) || jahr2 < jahr1) {
			return daysBetween(tag2, monat2, jahr2, tag1, monat1, jahr1);
		}

		// Wenn tag1 und monat1 spaeter im Jahr sind, wird bis tag2 und monat2 'gezaehlt' und ein Jahr dazuaddiert
		if (tagImJahr1 > tagImJahr2) {

			// Schaltjahre muessen beruecksichtigt werden
			tagDifferenz += isLeapYear(jahr1) ? 366: 365;
			tagDifferenz -= tagImJahr1 - tagImJahr2;
			jahr1 += 1;
		}else{
			tagDifferenz += tagImJahr2 - tagImJahr1;
		}
		
		// Die fehlenden Jahre werden noch als Tage addiert
		for (int i = jahr1; i < jahr2; i++) {
			tagDifferenz += isLeapYear(i) ? 366: 365;
		}

		return tagDifferenz;

	}



	/**
	*	Gibt den korrespondierenden Wochentag eines abgefragten Datums
	*
	*	@param tag 		der Tag des abgefragten Datums als Integer
	*	@param monat 	der Monat des abgefragten Datums als Wert des Enums 'Months'
	*	@param jahr 	das zugehoerige Jahr als Integer
	*
	*	@return 		den Wochentag des Datums als Wert des Enums 'Weekdays'
	*/

	public static Weekdays getWeekdayFromDate(int tag, Months monat, int jahr){

		// Vergleichsdatum, welches ein Sonntag ist
		int initTag = 1;
		int initMonat = 8;
		int initJahr = 1999;

		int tagImJahr = dayInYear(tag, Months.values()[monat.ordinal()], jahr);
		int tagImInitJahr = dayInYear(initTag, Months.values()[initMonat - 1], initJahr);

		// Differenz zwischen Datum und Vergleichsdatum, da hier erster Tag im Monat Sonntag war (so wie wir definiert hatten)
		int tageDazwischen = daysBetween(tag, monat.ordinal() + 1, jahr, initTag, initMonat, initJahr);

		// Es wird lediglich die Differenz der beiden benoetigt und somit abgezaehlt 
		// Man muss aber unterschieden, ob "rueckwaerts" oder "vorwaerts" gezaehlt wird
		if ((jahr == initJahr && tagImInitJahr > tagImJahr) || initJahr > jahr) {
			
			// wenn rueckwaerts gezaehlt wird (d.h. uebergebenes Datum ist kleiner als Vergleichsdatum) muss die Differenz von "oben" betrachtet werden
			return getNthWeekday(7 - (tageDazwischen % 7));
		}else{
			return getNthWeekday(tageDazwischen % 7);
		}

		
	}


} // Ende CalendarUtil




	

