package app;

import model.Hero;
import model.HTWRoom;
import java.util.Scanner;
import model.Lecturer;

/*
@author: Ben Bartoschik und emmanuel bessong
Dies ist die haubtklasse für die spiellogic hier werden die räume und der held initialisiert.
und so alles was das spiel ausmacht.
*/

public class EscapeGame {
    private Hero hero;   //ist es in Ordnung das final zu entnehmen 
    private final HTWRoom[] rooms = new HTWRoom[3];
    private final Lecturer[] lecturers = new Lecturer[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private int round = 1;
    private boolean smallRestUsedThisRound = false;


    private void initWorld() {
        lecturers[0] = new Lecturer("Prof. Gärtner");
        lecturers[1] = new Lecturer("Prof. Witt");
        lecturers[2] = new Lecturer("Prof. Odebrecht");
    
        rooms[0] = new HTWRoom("101", "Großer Hörsaal.", lecturers[0]);
        rooms[1] = new HTWRoom("202", "Übungsraum.", lecturers[1]);
        rooms[2] = new HTWRoom("303", "Büro.", lecturers[2]);
    }

    public EscapeGame() {

    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

         System.out.print("Bitte gib den Namen deines Helden ein: ");
         String name = sc.nextLine();

        hero = new Hero(name);

        initWorld();
        mainMenu();
    }


    public Hero getHero() {
        return hero;
    }

    public void gameMenu(){

    }
   /* 
noch hinzuzufügen 

liste der Lehrer
liste der räume
verbindung operational
beim spielstart zuweisung von räumen und lehrern
}
    */
   private void mainMenuoptions(){ 
        System.out.println("(1) Hochschule erkunden");
        System.out.println("(2) Hero Status anzeigen");
        System.out.println("(3) Laufzettel anzeigen");
        System.out.println("(4) Verschnaufpause machen");
        System.out.println("(5) Spiel verlassen");
   }

    private String mainMenuinput() {
        Scanner sc1 = new Scanner(System.in);
        String menuInput = sc1.nextLine();
        return menuInput;
    }
 


   private void mainMenu(){

    boolean exit = false;

    while(exit == false){

        mainMenuoptions(); 
        String menuInput = mainMenuinput();

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
            exit = true;
            break;
        default:
            System.out.println("Deine Eingabe ist ungültig, bitte versuche es erneut.");
            break;
    }
    }

   }
   private void hochschuleErkunden(){

   }
   private void heroStatusanzeigen() {
    int signed = 0;
    for (int i = 0; i < lecturers.length; i++) {
        if (lecturers[i].hasSigned()) signed++;
    }

    System.out.println("=== HERO STATUS ===");
    System.out.println("Name: " + hero.getName());
    System.out.println("Leben: " + hero.getLifePoints());
    System.out.println("XP: " + hero.getExperiencePoints());
    System.out.println("Runde: " + round);
    System.out.println("Laufzettel: " + signed + "/" + lecturers.length);
}
   
private void laufzettelAnzeigen() {
    System.out.println("=== LAUFZETTEL ===");

    int signed = 0;

    for (int i = 0; i < lecturers.length; i++) {
        if (lecturers[i].hasSigned()) {
            System.out.println(lecturers[i].getName() + " ✔ unterschrieben");
            signed++;
        } else {
            System.out.println(lecturers[i].getName() + " ✘ offen");
        }
    }

    System.out.println("Unterschriften: " + signed + "/" + lecturers.length);
}

private void verschnaufPausemachen() {
    System.out.println("(1) Kleine Verschnaufpause (+3 LP, pro Runde einmal)");
    System.out.println("(2) Große Verschnaufpause (+10 LP, kostet eine ganze Runde)");
    System.out.println("(0) Zurück");

    String input = mainMenuinput();

    switch (input) {
        case "1":
            if (smallRestUsedThisRound) {
                System.out.println("Kleine Verschnaufpause wurde diese Runde bereits genutzt.");
                return;
            }
            healHero(3);
            smallRestUsedThisRound = true;
            System.out.println("Du regenerierst 3 Lebenspunkte. Aktuell: " + hero.getLifePoints());
            break;

        case "2":
            healHero(10);
            System.out.println("Du regenerierst 10 Lebenspunkte. Aktuell: " + hero.getLifePoints());
            endRound(); // komplette Runde verbraucht
            break;

        case "0":
            break;

        default:
            System.out.println("Ungültige Eingabe.");
    }
}

   private void spielVerlassen(){

   }

   private void healHero(int amount) {
    int current = hero.getLifePoints();
    int newValue = current + amount;
    if (newValue > 50) newValue = 50;
    hero.setLifePoints(newValue);
}

private void endRound() {
    round++;
    smallRestUsedThisRound = false;
}



   







}
