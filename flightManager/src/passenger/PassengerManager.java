/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passenger;

/**
 *
 * @author VietHoang
 */
import dtos.Passenger;
import dtos.Flight;
import flight.FlightList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class PassengerManager extends ArrayList<Passenger> implements IPassengerManager {

    @Override
    public boolean addPassengerToFlight(String departureCity,
            String destinationCity, String day, ArrayList<Flight> listFlight) {
        for (Flight flight : listFlight) {
            if (departureCity.equals(flight.getDepartureCity())
                    && destinationCity.equals(flight.getDestinationCity())
                    && day.equals(flight.getDepartureTime())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addPassenger(String flightNumber, String name, String IDNumber, String phoneNumber, String randomID) {
        Passenger passenger = new Passenger(flightNumber, name, IDNumber, phoneNumber, randomID);
        this.add(passenger);

        FlightList flightList = new FlightList();
        flightList.addPassengerToFlight(flightNumber);
    }

    @Override
    public String generateRandomID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    @Override
    public void showAllPassengerByFlight(String searchInput) {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.format("│ %-10s │ %-15s │ %-15s │ %-20s │ %-20s │\n", "FLIGHT", "NAME", "ID NUMBER", "PHONE NUMBER", "RESERVATION ID");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        for (Passenger passenger : this) {
            if (searchInput.equals(passenger.getPassengerFlightNumber())) {
                System.out.print(passenger);
            }
        }
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }

    @Override
    public boolean checkReservationID(String reservationID) {
        for (Passenger passenger : this) {
            if (passenger.getReservationID().equals(reservationID)) {
                return true;
            }
        }
        return false;
    }

    public void readPassengerDataFromFile(String filename) throws FileNotFoundException {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return;
            }

            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] data = line.split(", ");

                String _flightNumber = data[0].trim();
                String _name = data[1].trim();
                String _IDNumber = data[2].trim();
                String _phoneNumber = data[3].trim();
                String _randomID = data[4].trim();

                Passenger passenger = new Passenger(_flightNumber, _name, _IDNumber, _phoneNumber, _randomID);
                this.add(passenger);
            }
            bfr.close();
            fr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
