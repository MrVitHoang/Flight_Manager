/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crew;

import dtos.Flight;

import java.util.ArrayList;

/**
 *
 * @author VietHoang
 */
public interface ICrewManager {
    public boolean checkFlightExist(String personFlightNumber, ArrayList<Flight> listFlight);

    public void addCrew(ArrayList<Flight> listFlight, String type);

    public String generateRandomID();

}
