/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin;

import dtos.Flight;
import dtos.Passenger;
import java.util.ArrayList;

/**
 *
 * @author VietHoang
 */
public interface ICheckin {
    public void checkin(String reservationID, String flightNumber, ArrayList<Flight> listFlight, ArrayList<Passenger> listPassenger);
    
    public void showPassengerByReservationID(String reservationID, ArrayList<Passenger> listPassenger);
}
