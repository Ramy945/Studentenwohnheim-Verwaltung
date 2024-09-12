package praesentationsschicht;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;
import datenhaltungsschicht.DBZugriff;
import logikschicht.Kategorieverwaltung;
import logikschicht.Mitarbeiter;
import logikschicht.Mitarbeiterverwaltung;
import logikschicht.Student;
import logikschicht.Studentverwaltung;
import logikschicht.Wohnheim;
import logikschicht.Wohnheimverwaltung;
import logikschicht.Wohnung;
import logikschicht.Wohnungverwaltung;
import logikschicht.Kategorie;

/**
 * Die Klasse KonsolenMenueWohnheimVerwaltung enthält Methoden für die Verwaltung von Wohnheimen
 * über die Konsole. Das Hauptmenü ermöglicht den Zugriff auf verschiedene Untermenüs, um
 * Wohnheime, Wohnungen, Studenten, Mitarbeiter und Wohnungskategorien zu verwalten.
 */

public class KonsolenMenueWohnheimVerwaltung {

	/**
     * Startet das Hauptmenü für die Verwaltung von Wohnheimen über die Konsole.
     *
     * @throws Exception Wenn ein Fehler während der Ausführung auftritt.
     */
	
	public static void StartMenue() throws Exception {
		
        int auswahl;
        Scanner scanner = new Scanner(System.in);
        
        do {
        	
        	System.out.println("------HauptMenü------");
            System.out.println("1) Wohnheime verwalten");
            System.out.println("2) Wohnungen verwalten");
            System.out.println("3) Studenten verwalten");
            System.out.println("4) Mitarbeiter verwalten");
            System.out.println("5) Wohnungskategorien verwalten");
            System.out.println("0) Exit");
            System.out.print("Geben Sie Ihre Auswahl ein: ");
            auswahl = scanner.nextInt();
            scanner.nextLine();

            switch (auswahl) {
                case 1:
                    subMenuWohnheim(scanner);
                    break;
                case 2:
                	subMenuWohnung(scanner);
                    break;
                case 3:
                	subMenuStudent(scanner);
                    break;
                case 4:
                	subMenuMitarbeiter(scanner);
                    break;
                case 5:
                	subMenuWohnungskategorie(scanner);
                	break;
                case 0:
                    System.out.println("Exiting the program. Auf Wiedersehen!");
                    System.exit(0);
                default:
                    System.out.println("Ungueltige Eingabe. Bitte erneut eingeben.\n");
            }
        } while (auswahl != 0);
        
        scanner.close();		
	}
	
	 /**
     * Zeigt das Untermenü für die Verwaltung von Wohnheimen über die Konsole an.
     *
     * @param scanner Ein Scanner-Objekt für die Benutzereingabe.
     */
	
	private static void subMenuWohnheim(Scanner scanner) {
		
		 int choice;
		
		 try {
	            System.setProperty("file.encoding", "UTF-8");
	            PrintStream out = new PrintStream(System.out, true, "UTF-8");
	            do{
	                out.println("\nWohnheim-Datenbank-Operationen:");
	                out.println("1. Neuen Wohnheim hinzufügen");
	                out.println("2. Wohnheim aktualisieren");
	                out.println("3. Wohnheim löschen");
	                out.println("4. Alle Wohnheim auflisten");
	                out.println("5. Wohnheim anhand der Wohnheimnummer anzeigen");
	                out.println("6. Zurück zum Hauptmenü");

	                out.print("Geben Sie Ihre Auswahl ein: ");
	                choice = scanner.nextInt();
	                scanner.nextLine();
	                System.out.println();
	                String wohnheimnummer;
	                String wohnheimname;
	                switch (choice) {
	                    case 1:
	                        out.print("Geben Sie die Wohnheimnummer ein: ");
	                        wohnheimnummer = scanner.nextLine();
	                        out.print("Geben Sie den Wohnheimname des Wohnheim ein: ");
	                        wohnheimname = scanner.nextLine();
	                        Wohnheim newWohnheim = new Wohnheim(wohnheimnummer, wohnheimname);

	                        if (Wohnheimverwaltung.storeWohnheim(newWohnheim)) {
	                            out.println("Der Wohnheim wurde hinzugefügt.");
	                        } else {
	                            out.println("Fehler beim Hinzufügen des Wohnheim.");
	                        }
	                        break;
	                    case 2:
	                        out.print("Geben Sie die Wohnheimnummer des zu aktualisierenden Wohnheim ein: ");
	                        wohnheimnummer = scanner.nextLine();
	                        Wohnheim wohnheimToUpdate = Wohnheimverwaltung.getWohnheimByWohnheimnummer(wohnheimnummer);

	                        if (wohnheimToUpdate != null) {
	                            out.println("Wohnheim gefunden. Aktuelle Daten:");
	                            out.println(wohnheimToUpdate.toString());

	                            out.print("Geben Sie den neuen Wohnheimnamen ein (oder Enter, um unverändert zu lassen): ");
	                            String newWohnheimname = scanner.nextLine();
	                            if (!newWohnheimname.isEmpty()) {
	                                wohnheimToUpdate.setWohnheimname(newWohnheimname);
	                            }
	                            if (Wohnheimverwaltung.updateWohnheim(wohnheimToUpdate)) {
	                                out.println("Wohnheim erfolgreich aktualisiert. Aktualisierte Daten:");
	                                out.println(wohnheimToUpdate.toString());
	                            } else {
	                                out.println("Fehler beim Aktualisieren des Wohnheim.");
	                            }
	                        } else {
	                            out.println("Wohnheim mit Wohnheimnummer " + wohnheimnummer + " wurde nicht gefunden.");
	                        }
	                        break;

	                    case 3:
	                    	out.print("Geben Sie die Wohnheimnummer des zu löschenden Wohnheims ein: ");
	                        wohnheimnummer = scanner.nextLine();
	                        Wohnheim wohnheimToDelete = Wohnheimverwaltung.getWohnheimByWohnheimnummer(wohnheimnummer);
	                        if (wohnheimToDelete != null) {
	                            if (Wohnheimverwaltung.deleteWohnheim(wohnheimToDelete)) {
	                                out.println("Wohnheim mit Wohnheimnummer " + wohnheimnummer + " wurde erfolgreich gelöscht.");
	                            } else {
	                                out.println("Fehler beim Löschen des Wohnheims.");
	                            }
	                        } else {
	                            out.println("Wohnheim mit Wohnheimnummer " + wohnheimnummer + " wurde nicht gefunden.");
	                        }
	                        break;

	                    case 4:
	                        List<Wohnheim> wohnheims = Wohnheimverwaltung.getAllWohnheim();
	                        for (Wohnheim wohnheim : wohnheims) {
	                            out.println(wohnheim.toString());
	                        }
	                        break;

	                    case 5:
	                        out.print("Geben Sie die Wohnheimnummer des anzuzeigenden Wohnheim ein: ");
	                        wohnheimnummer = scanner.nextLine();
	                        Wohnheim wohnheimToDisplay = Wohnheimverwaltung.getWohnheimByWohnheimnummer(wohnheimnummer);
	                        if (wohnheimToDisplay != null) {
	                            out.println(wohnheimToDisplay.toString());
	                        } else {
	                            out.println("Wohnheim mit Wohnheimnummer " + wohnheimnummer + " wurde nicht gefunden.");
	                        }
	                        break;

	                    case 6:	       	                    	
	                        DBZugriff.close();
	                        System.out.println();
	                        break;
	                        
	                    default:
	                        out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
	                }
	            }while(choice != 6);

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	/**
	 * Zeigt ein Untermenü für die Verwaltung von Wohnungen über die Konsole an.
	 *
	 * @param scanner Ein Scanner-Objekt für die Benutzereingabe.
	 */
	
	private static void subMenuWohnung(Scanner scanner) {
		try{
            System.setProperty("file.encoding", "UTF-8");
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            int choice;
            do {
                out.println("\nWohnung-Datenbank-Operationen:");
                out.println("1. Neuen Wohnung hinzufügen");
                out.println("2. Wohnung aktualisieren");
                out.println("3. Wohnung löschen");
                out.println("4. Alle Wohnungen auflisten");
                out.println("5. Wohnung anhand der Wohnungsnummer anzeigen");
                out.println("6. Zurück zum Hauptmenü");

                out.print("Geben Sie Ihre Auswahl ein: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                String wohnungsnummer;
                String anzahlZimmer;
                String KategorieKrz;
                String wohnheimnummer;
               
                switch (choice) {
                    case 1:
                        out.print("Geben Sie die Wohnungsnummer ein: ");
                        wohnungsnummer = scanner.nextLine();
                        out.print("Geben Sie die AnzahlZimmer der Wohnung ein: ");
                        anzahlZimmer = scanner.nextLine();
                        out.print("Geben Sie den KategorieKrz der Wohnung ein: ");
                        KategorieKrz = scanner.nextLine();
                        out.print("Geben Sie den Wohnheimnummer der Wohnung ein: ");
                        wohnheimnummer = scanner.nextLine();
                       
                        Wohnung newWohnung = new Wohnung(wohnungsnummer, anzahlZimmer, KategorieKrz, wohnheimnummer);

                        if (Wohnungverwaltung.storeWohnung(newWohnung)) {
                            out.println("Der Wohnung wurde hinzugefügt.");
                        } else {
                            out.println("Fehler beim Hinzufügen der Wohnung.");
                        }
                        break;
                    case 2:
                        out.print("Geben Sie die Wohnungsnummer des zu aktualisierenden Wohnung ein: ");
                        wohnungsnummer = scanner.nextLine();
                        Wohnung WohnungToUpdate = Wohnungverwaltung.getWohnungByWohnungsnummer(wohnungsnummer);

                        if (WohnungToUpdate != null) {
                            out.println("Wohnung gefunden. Aktuelle Daten:");
                            out.println(WohnungToUpdate.toString());

                            out.print("Geben Sie die neue AnzahlZimmer ein (oder Enter, um unverändert zu lassen): ");
                            String newAnzahlZimmer = scanner.nextLine();
                            if (!newAnzahlZimmer.isEmpty()) {
                                WohnungToUpdate.setAnzahlZimmer(newAnzahlZimmer);
                            }

                            out.print("Geben Sie den neuen KategorieKrz ein (oder Enter, um unverändert zu lassen): ");
                            String newKategorieKrz = scanner.nextLine();
                            if (!newKategorieKrz.isEmpty()) {
                                WohnungToUpdate.setKategorieKrz(newKategorieKrz);
                            }

                            out.print("Geben Sie den neuen Wohnheimnummer ein (oder Enter, um unverändert zu lassen): ");
                            String newWohnheimnummer = scanner.nextLine();
                            if (!newWohnheimnummer.isEmpty()) {
                                WohnungToUpdate.setWohnheimnummer(newWohnheimnummer);
                            }
                         
                            if (Wohnungverwaltung.updateWohnung(WohnungToUpdate)) {
                                out.println("Wohnung erfolgreich aktualisiert. Aktualisierte Daten:");
                                out.println(WohnungToUpdate.toString());
                            } else {
                                out.println("Fehler beim Aktualisieren der Wohnung.");
                            }
                        } else {
                            out.println("Wohnung mit Wohnungsnummer " + wohnungsnummer + " wurde nicht gefunden.");
                        }
                        break;

                    case 3:
                        out.print("Geben Sie die Wohnungsnummer des zu löschenden Wohnung ein: ");
                        wohnungsnummer = scanner.nextLine();
                        Wohnung WohnungToDelete = Wohnungverwaltung.getWohnungByWohnungsnummer(wohnungsnummer);
                        if (WohnungToDelete != null) {
                            if (Wohnungverwaltung.deleteWohnung(WohnungToDelete)) {
                                out.println("Wohnung mit Wohnungsnummer " + wohnungsnummer + " wurde erfolgreich gelöscht.");
                            } else {
                                out.println("Fehler beim Löschen der Wohnung.");
                            }
                        } else {
                            out.println("Wohnung mit Wohnungsnummer " + wohnungsnummer + " wurde nicht gefunden.");
                        }
                        break;

                    case 4:
                        List<Wohnung> wohnungen = Wohnungverwaltung.getAllWohnungen();
                        for (Wohnung wohnung : wohnungen) {
                            out.println(wohnung.toString());
                        }
                        break;

                    case 5:
                        out.print("Geben Sie die Wohnungsnummer des anzuzeigenden Wohnung ein: ");
                        wohnungsnummer = scanner.nextLine();
                        Wohnung WohnungToDisplay = Wohnungverwaltung.getWohnungByWohnungsnummer(wohnungsnummer);
                        if (WohnungToDisplay != null) {
                            out.println(WohnungToDisplay.toString());
                        } else {
                            out.println("Wohnung mit Wohnungsnummer " + wohnungsnummer + " wurde nicht gefunden.");
                        }
                        break;

                    case 6:                    
                        DBZugriff.close();
                        System.out.println();
                        break;
                        

                    default:
                        out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                }
            }while(choice != 6);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * Zeigt ein Untermenü für die Verwaltung von Studenten über die Konsole an.
	 *
	 * @param scanner Ein Scanner-Objekt für die Benutzereingabe.
	 */
	
	private static void subMenuStudent(Scanner scanner) {
		
		  try{
			  	int choice;
	            System.setProperty("file.encoding", "UTF-8");
	            PrintStream out = new PrintStream(System.out, true, "UTF-8");
	            do {
	                out.println("\nStudent-Datenbank-Operationen:");
	                out.println("1. Neuen Student hinzufügen");
	                out.println("2. Student aktualisieren");
	                out.println("3. Student löschen");
	                out.println("4. Alle Studenten auflisten");
	                out.println("5. Student anhand der Student_ID anzeigen");
	                out.println("6. Zurück zum Hauptmenü");


	                out.print("Geben Sie Ihre Auswahl ein: ");
	                choice = scanner.nextInt();
	                scanner.nextLine();
	                System.out.println();
	                String student_ID;
	                String anrede;
	                String vorname;
	                String nachname;
	                String geburtsdatum;
	                String email;
	                String wohnungsnummer;
	                String wohnheimnummer;


	                switch (choice) {
	                    case 1:
	                        out.print("Geben Sie die Student_ID ein: ");
	                        student_ID = scanner.nextLine();
	                        out.print("Geben Sie die Anrede des Student ein (Herr/Frau): ");
	                        anrede = scanner.nextLine();
	                        out.print("Geben Sie den Vornamen des Student ein: ");
	                        vorname = scanner.nextLine();
	                        out.print("Geben Sie den Nachnamen des Student ein: ");
	                        nachname = scanner.nextLine();
	                        out.print("Geben Sie das Geburtsdatum des Student ein (TT.MM.JJJJ): ");
	                        geburtsdatum = scanner.nextLine();
	                        out.print("Geben Sie die E-Mail-Adresse des Student ein: ");
	                        email = scanner.nextLine();
	                        out.print("Geben Sie die Wohnungsnummer des Student ein: ");
	                        wohnungsnummer = scanner.nextLine();
	                        out.print("Geben Sie den Wohnheimnummer des Student ein: ");
	                        wohnheimnummer = scanner.nextLine();

	                        Student newStudent = new Student(student_ID, anrede, vorname, nachname, geburtsdatum, email, wohnungsnummer, wohnheimnummer);

	                        if (Studentverwaltung.storeStudent(newStudent)) {
	                            out.println("Der Student wurde hinzugefügt.");
	                        } else {
	                            out.println("Fehler beim Hinzufügen des Student.");
	                        }
	                        break;
	                    case 2:
	                        out.print("Geben Sie die student_ID des zu aktualisierenden Student ein: ");
	                        student_ID = scanner.nextLine();
	                        Student StudentToUpdate = Studentverwaltung.getStudentByStudentID(student_ID);

	                        if (StudentToUpdate != null) {
	                            out.println("Student gefunden. Aktuelle Daten:");
	                            out.println(StudentToUpdate.toString());

	                            out.print("Geben Sie die neue Anrede ein (Herr/Frau oder Enter, um unverändert zu lassen): ");
	                            String newAnrede = scanner.nextLine();
	                            if (!newAnrede.isEmpty()) {
	                                StudentToUpdate.setAnrede(newAnrede);
	                            }

	                            out.print("Geben Sie den neuen Vornamen ein (oder Enter, um unverändert zu lassen): ");
	                            String newVorname = scanner.nextLine();
	                            if (!newVorname.isEmpty()) {
	                                StudentToUpdate.setVorname(newVorname);
	                            }

	                            out.print("Geben Sie den neuen Nachnamen ein (oder Enter, um unverändert zu lassen): ");
	                            String newNachname = scanner.nextLine();
	                            if (!newNachname.isEmpty()) {
	                                StudentToUpdate.setNachname(newNachname);
	                            }

	                            out.print("Geben Sie das neue Geburtsdatum (TT.MM.JJJJ) ein (oder Enter, um unverändert zu lassen): ");
	                            String newGeburtsdatum = scanner.nextLine();
	                            if (!newGeburtsdatum.isEmpty()) {
	                                StudentToUpdate.setGeburtsdatum(newGeburtsdatum);
	                            }

	                            out.print("Geben Sie die neue E-Mail-Adresse ein (oder Enter, um unverändert zu lassen): ");
	                            String newEmail = scanner.nextLine();
	                            if (!newEmail.isEmpty()) {
	                                StudentToUpdate.setEmail(newEmail);
	                            }

	                            out.print("Geben Sie die neue wohnungsnummer ein (oder Enter, um unverändert zu lassen): ");
	                            String newwohnungsnummer = scanner.nextLine();
	                            if (!newwohnungsnummer.isEmpty()) {
	                                StudentToUpdate.setWohnungsnummer(newwohnungsnummer);
	                            }

	                            out.print("Geben Sie den neuen wohnheimnummer ein (oder Enter, um unverändert zu lassen): ");
	                            String newwohnheimnummer = scanner.nextLine();
	                            if (!newwohnheimnummer.isEmpty()) {
	                                StudentToUpdate.setWohnheimnummer(newwohnheimnummer);
	                            }

	                            if (Studentverwaltung.updatestudent(StudentToUpdate)) {
	                                out.println("Student erfolgreich aktualisiert. Aktualisierte Daten:");
	                                out.println(StudentToUpdate.toString());
	                            } else {
	                                out.println("Fehler beim Aktualisieren des Student.");
	                            }
	                        } else {
	                            out.println("Student mit Student_ID " + student_ID + " wurde nicht gefunden.");
	                        }
	                        break;

	                    case 3:
	                        out.print("Geben Sie die student_ID des zu löschenden Student ein: ");
	                        student_ID = scanner.nextLine();
	                        Student studentToDelete = Studentverwaltung.getStudentByStudentID(student_ID);
	                        if (studentToDelete != null) {
	                            if (Studentverwaltung.deleteStudent(studentToDelete)){
	                            out.println("student mit student_ID " + student_ID + " wurde erfolgreich gelöscht.");
	                            } else {
	                            out.println("Fehler beim Löschen des student.");
	                            }
	                        } else {
	                            out.println("student mit student_ID " + student_ID + " wurde erfolgreich gefunden.");
	                        }
	                        break;

	                    case 4:
	                        List<Student> students = Studentverwaltung.getAllStudent();
	                        for (Student student : students) {
	                            out.println(student.toString());
	                        }
	                        break;

	                    case 5:
	                        out.print("Geben Sie die student_ID des anzuzeigenden student ein: ");
	                        student_ID = scanner.nextLine();
	                        Student studentToDisplay = Studentverwaltung.getStudentByStudentID(student_ID);
	                        if (studentToDisplay != null) {
	                            out.println(studentToDisplay.toString());
	                        } else {
	                            out.println("Student mit student_ID " + student_ID + " wurde nicht gefunden.");
	                        }
	                        break;

	                    case 6:
	                        DBZugriff.close();
	                        System.out.println();
	                        break;
	                        
	                    default:
	                        out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
	                }
	            }while(choice != 6);

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
		
	}
	
	/**
	 * Zeigt ein Untermenü für die Verwaltung von Mitarbeitern über die Konsole an.
	 *
	 * @param scanner Ein Scanner-Objekt für die Benutzereingabe.
	 */

	private static void subMenuMitarbeiter(Scanner scanner) {
		
		try{
			int choice;
            System.setProperty("file.encoding", "UTF-8");
            PrintStream out = new PrintStream(System.out, true, "UTF-8");

            do {
                out.println("\nMitarbeiter-Datenbank-Operationen:");
                out.println("1. Neuen Mitarbeiter hinzufügen");
                out.println("2. Mitarbeiter aktualisieren");
                out.println("3. Mitarbeiter löschen");
                out.println("4. Alle Mitarbeitern auflisten");
                out.println("5. Mitarbeitern anhand der MitarbeiterID anzeigen");
                out.println("6. Zurück zum Hauptmenü");
               

                out.print("Geben Sie Ihre Auswahl ein: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                String mitarbeiterID;
                String email;
                String vorname;
                String nachname;
                String rolle;        
                String wohnheimnummer;
               
                switch (choice) {
                    case 1:
                        out.print("Geben Sie die MitarbeiterID ein: ");
                        mitarbeiterID = scanner.nextLine();
                        out.print("Geben Sie die E-Mail-Adresse der Mitarbeiter ein: ");
                        email = scanner.nextLine();
                        out.print("Geben Sie den Vornamen der Mitarbeiter ein: ");
                        vorname = scanner.nextLine();
                        out.print("Geben Sie den Nachnamen der Mitarbeiter ein: ");
                        nachname = scanner.nextLine();                       
                        out.print("Geben Sie die Rolle der Mitarbeiter ein(Hausmeister oder Wohnungsverwalter): ");
                        rolle = scanner.nextLine();
                        out.print("Geben Sie die Wohnheimnummer des Wohnheimes, wo die Mitarbeiter arbeitet: ");
                        wohnheimnummer = scanner.nextLine();
                     
                        Mitarbeiter newMitarbeiter = new Mitarbeiter(mitarbeiterID, email, vorname, nachname, rolle, wohnheimnummer);

                        if (Mitarbeiterverwaltung.storeMitarbeiter(newMitarbeiter)) {
                            out.println("Die Mitarbeiter wurde hinzugefügt.");
                        } else {
                            out.println("Fehler beim Hinzufügen der Mitarbeiter.");
                        }
                        break;
                    case 2:
                        out.print("Geben Sie die MitarbeiterID des zu aktualisierenden Mitarbeiter ein: ");
                        mitarbeiterID = scanner.nextLine();
                        Mitarbeiter mitarbeiterToUpdate = Mitarbeiterverwaltung.getMitarbeiterbyMitarbeiterID(mitarbeiterID);

                        if (mitarbeiterToUpdate != null) {
                            out.println("Mitarbeiter gefunden. Aktuelle Daten:");
                            out.println(mitarbeiterToUpdate.toString());

                            out.print("Geben Sie die neue E-Mail-Adresse ein (oder Enter, um unverändert zu lassen): ");
                            String newEmail = scanner.nextLine();
                            if (!newEmail.isEmpty()) {
                                mitarbeiterToUpdate.setEmail(newEmail);
                            }

                            out.print("Geben Sie den neuen Vornamen ein (oder Enter, um unverändert zu lassen): ");
                            String newVorname = scanner.nextLine();
                            if (!newVorname.isEmpty()) {
                                mitarbeiterToUpdate.setVorname(newVorname);
                            }

                            out.print("Geben Sie den neuen Nachnamen ein (oder Enter, um unverändert zu lassen): ");
                            String newNachname = scanner.nextLine();
                            if (!newNachname.isEmpty()) {
                                mitarbeiterToUpdate.setNachname(newNachname);
                            }


                            out.print("Geben Sie die neue Rolle ein (oder Enter, um unverändert zu lassen): ");
                            String newRolle = scanner.nextLine();
                            if (!newRolle.isEmpty()) {
                                mitarbeiterToUpdate.setRolle(newRolle);
                            }

                            out.print("Geben Sie den neuen Wohnheimnummer ein (oder Enter, um unverändert zu lassen): ");
                            String newWohnheimnummer = scanner.nextLine();
                            if (!newWohnheimnummer.isEmpty()) {
                                mitarbeiterToUpdate.setWohnheimnummer(newWohnheimnummer);
                            }
                           
                            if (Mitarbeiterverwaltung.updateMitarbeiter(mitarbeiterToUpdate)) {
                                out.println("Mitarbeiter erfolgreich aktualisiert. Aktualisierte Daten:");
                                out.println(mitarbeiterToUpdate.toString());
                            } else {
                                out.println("Fehler beim Aktualisieren der Mitarbeiter.");
                            }
                        } else {
                            out.println("Mitarbeiter mit MitarbeiterID " + mitarbeiterID + " wurde nicht gefunden.");
                        }
                        break;

                    case 3:
                        out.print("Geben Sie die MitarbeiterID der zu löschenden Mitarbeiter ein: ");
                        mitarbeiterID = scanner.nextLine();
                        Mitarbeiter mitarbeiterToDelete = Mitarbeiterverwaltung.getMitarbeiterbyMitarbeiterID(mitarbeiterID);
                        if (mitarbeiterToDelete != null) {
                            if (Mitarbeiterverwaltung.deleteMitarbeiter(mitarbeiterToDelete)) {
                                out.println("Mitarbeiter mit MitarbeiterID " + mitarbeiterID + " wurde erfolgreich gelöscht.");
                            } else {
                                out.println("Fehler beim Löschen der Mitarbeiter.");
                            }
                        } else {
                            out.println("Mitarbeiter mit MitarbeiterID " + mitarbeiterID + " wurde nicht gefunden.");
                        }
                        break;

                    case 4:
                        List<Mitarbeiter> mitarbeitern = Mitarbeiterverwaltung.getAllMitarbeiter();
                        for (Mitarbeiter mitarbeiter : mitarbeitern) {
                            out.println(mitarbeiter.toString());
                        }
                        break;

                    case 5:
                        out.print("Geben Sie die MitarbeiterID der anzuzeigenden Mitarbeiter ein: ");
                        mitarbeiterID = scanner.nextLine();
                        Mitarbeiter MitarbeiterToDisplay = Mitarbeiterverwaltung.getMitarbeiterbyMitarbeiterID(mitarbeiterID);
                        if (MitarbeiterToDisplay != null) {
                            out.println(MitarbeiterToDisplay.toString());
                        } else {
                            out.println("Mitarbeiter mit MitarbeiterID " + mitarbeiterID + " wurde nicht gefunden.");
                        }
                        break;

                    case 6:
                        DBZugriff.close();
                        System.out.println();
                        break;
                    default:
                        out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                }
            }while(choice != 6);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		
	}

	/**
	 * Zeigt ein Untermenü für die Verwaltung von Wohnungskategorien über die Konsole an.
	 *
	 * @param scanner Ein Scanner-Objekt für die Benutzereingabe.
	 * @throws Exception Wenn ein Fehler bei der Verarbeitung auftritt.
	 */
	
	private static void subMenuWohnungskategorie(Scanner scanner) throws Exception {
		
		
		try {
			 System.setProperty("file.encoding", "UTF-8");
	         PrintStream out = new PrintStream(System.out, true, "UTF-8");
	         int auswahl;

         do {
         
             out.println("\nMenu-Wohnungskategorien:");
             out.println("1. Neuen Wohnungskategorie hinzufügen");
             out.println("2. Wohnungskategorie aktualisieren");
             out.println("3. Wohnungskategorie löschen");
             out.println("4. Alle Wohnungskategorien auflisten");
             out.println("5. Kategorie anhand der katkrz anzeigen");
             out.println("6. Zurück zu Hauptmenü");
             out.print("Geben Sie Ihre Auswahl ein: ");
             auswahl = scanner.nextInt();
             scanner.nextLine();
             System.out.println();
             String katkrz;
             String bezeichnung;
             String oberkat;
             switch (auswahl) {
                 case 1:
                     out.print("Geben Sie die KategorieKrz ein: ");
                     katkrz = scanner.nextLine();
                     out.print("Geben Sie die Kategorie Bezeichnung ein: ");
                     bezeichnung = scanner.nextLine();
                     out.print("Geben Sie die Oberkategorie ein (oder Enter, falls ): ");
                     oberkat = scanner.nextLine();
                     Kategorie newKategorie = new Kategorie(katkrz,bezeichnung,oberkat);

                     if (Kategorieverwaltung.storeKategorie(newKategorie)) {
                         out.println("Die Wohnungkategorie wurde hinzugefügt.");
                     } else {
                         out.println("Fehler beim Hinzufügen der Wohnung.");
                     }
                     break;
                 case 2:
                     out.print("Geben Sie die KategorieKrz des zu aktualisierenden Kategorie ein: ");
                     katkrz = scanner.nextLine();
                     Kategorie kategorieToUpdate = Kategorieverwaltung.getkategorieByKategoriekrz(katkrz);

                     if (kategorieToUpdate != null) {
                         out.println("Kategorie gefunden. Aktuelle Daten:");
                         out.println(kategorieToUpdate.toString());

                         out.print("Geben Sie die neue Bezeichnung ein (oder Enter, um unverändert zu lassen): ");
                         String newBezeichnung = scanner.nextLine();
                         if (!newBezeichnung.isEmpty()) {
                             kategorieToUpdate.setBezeichnung(newBezeichnung);
                         }

                         out.print("Geben Sie den neuen OberKategorie ein (oder Enter, um unverändert zu lassen): ");
                         String newOberkategorie = scanner.nextLine();
                         if (!newOberkategorie.isEmpty()) {
                             kategorieToUpdate.setOberkategorie(newOberkategorie);
                         }


                         if (Kategorieverwaltung.updateKategorie(kategorieToUpdate)) {
                             out.println("Wohnung erfolgreich aktualisiert. Aktualisierte Daten:");
                             out.println(kategorieToUpdate.toString());
                         } else {
                             out.println("Fehler beim Aktualisieren der Wohnung.");
                         }
                     } else {
                         out.println("Kategorie mit KategorieKrz " + katkrz + " wurde nicht gefunden.");
                     }
                     break;

                 case 3:
                     out.print("Geben Sie die KategorieKrz des zu löschenden Wohnung ein: ");
                     katkrz = scanner.nextLine();
                     Kategorie kategorieToDelete = Kategorieverwaltung.getkategorieByKategoriekrz(katkrz);
                     if (kategorieToDelete != null) {
                         if (Kategorieverwaltung.deleteKategorie(kategorieToDelete)) {
                             out.println("Kategorie mit KategorieKrz " + katkrz + " wurde erfolgreich gelöscht.");
                         } else {
                             out.println("Fehler beim Löschen des kategorie.");
                         }
                     } else {
                         out.println("kategorie mit kategorieKrz " + katkrz + " wurde nicht gefunden.");
                     }
                     break;

                 case 4:
                 	List<Kategorie> kategorie = Kategorieverwaltung.getAllKategorie();
                     for (Kategorie kat : kategorie) {
                         out.println(kat.toString());
                     }
                     break;

                 case 5:
                     out.print("Geben Sie die KategorieKrz des anzuzeigenden Kategorie ein: ");
                     katkrz = scanner.nextLine();
                     Kategorie kategorieToDisplay = Kategorieverwaltung.getkategorieByKategoriekrz(katkrz);
                     if (kategorieToDisplay != null) {
                         out.println(kategorieToDisplay.toString());
                     } else {
                         out.println("Kategorie mit KategorieKrz " + katkrz + " wurde nicht gefunden.");
                     }
                     break;
              
                 case 6:
                     DBZugriff.close();
                     System.out.println();
                     break;

                 default:
                     out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
             }
         }while(auswahl != 6);
         
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
