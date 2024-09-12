package logikschicht;

import java.util.ArrayList;
import java.util.List;

import datenhaltungsschicht.DBZugriffWohnung;


/**
 * Die Klasse Wohnungverwaltung stellt Methoden zur Verwaltung von Wohnungen bereit.
 */

public class Wohnungverwaltung {
    private static List<Wohnung> wohnungen = new ArrayList<>();

    /**
     * Speichert eine neue Wohnung in der Datenbank.
     *
     * @param wohnung Die Wohnung, die gespeichert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Speichern auftritt.
     */
    
    public static boolean storeWohnung(Wohnung wohnung) throws Exception {
    	boolean stored = DBZugriffWohnung.insert(wohnung);
    	if(stored)
    	{
    		wohnungen.add(wohnung);
    	}
    	return stored;
    }

    /**
     * Aktualisiert eine vorhandene Wohnung in der Datenbank.
     *
     * @param wohnung Die Wohnung, die aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Aktualisieren auftritt.
     */
    
    public static boolean updateWohnung(Wohnung wohnung) throws Exception {
    	boolean updated = DBZugriffWohnung.update(wohnung);
    	if(updated)
    	{
        for (Wohnung existingWohnung : wohnungen) {
            if (existingWohnung.getWohnungsNummer() == wohnung.getWohnungsNummer()) {
            	existingWohnung.setAnzahlZimmer(wohnung.getAnzahlZimmer());
            	existingWohnung.setKategorieKrz(wohnung.getKategorieKrz());
            	existingWohnung.setWohnheimnummer(wohnung.getWohnheimnummer());
                break;
            }
        }
        }
    	return updated;
    }
    
    /**
     * Löscht eine Wohnung aus der Datenbank.
     *
     * @param wohnung Die Wohnung, die gelöscht werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Löschen auftritt.
     */

    public static boolean deleteWohnung(Wohnung wohnung) throws Exception {
    	if(DBZugriffWohnung.delete(wohnung))
    	{
    	wohnungen.remove(wohnung);
    	return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Liefert eine Wohnung anhand ihrer Wohnungsnummer aus der Datenbank.
     *
     * @param wohnungsnummer Die Wohnungsnummer der gesuchten Wohnung.
     * @return Die Wohnung mit der angegebenen Wohnungsnummer oder null, wenn nicht gefunden.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
    
    public static Wohnung getWohnungByWohnungsnummer(String wohnungsnummer) throws Exception
    {	
    	return DBZugriffWohnung.getWohnungByWohnungsnummer(wohnungsnummer);
    }
    
    /**
     * Liefert alle Wohnungen aus der Datenbank.
     *
     * @return Eine Liste von Wohnung-Objekten.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */

    public static List<Wohnung> getAllWohnungen() throws Exception {
        wohnungen = DBZugriffWohnung.getAllWohnungen();
        return wohnungen;
    }
}