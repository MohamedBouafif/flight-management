/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Reservation;

import AirCraft.AirCraft;
import AirCraft.DuplicateElementException;
import AirCraft.SeatAvailability;
import AirCraft.SeatType;
import AirCraft.Status;
import com.mycompany.flights.Airport;
import com.mycompany.flights.Flight;
import java.util.Date;

/**
 *
 * @author moham
 */
public class MainReservation {

    public static void main(String[] args) {
        //First step: creating the flights (create airports than flights)
        Airport airport_departure = new Airport("Tunisair", "Tunis", "ID1");
        Airport airport_arrival = new Airport("Marocair", "Marocco", "ID2");

        Flight flight1 = new Flight(1, airport_departure, airport_arrival, com.mycompany.flights.FlightStatus.INFLIGHT);
        
        
        //Step 2 : Create the aircrafts that will take these flights
        AirCraft airCraft1 = new AirCraft("Helicopter", "Ss24", new Date(2000, 12, 05), Status.WORKING);
        
        //Step 3: Create the flightseats that are going to take the passengers and also mention their flights
        FlightSeat flightSeat1 = new FlightSeat(1, SeatType.BUSINESS, SeatAvailability.AVAILABLE,1000,"11d",flight1);
        FlightSeat flightSeat2 = new FlightSeat(2,SeatType.ECONOMY, SeatAvailability.AVAILABLE,1000,"11d" ,flight1);
        
        //Step4 : assign these flightseats to the aircrafts that will take these flights
        try {
            flightSeat1.assignAirCraft(airCraft1);   // Automatically flightseat1 will be assinged to aircraft1 and the list of seats of aircraft1 will contain seat1
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }
        try {
            flightSeat2.assignAirCraft(airCraft1);
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }
        
        //Step 5 : Create the passengers that are going to fly
        
        Passenger passenger1 = new Passenger("Mohamed", "Bouafif", new Date(2001, 12, 05),"NB55JD");
        Passenger passenger2= new Passenger("Mariem", "Charef", new Date(2002, 12, 05),"CCNF445");
        
        //Step 6: Assign to each passenger his flightseat (be careful if that seat is already taken by another passenger)
        try{
            passenger1.assignFlightSeatToPassenger(flightSeat1);
        } catch(AvailabilityException e){
            System.out.println(e.getMessage());
        }
        try{
            passenger2.assignFlightSeatToPassenger(flightSeat2);
        } catch(AvailabilityException e){
            System.out.println(e.getMessage());
        }
        
        //Step 7 :Create the flightreservation 
        FlightReservation flightReservation = new FlightReservation("1",flight1);
        
        //Step 8 :Add each passenger to the flight reservation 
        
        flightReservation.addFlightReservation(passenger1);
        
            
       
        flightReservation.addFlightReservation(passenger2);
        
           
        System.out.println("The reservations:\n" + flightReservation);
        

        
    }

}