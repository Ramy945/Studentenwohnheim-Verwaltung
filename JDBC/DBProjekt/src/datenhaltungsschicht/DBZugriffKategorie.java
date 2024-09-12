package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Kategorie;

/**
 * Die Klasse DBZugriffKategorie erweitert die DBZugriff-Klasse und bietet Methoden für den Zugriff auf Datenbankoperationen
 * bezüglich der Wohnungskategorien.
 */

public class DBZugriffKategorie extends DBZugriff {

	private static ResultSet datenmenge;
	
	/**
     * Fügt eine neue Kategorie in die Datenbank ein.
     *
     * @param kat Die Kategorie, die hinzugefügt werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Hinzufügen der Kategorie auftritt.
     */

    public static boolean insert(Kategorie kat) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Wohnungskategorie (KategorieKrz, Bezeichnung, Oberkategorie) VALUES ('"
                + kat.getKategorieKrz() + "', '" + kat.getBezeichnung() + "', '" + kat.getOberkategorie() + "')";

        try {
        	
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Hinzufügen der Kategorie " + kat.getKategorieKrz() + " aufgetreten.";
            
            throw new Exception(errorMessage);
        }finally { 
        	close();
        }
        return true;
    }

    /**
     * Aktualisiert eine vorhandene Kategorie in der Datenbank.
     *
     * @param kat Die Kategorie, die aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Aktualisieren der Kategorie auftritt.
     */
    
    public static boolean update(Kategorie kat) throws Exception {
        connect();
        String updateCommand = "UPDATE Wohnungskategorie SET Bezeichnung = '" + kat.getBezeichnung() + "', Oberkategorie = '" + kat.getOberkategorie() + "' WHERE KategorieKrz = '" + kat.getKategorieKrz() + "'";

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren der WohnungsKategorie " + kat.getKategorieKrz() + " aufgetreten.";
            
            throw new Exception(errorMessage);
        }finally {
        	close();
        }
        return true;
    }

    /**
     * Löscht eine Kategorie aus der Datenbank.
     *
     * @param kat Die zu löschende Kategorie.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Löschen der Kategorie auftritt.
     */
    
    public static boolean delete(Kategorie kat) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Wohnungskategorie WHERE kategorieKrz = '" + kat.getKategorieKrz() + "'";

        try {
        	
        	befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Löschen der Wohnungskategorie " + kat.getKategorieKrz() + " aufgetreten.";
            
            throw new Exception(errorMessage);
        }finally {
        	close();
        }
        return true;
    }
    
    /**
     * Ruft alle Wohnungskategorien aus der Datenbank ab.
     *
     * @return Eine Liste aller Wohnungskategorien.
     * @throws Exception Wenn ein Fehler beim Lesen der Kategoriedaten auftritt.
     */

    public static List<Kategorie> getAllKategorien() throws Exception {
    	
        ArrayList<Kategorie> kategorien = new ArrayList<>();
        connect();
        try {
        
    		datenmenge = befehl.executeQuery("SELECT * FROM Wohnungskategorie");
	        while (getNext()) {
	            String kategorieKrz = datenmenge.getString("KategorieKrz");
	            String bezeichnung = datenmenge.getString("Bezeichnung");
	            String oberkategorie = datenmenge.getString("Oberkategorie");
	          
	            Kategorie kat = new Kategorie(kategorieKrz, bezeichnung, oberkategorie);
	            kategorien.add(kat);
	        }
       
    	}catch(Exception e) {
    	
    		throw new Exception("Es ist ein Fehler beim Lesen der Kategoriedaten aufgetreten. ");
    	}finally{
    		close();
    	}
        return kategorien;
    }

    /**
     * Ruft eine Kategorie anhand ihrer Kategoriekürzel aus der Datenbank ab.
     *
     * @param kategorieKrz Das Kürzel der abzurufenden Kategorie.
     * @return Die abgerufene Kategorie oder null, wenn keine Kategorie mit dem Kürzel gefunden wurde.
     * @throws Exception Wenn ein Fehler beim Lesen der Kategoriedaten auftritt.
     */
    
    public static Kategorie getKategorieByKategorieKrz(String kategorieKrz) throws Exception {
        connect();
        Kategorie kat = null;
        String query = "SELECT * FROM Wohnungskategorie WHERE KategorieKrz = '" + kategorieKrz + "'";
        
        try {
        
	        datenmenge = befehl.executeQuery(query);
	
	        if (datenmenge.next()) {
	            String bezeichnung = getBezeichnung();
	            String oberkategorie = getOberkategorie();
	            
	            kat = new Kategorie(kategorieKrz, bezeichnung, oberkategorie);
	        }
        }catch(Exception e) {
        
    		throw new Exception("Es ist ein Fehler beim Lesen der Kategoriedaten aufgetreten. ");
        }finally {
        	close();
        }
        return kat;
    }

    /**
     * Prüft, ob weitere Datensätze in der Ergebnismenge vorhanden sind.
     *
     * @return true, wenn weitere Datensätze vorhanden sind, sonst false.
     * @throws Exception Wenn ein Fehler beim Lesen der Ergebnismenge auftritt.
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
     * Gibt das Kürzel der aktuellen Kategorie in der Ergebnismenge zurück.
     *
     * @return Das Kürzel der aktuellen Kategorie.
     * @throws SQLException Wenn ein Fehler beim Lesen des Kategoriekürzels auftritt.
     */

    public static String getKategorieKrz() throws SQLException  {
        return datenmenge.getString("KategorieKrz");
    }
    
    /**
     * Gibt die Bezeichnung der aktuellen Kategorie in der Ergebnismenge zurück.
     *
     * @return Die Bezeichnung der aktuellen Kategorie.
     * @throws Exception Wenn ein Fehler beim Lesen der Bezeichnung auftritt.
     */

    public static String getBezeichnung() throws Exception {
        return datenmenge.getString("Bezeichnung");
    }
    
    /**
     * Gibt die Oberkategorie der aktuellen Kategorie in der Ergebnismenge zurück.
     *
     * @return Die Oberkategorie der aktuellen Kategorie.
     * @throws Exception Wenn ein Fehler beim Lesen der Oberkategorie auftritt.
     */

    public static String getOberkategorie() throws Exception {
        return datenmenge.getString("Oberkategorie");
    }

   
}
