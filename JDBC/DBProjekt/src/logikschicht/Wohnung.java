package logikschicht;

/**
 * Die Klasse Wohnung repräsentiert eine Wohnung im System.
 */

public class Wohnung {
	
	private String wohnungsNummer;
	private String anzahlZimmer;
	private String kategorieKrz;
	private String wohnheimnummer;
	
 /**
  * Standardkonstruktor für die Klasse Wohnung.
  */
	
 public Wohnung() {
	 
	 
 }
 
 /**
  * Konstruktor für die Klasse Wohnung mit Parametern.
  *
  * @param wohnungsNummer Die Nummer der Wohnung.
  * @param anzahlZimmer   Die Anzahl der Zimmer in der Wohnung.
  * @param kategorieKrz  Die Kurzform der Kategorie der Wohnung.
  * @param wohnheimnummer Die Nummer des Wohnheims, zu dem die Wohnung gehört.
  */

 public Wohnung(String wohnungsNummer, String anzahlZimmer, String kategorieKrz, String wohnheimnummer) {
	super();
	this.wohnungsNummer = wohnungsNummer;
	this.anzahlZimmer = anzahlZimmer;
	this.kategorieKrz = kategorieKrz;
	this.wohnheimnummer = wohnheimnummer;
 }	

 /**
 * Gibt die Nummer der Wohnung zurück.
 *
 * @return Die Nummer der Wohnung.
 */

 public String getWohnungsNummer() {
	return wohnungsNummer;
 }	

/**
 * Setzt die Nummer der Wohnung.
 *
 * @param wohnungsNummer Die zu setzende Nummer der Wohnung.
 */

public void setWohnungsNummer(String wohnungsNummer) {
	this.wohnungsNummer = wohnungsNummer;
}

/**
 * Gibt die Anzahl der Zimmer in der Wohnung zurück.
 *
 * @return Die Anzahl der Zimmer in der Wohnung.
 */

public String getAnzahlZimmer() {
	return anzahlZimmer;
}

/**
 * Setzt die Anzahl der Zimmer in der Wohnung.
 *
 * @param anzahlZimmer Die zu setzende Anzahl der Zimmer in der Wohnung.
 */

public void setAnzahlZimmer(String anzahlZimmer) {
	this.anzahlZimmer = anzahlZimmer;
}

/**
 * Gibt die Kurzform der Kategorie der Wohnung zurück.
 *
 * @return Die Kurzform der Kategorie der Wohnung.
 */

public String getKategorieKrz() {
	return kategorieKrz;
}

/**
 * Setzt die Kurzform der Kategorie der Wohnung.
 *
 * @param kategorieKrz Die zu setzende Kurzform der Kategorie der Wohnung.
 */

public void setKategorieKrz(String kategorieKrz) {
	this.kategorieKrz = kategorieKrz;
}

/**
 * Gibt die Nummer des Wohnheims zurück, zu dem die Wohnung gehört.
 *
 * @return Die Nummer des Wohnheims.
 */

public String getWohnheimnummer() {
	return wohnheimnummer;
}

/**
 * Setzt die Nummer des Wohnheims, zu dem die Wohnung gehört.
 *
 * @param wohnheimnummer Die zu setzende Nummer des Wohnheims.
 */

public void setWohnheimnummer(String wohnheimnummer) {
	this.wohnheimnummer = wohnheimnummer;
}

/**
 * Gibt eine Zeichenkettendarstellung der Wohnung zurück.
 *
 * @return Eine Zeichenkettendarstellung der Wohnung.
 */

@Override
 public String toString() {
     return "Wohnungsnummer: " + wohnungsNummer +
            "\nAnzahl Zimmer: " + anzahlZimmer +
            "\nWohnungskategorie: " + kategorieKrz +
            "\nWohnheimnummer: " + wohnheimnummer +
            "\n";
 }
}