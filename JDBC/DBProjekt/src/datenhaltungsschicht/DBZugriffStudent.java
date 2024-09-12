package datenhaltungsschicht;

import logikschicht.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse DBZugriffStudent erweitert die DBZugriff-Klasse und bietet Methoden für den Zugriff auf Datenbankoperationen
 * bezüglich der Studenten.
 */

public class DBZugriffStudent extends DBZugriff {
    private static ResultSet datenmenge;
    
    /**
     * Fügt einen neuen Studenten in die Datenbank ein.
     *
     * @param student Der Student, der hinzugefügt werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Hinzufügen des Studenten auftritt.
     */

    public static boolean insert(Student student) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Student (Student_ID, Anrede, Vorname, Nachname, Geburtsdatum, Email,Wohnungsnummer,Wohnheimnummer) VALUES ("
                + student.getStudentID() + ", '" + student.getAnrede() + "', '" + student.getVorname() + "', '" + student.getNachname() + "', TO_DATE('"
                + student.getGeburtsdatum() + "', 'dd.MM.yyyy'), '"
                + student.getEmail() + "', '" + student.getWohnungsnummer() + "', '" + student.getWohnheimnummer() +"')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Hinzufügen des Student " + student.getStudentID()+ " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
            close();
        }
        return true;
    }
    
    /**
     * Aktualisiert einen vorhandenen Studenten in der Datenbank.
     *
     * @param student Der Student, der aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Aktualisieren des Studenten auftritt.
     */

    public static boolean update(Student student) throws Exception {
        connect();
        String updateCommand = "UPDATE Student SET Anrede = '" + student.getAnrede() + "',Vorname= '" + student.getVorname() + "',Nachname= '" + student.getNachname() + "',Geburtsdatum= TO_DATE('"
                + student.getGeburtsdatum() + "', 'dd.MM.yyyy'),email= '"
                + student.getEmail() + "',Wohnungsnummer=  '" + student.getWohnungsnummer() + "',Wohnheimnummer= " + student.getWohnheimnummer() +" WHERE Student_ID = " + student.getStudentID();

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren des Student " + student.getStudentID() + " aufgetreten.";

            throw new Exception(errorMessage);
        }finally {
            close();
        }
        return true;
    }
    
    /**
     * Löscht einen Studenten aus der Datenbank.
     *
     * @param student Der zu löschende Student.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Löschen des Studenten auftritt.
     */

    public static boolean delete(Student student) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Student WHERE Student_ID = " + student.getStudentID();

        try {

            befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Löschen des Student " + student.getStudentID()+ " aufgetreten.";

            throw new Exception(errorMessage);
        }finally {
            close();
        }
        return true;
    }
    
    /**
     * Ruft alle Studenten aus der Datenbank ab.
     *
     * @return Eine Liste aller Studenten.
     * @throws Exception Wenn ein Fehler beim Lesen der Studentendaten auftritt.
     */

    public static List<Student> getAllStudent() throws Exception {

        ArrayList<Student> students = new ArrayList<>();
        connect();
        try {

            datenmenge = befehl.executeQuery("SELECT * FROM Student");
            while (getNext()) {
                String Student_ID = datenmenge.getString("Student_ID");
                String anrede = datenmenge.getString("Anrede");
                String vorname = datenmenge.getString("Vorname");
                String nachname = datenmenge.getString("Nachname");
                String geburtsdatum = datenmenge.getString("Geburtsdatum");
                String email = datenmenge.getString("email");
                String wohnungsnummer = datenmenge.getString("Wohnungsnummer");
                String wohnheimnummer = datenmenge.getString("Wohnheimnummer");

                Student student = new Student(Student_ID, anrede, vorname, nachname, geburtsdatum, email, wohnungsnummer, wohnheimnummer);
                students.add(student);
            }

        }catch(Exception e) {

            throw new Exception("Es ist ein Fehler beim Lesen der Studentdaten aufgetreten. ");
        }finally{
            close();
        }
        return students;
    }
    
    /**
     * Ruft einen Studenten anhand seiner Studenten-ID aus der Datenbank ab.
     *
     * @param studentID Die ID des abzurufenden Studenten.
     * @return Der abgerufene Student oder null, wenn kein Student mit der ID gefunden wurde.
     * @throws Exception Wenn ein Fehler beim Lesen der Studentendaten auftritt.
     */

    public static Student getStudentByStudentID(String studentID) throws Exception {
        connect();
        Student student = null;
        String query = "SELECT * FROM Student WHERE Student_ID = " + studentID;

        try {

            datenmenge = befehl.executeQuery(query);

            if (datenmenge.next()) {
                String anrede = getAnrede();
                String vorname = getVorname();
                String nachname = getNachname();
                String geburtsdatum = getGeburtsdatum();
                String email = getEmail();
                String wohnungsnummer = getWohnungsnummer();
                String wohnheimnummer = Integer.toString(getWohnheimnummer());

                student = new Student(studentID, anrede, vorname, nachname, geburtsdatum, email, wohnungsnummer, wohnheimnummer);
            }
        }catch(Exception e) {

            throw new Exception("Es ist ein Fehler beim Lesen der Studentdaten aufgetreten. ");
        }finally {
            close();
        }
        return student;
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
     * Gibt die Studenten-ID des aktuellen Datensatzes in der Ergebnismenge zurück.
     *
     * @return Die Studenten-ID des aktuellen Datensatzes.
     * @throws SQLException Wenn ein Fehler beim Lesen der Studenten-ID auftritt.
     */

    public static int getstudentID() throws SQLException  {
        return datenmenge.getInt("student_ID");
    }
    
    /**
     * Gibt die Anrede des aktuellen Studenten in der Ergebnismenge zurück.
     *
     * @return Die Anrede des aktuellen Studenten.
     * @throws Exception Wenn ein Fehler beim Lesen der Anrede auftritt.
     */

    public static String getAnrede() throws Exception {
        return datenmenge.getString("Anrede");
    }
    
    /**
     * Gibt den Vornamen des aktuellen Studenten in der Ergebnismenge zurück.
     *
     * @return Der Vorname des aktuellen Studenten.
     * @throws Exception Wenn ein Fehler beim Lesen des Vornamens auftritt.
     */

    public static String getVorname() throws Exception {
        return datenmenge.getString("Vorname");
    }
    
    /**
     * Gibt den Nachnamen des aktuellen Studenten in der Ergebnismenge zurück.
     *
     * @return Der Nachname des aktuellen Studenten.
     * @throws Exception Wenn ein Fehler beim Lesen des Nachnamens auftritt.
     */

    public static String getNachname() throws Exception {
        return datenmenge.getString("Nachname");
    }
    

    /**
     * Gibt das Geburtsdatum des aktuellen Studenten in der Ergebnismenge zurück.
     *
     * @return Das Geburtsdatum des aktuellen Studenten im Format "dd.MM.yyyy".
     * @throws Exception Wenn ein Fehler beim Lesen des Geburtsdatums auftritt.
     */

    public static String getGeburtsdatum() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(datenmenge.getDate("Geburtsdatum"));
    }
    
    /**
     * Gibt die E-Mail-Adresse des aktuellen Studenten in der Ergebnismenge zurück.
     *
     * @return Die E-Mail-Adresse des aktuellen Studenten.
     * @throws Exception Wenn ein Fehler beim Lesen der E-Mail-Adresse auftritt.
     */

    public static String getEmail() throws Exception {
        return datenmenge.getString("Email");
    }
    
    /**
     * Gibt die Wohnungsnummer des aktuellen Studenten in der Ergebnismenge zurück.
     *
     * @return Die Wohnungsnummer des aktuellen Studenten.
     * @throws Exception Wenn ein Fehler beim Lesen der Wohnungsnummer auftritt.
     */

    public static String getWohnungsnummer() throws Exception {
        return datenmenge.getString("Wohnungsnummer");
    }
    
    /**
     * Gibt die Wohnheimnummer des aktuellen Studenten in der Ergebnismenge zurück.
     *
     * @return Die Wohnheimnummer des aktuellen Studenten.
     * @throws Exception Wenn ein Fehler beim Lesen der Wohnheimnummer auftritt.
     */
    
    public static int getWohnheimnummer() throws Exception {
        return datenmenge.getInt("Wohnheimnummer");
    }

}
