package logikschicht;

import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffKategorie;

/**
 * Die Klasse Kategorieverwaltung stellt Methoden zur Verwaltung von Kategorien bereit.
 */

public class Kategorieverwaltung {
	
	private static List<Kategorie> kategorien = new ArrayList<>();

	/**
     * Liefert alle Kategorien aus der Datenbank.
     *
     * @return Eine Liste von Kategorie-Objekten.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
	
	public static List<Kategorie> getAllKategorie() throws Exception {
		 kategorien = DBZugriffKategorie.getAllKategorien();
	        return kategorien;
	}

	 /**
     * Speichert eine neue Kategorie in der Datenbank.
     *
     * @param newKat Die Kategorie, die gespeichert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Speichern auftritt.
     */
	
	public static boolean storeKategorie(Kategorie newKat) throws Exception {
		boolean stored = DBZugriffKategorie.insert(newKat);
    	if(stored)
    	{
    		kategorien.add(newKat);
    	}
    	return stored;
	}
	
	/**
     * Liefert eine Kategorie anhand ihres Kürzels aus der Datenbank.
     *
     * @param katkrz Das Kürzel der gesuchten Kategorie.
     * @return Die Kategorie mit dem angegebenen Kürzel oder null, wenn nicht gefunden.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */

	public static Kategorie getkategorieByKategoriekrz(String katkrz) throws Exception{
		return DBZugriffKategorie.getKategorieByKategorieKrz(katkrz);
	}
	
    /**
     * Aktualisiert eine vorhandene Kategorie in der Datenbank.
     *
     * @param kategorieToUpdate Die zu aktualisierende Kategorie.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Aktualisieren auftritt.
     */

	public static boolean updateKategorie(Kategorie kategorieToUpdate) throws Exception {
		boolean updated = DBZugriffKategorie.update(kategorieToUpdate);
    	if(updated)
    	{
        for (Kategorie existingKategorie : kategorien) {
            if (existingKategorie.getKategorieKrz() == kategorieToUpdate.getKategorieKrz()) {
                existingKategorie.setBezeichnung(kategorieToUpdate.getBezeichnung());
                existingKategorie.setOberkategorie(kategorieToUpdate.getOberkategorie());
                break;
            }
        }
        }
    	return updated;
	}

	  /**
     * Löscht eine Kategorie aus der Datenbank.
     *
     * @param kategorieToDelete Die Kategorie, die gelöscht werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Löschen auftritt.
     */
	
	public static boolean deleteKategorie(Kategorie kategorieToDelete) throws Exception {
		if(DBZugriffKategorie.delete(kategorieToDelete))
    	{
    	kategorien.remove(kategorieToDelete);
    	return true;
    	}
    	else {
		return false;
	}	
	}

}
