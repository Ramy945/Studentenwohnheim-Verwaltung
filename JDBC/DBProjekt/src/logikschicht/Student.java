package logikschicht;

/**
 * Die Klasse Student repräsentiert einen Studenten im System.
 */

public class Student {
    private String studentID;
    private String anrede;
    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private String email;
    private String wohnungsnummer;
    private String wohnheimnummer;
    
    /**
     * Standardkonstruktor für die Klasse Student.
     */

    public Student() {

    }
    
    /**
     * Konstruktor für die Klasse Student mit Parametern.
     *
     * @param studentID      Die ID des Studenten.
     * @param anrede         Die Anrede des Studenten.
     * @param vorname        Der Vorname des Studenten.
     * @param nachname       Der Nachname des Studenten.
     * @param geburtsdatum   Das Geburtsdatum des Studenten.
     * @param email          Die E-Mail-Adresse des Studenten.
     * @param wohnungsnummer Die Nummer der Wohnung des Studenten.
     * @param wohnheimnummer Die Nummer des Wohnheims, zu dem der Student gehört.
     */

    public Student(String studentID, String anrede, String vorname, String nachname, String geburtsdatum, String email, String wohnungsnummer, String wohnheimnummer) {
        this.studentID = studentID;
        this.anrede = anrede;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.email = email;
        this.wohnungsnummer = wohnungsnummer;
        this.wohnheimnummer = wohnheimnummer;
    }

    /**
     * Gibt die ID des Studenten zurück.
     *
     * @return Die ID des Studenten.
     */
    
    public String getStudentID() {
        return studentID;
    }

    /**
     * Setzt die ID des Studenten.
     *
     * @param studentID Die zu setzende ID des Studenten.
     */
    
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    /**
     * Gibt die Anrede des Studenten zurück.
     *
     * @return Die Anrede des Studenten.
     */

    public String getAnrede() {
        return anrede;
    }

    /**
     * Setzt die Anrede des Studenten.
     *
     * @param anrede Die zu setzende Anrede des Studenten.
     */
    
    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }
    
    /**
     * Gibt den Vornamen des Studenten zurück.
     *
     * @return Der Vorname des Studenten.
     */

    public String getVorname() {
        return vorname;
    }
    
    /**
     * Setzt den Vornamen des Studenten.
     *
     * @param vorname Der zu setzende Vorname des Studenten.
     */

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    /**
     * Gibt den Nachnamen des Studenten zurück.
     *
     * @return Der Nachname des Studenten.
     */

    public String getNachname() {
        return nachname;
    }
    
    /**
     * Setzt den Nachnamen des Studenten.
     *
     * @param nachname Der zu setzende Nachname des Studenten.
     */


    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt das Geburtsdatum des Studenten zurück.
     *
     * @return Das Geburtsdatum des Studenten.
     */
    
    public String getGeburtsdatum() {
        return geburtsdatum;
    }
    
    /**
     * Setzt das Geburtsdatum des Studenten.
     *
     * @param geburtsdatum Das zu setzende Geburtsdatum des Studenten.
     */

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
    
    /**
     * Gibt die E-Mail-Adresse des Studenten zurück.
     *
     * @return Die E-Mail-Adresse des Studenten.
     */

    public String getEmail() {
        return email;
    }
    
    /**
     * Setzt die E-Mail-Adresse des Studenten.
     *
     * @param email Die zu setzende E-Mail-Adresse des Studenten.
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gibt die Wohnungsnummer des Studenten zurück.
     *
     * @return Die Wohnungsnummer des Studenten.
     */
    
    public String getWohnungsnummer() {
        return wohnungsnummer;
    }
    
    /**
     * Setzt die Wohnungsnummer des Studenten.
     *
     * @param wohnungsnummer Die zu setzende Wohnungsnummer des Studenten.
     */

    public void setWohnungsnummer(String wohnungsnummer) {
        this.wohnungsnummer = wohnungsnummer;
    }
    
    /**
     * Gibt die Wohnheimnummer des Studenten zurück.
     *
     * @return Die Wohnheimnummer des Studenten.
     */

    public String getWohnheimnummer() {
        return wohnheimnummer;
    }
    
    /**
     * Setzt die Wohnheimnummer des Studenten.
     *
     * @param wohnheimnummer Die zu setzende Wohnheimnummer des Studenten.
     */

    public void setWohnheimnummer(String wohnheimnummer) {
        this.wohnheimnummer = wohnheimnummer;
    }
    

    /**
     * Gibt eine Zeichenkettendarstellung des Studenten zurück.
     *
     * @return Eine Zeichenkettendarstellung des Studenten.
     */

    @Override
    public String toString() {
        return "Student ID: " + studentID +
                "\nAnrede: " + anrede +
                "\nVorname: " + vorname +
                "\nNachname: " + nachname +
                "\nGeburtsdatum: " + geburtsdatum +
                "\nEmail: " + email +
                "\nWohnungsnummer: " + wohnungsnummer +
                "\nWohnheimnummer: " + wohnheimnummer + "\n";
    }
}