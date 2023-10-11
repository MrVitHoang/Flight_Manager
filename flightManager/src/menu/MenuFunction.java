/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import checkin.CheckinManager;
import crew.CrewManager;
import dtos.Flight;
import dtos.Person;
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
    CrewManager crewManager = new CrewManager();
    Scanner scanner = new Scanner(System.in);

    public MenuFunction() {
        try {
            flightList.readFlightDataFromFile("data/flight.dat");

            passengerManager.readPassengerDataFromFile("data/passenger.dat");

            checkinManager.readCheckinDataFromFile("data/checkin.dat");

            crewManager.readCrewDataFromFile("data/crew.dat");
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

        String choice;
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│                       MENU                       │");
        System.out.println("├──────────────────────────────┤");
        System.out.println("│                 1. Add flight                    │");
        System.out.println("│                 2. Exit                          │");
        System.out.println("└──────────────────────────────┘");
        System.out.print("Enter choice: ");
        choice = scanner.nextLine();

        do {
            switch (choice) {
                case "1":
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
                    departureCity = scanner.nextLine().toUpperCase().trim().replaceAll("\\s+", "");

                    System.out.print("Enter arrival city: ");
                    destinationCity = scanner.nextLine().toUpperCase().replaceAll("\\s+", "");

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

                    System.out.print("Enter number of seats: ");
                    limitSeats = Integer.parseInt(scanner.nextLine());
                    while (limitSeats <= 0) {
                        System.out.println("Invalid number (Number must greater than 0)");
                    }

                    flightList.addFlight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, limitSeats);

                    System.out.println();
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│                       MENU                       │");
                    System.out.println("├──────────────────────────────┤");
                    System.out.println("│                 1. Add flight                    │");
                    System.out.println("│                 2. Exit                          │");
                    System.out.println("└──────────────────────────────┘");
                    System.out.print("Enter choice: ");
                    choice = scanner.nextLine();
                    System.out.println();
                    break;
                case "2":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid choice");
                    System.out.println();
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│                       MENU                       │");
                    System.out.println("├──────────────────────────────┤");
                    System.out.println("│                 1. Add flight                    │");
                    System.out.println("│                 2. Exit                          │");
                    System.out.println("└──────────────────────────────┘");
                    System.out.print("Enter choice: ");
                    choice = scanner.nextLine();
                    break;
            }
        } while (choice.equals("1"));
    }

    public void f2() {

        boolean isExist;
        String name;
        String IDNumber;
        String phoneNumber;
        String randomID;
        String type;

        String flightNumber = null;
        String departureCity;
        String destinationCity;
        String date;

        String choice;
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│                       MENU                       │");
        System.out.println("├──────────────────────────────┤");
        System.out.println("│                 1. Booking                       │");
        System.out.println("│                 2. Exit                          │");
        System.out.println("└──────────────────────────────┘");
        System.out.print("Enter choice: ");
        choice = scanner.nextLine();

        do {
            switch (choice) {
                case "1":
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
                            type = "PASSENGER";
                            passengerManager.addPassenger(flightNumber, name, IDNumber, phoneNumber, randomID, type);
                            System.out.println("BOOK SUCCESSFUL");
                            System.out.println();
                            
                            System.out.println("┌──────────────────────────────┐");
                            System.out.println("│                       MENU                       │");
                            System.out.println("├──────────────────────────────┤");
                            System.out.println("│                 1. Booking                       │");
                            System.out.println("│                 2. Exit                          │");
                            System.out.println("└──────────────────────────────┘");
                            System.out.print("Enter choice: ");
                            choice = scanner.nextLine();

                        } else {
                            isExist = false;
                            System.out.println("The flight doesn't exist");
                        }
                    } while (!isExist);
                    break;
                case "2" :
                    System.out.println();
                    return;
            }
            
        } while (choice.equals("2"));
    }

    public void f3() {
        System.out.print("Enter reservation ID: ");
        String reservationID = scanner.nextLine();
        String flightNumber = null;
        for (Person passenger : passengerManager) {
            if (reservationID.equals(passenger.getReservationID())) {
                flightNumber = passenger.getPersonFlightNumber();
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
        String choice;
        String searchFlight;
        do {
            crewManager.sortFlightName();
            System.out.println("┌──────────────────────────────┐");
            System.out.println("│                       MENU                       │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│   1. Add or show pilot to the flight             │");
            System.out.println("│   2. Add or show flight attendants to the flight │");
            System.out.println("│   3. Add ground staff to the flight              │");
            System.out.println("│   4. Exit                                        │");
            System.out.println("└──────────────────────────────┘");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│                       MENU                       │");
                    System.out.println("├──────────────────────────────┤");
                    System.out.println("│                 1. Add pilot                     │");
                    System.out.println("│                 2. Show pilots                   │");
                    System.out.println("│                 3. Exit                          │");
                    System.out.println("└──────────────────────────────┘");
                    System.out.print("Enter choice: ");
                    String choiceCase1 = scanner.nextLine().trim();
                    do {
                        switch (choiceCase1) {
                            case "1":
                                crewManager.addCrew(flightList, "PILOT");
                                break;
                            case "2":
                                System.out.print("Enter flight (leave empty to show all pilot): ");
                                searchFlight = scanner.nextLine().toUpperCase().trim();
                                crewManager.showAllPilot(searchFlight);
                                System.out.print("Press any key to continue: ");
                                scanner.nextLine();
                                break;
                            case "3":
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }
                        if (!choiceCase1.equals("3")) {
                            System.out.println("┌──────────────────────────────┐");
                            System.out.println("│                       MENU                       │");
                            System.out.println("├──────────────────────────────┤");
                            System.out.println("│                 1. Add pilot                     │");
                            System.out.println("│                 2. Show pilots                   │");
                            System.out.println("│                 3. Exit                          │");
                            System.out.println("└──────────────────────────────┘");
                            System.out.print("Enter choice: ");
                            choiceCase1 = scanner.nextLine().trim();
                        }
                    } while (!choiceCase1.equals("3"));
                    
                    break;
                case "2":
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│                       MENU                       │");
                    System.out.println("├──────────────────────────────┤");
                    System.out.println("│            1. Add flight attendant               │");
                    System.out.println("│            2. Show attendants                    │");
                    System.out.println("│            3. Exit                               │");
                    System.out.println("└──────────────────────────────┘");
                    System.out.print("Enter choice: ");
                    String choiceCase2 = scanner.nextLine();
                    do {
                        switch (choiceCase2) {
                            case "1":
                                crewManager.addCrew(flightList, "FLIGHT ATTENDANT");
                                break;
                            case "2":
                                System.out.print("Enter flight (leave empty to show all flight attendant): ");
                                searchFlight = scanner.nextLine().toUpperCase().trim();
                                crewManager.showAllFlightAttendant(searchFlight);
                                System.out.print("Press any key to continue: ");
                                scanner.nextLine();
                                break;
                            case "3":
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                        if (!choiceCase2.equals("3")) {
                            System.out.println("┌──────────────────────────────┐");
                            System.out.println("│                       MENU                       │");
                            System.out.println("├──────────────────────────────┤");
                            System.out.println("│            1. Add flight attendant               │");
                            System.out.println("│            2. Show attendants                    │");
                            System.out.println("│            3. Exit                               │");
                            System.out.println("└──────────────────────────────┘");
                            System.out.print("Enter choice: ");
                            choiceCase2 = scanner.nextLine().trim();
                        }
                    } while (!choiceCase2.equals("3"));
                    break;
                case "3":
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│                       MENU                       │");
                    System.out.println("├──────────────────────────────┤");
                    System.out.println("│            1. Add ground staff                   │");
                    System.out.println("│            2. Show ground staffs                 │");
                    System.out.println("│            3. Exit                               │");
                    System.out.println("└──────────────────────────────┘");
                    System.out.print("Enter choice: ");
                    String choiceCase3 = scanner.nextLine().trim();
                    do {
                        switch (choiceCase3) {
                        case "1":
                            crewManager.addCrew(flightList, "GROUND STAFF");
                            break;
                        case "2":
                            System.out.print("Enter flight (leave empty to show all ground staff): ");
                            searchFlight = scanner.nextLine().toUpperCase().trim();
                            crewManager.showAllGroundStaff(searchFlight);
                            System.out.print("Press any key to continue: ");
                            scanner.nextLine();
                            break;
                        case "3":
                            break;
                        default:
                            System.out.println("Invalid input.");
                            break;
                        }
                        if (!choiceCase3.equals("3")) {
                            System.out.println("┌──────────────────────────────┐");
                            System.out.println("│                       MENU                       │");
                            System.out.println("├──────────────────────────────┤");
                            System.out.println("│            1. Add ground staff                   │");
                            System.out.println("│            2. Show ground staffs                 │");
                            System.out.println("│            3. Exit                               │");
                            System.out.println("└──────────────────────────────┘");
                            System.out.print("Enter choice: ");
                            choiceCase3 = scanner.nextLine().trim();
                        }
                    } while (!choiceCase3.equals("3"));
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        } while (!choice.equals("4"));

    }

    public void f5() {
        flightList.writeFlightDataToFile("data/flight.dat");
        passengerManager.writePassengerDataToFile("data/passenger.dat");
        checkinManager.writeCheckinDataToFile("data/checkin.dat");
        crewManager.writeCrewDataFromFile("data/crew.dat");
        System.out.println("Print successful");
        System.out.println();
        System.out.println("Press any key to continue: ");
        scanner.nextLine();
    }

    public void f6() {
        flightList.sortDepartureTime();
        flightList.showFlight();
        System.out.print("Press any key to continue: ");
        scanner.nextLine();
    }
}
