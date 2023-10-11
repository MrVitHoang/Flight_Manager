/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author VietHoang
 */
public abstract class Person {

    String personFlightNumber;
    private String name;
    private String IDNumber;
    private String phoneNumber;
    private String reservationID;
    private String type;

    public Person() {
    }

    public Person(String personFlightNumber, String name, String IDNumber, String phoneNumber, String reservationID, String type) {
        this.personFlightNumber = personFlightNumber;
        this.name = name;
        this.IDNumber = IDNumber;
        this.phoneNumber = phoneNumber;
        this.reservationID = reservationID;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPersonFlightNumber() {
        return personFlightNumber;
    }

    public void setPersonFlightNumber(String personFlightNumber) {
        this.personFlightNumber = personFlightNumber;
    }
    
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
