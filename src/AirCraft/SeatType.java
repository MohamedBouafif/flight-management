/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package AirCraft;

/**
 * Enum representing seat types in an aircraft.
 * It provides a display name for each type.
 * 
 * @author moham
 */
public enum SeatType {
    ECONOMY("ECONOMY"),
    BUSINESS("BUSINESS");

    // Field to hold the display name
    private final String displayName;

    // Constructor to initialize the display name
    SeatType(String displayName) {
        this.displayName = displayName;
    }

    // Getter for the display name
    public String getDisplayName() {
        return displayName;
    }

    // Override toString to return the display name
    @Override
    public String toString() {
        return displayName;
    }
}
