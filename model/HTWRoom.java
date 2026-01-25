package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Repräsentiert die Räume im Spiel.
 * Jeder Raum hat eine eindeutige Kennung, eine Beschreibung und einen
 * zugehörigen Dozenten.
 */

public class HTWRoom implements Serializable {

    private static final long serialVersionUID = 9065680017147292999L;

    private String identifier;
    private String description;
    private Lecturer lecturer;

    /**
     * Erstellt einen Raum.
     *
     * @param identifier  Kennung
     * @param description Beschreibung
     * @param lecturer    Dozent
     */

    public HTWRoom(String identifier, String description, Lecturer lecturer) {
        this.identifier = identifier;
        this.description = description;
        this.lecturer = lecturer;
    }

    /**
     * Gibt die Raumkennung zurück.
     *
     * @return Kennung
     */

    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gibt die Raumbeschreibung zurück.
     *
     * @return Beschreibung
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gibt den Dozenten zurück.
     *
     * @return Dozent
     */
    public Lecturer getLecturer() {
        return lecturer;
    }

}
