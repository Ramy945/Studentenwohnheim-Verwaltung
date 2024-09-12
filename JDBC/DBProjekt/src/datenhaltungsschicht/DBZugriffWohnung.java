package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Wohnung;

/**
 * Die Klasse DBZugriffWohnung stellt Methoden für den Zugriff auf die Datenbank für Wohnungsdaten zur Verfügung.
 */

public class DBZugriffWohnung extends DBZugriff {

	private static ResultSet datenmenge;
	
	/**
     * Fügt eine neue Wohnung in die Datenbank ein.
     *
     * @param wohnung Die Wohnung, die hinzugefügt werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Hinzufügen auftritt.
     */

    public static boolean insert(Wohnung wohnung) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Wohnung (Wohnungsnummer, AnzahlZimmer, KategorieKrz, Wohnheimnummer) VALUES ('"
                + wohnung.getWohnungsNummer() + "', " + wohnung.getAnzahlZimmer() + ", '" + wohnung.getKategorieKrz() + "', '" + wohnung.getWohnheimnummer() + "')";

        try {
        	
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Hinzufügen der Wohnung " + wohnung.getWohnungsNummer() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally { 
        	close();
        }
        return true;
    }
    
    /**
     * Aktualisiert eine vorhandene Wohnung in der Datenbank.
     *
     * @param wohnung Die Wohnung, die aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Aktualisieren auftritt.
     */

    public static boolean update(Wohnung wohnung) throws Exception {
        connect();
        String updateCommand = "UPDATE Wohnung SET AnzahlZimmer = " + wohnung.getAnzahlZimmer() + ", KategorieKrz = '" + wohnung.getKategorieKrz() + "', Wohnheimnummer = '" + wohnung.getWohnheimnummer() +
               "' WHERE Wohnungsnummer = " + wohnung.getWohnungsNummer();

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren der Wohnung " + wohnung.getWohnungsNummer() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
        	close();
        }
        return true;
    }
    
    /**
     * Löscht eine Wohnung aus der Datenbank.
     *
     * @param wohnung Die Wohnung, die gelöscht werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Löschen auftritt.
     */

    public static boolean delete(Wohnung wohnung) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Wohnung WHERE Wohnungsnummer = " + wohnung.getWohnungsNummer();

        try {
        	befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Löschen der Wohnung " + wohnung.getWohnungsNummer() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
        	close();
        }
        return true;
    }
    
    /**
     * Liefert eine Liste aller Wohnungen aus der Datenbank.
     *
     * @return Eine Liste von Wohnung-Objekten.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */

    public static List<Wohnung> getAllWohnungen() throws Exception {
    	
        ArrayList<Wohnung> wohnungen = new ArrayList<>();
        connect();
        try {
        
    		datenmenge = befehl.executeQuery("SELECT * FROM Wohnung");
	        while (getNext()) {
	            String wohnungsnummer = datenmenge.getString("Wohnungsnummer");
	            String anzahlZimmer = datenmenge.getString("AnzahlZimmer");
	            String kategorieKrz = datenmenge.getString("KategorieKrz");
	            String wohnheimNummer = datenmenge.getString("Wohnheimnummer");
	            
	            Wohnung wohnung = new Wohnung(wohnungsnummer, anzahlZimmer, kategorieKrz, wohnheimNummer);
	            wohnungen.add(wohnung);
	        }
       
    	}catch(Exception e) {
    		
    		throw new Exception("Es ist ein Fehler beim Lesen der Wohnungsdaten aufgetreten. ");
    	}finally{
    		close();
    	}
        return wohnungen;
    }
    
    /**
     * Liefert eine Wohnung anhand ihrer Wohnungsnummer aus der Datenbank.
     *
     * @param wohnungsnummer Die Wohnungsnummer der gesuchten Wohnung.
     * @return Die Wohnung mit der angegebenen Wohnungsnummer oder null, wenn nicht gefunden.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */

    public static Wohnung getWohnungByWohnungsnummer(String wohnungsnummer) throws Exception {
        connect();
        Wohnung wohnung = null;
        String query = "SELECT * FROM Wohnung WHERE Wohnungsnummer = " + wohnungsnummer;
        
        try {
        	
	        datenmenge = befehl.executeQuery(query);
	
	        if (datenmenge.next()) {
	            String anzahlZimmer = Integer.toString(getAnzahlZimmer());
	            String kategorieKrz = getKategorieKrz();
	            String wohnheimNummer = getWohnheimnummer();
	          
	            wohnung = new Wohnung(wohnungsnummer, anzahlZimmer, kategorieKrz, wohnheimNummer);
	        }
        }catch(Exception e) {
        	
    		throw new Exception("Es ist ein Fehler beim Lesen der Wohnungsdaten aufgetreten. ");
        }finally {
        	close();
        }
        return wohnung;
    }
    
    /**
     * Bewegt den Zeiger auf den nächsten Datensatz in der Ergebnismenge.
     *
     * @return true, wenn ein weiterer Datensatz vorhanden ist, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Verschieben des Zeigers auftritt.
     */

    public static boolean getNext() throws Exception {
        if (datenmenge.next()) {
            return true;
        } else {
            close();
            return false;
        }
    }
    
    /**
     * Liefert die Wohnungsnummer des aktuellen Datensatzes in der Ergebnismenge.
     *
     * @return Die Wohnungsnummer des aktuellen Datensatzes.
     * @throws SQLException Wenn ein Fehler beim Lesen der Wohnungsnummer auftritt.
     */

    public static String getWohnungsNummer() throws SQLException  {
        return datenmenge.getString("Wohnungsnummer");
    }
    
    /**
     * Liefert die Anzahl der Zimmer des aktuellen Datensatzes in der Ergebnismenge.
     *
     * @return Die Anzahl der Zimmer des aktuellen Datensatzes.
     * @throws Exception Wenn ein Fehler beim Lesen der Anzahl der Zimmer auftritt.
     */

    public static int getAnzahlZimmer() throws Exception {
        return datenmenge.getInt("AnzahlZimmer");
    }
    
    /**
     * Liefert die Kategoriekürzel des aktuellen Datensatzes in der Ergebnismenge.
     *
     * @return Das Kategoriekürzel des aktuellen Datensatzes.
     * @throws Exception Wenn ein Fehler beim Lesen der Kategoriekürzel auftritt.
     */

    public static String getKategorieKrz() throws Exception {
        return datenmenge.getString("KategorieKrz");
    }

    /**
     * Liefert die Wohnheimnummer des aktuellen Datensatzes in der Ergebnismenge.
     *
     * @return Die Wohnheimnummer des aktuellen Datensatzes.
     * @throws Exception Wenn ein Fehler beim Lesen der Wohnheimnummer auftritt.
     */
    
    public static String getWohnheimnummer() throws Exception {
        return datenmenge.getString("Wohnheimnummer");
    }

}
