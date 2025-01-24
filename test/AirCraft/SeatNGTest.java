/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package AirCraft;

import Reservation.Seat;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author MARIEM
 */
public class SeatNGTest {
    
    public SeatNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
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
        assertEquals(result, expResult);
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
        assertEquals(result, expResult);
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
        assertEquals(result, expResult);
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
        assertEquals(result, expResult);
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
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
