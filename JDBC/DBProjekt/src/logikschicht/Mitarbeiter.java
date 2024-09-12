package logikschicht;

/**
 * Die Klasse Mitarbeiter repräsentiert einen Mitarbeiter im System.
 */

public class Mitarbeiter {
	private String mitarbeiterID;
    private String email;
    private String vorname;
    private String nachname;
    private String rolle;
    private String wohnheimnummer;
  
    /**
     * Standardkonstruktor für die Klasse Mitarbeiter.
     */
    
 public Mitarbeiter() {
     
 }

 /**
  * Konstruktor für die Klasse Mitarbeiter mit Parametern.
  *
  * @param mitarbeiterID Die ID des Mitarbeiters.
  * @param email         Die E-Mail-Adresse des Mitarbeiters.
  * @param vorname       Der Vorname des Mitarbeiters.
  * @param nachname      Der Nachname des Mitarbeiters.
  * @param rolle         Die Rolle des Mitarbeiters.
  * @param wohnheimnummer Die Wohnheimnummer, zu der der Mitarbeiter gehört.
  */
 
 public Mitarbeiter(String mitarbeiterID, String email, String vorname, String nachname, String rolle,
		String wohnheimnummer) {
	super();
	this.mitarbeiterID = mitarbeiterID;
	this.email = email;
	this.vorname = vorname;
	this.nachname = nachname;
	this.rolle = rolle;
	this.wohnheimnummer = wohnheimnummer;
}
 
 /**
  * Gibt die ID des Mitarbeiters zurück.
  *
  * @return Die ID des Mitarbeiters.
  */
 
public String getMitarbeiterID() {
	return mitarbeiterID;
}

/**
 * Setzt die ID des Mitarbeiters.
 *
 * @param mitarbeiterID Die zu setzende ID des Mitarbeiters.
 */

public void setMitarbeiterID(String mitarbeiterID) {
	this.mitarbeiterID = mitarbeiterID;
}

/**
 * Gibt die E-Mail-Adresse des Mitarbeiters zurück.
 *
 * @return Die E-Mail-Adresse des Mitarbeiters.
 */

public String getEmail() {
	return email;
}

/**
 * Setzt die E-Mail-Adresse des Mitarbeiters.
 *
 * @param email Die zu setzende E-Mail-Adresse des Mitarbeiters.
 */

public void setEmail(String email) {
	this.email = email;
}

/**
 * Gibt den Vornamen des Mitarbeiters zurück.
 *
 * @return Der Vorname des Mitarbeiters.
 */

public String getVorname() {
	return vorname;
}

/**
 * Setzt den Vornamen des Mitarbeiters.
 *
 * @param vorname Der zu setzende Vorname des Mitarbeiters.
 */


public void setVorname(String vorname) {
	this.vorname = vorname;
}

/**
 * Gibt den Nachnamen des Mitarbeiters zurück.
 *
 * @return Der Nachname des Mitarbeiters.
 */

public String getNachname() {
	return nachname;
}

/**
 * Setzt den Nachnamen des Mitarbeiters.
 *
 * @param nachname Der zu setzende Nachname des Mitarbeiters.
 */

public void setNachname(String nachname) {
	this.nachname = nachname;
}

/**
 * Gibt die Rolle des Mitarbeiters zurück.
 *
 * @return Die Rolle des Mitarbeiters.
 */

public String getRolle() {
	return rolle;
}

/**
 * Setzt die Rolle des Mitarbeiters.
 *
 * @param rolle Die zu setzende Rolle des Mitarbeiters.
 */

public void setRolle(String rolle) {
	this.rolle = rolle;
}

/**
 * Gibt die Wohnheimnummer des Mitarbeiters zurück.
 *
 * @return Die Wohnheimnummer des Mitarbeiters.
 */


public String getWohnheimnummer() {
	return wohnheimnummer;
}

/**
 * Setzt die Wohnheimnummer des Mitarbeiters.
 *
 * @param wohnheimnummer Die zu setzende Wohnheimnummer des Mitarbeiters.
 */

public void setWohnheimnummer(String wohnheimnummer) {
	this.wohnheimnummer = wohnheimnummer;
}

/**
 * Gibt eine Zeichenkettendarstellung des Mitarbeiters zurück.
 *
 * @return Eine Zeichenkettendarstellung des Mitarbeiters.
 */

@Override
 public String toString() {
     return "Mitarbeiter_ID: " + mitarbeiterID +
    		"\nEmail: " + email +
            "\nVorname: " + vorname +
            "\nNachname: " + nachname +
            "\nRolle: " + rolle +
            "\nWohnheimnummer: " + wohnheimnummer +
            "\n";
 }
}