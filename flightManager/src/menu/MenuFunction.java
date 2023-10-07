/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import checkin.CheckinManager;
import dtos.Flight;
import dtos.Passenger;
import flight.FlightList;
import java.io.FileNotFoundException;
import passenger.PassengerManager;
import java.util.Scanner;

/**
 *
 * @author VietHoang
 */
public class MenuFunction {

    FlightList flightList = new FlightList();
    PassengerManager passengerManager = new PassengerManager();
    CheckinManager checkinManager = new CheckinManager();
    Scanner scanner = new Scanner(System.in);

    public MenuFunction() {
        try {
            flightList.readFlightDataFromFile("flight.dat");

            passengerManager.readPassengerDataFromFile("passenger.dat");
            
            checkinManager.readCheckinDataFromFile("checkin.dat");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void f1() {

        String flightNumber;
        String departureCity;
        String destinationCity;
        String departureTime;
        String arrivalTime;
        int limitSeats;

        System.out.print("Enter flight number: ");
        flightNumber = scanner.nextLine().toUpperCase();
        while (!flightList.checkFlightNumber(flightNumber) || !flightList.isFlightExist(flightNumber)) {
            if (!flightList.isFlightExist(flightNumber)) {
                System.out.println("The flight number has already exist");
                System.out.print("Enter again: ");
            } else if (!flightList.checkFlightNumber(flightNumber)) {
                System.out.println("The flight number must be follow as: Fxyzt, with xyzt is a number and no spaces, example: \"F0001\"");
                System.out.print("Enter again: ");
            }
            flightNumber = scanner.nextLine().toUpperCase();
        }

        System.out.print("Enter departure city: ");
        departureCity = scanner.nextLine().toUpperCase();

        System.out.print("Enter arrival city: ");
        destinationCity = scanner.nextLine().toUpperCase();

        System.out.print("Enter departure time (dd/MM/yyyy HH:mm): ");
        departureTime = scanner.nextLine();
        while (!flightList.isValidTime(departureTime)) {
            System.out.println("Time must be follow as dd/MM/yyyy HH:mm.");
            System.out.print("Enter again: ");
            departureTime = scanner.nextLine();
        }

        System.out.print("Enter arrival time (dd/MM/yyyy HH:mm): ");
        arrivalTime = scanner.nextLine();
        while (!flightList.isValidTime(arrivalTime)) {
            System.out.println("Time must be follow as dd/MM/yyyy HH:mm.");
            System.out.print("Enter again: ");
            arrivalTime = scanner.nextLine();
        }

        while (!flightList.isArrivalAfterDeparture(departureTime, arrivalTime)) {
            System.out.println("Invalid time (arrival time must happen after depature time)");
            System.out.print("Enter departure time (dd/MM/yyyy HH:mm): ");
            departureTime = scanner.nextLine();
            while (!flightList.isValidTime(departureTime)) {
                System.out.println("Time must be follow as dd/MM/yyyy HH:mm.");
                System.out.print("Enter again: ");
                departureTime = scanner.nextLine();
            }

            System.out.print("Enter arrival time (dd/MM/yyyy HH:mm): ");
            arrivalTime = scanner.nextLine();
            while (!flightList.isValidTime(arrivalTime)) {
                System.out.println("Time must be follow as dd/MM/yyyy HH:mm.");
                System.out.print("Enter again: ");
                arrivalTime = scanner.nextLine();
            }
        }

        System.out.println("Enter number of seats: ");
        limitSeats = Integer.parseInt(scanner.nextLine());
        while (limitSeats <= 0) {
            System.out.println("Invalid number (Number must greater than 0)");
        }

        flightList.addFlight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, limitSeats);
    }

    public void f2() {

        boolean isExist;
        String name;
        String IDNumber;
        String phoneNumber;
        String randomID;

        String flightNumber = null;
        String departureCity;
        String destinationCity;
        String date;
        
        do {
            flightList.showFlight();
            System.out.print("Enter the flight departure: ");
            departureCity = scanner.nextLine().toUpperCase();
            System.out.print("Enter the flight destination: ");
            destinationCity = scanner.nextLine().toUpperCase();
            System.out.print("Enter the date (dd/MM/yy HH:mm): ");
            date = scanner.nextLine();
            
            if (passengerManager.addPassengerToFlight(departureCity, destinationCity, date, flightList)) {
                isExist = true;

                System.out.print("Enter name: ");
                name = scanner.nextLine();
                System.out.print("Enter ID number: ");
                IDNumber = scanner.nextLine();
                System.out.print("Enter phone number: ");
                phoneNumber = scanner.nextLine();

                for (Flight flight : flightList) {
                    if (departureCity.equals(flight.getDepartureCity())
                            && destinationCity.equals(flight.getDestinationCity())
                            && date.equals(flight.getDepartureTime())) {
                        flightNumber = flight.getFlightNumber();
                    }
                }
                randomID = passengerManager.generateRandomID();
                passengerManager.addPassenger(flightNumber, name, IDNumber, phoneNumber, randomID);

            } else {
                isExist = false;
                System.out.println("The flight doesn't exist");
            }
        } while (!isExist);
    }

    public void f3() {
        System.out.println("Enter reservation ID: ");
        String reservationID = scanner.nextLine();
        String flightNumber = null;
        for (Passenger passenger : passengerManager) {
            if (reservationID.equals(passenger.getReservationID())) {
                flightNumber = passenger.getPassengerFlightNumber();
                break; // Exit the loop once a match is found.
            }
        }
        if (passengerManager.checkReservationID(reservationID)) {
           checkinManager.checkin(reservationID, flightNumber, flightList, passengerManager);
        } else {
            System.out.println("This passenger doesnt exist");
        }
    }
    
    public void f4() {
        
    }
    
    public void f5() {
        flightList.writeFlightListDataToFile("flight.dat");
        passengerManager.writePassengerListDataToFile("passenger.dat");
        checkinManager.writeCheckinDataToFile("checkin.dat");
    }
}
