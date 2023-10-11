/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passenger;

import dtos.Flight;
import java.util.ArrayList;

/**
 *
 * @author VietHoang
 */
public interface IPassengerManager {

    public boolean addPassengerToFlight(String departureCity, String destinationCity, String day, ArrayList<Flight> listFlight);

    public void addPassenger(String flightNumber, String name, String IDNumber, String phoneNumber, String randomID, String type);
    
    public String generateRandomID();
    
    public void showAllPassengerByFlight(String searchInput);
    
    public boolean checkReservationID(String reservationID);
}
