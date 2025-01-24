/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AirCraft;

import Reservation.Seat;
import java.util.Date;
import com.mycompany.flights.Flight;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author moham
 * 
 * 
 * 
 * 
 * Each Aircraft Must Have a Name, model , Date of Fabrication , Status, number of Seats
 * Also An aircraft must be assigned to many Flights  -> relation between package Aircraft and Flight
 */public class AirCraft {
    private String name;
    private String model;
    private Date dateFabrication;
    private Status status;
    private List<Flight> flights;
    private int seatNumber;
    private List<Seat> seats;
    
    public  AirCraft(String name, String model, Date dateFabrication, Status status){
        this.name = name;
        this.model = model;
        this.dateFabrication = dateFabrication;
        this.status = status;
        this.flights = new ArrayList<>();
        this.seats = new ArrayList<>();
        this.seatNumber = 0; //initially the number of seats is null 
    }
    public List<Flight> getFlights() {
        return flights;
    }
    public int getNumberSeat() {
        return seatNumber;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setNumberSeat(int numberSeat) {
        this.seatNumber = numberSeat;
    }
    
    //This function allows to assign a flight to an aircraft and simultinuously assign that aircraft to the flight
    public void assignFlight(Flight flight){
        if(!flights.contains(flight)){
            flights.add(flight);
            flight.setAirCraft(this);
        }
    }
    //Adding a seat to an aircraft : the seat must not be already existing 
    public void addSeat(Seat seat)throws DuplicateElementException{
        if(seats.contains(seat))
            throw new DuplicateElementException("The aircraft already contains that seat ! ");
        seats.add(seat);
        seatNumber++;
        
    }
    
    //Calculate the number fo available seats
    public int availaibleSeatNumber(){
        return (int)seats.stream().filter(seat -> seat.getSeatAvailability()==SeatAvailability.AVAILABLE).count();
        
    }
    public String getName(){
        return name;
    }

    public String getModel() {
        return model;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AirCraft{" + "name=" + name + ", model=" + model + ", dateFabrication=" + dateFabrication + ", status=" + status + ", flights=" + flights + ", numberSeat="  +seatNumber + '}';
    }

    
}   