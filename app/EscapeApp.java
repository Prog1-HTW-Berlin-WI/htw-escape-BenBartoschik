package app;

import java.io.Serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Startet das Spiel und zeigt das Hauptmenü an.
 * Hier kann man ein neues Spiel starten, speichern, laden und beenden.
 *
 * @author Ben
 * @author Emmanuel
 */
public class EscapeApp implements Serializable {

    public static final String SAVE_FILE_NAME = "save";
    private EscapeGame game;
    private boolean gameRunning = true;

    private static final long serialVersionUID = 1729389822767173584L;

    /**
     * Startet die App und führt das Menü aus.
     *
     * @param args Startargumente
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (app.gameRunning) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
     * Zeigt das Hauptmenü an.
     */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");

        if (isGameRunning() && !isGameFinished()) {
            System.out.println("(2) Resume game");
        }
        if (isGameRunning()) {
            System.out.println("(3) Save game");
        }
        if (hasSavedGame()) {
            System.out.println("(4) Load game");
            System.out.println("(5) Delete saved game");
        }

        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number!");
    }

    /**
     * Liest eine Eingabe von der Konsole.
     *
     * @return Eingabe
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Benutzereingabe verarbeiten
     * 
     * @param input
     *              Hier wird unterschieden was der benutzer machen will
     *              1: neues spiel starten
     *              2: spiel fortsetzen
     *              3: spiel speichern
     *              4: spiel laden
     *              5: gespeichertes spiel löschen
     *              6: spiel beenden
     *              default: ungültige eingabe
     *              also hier wird das haubtmenü des spiels difiniert und verweißt
     *              auf die entsprechenden methoden
     */

    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startGame();
                break;
            case "2":
                if (isGameRunning() && !isGameFinished()) {
                    resumeGame();
                } else {
                    System.out.println("No game is currently running. Please start a new game or load a saved game.");
                }
                break;
            case "3":
                if (isGameRunning()) {
                    saveGame();
                } else {
                    System.out.println("No game is currently running. Please start a new game or load a saved game.");
                }
                break;
            case "4":
                if (hasSavedGame()) {
                    loadGame();
                    resumeGame();
                } else {
                    System.out.println("No saved game found. Please start a new game.");
                }
                break;
            case "5":
                deleteGame();
                break;
            case "6":
                System.out.println("Quitting the game. Goodbye!");
                this.gameRunning = false;
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
     * Startet ein neues Spiel.
     */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
     * Setzt das aktuelle Spiel fort.
     */

    private void resumeGame() {
        this.game.run();

    }

    /**
     * Löscht den gespeicherten Spielstand.
     */

    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
     * Speichert das aktuelle Spiel.
     */

    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
     * Lädt ein gespeichertes Spiel.
     */

    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob ein Spiel gestartet wurde.
     *
     * @return Spiel vorhanden
     */

    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * Prüft, ob das Spiel beendet ist.
     *
     * @return Spiel beendet
     */

    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob ein Spielstand existiert.
     *
     * @return Spielstand vorhanden
     */

    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
