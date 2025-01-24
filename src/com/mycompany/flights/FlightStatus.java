package com.mycompany.flights;


public enum FlightStatus {
    SCHEDULED("Scheduled"),
    DELAYED("Delayed"),
    INFLIGHT("InFlight"),
    CANCELED("Canceled");

    private final String displayName;

    FlightStatus(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static FlightStatus fromDisplayName(String displayName) {
    for (FlightStatus status : FlightStatus.values()) {
        if (status.getDisplayName().equalsIgnoreCase(displayName)) {
            return status;
        }
    }
    throw new IllegalArgumentException("Aucun statut trouvé pour le nom d'affichage: " + displayName);
}
    @Override
    public String toString() {
        return displayName;
    }
}
