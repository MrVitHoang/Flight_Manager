/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight;

/**
 *
 * @author VietHoang
 */
public interface IFlighManager {

    public boolean checkFlightNumber(String getNumber);

    public boolean isValidTime(String timeStr);

    public boolean isArrivalAfterDeparture(String departureStr, String arrivalStr);

    public boolean isFlightExist(String getNumber);

    public void addPassengerToFlight(String flightNumber);
}
