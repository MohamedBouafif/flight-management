/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.flights;

/**
 *
 * @author moham
 */
import AirCraft.AirCraft;
import com.mycompany.flights.Airport;
import com.mycompany.flights.FlightStatus;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

public class Flight {
    private int number;
    private Airport departure_airport;
    private Airport arrival_airport;
    private float duration;
    private LocalDateTime departure_time;
    private FlightStatus status;//programmé,retardé ou en vol
    private AirCraft airCaft;    // each Flight has 1 and only 1 aircraft assigned to it 

    public Flight(int number, Airport departure_airport, Airport arrival_airport, FlightStatus s) {
        this.number = number;
        this.departure_airport = departure_airport;
        this.arrival_airport = arrival_airport;
        this.status = s;   
    }
    
    /*
    Im going to assign an aircraft for the flight
    but i have to add an exception which is that 
    only allows  to assign the aircraft from the 
    list of aircrafts that they already exists.
    */
    public void setAirCraft(AirCraft airCraft){
        this.airCaft = airCraft;
    }
    public int getNumber() {
        return number;
    }

    public AirCraft getAirCaft() {
        return airCaft;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Airport getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(Airport departure_airport) {
        this.departure_airport = departure_airport;
    }

    public Airport getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(Airport arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public LocalDateTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalDateTime departure_time) {
        this.departure_time = departure_time;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }
    public String getAssignedAircraft() {
        return this.airCaft.getName();
    }
    public void setAssignedAirCraft(String s){
        this.airCaft.setName(s);
    }
    @Override
    public String toString() {
        return "Flight{" + "number=" + number + ", departure_airport=" + departure_airport + ", arrival_airport=" + arrival_airport + ", duration=" + duration + ", departure_time=" + departure_time + ", status=" + status + ", airCaft=" + airCaft + '}';
    }

    public void setAssignedAircraft(String selectedAircraft) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
 
}
   
 
