package logikschicht;

import java.util.ArrayList;
import java.util.List;

import datenhaltungsschicht.DBZugriffMitarbeiter;

/**
 * Die Klasse Mitarbeiterverwaltung stellt Methoden zur Verwaltung von Mitarbeitern bereit.
 */

public class Mitarbeiterverwaltung {
    private static List<Mitarbeiter> mitarbeitern = new ArrayList<>();

    /**
     * Speichert einen neuen Mitarbeiter in der Datenbank.
     *
     * @param mitarbeiter Der Mitarbeiter, der gespeichert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Speichern auftritt.
     */
    
    public static boolean storeMitarbeiter(Mitarbeiter mitarbeiter) throws Exception {
    	boolean stored = DBZugriffMitarbeiter.insert(mitarbeiter);
    	if(stored)
    	{
    		mitarbeitern.add(mitarbeiter);
    	}
    	return stored;
    }
    
    /**
     * Aktualisiert einen vorhandenen Mitarbeiter in der Datenbank.
     *
     * @param mitarbeiter Der Mitarbeiter, der aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Aktualisieren auftritt.
     */

    public static boolean updateMitarbeiter(Mitarbeiter mitarbeiter) throws Exception {
    	boolean updated = DBZugriffMitarbeiter.update(mitarbeiter);
    	if(updated)
    	{
        for (Mitarbeiter existingMitarbeiter : mitarbeitern) {
            if (existingMitarbeiter.getMitarbeiterID() == mitarbeiter.getMitarbeiterID()) {
            	existingMitarbeiter.setEmail(mitarbeiter.getEmail());
            	existingMitarbeiter.setVorname(mitarbeiter.getVorname());
            	existingMitarbeiter.setNachname(mitarbeiter.getNachname());
            	existingMitarbeiter.setRolle(mitarbeiter.getRolle());
                existingMitarbeiter.setWohnheimnummer(mitarbeiter.getWohnheimnummer());
                break;
            }
        }
        }
    	return updated;
    }
    
    /**
     * Löscht einen Mitarbeiter aus der Datenbank.
     *
     * @param mitarbeiter Der Mitarbeiter, der gelöscht werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Löschen auftritt.
     */

    public static boolean deleteMitarbeiter(Mitarbeiter mitarbeiter) throws Exception {
    	if(DBZugriffMitarbeiter.delete(mitarbeiter))
    	{
    	mitarbeitern.remove(mitarbeiter);
    	return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Liefert einen Mitarbeiter anhand seiner Mitarbeiter-ID aus der Datenbank.
     *
     * @param mitarbeiterID Die Mitarbeiter-ID des gesuchten Mitarbeiters.
     * @return Der Mitarbeiter mit der angegebenen Mitarbeiter-ID oder null, wenn nicht gefunden.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
    
    public static Mitarbeiter getMitarbeiterbyMitarbeiterID(String mitarbeiterID) throws Exception
    {	
    	return DBZugriffMitarbeiter.getMitarbeiterByMitarbeiterID(mitarbeiterID);
    }
    
    /**
     * Liefert alle Mitarbeiter aus der Datenbank.
     *
     * @return Eine Liste von Mitarbeiter-Objekten.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
    
    public static List<Mitarbeiter> getAllMitarbeiter() throws Exception {
        mitarbeitern = DBZugriffMitarbeiter.getAllMitarbeiter();
        return mitarbeitern;
    }
}