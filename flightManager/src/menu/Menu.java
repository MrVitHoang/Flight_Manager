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

    public void Menu() {
        MenuFunction function = new MenuFunction();
        Scanner scanner = new Scanner(System.in);

        System.out.println("┌──────────────────────────────┐");
        System.out.println("│                       MENU                       │");
        System.out.println("├──────────────────────────────┤");
        System.out.println("│      1. Flight schedule management               │");
        System.out.println("│      2. Passenger reservation and booking        │");
        System.out.println("│      3. Check-in and seat allocation             │");
        System.out.println("│      4. Crew Management                          │");
        System.out.println("│      5. Print to data file                       │");
        System.out.println("│      6. Show flight                              │");
        System.out.println("│      7. Exit                                     │");
        System.out.println("└──────────────────────────────┘");
        System.out.print("Enter choice: ");
        String chose = scanner.nextLine();
        System.out.println();
        if (chose.equals("7")) {
            return;
        }
        do {
            switch (chose) {
                case "1":
                    function.f1();
                    break;
                case "2":
                    function.f2();
                    break;
                case "3":
                    function.f3();
                    break;
                case "4":
                    function.f4();
                    break;
                case "5":
                    function.f5();
                    break;
                case "6":
                    function.f6();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("┌──────────────────────────────┐");
            System.out.println("│                       MENU                       │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│      1. Flight schedule management               │");
            System.out.println("│      2. Passenger reservation and booking        │");
            System.out.println("│      3. Check-in and seat allocation             │");
            System.out.println("│      4. Crew Management                          │");
            System.out.println("│      5. Print to data file                       │");
            System.out.println("│      6. Show flight                              │");
            System.out.println("│      7. Exit                                     │");
            System.out.println("└──────────────────────────────┘");
            System.out.print("Enter choice: ");
            chose = scanner.nextLine();
            System.out.println();
        } while (!chose.equals("7"));
    }
}
