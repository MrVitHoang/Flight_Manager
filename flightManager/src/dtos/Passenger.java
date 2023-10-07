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
public class Passenger extends Flight {

    private String passengerFlightNumber;
    private String name;
    private String IDNumber;
    private String phoneNumber;
    private String reservationID;

    public Passenger() {
    }

    public Passenger(String passengerFlightNumber, String name, String IDNumber, String phoneNumber, String reservationID) {
        this.passengerFlightNumber = passengerFlightNumber;
        this.name = name;
        this.IDNumber = IDNumber;
        this.phoneNumber = phoneNumber;
        this.reservationID = reservationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassengerFlightNumber() {
        return passengerFlightNumber;
    }

    public void setPassengerFlightNumber(String passengerFlightNumber) {
        this.passengerFlightNumber = passengerFlightNumber;
    }
    
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }
    @Override
    public String toString() {
        return String.format("│ %-10s │ %-15s │ %-15s │ %-20s │ %-20s │\n",
                passengerFlightNumber, name, IDNumber, phoneNumber, reservationID);
    }
}
