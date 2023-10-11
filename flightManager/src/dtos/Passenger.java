/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author VietHoang
 */
public class Passenger extends Person {
    public Passenger (String passengerFlightNumber, String name, String IDNumber, String phoneNumber, String reservationID, String type) {
        super(passengerFlightNumber, name, IDNumber, phoneNumber, reservationID, type);
    }
    
    @Override
    public String toString() {
        return String.format("│ %-10s │ %-15s │ %-15s │ %-20s │ %-20s │\n",
                getPersonFlightNumber(), getName(), getIDNumber(), getPhoneNumber(), getReservationID());
    }
}
