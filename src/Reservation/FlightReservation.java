/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation;

import com.mycompany.flights.Flight;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author moham
 * 
 * A flight reservation can only be assigned to a unique aircraft
 * which is true since i already made in the flight class an attribute named aircraft
 */
public class FlightReservation {
    private String flightReservationNumber;
    private Flight flight;
    private Map<Passenger,FlightSeat> passengersFlightSeat;  // Each passenger is assigned to unique flight seat in the aircraft
    public FlightReservation(String flightReservationNumber, Flight flight) {
        this.flightReservationNumber = flightReservationNumber;
        this.flight = flight;
        passengersFlightSeat = new HashMap<>();
    }
    //If we want to add a reservation for a passenger that doesnt belong to the this flight we have to rase an exception
    public void addFlightReservation(Passenger passenger)  {
      
       passengersFlightSeat.put(passenger, passenger.getFlightSeat());
    }
    public String getFlightReservationNumber() {
        return flightReservationNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public Map<Passenger, FlightSeat> getPassengersFlightSeat() {
        return passengersFlightSeat;
    }

    public void setFlightReservationNumber(String flightReservationNumber) {
        this.flightReservationNumber = flightReservationNumber;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassengersFlightSeat(Map<Passenger, FlightSeat> passengersFlightSeat) {
        this.passengersFlightSeat = passengersFlightSeat;
    }

    @Override
    public String toString() {
        return "FlightReservation{" + "flightReservationNumber=" + flightReservationNumber + ", flights=" + flight + ", passengersFlightSeat=" + passengersFlightSeat + '}';
    }

        
    
    
}