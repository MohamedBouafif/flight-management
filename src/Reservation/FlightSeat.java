/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation;

import AirCraft.SeatAvailability;
import AirCraft.SeatType;
import com.mycompany.flights.Flight;
import java.util.Objects;

/**
 *
 * @author moham
 *
 * the flight seat status must be changed to inavailable when we assign to it a
 * passenger
 *
 */
//A flight seat is a seat that belongs to a flight -> its a seat that has a price , reservationNumber and a flight that it belongs to 
public final class FlightSeat extends Seat {
    public float price;
    public String resevationNumber;
    public Flight flight;

   public FlightSeat(int seatNumber, SeatType seatType, SeatAvailability seatAvailability, float price, String reservationNumber, Flight flight) {
        super(seatNumber, seatType, seatAvailability);
        this.price = price;
        this.resevationNumber = reservationNumber;
        this.flight = flight;
        
        
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }

    public float getPrice() {
        return price;
    }

    public String getResevationNumber() {
        return resevationNumber;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setResevationNumber(String resevationNumber) {
        this.resevationNumber = resevationNumber;
    }

    @Override
    public String toString() {
        return "FlightSeat{" + "price=" + price + ", resevationNumber=" + resevationNumber + ", flight=" + flight + '}';
    }

    public boolean equals(FlightSeat flightseat) {
        if (this == flightseat) {
            return true; // Same reference
        }
        if (flightseat == null) {
            return false; // Null object is not equal
        }
        // Compare all relevant fields for equality
        return this.getSeatNumber() == flightseat.getSeatNumber()
                && // Compare seat number
                this.getSeatType() == flightseat.getSeatType()
                && // Compare seat type (enum comparison)
                this.getSeatAvailability() == flightseat.getSeatAvailability()
                && // Compare availability (enum comparison)
                Float.compare(this.price, flightseat.price) == 0
                && // Compare float price
                Objects.equals(this.resevationNumber, flightseat.resevationNumber)
                && // Compare reservation number
                Objects.equals(this.flight, flightseat.flight); // Compare flight objects
    }

}
