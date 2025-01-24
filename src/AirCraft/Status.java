/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package AirCraft;

/**
 *
 * @author moham
 */
public enum Status {
    WORKING("WORKING"),
    OLD("OLD"),
    BROKEN("BROKEN"),
    REFACTORING("REFACTORING"),;

   private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }
}