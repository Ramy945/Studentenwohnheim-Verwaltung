package logikschicht;

/**
 * Die Klasse Kategorie repräsentiert eine Kategorie für Wohnungen.
 */

public class Kategorie {
	
	private String kategorieKrz;
	private String bezeichnung;
	private String oberkategorie;
	
	/**
     * Standardkonstruktor für die Klasse Kategorie.
     */
	
	public Kategorie() {
		
	}
	
	/**
     * Konstruktor für die Klasse Kategorie mit Parametern.
     *
     * @param kategorieKrz Das Kürzel der Kategorie.
     * @param bezeichnung   Die Bezeichnung der Kategorie.
     * @param oberkategorie Die Oberkategorie, zu der diese Kategorie gehört.
     */
	
	public Kategorie(String kategorieKrz, String bezeichnung, String oberkategorie) {
		
		this.kategorieKrz = kategorieKrz;
		this.bezeichnung = bezeichnung;
		this.oberkategorie = oberkategorie;
	}
	
	/**
     * Gibt das Kürzel der Kategorie zurück.
     *
     * @return Das Kürzel der Kategorie.
     */

	public String getKategorieKrz() {
		return kategorieKrz;
	}
	
	 /**
     * Setzt das Kürzel der Kategorie.
     *
     * @param kategorieKrz Das zu setzende Kürzel der Kategorie.
     */

	public void setKategorieKrz(String kategorieKrz) {
		this.kategorieKrz = kategorieKrz;
	}
	
	/**
     * Gibt die Bezeichnung der Kategorie zurück.
     *
     * @return Die Bezeichnung der Kategorie.
     */

	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
     * Setzt die Bezeichnung der Kategorie.
     *
     * @param bezeichnung Die zu setzende Bezeichnung der Kategorie.
     */
	
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
     * Gibt die Oberkategorie der Kategorie zurück.
     *
     * @return Die Oberkategorie der Kategorie.
     */

	public String getOberkategorie() {
		return oberkategorie;
	}
	
	/**
     * Setzt die Oberkategorie der Kategorie.
     *
     * @param oberkategorie Die zu setzende Oberkategorie der Kategorie.
     */

	public void setOberkategorie(String oberkategorie) {
		this.oberkategorie = oberkategorie;
	}
	
	/**
     * Gibt eine Zeichenkettendarstellung der Kategorie zurück.
     *
     * @return Eine Zeichenkettendarstellung der Kategorie.
     */

	@Override
	public String toString() {
		return "KategorieKrz: " + kategorieKrz +
            "\nBezeichnung: " + bezeichnung +
            "\nOberkategorie: " + oberkategorie +
            "\n";
 }

}
