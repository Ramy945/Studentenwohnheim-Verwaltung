package logikschicht;

import datenhaltungsschicht.DBZugriffStudent;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Studentverwaltung stellt Methoden zur Verwaltung von Studenten bereit.
 */

public class Studentverwaltung {
    private static List<Student> students = new ArrayList<>();

    /**
     * Speichert einen neuen Studenten in der Datenbank.
     *
     * @param student Der Student, der gespeichert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Speichern auftritt.
     */
    
    public static boolean storeStudent(Student student) throws Exception {
        boolean stored = DBZugriffStudent.insert(student);
        if(stored)
        {
            students.add(student);
        }
        return stored;
    }

    /**
     * Aktualisiert einen vorhandenen Studenten in der Datenbank.
     *
     * @param student Der Student, der aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Aktualisieren auftritt.
     */
    
    public static boolean updatestudent(Student student) throws Exception {
        boolean updated = DBZugriffStudent.update(student);
        if(updated)
        {
            for (Student existingStudent : students) {
                if (existingStudent.getStudentID() == student.getStudentID()) {
                    existingStudent.setAnrede(student.getAnrede());
                    existingStudent.setVorname(student.getVorname());
                    existingStudent.setNachname(student.getNachname());
                    existingStudent.setGeburtsdatum(student.getGeburtsdatum());
                    existingStudent.setEmail(student.getEmail());
                    existingStudent.setWohnungsnummer(student.getWohnungsnummer());
                    existingStudent.setWohnheimnummer(student.getWohnheimnummer());
                    break;
                }
            }
        }
        return updated;
    }
    
    /**
     * Löscht einen Studenten aus der Datenbank.
     *
     * @param student Der Student, der gelöscht werden soll.
     * @return true, wenn die Operation erfolgreich war, andernfalls false.
     * @throws Exception Wenn ein Fehler beim Löschen auftritt.
     */

    public static boolean deleteStudent(Student student) throws Exception {
        if(DBZugriffStudent.delete(student))
        {
            students.remove(student);
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Liefert einen Studenten anhand seiner Studenten-ID aus der Datenbank.
     *
     * @param studentID Die Studenten-ID des gesuchten Studenten.
     * @return Der Student mit der angegebenen Studenten-ID oder null, wenn nicht gefunden.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
    
    public static Student getStudentByStudentID(String studentID) throws Exception
    {
        return DBZugriffStudent.getStudentByStudentID(studentID);
    }

    /**
     * Liefert alle Studenten aus der Datenbank.
     *
     * @return Eine Liste von Studenten-Objekten.
     * @throws Exception Wenn ein Fehler beim Lesen der Daten auftritt.
     */
    
    public static List<Student> getAllStudent() throws Exception {
        students = DBZugriffStudent.getAllStudent();
        return students;
    }
}
