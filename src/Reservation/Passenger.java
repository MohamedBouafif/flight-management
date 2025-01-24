/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reservation;

import AirCraft.SeatAvailability;
import java.util.Date;

/**
 *
 * @author moham
 * a passenger must have a flight seat (sinon 3lech nda5l fih lil base de donne min aslou)
 * Functionalities:
 *  I want to know the passenger's seat
 *  I want to know the passenger's flight 
 *  I want to know the passenger's aircraft
 *  how ? the seat class contains all these information
 * 
 */
public class Passenger {
    private String firstName;
    private String secondName;
    private Date BirthDate;
    private String passeportNumber;
    private FlightSeat flightSeat;
    public Passenger(String firstName, String secondName, Date BirthDate, String passeportNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.BirthDate = BirthDate;
        this.passeportNumber = passeportNumber;
    }
    public void assignFlightSeatToPassenger(FlightSeat flightSeat) throws AvailabilityException {
        if(flightSeat.getSeatAvailability() == SeatAvailability.INAVAILABLE)
            throw new AvailabilityException("This seat cannot be assigned to this passenger because its not Available");
        this.flightSeat = flightSeat;
        this.flightSeat.setSeatAvailability(SeatAvailability.INAVAILABLE);
    }

    public FlightSeat getFlightSeat() {
        return flightSeat;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getPasseportNumber() {
        return passeportNumber;
    }

    public void setPasseportNumber(String passeportNumber) {
        this.passeportNumber = passeportNumber;
    }

    @Override
    public String toString() {
        return "Passenger{" + "firstName=" + firstName + ", secondName=" + secondName + ", BirthDate=" + BirthDate + ", passeportNumber=" + passeportNumber + ", flightSeat=" + flightSeat + '}';
    }
    
    
    
}