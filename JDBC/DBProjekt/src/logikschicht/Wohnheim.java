package logikschicht;

/**
 * Die Klasse Wohnheim repräsentiert ein Wohnheim im System.
 */

public class Wohnheim {
    private String wohnheimnummer;
    private String wohnheimname;

    /**
     * Konstruktor für die Klasse Wohnheim mit Parametern.
     *
     * @param wohnheimnummer Die Nummer des Wohnheims.
     * @param wohnheimname   Der Name des Wohnheims.
     */
    
    public Wohnheim(String wohnheimnummer, String wohnheimname) {
        this.wohnheimnummer = wohnheimnummer;
        this.wohnheimname = wohnheimname;
    }
    
    /**
     * Standardkonstruktor für die Klasse Wohnheim.
     */
    
    public Wohnheim() {
    }

    /**
     * Gibt die Nummer des Wohnheims zurück.
     *
     * @return Die Nummer des Wohnheims.
     */
    
    public String getWohnheimnummer() {
        return wohnheimnummer;
    }
    
    /**
     * Setzt die Nummer des Wohnheims.
     *
     * @param wohnheimnummer Die zu setzende Nummer des Wohnheims.
     */

    public void setWohnheimnummer(String wohnheimnummer) {
        this.wohnheimnummer = wohnheimnummer;
    }

    /**
     * Gibt den Namen des Wohnheims zurück.
     *
     * @return Der Name des Wohnheims.
     */
    
    public String getWohnheimname() {
        return wohnheimname;
    }
    
    /**
     * Setzt den Namen des Wohnheims.
     *
     * @param wohnheimname Der zu setzende Name des Wohnheims.
     */

    public void setWohnheimname(String wohnheimname) {
        this.wohnheimname = wohnheimname;
    }

    /**
     * Gibt eine Zeichenkettendarstellung des Wohnheims zurück.
     *
     * @return Eine Zeichenkettendarstellung des Wohnheims.
     */
    
    @Override
    public String toString() {
        return "Wohnheimnummer: " + wohnheimnummer +
                "\nWohnheimname: " + wohnheimname +
                 "\n";
    }
}
