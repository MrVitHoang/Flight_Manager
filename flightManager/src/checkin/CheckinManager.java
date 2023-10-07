/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin;

import dtos.Flight;
import dtos.Passenger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author VietHoang
 */
public class CheckinManager extends ArrayList<Checkin> implements ICheckin {

    public void readCheckinDataFromFile(String filename) throws FileNotFoundException {
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
                String _reservationID = data[1].trim();
                int _seat = Integer.parseInt(data[2].trim());
                Checkin checkin = new Checkin(_flightNumber, _reservationID, _seat);
                this.add(checkin);
            }
            bfr.close();
            fr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void writeCheckinDataToFile(String filename) {
        try {

            File f = new File(filename);

            FileWriter fw = new FileWriter(f);

            PrintWriter pw = new PrintWriter(fw);
            for (Checkin Checkin : this) {
                pw.println(Checkin.getFlightNumber()
                        + ", " + Checkin.getReservationID()
                        + ", " + Checkin.getSeat());
            }
            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void showPassengerByReservationID(String reservationID, ArrayList<Passenger> listPassenger) {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.format("│ %-10s │ %-15s │ %-15s │ %-20s │ %-20s │\n", "FLIGHT", "NAME", "ID NUMBER", "PHONE NUMBER", "RESERVATION ID");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        for (Passenger passenger : listPassenger) {
            if (reservationID.equals(passenger.getReservationID())) {
                System.out.print(passenger);
            }
        }
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }

    @Override
    public void checkin(String reservationID, String flightNumber, ArrayList<Flight> listFlight, ArrayList<Passenger> listPassenger) {
        Scanner scanner = new Scanner(System.in);

        for (Checkin checkin : this) {
            if (checkin.getReservationID().equals(reservationID)) {
                System.out.println("This passenger checkin already");
                return;
            }
        }
        
        showPassengerByReservationID(reservationID, listPassenger);
        
        Flight selectedFlight = null;
        for (Flight flight : listFlight) {
            if (flightNumber.equals(flight.getFlightNumber())) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight doesnt exist");
            return;
        }

        System.out.println("Available seat(s):");
        for (Flight flight : listFlight) {
            if (flightNumber.equals(flight.getFlightNumber())) {
                System.out.println(flight.getLimitSeats());
            }
        }

        ArrayList<Integer> seatList = selectedFlight.getLimitSeats();

        System.out.println("Enter seat: ");
        int chosenNumber = Integer.parseInt(scanner.nextLine());

        if (seatList.contains(chosenNumber)) {
            seatList.remove(Integer.valueOf(chosenNumber));
            System.out.println("Successfully");
        } else if (chosenNumber > seatList.size()){
            System.out.println("Seat doesnt exist");
        } else {
            System.out.println("Unavailable seat");
        }
        
        
        Checkin checkin = new Checkin(flightNumber, reservationID, chosenNumber);
        this.add(checkin);
    }
}
