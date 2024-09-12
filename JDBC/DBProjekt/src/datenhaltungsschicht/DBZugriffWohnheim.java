package datenhaltungsschicht;

import logikschicht.Wohnheim;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Die Klasse DBZugriffWohnheim erweitert die DBZugriff-Klasse und bietet Methoden für den Zugriff auf Datenbankoperationen
 * bezüglich der Wohnheime.
 */

public class DBZugriffWohnheim extends DBZugriff {
    private static ResultSet datenmenge;
    
    /**
     * Fügt ein neues Wohnheim in die Datenbank ein.
     *
     * @param wohnheim Das Wohnheim, das hinzugefügt werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Hinzufügen des Wohnheims auftritt.
     */

    public static boolean insert(Wohnheim wohnheim) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Wohnheim (Wohnheimnummer, Wohnheimname) VALUES ("
                + wohnheim.getWohnheimnummer()+ ", '" + wohnheim.getWohnheimname()+ "')";
        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Hinzufügen des Wohnheim " + wohnheim.getWohnheimnummer() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
            close();
        }
        return true;
    }
    
    /**
     * Aktualisiert ein vorhandenes Wohnheim in der Datenbank.
     *
     * @param wohnheim Das Wohnheim, das aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Aktualisieren des Wohnheims auftritt.
     */

    public static boolean update(Wohnheim wohnheim) throws Exception {
        connect();
        String updateCommand = "UPDATE Wohnheim SET WohnheimName = '" + wohnheim.getWohnheimname() + "' WHERE Wohnheimnummer = " + wohnheim.getWohnheimnummer();

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren des Wohnheim " + wohnheim.getWohnheimnummer() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
            close();
        }
        return true;
    }

    /**
     * Löscht ein Wohnheim aus der Datenbank.
     *
     * @param wohnheim Das zu löschende Wohnheim.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Löschen des Wohnheims auftritt.
     */
    
    public static boolean delete(Wohnheim wohnheim) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Wohnheim WHERE Wohnheimnummer = " + wohnheim.getWohnheimnummer();
        try {
            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Löschen des Wohnheim" + wohnheim.getWohnheimnummer() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
            close();
        }
        return true;
    }
    
    /**
     * Ruft alle Wohnheime aus der Datenbank ab.
     *
     * @return Eine Liste aller Wohnheime.
     * @throws Exception Wenn ein Fehler beim Lesen der Wohnheimdaten auftritt.
     */
    
    public static List<Wohnheim> getAllWohnheim() throws Exception {

        ArrayList<Wohnheim> wohnheims = new ArrayList<>();
        connect();
        try {
            datenmenge = befehl.executeQuery("SELECT * FROM WOHNHEIM");
            while (getNext()) {
                String wohnheimnummer = datenmenge.getString("Wohnheimnummer");
                String wohnheimname = datenmenge.getString("Wohnheimname");
                Wohnheim wohnheim = new Wohnheim(wohnheimnummer, wohnheimname);
                wohnheims.add(wohnheim);
            }
        }catch(Exception e) {
            throw new Exception("Es ist ein Fehler beim Lesen der Wohnheimdaten aufgetreten. ");
        }finally{
            close();
        }
        return wohnheims;
    }
    
    /**
     * Ruft ein Wohnheim anhand seiner Wohnheimnummer aus der Datenbank ab.
     *
     * @param wohnheimnummer Die Nummer des abzurufenden Wohnheims.
     * @return Das Wohnheim mit der angegebenen Wohnheimnummer.
     * @throws Exception Wenn ein Fehler beim Lesen der Wohnheimdaten auftritt.
     */

    public static Wohnheim getWohnheimByWohnheimnummer(String wohnheimnummer) throws Exception {
        connect();
        Wohnheim wohnheim = null;
        String query = "SELECT * FROM Wohnheim WHERE Wohnheimnummer = " + wohnheimnummer;

        try {
            datenmenge = befehl.executeQuery(query);
            if (datenmenge.next()) {
                String wohnheimname = getWohnheimname();
                wohnheim = new Wohnheim(wohnheimnummer, wohnheimname);
            }
        }catch(Exception e) {
            throw new Exception("Es ist ein Fehler beim Lesen der Wohnheimdaten aufgetreten. ");
        }finally {
            close();
        }
        return wohnheim;
    }
    
    /**
     * Überprüft, ob es einen weiteren Datensatz in der Ergebnismenge gibt.
     *
     * @return true, wenn es einen weiteren Datensatz gibt, sonst false.
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
     * Gibt die Wohnheimnummer des aktuellen Datensatzes in der Ergebnismenge zurück.
     *
     * @return Die Wohnheimnummer des aktuellen Datensatzes.
     * @throws SQLException Wenn ein Fehler beim Lesen der Wohnheimnummer auftritt.
     */
    
    public static int getWohnheimnummer() throws SQLException  {
        return datenmenge.getInt("Wohnheimnummer");
    }
    
    /**
     * Gibt den Wohnheimnamen des aktuellen Datensatzes in der Ergebnismenge zurück.
     *
     * @return Der Wohnheimname des aktuellen Datensatzes.
     * @throws Exception Wenn ein Fehler beim Lesen des Wohnheimnamens auftritt.
     */
    
    public static String getWohnheimname() throws Exception {
        return datenmenge.getString("Wohnheimname");
    }
}
