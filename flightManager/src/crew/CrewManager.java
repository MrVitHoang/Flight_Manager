/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crew;

import dtos.Crew;
import dtos.Flight;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author VietHoang
 */
public class CrewManager extends ArrayList<Crew> implements ICrewManager{
    @Override
    public boolean checkFlightExist(String personFlightNumber, ArrayList<Flight> listFlight) {
        for (Flight flight : listFlight) {
            if (flight.getFlightNumber().equals(personFlightNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addCrew(ArrayList<Flight> listFlight, String type) {
        Scanner scanner = new Scanner(System.in);
        String personFlightNumber;
        String name;
        String reservationID = generateRandomID();

        System.out.print("Enter the flight number: ");
        personFlightNumber = scanner.nextLine().toUpperCase().trim();

        while (!checkFlightExist(personFlightNumber, listFlight)) {
            System.out.println("The flight doesn't exits.");
            System.out.print("Enter again: ");
            personFlightNumber = scanner.nextLine().trim().toUpperCase();
        }
        System.out.print("Enter name: ");
        name = scanner.nextLine().trim().toUpperCase();
        while (name.equals("") || name.isEmpty()) {
            System.out.println("Invalid name.");
            System.out.print("Enter again: ");
            name = scanner.nextLine().toUpperCase().trim();
        }
        Crew flightAttendants = new Crew(personFlightNumber, name, reservationID, type);
        this.add(flightAttendants);
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

    public void showAllPilot(String searchFlight) {
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│                       PILOT                      │");
        System.out.format("│ %-10s │ %-15s │ %-16s │\n", "FLIGHT", "NAME", "RESERVATION ID");
        System.out.println("├──────────────────────────────┤");
        for (Crew crew : this) {
            if (searchFlight.equals(crew.getPersonFlightNumber()) && "PILOT".equals(crew.getType())) {
                System.out.print(crew);
            } else if (searchFlight.equals("") && "PILOT".equals(crew.getType()) || searchFlight.isEmpty() && "PILOT".equals(crew.getType())) {
                System.out.print(crew);
            }
        }
        System.out.println("└──────────────────────────────┘");
    }

    public void showAllFlightAttendant(String searchFlight) {
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│                FLIGHT ATTENDANT                  │ ");
        System.out.format("│ %-10s │ %-15s │ %-16s │\n", "FLIGHT", "NAME", "RESERVATION ID");
        System.out.println("├──────────────────────────────┤");
        for (Crew crew : this) {
            if (searchFlight.equals(crew.getPersonFlightNumber()) && "FLIGHT ATTENDANT".equals(crew.getType())) {
                System.out.print(crew);
            } else if (searchFlight.equals("") && "FLIGHT ATTENDANT".equals(crew.getType()) || searchFlight.isEmpty() && "FLIGHT ATTENDANT".equals(crew.getType())) {
                System.out.print(crew);
            }
        }
        System.out.println("└──────────────────────────────┘");
    }
    
    public void showAllGroundStaff(String searchFlight) {
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│                  GROUND STAFF                    │ ");
        System.out.format("│ %-10s │ %-15s │ %-16s │\n", "FLIGHT", "NAME", "RESERVATION ID");
        System.out.println("├──────────────────────────────┤");
        for (Crew crew : this) {
            if (searchFlight.equals(crew.getPersonFlightNumber()) && "GROUND STAFF".equals(crew.getType())) {
                System.out.print(crew);
            } else if (searchFlight.equals("") && "GROUND STAFF".equals(crew.getType()) || searchFlight.isEmpty() && "GROUND STAFF".equals(crew.getType())) {
                System.out.print(crew);
            }
        }
        System.out.println("└──────────────────────────────┘");
    }

    public void readCrewDataFromFile(String filename) throws FileNotFoundException {
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
                String _reservationID = data[2].trim();
                String _type = data[3].trim();

                Crew crew = new Crew(_flightNumber, _name, _reservationID, _type);
                this.add(crew);
            }
            bfr.close();
            fr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeCrewDataFromFile(String filename) {
        try {

            File f = new File(filename);

            FileWriter fw = new FileWriter(f);

            PrintWriter pw = new PrintWriter(fw);
            for (Crew crew : this) {
                pw.println(crew.getPersonFlightNumber()
                        + ", " + crew.getName()
                        + ", " + crew.getReservationID()
                        + ", " + crew.getType());
            }
            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    public void sortFlightName() {
        Collections.sort(this, Crew.comparatorFlightName);
    }
}
