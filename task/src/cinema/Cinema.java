package cinema;

import java.util.Scanner;

public class Cinema {

    static int rows;
    static int seats;
    static int buySeats = 0;
    static int bank = 0;
    static int fullBank;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializationCinema();
        System.out.println("");
        char cinemaZal[][] = renderCinema(rows, seats);
        int menuChoise = 0;
        do {
            printMenu();
            menuChoise = scanner.nextInt();
            switch (menuChoise) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    renderMatrix(cinemaZal);
                    break;
                case 2:
                    buyTicket(cinemaZal);
                    break;
                case 3:
                    outputStatistic();
                    break;
                default:
                    break;
            }
            System.out.print("\n");
        } while (true);
    }

    public static void initializationCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        int eight = 0;
        int ten = 0;

        for (int i = 1; i <= rows; i++) {
            if (rows * seats > 60) {
                if (i > rows / 2) {
                    eight += seats;
                } else {
                    ten += seats;
                }
            } else {
                ten += seats;
            }
        }

        fullBank = eight * 8 + ten * 10;
    }

    public static void printMenu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static char[][] buyTicket(char[][] cinemaZal) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();


        if (row > rows || seat > seats) {
            System.out.print("\n");
            System.out.println("Wrong input!");
            buyTicket(cinemaZal);
            return cinemaZal;
        } else {
            char symbol = cinemaZal[row][seat];
            if (symbol == 'B') {
                System.out.print("\n");
                System.out.println("That ticket has already been purchased!");
                buyTicket(cinemaZal);
                return cinemaZal;
            } else {
                System.out.print("\n");
                cinemaZal[row][seat] = 'B';
                System.out.println("Ticket price: $" + seatIncome(row,seat, rows * seats, rows));
                buySeats++;
                return cinemaZal;
            }
        }

    }

    public static void totalIncome(int rows, int seats) {
        System.out.print("Total income:\n$");
        if (rows * seats > 60) {
            int n1 = rows / 2;
            int n2 = rows - n1;
            System.out.print(n1 * seats * 10 + n2 * seats * 8);
        } else {
            System.out.print(rows * seats * 10);
        }
    }

    public static int seatIncome(int row, int seat, int seatsAll, int rows) {
        if (seatsAll > 60) {
            if (row > rows / 2) {
                //System.out.println("Ticket price: $8");
                bank += 8;
                return 8;
            } else {
                //System.out.println("Ticket price: $10");
                bank += 10;
                return 10;
            }
        } else {
            //System.out.println("Ticket price: $10");
            bank += 10;
            return 10;
        }
    }

    public static char[][] renderCinema(int rows, int seats) {
        char[][] matrixSymbols = new char[rows + 1][seats + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                matrixSymbols[0][j] = (char) (j + 48);
                matrixSymbols[i][j] = 'S';
            }
            matrixSymbols[i][0] = (char) (i + 48);
        }
        //renderMatrix(matrixSymbols);
        return matrixSymbols;
    }

    public static void renderMatrix(char[][] matrixSymbols) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    System.out.print(" ");
                }
                System.out.print(matrixSymbols[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void outputStatistic() {
        System.out.println("\nNumber of purchased tickets: " + buySeats + "\n" +
                "Percentage: " + String.format("%.2f",(1.0 * (buySeats * 100) / (rows * seats))) + "%\n" +
                "Current income: $" + bank +
                "\nTotal income: $" + fullBank);
    }

}