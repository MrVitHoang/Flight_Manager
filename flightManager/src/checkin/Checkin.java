/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin;

/**
 *
 * @author VietHoang
 */
public class Checkin {
    String flightNumber;
    String reservationID;
    int seat;

    public Checkin() {
    }

    public Checkin(String flightName, String reservationID, int seat) {
        this.flightNumber = flightName;
        this.reservationID = reservationID;
        this.seat = seat;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
