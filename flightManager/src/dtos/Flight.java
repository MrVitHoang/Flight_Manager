/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author VietHoang
 */
public class Flight {

    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
    ArrayList<Integer> limitSeats;

    public Flight() {
    }

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.limitSeats = new ArrayList<>();
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ArrayList<Integer> getLimitSeats() {
        return limitSeats;
    }

    public void setLimitSeats(ArrayList<Integer> limitSeats) {
        this.limitSeats = limitSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return String.format("│ %-10s │ %-15s │ %-15s │ %-20s │ %-20s │ %-15s │\n",
                flightNumber, departureCity, destinationCity, departureTime, arrivalTime, availableSeats);
    }

    public static Comparator comparatorDepartureTime = new Comparator<Flight>() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        @Override
        public int compare(Flight flight1, Flight flight2) {
            String departureTime1Str = flight1.getDepartureTime();
            String departureTime2Str = flight2.getDepartureTime();

            LocalDateTime dateTime1 = LocalDateTime.parse(departureTime1Str, formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(departureTime2Str, formatter);

            return dateTime1.compareTo(dateTime2);
        }
    };
}
