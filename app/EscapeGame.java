package app;
import java.io.Serializable;


import model.Hero;
import model.Alien;
import model.HTWRoom;
import java.util.Scanner;
import model.Lecturer;

import model.AlienWeak;
import model.AlienStrong;
import model.AlienFrendly;


/*
@author: Ben Bartoschik und emmanuel bessong
Dies ist die haubtklasse für die spiellogic hier werden die räume und der held initialisiert.
und so alles was das spiel ausmacht.
*/

public class EscapeGame implements Serializable {
    private Hero hero;   //ist es in Ordnung das final zu entnehmen 
    private final HTWRoom[] rooms = new HTWRoom[6];
    private final Lecturer[] lecturers = new Lecturer[6];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private int round = 1;
    private boolean smallRestUsedThisRound = false;
    private static final long serialVersionUID = 1729389822767173584L;

        /*  
        * Initialisiert die Welt mit Räumen und Dozenten.
        * Es ist Gänig initWorld zur Welterstellung zu nutzen.
        * es weißt außerdem den Dozenten die Räume zu.
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
        rooms[2] = new HTWRoom("220", "Büro.", lecturers[2]);
        rooms[3] = new HTWRoom("221", "Büro.", lecturers[3]);
        rooms[4] = new HTWRoom("222", "Büro.", lecturers[4]);
        rooms[5] = new HTWRoom("223", "Büro.", lecturers[5]);
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

    //Wird aufgerufen wenn ein neues spiel gestartet wird.
    //und bezeichnet den hauptspiel loop.
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
   // zeigt die optionen des hauptmenüs an. 
   private void mainMenuoptions(){ 
        System.out.println("(1) Hochschule erkunden");
        System.out.println("(2) Hero Status anzeigen");
        System.out.println("(3) Laufzettel anzeigen");
        System.out.println("(4) Verschnaufpause machen");
        System.out.println("(5) Spiel verlassen");
   }
    // Liest die eingabe des spielers im hauptmenü ein.
    private String mainMenuinput() {
        Scanner sc1 = new Scanner(System.in);
        String menuInput = sc1.nextLine();
        return menuInput;
    }
 

    //Wertet die eingaben im hauptmenü aus und ruft die entsprechenden methoden auf.
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
    /**
     * Bezeichnet die Haubttätigkeit im spiel das ERKUNDEN der Hochschule.
     * hier werden zufällig räume ausgewählt und zufällige ereignisse ausgelöst.
     */
    private void hochschuleErkunden() {
    if (gameFinished) return;

        //Ein ZUfälliger raum wird ausgewählt
    int roomIndex = (int) (Math.random() * rooms.length);
    HTWRoom room = rooms[roomIndex];
    System.out.println("Du erkundest Raum " + room.getIdentifier() + " - " + room.getDescription());

    double r = Math.random();

    // zu 20% passiert nichts
    if (r < 0.20) {
        System.out.println("I found nothing interesting here. WoW ");
        endRound();
        return;
    }
    // zu 52% wird ein Alien angetroffen
    if (r < 0.72) { 
        alienEncounter();   
        endRound();
        return;
    }
    // zu 28% wird ein Dozent angetroffen
    lecturerEncounter(room); 
    endRound();
}

        // zeigt den status des helden an.
   private void heroStatusanzeigen() {
    int signed = 0;
    for (int i = 0; i < lecturers.length; i++) {
        if (lecturers[i].hasSigned()) signed++;
    }

    System.out.println("=== HERO STATUS ===");
    System.out.println("Name: " + hero.getName());
    System.out.println("Leben: " + hero.getHealthPoints());
    System.out.println("XP: " + hero.getExperiencePoints());
    System.out.println("Runde: " + round);
    System.out.println("Laufzettel: " + signed + "/" + lecturers.length);
}
    // zeigt den laufzettel an.
private void laufzettelAnzeigen() {
    System.out.println("=== LAUFZETTEL ===");

    int signed = 0;

    for (int i = 0; i < lecturers.length; i++) {
        if (lecturers[i].hasSigned()) {
            System.out.println(lecturers[i].getName() + " [unterschrieben]");
            signed++;
        } else {
            System.out.println(lecturers[i].getName() + " [/]");
        }
    }

    System.out.println("Unterschriften: " + signed + "/" + lecturers.length);
}
    // ermöglicht dem helden eine verschnaufpause zu machen.
    //Unterscheid zwischen kleiner (zwischenpause) und großer (ganze runde verbraucht) verschnaufpause.
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
            System.out.println("Du regenerierst 3 Lebenspunkte. Aktuell: " + hero.getHealthPoints());
            break;

        case "2":
            healHero(10);
            System.out.println("Du regenerierst 10 Lebenspunkte. Aktuell: " + hero.getHealthPoints());
            endRound(); // komplette Runde verbraucht
            break;

        case "0":
            break;

        default:
            System.out.println("Ungültige Eingabe.");
    }
}
    // Beendet das Spiel und führt einen ins main menü zurück.
   private void spielVerlassen(){

   }
    //Bezeichnet die Begegnung mit einem Dozenten.
   private void lecturerEncounter(HTWRoom room) {
    Lecturer lec = room.getLecturer();
    System.out.println("Du triffst " + lec.getName() + ".");

    // Interaktion (minimal)
    if (lec.isReadyToSign()) {
        // entweder hero speichert Unterschriften:
        hero.signExerciseLeader(lec);

        // oder Lecturer merkt es:
        lec.sign();

        System.out.println(lec.getName() + " hat unterschrieben.");
    } else {
        System.out.println(lec.getName() + " ist noch nicht bereit zu unterschreiben.");
    }
}


    // Heilt den Helden um den angegebenen Betrag, maximal auf 50 LP.
   private void healHero(int amount) {
    int current = hero.getHealthPoints();
    int newValue = current + amount;
    if (newValue > 50) newValue = 50;
    hero.setHealthPoints(newValue);
}

    // Erstellt ein zufälliges Alien basierend auf den vorgegebenen Wahrscheinlichkeiten.
private Alien createRandomAlien() {
    double r = Math.random();

    if (r < 0.25) {
        return new AlienFrendly();   // 25 %
    } else if (r < 0.65) {
        return new AlienWeak();       // 40 %
    } else {
        return new AlienStrong();     // 35 %
    }
}
    // Bezeichnet die Begegnung mit einem Alien und die daraus resultierende Kampfmechanik.
private void alienEncounter() {
    Alien alien = createRandomAlien();

    System.out.println("Du triffst auf " + alien.getName());
    System.out.println(alien.getGreeting());

    if (alien.isFriendly()) {
        System.out.println("It issnt Ataking you. But why?");
        return;
    }

    while (!alien.isDefeated() && hero.isOperational()) {
        System.out.println("(1) Angreifen");
        System.out.println("(2) Fliehen");

        
        String choice = mainMenuinput();
    
       
        if ("2".equals(choice)) {
            if (hero.flee()) {
                System.out.println("Flucht gelungen.");
                return;
            } else {
                int dmg = alien.doDamage();
                hero.takeDamage(dmg);
                System.out.println("Flucht fehlgeschlagen. Schaden: " + dmg);
            }
        } else {
            int heroDmg = hero.attack();
            alien.takeDamage(heroDmg);
            System.out.println("Du machst " + heroDmg + " Schaden.");

            if (alien.isDefeated()) {
                System.out.println("Alien besiegt!");
                hero.addExperiencePoints(1);
                return;
            }

            int alienDmg = alien.doDamage();
            hero.takeDamage(alienDmg);
            System.out.println("Alien trifft dich für " + alienDmg);
        }
    }
}



    // Beendet die aktuelle Runde und überprüft, ob das Spiel vorbei ist.
private void endRound() {
    round++;
    smallRestUsedThisRound = false;

    if (round > 24) {
        System.out.println("Du hast es nicht innerhalb von 24 Stunden geschafft. GAME OVER.");
        gameFinished = true;
        gameRunning = false;
    }
}








}
