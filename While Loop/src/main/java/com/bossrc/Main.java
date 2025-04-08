package com.bossrc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter number: ");
            int number = sc.nextInt();

            switch (number) {
                case 1:
                    System.out.println("You have entered 1 number\n");
                    break;
                case 2:
                    System.out.println("You have entered 2 numbers\n");
                    break;
                case 3:
                    System.out.println("You have entered 3 numbers\n");
                    break;
                case 4:
                    System.out.println("You have entered 4 numbers\n");
                    break;
                case 5:
                    System.out.println("You have entered 5 numbers\n");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}