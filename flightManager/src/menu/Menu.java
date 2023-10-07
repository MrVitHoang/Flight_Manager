/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.util.Scanner;

/**
 *
 * @author VietHoang
 */
public class Menu {

    public void Menu(){
        MenuFunction function = new MenuFunction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter: ");
        System.out.println("1. Flight schedule management\n"
                + "2. Passenger reservation and booking\n"
                + "3. Check-in and seat allocation\n"
                + "4. Crew Management and Administrator Access\n"
                + "5. Print to data file");
        int chose = Integer.parseInt(scanner.nextLine());
        do {
            switch (chose) {
                case 1:
                    function.f1();
                    break;
                case 2:
                    function.f2();
                    break;
                case 3:
                    function.f3();
                    break;
                case 4:
                    System.out.println("Not updated yet");
                    break;
                case 5:
                    function.f5();
                    break;
            }
            System.out.println("1. Flight schedule management\n"
                + "2. Passenger reservation and booking\n"
                + "3. Check-in and seat allocation\n"
                + "4. Crew Management and Administrator Access\n"
                + "5. Print to data file");
            chose = Integer.parseInt(scanner.nextLine());
        } while (chose != 6);
    }
}
