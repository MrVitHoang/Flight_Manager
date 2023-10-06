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
                + "3. Check-in and seat allocation\n");
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
            }
            System.out.println("Enter 1 or 2: ");
            chose = Integer.parseInt(scanner.nextLine());
        } while (chose != 4);
    }
}
