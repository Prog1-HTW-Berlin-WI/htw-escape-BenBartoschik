package app;

import java.io.Serializable;
import java.util.Scanner;

import model.Alien;
import model.AlienFrendly;
import model.AlienStrong;
import model.AlienWeak;
import model.HTWRoom;
import model.Hero;
import model.Lecturer;

/**
 * Zentrale Spiellogik des Konsolen-Spiels „HTW Escape“.
 * Der Spieler erkundet Räume, sammelt Unterschriften und hat Zufallsbegegnungen.
 *
 * @author Ben
 * @author Emanuel
 */

public class EscapeGame implements Serializable {

    private static final long serialVersionUID = 1729389822767173584L;

    private transient Scanner scanner;

    private Hero hero;

    // 5 Räume / 5 Übungsleiter -> 5 Unterschriften
    private final HTWRoom[] rooms = new HTWRoom[6];
    private final Lecturer[] lecturers = new Lecturer[6];

    private boolean gameRunning = true;
    private boolean gameFinished = false;

    private int round = 1;
    private boolean smallRestUsedThisRound = false;

    /**
     * Erstellt ein neues Spiel.
     */

    public EscapeGame() {
        initWorld();
    }

    /**
     * Initialisiert Räume und Dozenten.
     */

    private void initWorld() {
        lecturers[0] = new Lecturer("Prof. Gärtner");
        lecturers[1] = new Lecturer("Prof. Witt");
        lecturers[2] = new Lecturer("Prof. Odebrecht");
        lecturers[3] = new Lecturer("Prof. Merkel");
        lecturers[4] = new Lecturer("Prof. Schulz");
        lecturers[5] = new Lecturer("Prof. Merz");

        rooms[0] = new HTWRoom("218", "Großer Hörsaal.", lecturers[0]);
        rooms[1] = new HTWRoom("219", "Übungsraum.", lecturers[1]);
        rooms[2] = new HTWRoom("220", "Hörsaal", lecturers[2]);
        rooms[3] = new HTWRoom("221", "Computer Raum", lecturers[3]);
        rooms[4] = new HTWRoom("222", "Übungsraum", lecturers[4]);
        rooms[5] = new HTWRoom("223", "Großer Hörsaal", lecturers[5]);
    }

    /**
     * Initialisiert den Scanner.
     */

    private void initScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    /**
     * Liest eine Eingabezeile.
     *
     * @return Eingabe
     */

    private String readInput() {
        initScanner();
        return scanner.nextLine();
    }

    /**
     * Prüft, ob das Spiel läuft.
     *
     * @return Spiel läuft
     */

    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Prüft, ob das Spiel beendet ist.
     *
     * @return Spiel beendet
     */

    public boolean isGameFinished() {
        return gameFinished;
    }

     /**
     * Gibt den Helden zurück.
     *
     * @return Held
     */

    public Hero getHero() {
        return hero;
    }

    /**
     * Startet oder setzt das Spiel fort.
     */
    // Hauptloop starten / Resume nach Load möglich

    public void run() {
        initScanner();

        if (!gameFinished) {
            gameRunning = true;   
        }

        // Falls nach Load irgendwas fehlt (Sicherheitsnetz)
        if (rooms[0] == null || lecturers[0] == null) {
            initWorld();
        }

        if (hero == null) {
            System.out.print("Bitte gib den Namen deines Helden ein: ");
            String name = readInput();
            if (name.length() == 0) {
                name = "Hero";
            }
            hero = new Hero(name);
        }

        mainMenu();
    }

    /**
     * Gibt die Menüoptionen aus.
     */

    private void mainMenuoptions() {
        System.out.println("\n=== Runde " + round + " ===");
        System.out.println("(1) Hochschule erkunden");
        System.out.println("(2) Hero Status anzeigen");
        System.out.println("(3) Laufzettel anzeigen");
        System.out.println("(4) Verschnaufpause machen");
        System.out.println("(5) Spiel verlassen");
        System.out.print("Deine Wahl: ");
    }

    /**
     * Führt das Hauptmenü aus.
     */

    private void mainMenu() {
        while (gameRunning && !gameFinished) {
            mainMenuoptions();
            String menuInput = readInput();

            switch (menuInput) {
                case "1":
                    hochschuleErkunden();
                    break;
                case "2":
                    heroStatusanzeigen();
                    break;
                case "3":
                    laufzettelAnzeigen();
                    break;
                case "4":
                    verschnaufPausemachen();
                    break;
                case "5":
                    spielVerlassen();
                    return;
                default:
                    System.out.println("Deine Eingabe ist ungültig, bitte versuche es erneut.");
                    break;
            }
        }
    }

    /**
     * Löst ein zufälliges Ereignis beim Erkunden aus.
     */

    private void hochschuleErkunden() {
        if (gameFinished) return;

        // Majuntke erst beim NÄCHSTEN Erkunden nach 6 Unterschriften
        if (countSignedLecturers() >= 6) {
            treffeProfessorinMajuntke();
            return;
        }

        int roomIndex = (int) (Math.random() * rooms.length);
        HTWRoom room = rooms[roomIndex];

        System.out.println("Du erkundest Raum " + room.getIdentifier() + " - " + room.getDescription());

        double r = Math.random();

        // 20% nichts
        if (r < 0.20) {
            System.out.println("Hier ist nichts Interessantes passiert.");
            endRound();
            return;
        }

        // 52% Alien
        if (r < 0.72) {
            alienEncounter();
            endRound();
            return;
        }

        // 28% Lecturer, aber nicht wenn schon unterschrieben
        Lecturer lec = room.getLecturer();
        if (lec == null || lec.hasSigned()) {
            System.out.println("Der Raum ist leer.");
            endRound();
            return;
        }

        lecturerEncounter(room);
        endRound();
    }

    /**
     * Zeigt den Status des Helden an.
     */

    private void heroStatusanzeigen() {
        System.out.println("\n=== HERO STATUS ===");
        System.out.println("Name: " + hero.getName());
        System.out.println("Leben: " + hero.getHealthPoints());
        System.out.println("XP: " + hero.getExperiencePoints());
        System.out.println("Runde: " + round);
        System.out.println("Laufzettel: " + countSignedLecturers() + "/6 Unterschriften");
    }

    /**
     * Zeigt die gesammelten Unterschriften an.
     */

    private void laufzettelAnzeigen() {
        System.out.println("\n=== LAUFZETTEL ===");
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i].hasSigned()) {
                System.out.println(lecturers[i].getName() + " [unterschrieben]");
            } else {
                System.out.println(lecturers[i].getName() + " [/]");
            }
        }
    }

    /**
     * Führt eine Verschnaufpause aus.
     */

    private void verschnaufPausemachen() {
        System.out.println("\n(1) Kleine Verschnaufpause (+3 LP, pro Runde einmal)");
        System.out.println("(2) Große Verschnaufpause (+10 LP, kostet eine ganze Runde)");
        System.out.println("(0) Zurück");

        String input = readInput();

        switch (input) {
            case "1":
                if (smallRestUsedThisRound) {
                    System.out.println("Kleine Verschnaufpause wurde diese Runde bereits genutzt.");
                    return;
                }
                healHero(3);
                smallRestUsedThisRound = true;
                System.out.println("Aktuelle LP: " + hero.getHealthPoints());
                break;

            case "2":
                healHero(10);
                System.out.println("Aktuelle LP: " + hero.getHealthPoints());
                endRound();
                break;

            case "0":
                break;

            default:
                System.out.println("Ungültige Eingabe.");
                break;
        }
    }

    /**
     * Beendet das Spiel über das Menü.
     */

    private void spielVerlassen() {
        System.out.println("Zurück ins Hauptmenü...");
        gameRunning = false;
    }
    

    /**
     * Begegnung mit einem Dozenten im Raum.
     *
     * @param room Raum
     */

    private void lecturerEncounter(HTWRoom room) {
        Lecturer lec = room.getLecturer();

        // Sicherheitscheck
        if (lec == null || lec.hasSigned()) {
            System.out.println("Niemand ist da.");
            return;
        }

        System.out.println("Du triffst " + lec.getName() + ".");

        if (lec.isReadyToSign()) {
            lec.sign();
            hero.signExerciseLeader(lec);
            System.out.println(lec.getName() + " hat unterschrieben.");
        } else {
            System.out.println(lec.getName() + " ist noch nicht bereit zu unterschreiben.");
        }
    }

     /**
     * Heilt den Helden.
     *
     * @param amount Heilpunkte
     */

    private void healHero(int amount) {
        int current = hero.getHealthPoints();
        int newValue = current + amount;
        if (newValue > 50) newValue = 50;
        hero.setHealthPoints(newValue);
    }

    /**
     * Erstellt ein zufälliges Alien.
     *
     * @return Alien
     */

    private Alien createRandomAlien() {
        double r = Math.random();
        if (r < 0.25) return new AlienFrendly();
        if (r < 0.65) return new AlienWeak();
        return new AlienStrong();
    }

    /**
     * Führt eine Alien-Begegnung aus.
     */

    private void alienEncounter() {
        Alien alien = createRandomAlien();

        System.out.println("Du triffst auf " + alien.getName());
        System.out.println(alien.getGreeting());

        if (alien.isFriendly()) {
            System.out.println("Es greift dich nicht an.");
            return;
        }

        while (!alien.isDefeated() && hero.isOperational()) {
            System.out.println("(1) Angreifen");
            System.out.println("(2) Fliehen");

            String choice = readInput();

            if ("2".equals(choice)) {
                if (hero.flee()) {
                    System.out.println("Flucht gelungen.");
                    return;
                } else {
                    int dmg = alien.doDamage();
                    hero.takeDamage(dmg);
                    System.out.println("Flucht fehlgeschlagen. Schaden: " + dmg);

                    if (!hero.isOperational()) { 
                        GameOver();
                        return;
                    }
                }
            } else if ("1".equals(choice)) {
                int heroDmg = hero.attack();
                alien.takeDamage(heroDmg);
                System.out.println("Du machst " + heroDmg + " Schaden.");

                if (alien.isDefeated()) {
                    System.out.println("Alien besiegt!");
                    hero.addExperiencePoints(1); // so wie in deinem Code
                    return;
                }

                int alienDmg = alien.doDamage();
                hero.takeDamage(alienDmg);
                System.out.println("Alien trifft dich für " + alienDmg);

                if (!hero.isOperational()) { 
                    GameOver();
                    return;
                }
            }
             else {
                System.out.println("Ungültige Eingabe.");
            }
        }
    }

    /**
     * Beendet die Runde und zählt hoch.
     */

    private void endRound() {
        smallRestUsedThisRound = false;

        if (round >= 24) {
            System.out.println("\n========================================");
            System.out.println("24 Runden sind vorbei!");
            System.out.println("Professorin Majuntke steigt in ihr Raumschiff (sie ist in Wahrheit ein Alien) und fliegt davon.");
            System.out.println("Was mit der HTW passieren wird, weiß keiner...");
            System.out.println("GAME OVER");
            System.out.println("========================================\n");
            gameFinished = true;
            gameRunning = false;
            return;
        }

        round++;
    }

    /**
     * Zählt die Unterschriften.
     *
     * @return Anzahl
     */

    private int countSignedLecturers() {
        int signed = 0;
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i].hasSigned()) signed++;
        }
        return signed;
    }

    /**
     * Startet das Endevent mit Professorin Majuntke.
     */

    private void treffeProfessorinMajuntke() {
        System.out.println("\n========================================");
        System.out.println("Du hast alle 6 Unterschriften gesammelt!");
        System.out.println("Endlich findest du Professorin Majuntke.");
        System.out.println("Sie sagt: \"Beantworte mir eine Frage zu Grundlagen der Programmierung!\"");
        System.out.println("========================================\n");

        if (stelleMultipleChoiceFrage()) {
            System.out.println("\n========================================");
            System.out.println("Richtig! Du bekommst eine Urkunde.");
            System.out.println("Die Türen der HTW öffnen sich wieder.");
            System.out.println("DU HAST GEWONNEN!");
            System.out.println("========================================\n");
            gameFinished = true;
            gameRunning = false;
            return;
        }

        System.out.println("\n========================================");
        System.out.println("Falsch! Zweiter Prüfungszeitraum!");
        System.out.println("Noch eine Chance...");
        System.out.println("========================================\n");

        if (stelleMultipleChoiceFrage()) {
            System.out.println("\n========================================");
            System.out.println("Richtig! Du bekommst eine Urkunde.");
            System.out.println("DU HAST GEWONNEN!");
            System.out.println("========================================\n");
        } else {
            System.out.println("\n========================================");
            System.out.println("Wieder falsch!");
            System.out.println("Professorin Majuntke fliegt mit ihrem Raumschiff davon und sagt:");
            System.out.println("\"Tja… nächstes Semester dann!\"");
            System.out.println("GAME OVER.");
            System.out.println("========================================\n");
        }

        gameFinished = true;
        gameRunning = false;
    }

    /**
     * Stellt eine zufällige Multiple-Choice-Frage.
     *
     * @return richtig/falsch
     */

    private boolean stelleMultipleChoiceFrage() {
        int frage = (int) (Math.random() * 3);

        if (frage == 0) {
            System.out.println("Frage 1: Was ist eine Schleife?");
            System.out.println("1) Eine Bedingung");
            System.out.println("2) Eine wiederholte Ausführung von Code");
            System.out.println("3) Ein Datentyp");
            System.out.println("4) Ein Objekt");
            System.out.print("Antwort (1-4): ");
            return "2".equals(readInput());
        }

        if (frage == 1) {
            System.out.println("Frage 2: Was macht ein if-Statement?");
            System.out.println("1) Wiederholt Code");
            System.out.println("2) Beendet das Programm");
            System.out.println("3) Prüft eine Bedingung");
            System.out.println("4) Erstellt ein Objekt");
            System.out.print("Antwort (1-4): ");
            return "3".equals(readInput());
        }

        System.out.println("Frage 3: Was ist ein Objekt?");
        System.out.println("1) Eine Klasse");
        System.out.println("2) Ein Datentyp");
        System.out.println("3) Eine Methode");
        System.out.println("4) Eine Instanz einer Klasse");
        System.out.print("Antwort (1-4): ");
        return "4".equals(readInput());
    }

    /**
     * Beendet das Spiel bei Niederlage.
     */

    public void GameOver() {
        if (!hero.isOperational()) {
            System.out.println("\n========================================");
            System.out.println("Dein Held ist nicht mehr einsatzfähig!");
            System.out.println("GAME OVER");
            System.out.println("========================================\n");
        }
        gameFinished = true;
        gameRunning = false;
    }
}
