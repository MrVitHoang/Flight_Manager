/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Comparator;

/**
 *
 * @author VietHoang
 */
public class Crew extends Person {

    public Crew(String personFlightNumber, String name, String reservationID, String type) {
        super(personFlightNumber, name, null, null, reservationID, type);
    }

    @Override
    public String toString() {
        return String.format("│ %-10s │ %-15s │ %-16s │\n",
                getPersonFlightNumber(), getName(), getReservationID());
    }
    
    public static Comparator comparatorFlightName = new Comparator<Crew>() {
        @Override
        public int compare(Crew o1, Crew o2) {
            return o1.getPersonFlightNumber().compareTo(o2.getPersonFlightNumber());
        }
    };
}
