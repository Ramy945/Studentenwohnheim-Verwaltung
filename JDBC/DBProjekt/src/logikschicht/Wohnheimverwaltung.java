package logikschicht;
import java.util.ArrayList;
import java.util.List;
import datenhaltungsschicht.DBZugriffWohnheim;

/**
 * Die Klasse Wohnheimverwaltung stellt Methoden zur Verwaltung von Wohnheimen bereit.
 */

public class Wohnheimverwaltung {
    private static List<Wohnheim> wohnheims = new ArrayList<>();

    /**
     * Speichert ein neues Wohnheim in der Datenbank.
     *
     * @param wohnheim Das Wohnheim, das gespeichert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Speichern auftritt.
     */
    
    public static boolean storeWohnheim(Wohnheim wohnheim) throws Exception {
        boolean stored = DBZugriffWohnheim.insert(wohnheim);
        if(stored)
        {
            wohnheims.add(wohnheim);
        }
        return stored;
    }
    
    /**
     * Aktualisiert ein vorhandenes Wohnheim in der Datenbank.
     *
     * @param wohnheim Das Wohnheim, das aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Aktualisieren auftritt.
     */

    public static boolean updateWohnheim(Wohnheim wohnheim) throws Exception {
        boolean updated = DBZugriffWohnheim.update(wohnheim);
        if(updated)
        {
            for (Wohnheim existingWohnheim : wohnheims) {
                if (existingWohnheim.getWohnheimnummer()==wohnheim.getWohnheimnummer() ) {
                    existingWohnheim.setWohnheimname(wohnheim.getWohnheimname());
                    break;
                }
            }
        }
        return updated;
    }
    
    /**
     * Löscht ein Wohnheim aus der Datenbank.
     *
     * @param wohnheim Das Wohnheim, das gelöscht werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Löschen auftritt.
     */

    public static boolean deleteWohnheim(Wohnheim wohnheim) throws Exception {
       

    	if(DBZugriffWohnheim.delete(wohnheim))
        {	
            wohnheims.remove(wohnheim);
            return true;
        }
        else {
            return false;
        }   
    }
    
    /**
     * Liefert ein Wohnheim anhand seiner Wohnheimnummer aus der Datenbank.
     *
     * @param wohnheimnummer Die Wohnheimnummer des gesuchten Wohnheims.
     * @return Das Wohnheim mit der angegebenen Wohnheimnummer oder null, wenn nicht gefunden.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
    
    public static Wohnheim getWohnheimByWohnheimnummer(String wohnheimnummer) throws Exception
    {
        return DBZugriffWohnheim.getWohnheimByWohnheimnummer(wohnheimnummer);
    }

    /**
     * Liefert alle Wohnheime aus der Datenbank.
     *
     * @return Eine Liste von Wohnheim-Objekten.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
    
    public static List<Wohnheim> getAllWohnheim() throws Exception {
        wohnheims = DBZugriffWohnheim.getAllWohnheim();
        return wohnheims;
    }
}
