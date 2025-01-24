/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AirCraft;

import Reservation.Seat;
import com.mycompany.flights.Airport;
import com.mycompany.flights.Flight;
import java.util.Date;

/**
 *
 * @author si  mohamed
 */
public class Main {

    public static void main(String[] args) {

        AirCraft airCraft1 = new AirCraft("Helicopter", "Ss24", new Date(2000, 12, 05), Status.WORKING);
        AirCraft airCraft2 = new AirCraft("Tayara", "Ss222", new Date(2005, 11, 03), Status.BROKEN);

        Seat seat1 = new Seat(1, SeatType.BUSINESS, SeatAvailability.AVAILABLE);
        Seat seat2 = new Seat(2, SeatType.ECONOMY, SeatAvailability.AVAILABLE);
        Seat seat3 = new Seat(3, SeatType.BUSINESS, SeatAvailability.INAVAILABLE);

        try {
            seat1.assignAirCraft(airCraft1);   // Automatically seat1 will be assinged to aircraft1 and the list of seats of aircraft1 will contain seat1
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }
        try {
            seat2.assignAirCraft(airCraft1);
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }
        try {
            seat3.assignAirCraft(airCraft1);
        } catch (DuplicateElementException e) {
            System.out.println(e.getMessage());
        }

        Airport airport_departure = new Airport("Tunisair", "Tunis", "ID1");
        Airport airport_arrival = new Airport("Marocair", "Marocco", "ID2");

        Flight flight1 = new Flight(1, airport_departure, airport_arrival, com.mycompany.flights.FlightStatus.INFLIGHT);
        Flight flight2 = new Flight(2, airport_departure, airport_arrival, com.mycompany.flights.FlightStatus.DELAYED);

        airCraft1.assignFlight(flight1); // automatically flight1 will be assigned to aircraft1
        airCraft1.assignFlight(flight2); // automatically flight2 will be assigned to aircraft2

        AirCrafts airCrafts = new AirCrafts();
        try {
            airCrafts.addAirCraft(airCraft1);
        } catch (DuplicateElementException e) {
            System.out.print(e.getMessage());
        }
        try {
            airCrafts.addAirCraft(airCraft2);
        } catch (DuplicateElementException e) {
            System.out.print(e.getMessage());
        }

        //printing all the airCrafts
        System.out.println("All Aircrafts in DataBase");
        airCrafts.airCraftFilterAndPerform(airCraft -> true, airCraft -> System.out.println(airCraft));
        
        //Printing only WORKING aircrafts
        System.out.println("Working AirCrafts");
        airCrafts.airCraftFilterAndPerform(airCraft -> airCraft.getStatus()==Status.WORKING, airCraft -> System.out.println(airCraft));
        
        //Printing only  NOT WORKING aircrafts
        System.out.println("Not Working AirCrafts");
        airCrafts.airCraftFilterAndPerform(airCraft -> airCraft.getStatus()==Status.BROKEN || airCraft.getStatus()==Status.REFACTORING , airCraft -> System.out.println(airCraft));
        
        //Sorting aircrafts with date of fabrication
        airCrafts.sortAirCrafts((aircraft1, aircraft2) -> aircraft1.getDateFabrication().compareTo(aircraft2.getDateFabrication()));
        System.out.println("Sort with Date of Fabrication");
        airCrafts.airCraftFilterAndPerform(airCraft -> true , airCraft -> System.out.println(airCraft));
        
        //Sorting aircrafts with number of seats who are available !!  in decreasing order
        airCrafts.sortAirCrafts((aircraft1, aircraft2) -> -1 * (aircraft1.availaibleSeatNumber()- aircraft2.availaibleSeatNumber()));
        System.out.println("Sort with Number of available seats");
        airCrafts.airCraftFilterAndPerform(airCraft -> true , airCraft -> System.out.println(airCraft));
        
        
        
    }

}