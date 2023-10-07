/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight;

import dtos.Flight;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 * @author VietHoang
 */
public class FlightList extends ArrayList<Flight> implements IFlighManager {

    @Override
    public boolean checkFlightNumber(String getNumber) {
        /*getNumber = getFlightNumber();*/
        if (getNumber.length() != 5 || getNumber.isEmpty()) {
            return false;
        }
        if ("F".equals(Character.toString(getNumber.charAt(0)))) {
            for (int i = 0; i < 4; i++) {
                if (!Character.isDigit(getNumber.charAt(i + 1))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean isArrivalAfterDeparture(String departureTimeString, String arrivalTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, formatter);
            LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeString, formatter);

            if (arrivalTime.isAfter(departureTime)) {
                return true;
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void addFlight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, int limitSeats) {
        Flight flightInformation = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, limitSeats);
        this.add(flightInformation);
    }

    public void showFlight() {
        if (this.isEmpty()) {
            System.out.println("The flight list is empty");
            return;
        }
        System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
        System.out.format("│ %-10s │ %-15s │ %-15s │ %-20s │ %-20s │ %-15s │\n", "FLIGHT", "DEPARTURE", "DESTINATION", "DEPARTURE TIME", "ARRIVAL TIME", "AVAILABLE SEATS");
        System.out.println("├─────────────────────────────────────────────────────────────────────┤");
        for (Flight flight : this) {
            System.out.print(flight);
        }
        System.out.println("└─────────────────────────────────────────────────────────────────────┘");
    }
    
    public void writeFlightListDataToFile(String filename) {
        try {

            File f = new File(filename);

            FileWriter fw = new FileWriter(f);
//String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, int seatLimit
            PrintWriter pw = new PrintWriter(fw);
            for (Flight flight : this) {
                pw.println(flight.getFlightNumber()
                        + ", " + flight.getDepartureCity()
                        + ", " + flight.getDepartureCity()
                        + ", " + flight.getDepartureTime()
                        + ", " + flight.getArrivalTime()
                        + ", " + flight.getLimitSeats());
            }
            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void readFlightDataFromFile(String filename) throws FileNotFoundException {
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
                String _departureCity = data[1].trim();
                String _destinationCity = data[2].trim();
                String _departureTime = data[3].trim();
                String _arrivalTime = data[4].trim();
                int _limitSeats = Integer.parseInt(data[5].trim());

                Flight flightInformation = new Flight(_flightNumber, _departureCity, _destinationCity,
                        _departureTime, _arrivalTime, _limitSeats);
                this.add(flightInformation);
            }
            bfr.close();
            fr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean isFlightExist(String getNumber) {
        for (Flight flight : this) {
            if (getNumber.equals(flight.getFlightNumber())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addPassengerToFlight(String flightNumber) {
        for (Flight flight : this) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            }
        }
    }
}
