/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package AirCraft;

import Reservation.Seat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MARIEM
 */
public class SeatTest {
    
    public SeatTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getSeatAvailability method, of class Seat.
     */
    @Test
    public void testGetSeatAvailability() {
        System.out.println("getSeatAvailability");
        Seat instance = null;
        SeatAvailability expResult = null;
        SeatAvailability result = instance.getSeatAvailability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAircraft method, of class Seat.
     */
    @Test
    public void testGetAircraft() {
        System.out.println("getAircraft");
        Seat instance = null;
        AirCraft expResult = null;
        AirCraft result = instance.getAircraft();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSeatAvailability method, of class Seat.
     */
    @Test
    public void testSetSeatAvailability() {
        System.out.println("setSeatAvailability");
        SeatAvailability seatAvailability = null;
        Seat instance = null;
        instance.setSeatAvailability(seatAvailability);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeatNumber method, of class Seat.
     */
    @Test
    public void testGetSeatNumber() {
        System.out.println("getSeatNumber");
        Seat instance = null;
        int expResult = 0;
        int result = instance.getSeatNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeatType method, of class Seat.
     */
    @Test
    public void testGetSeatType() {
        System.out.println("getSeatType");
        Seat instance = null;
        SeatType expResult = null;
        SeatType result = instance.getSeatType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSeatNumber method, of class Seat.
     */
    @Test
    public void testSetSeatNumber() {
        System.out.println("setSeatNumber");
        int seatNumber = 0;
        Seat instance = null;
        instance.setSeatNumber(seatNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignAirCraft method, of class Seat.
     */
    @Test
    public void testAssignAirCraft() throws Exception {
        System.out.println("assignAirCraft");
        AirCraft airCraft = null;
        Seat instance = null;
        instance.assignAirCraft(airCraft);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSeatType method, of class Seat.
     */
    @Test
    public void testSetSeatType() {
        System.out.println("setSeatType");
        SeatType seatType = null;
        Seat instance = null;
        instance.setSeatType(seatType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Seat.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Seat instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
