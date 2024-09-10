
import java.util.Scanner;
public class planeManagement {

    // a 2D array to represent the seating rows, with 4 rows and 14 columns
    private static final char[][] Structure = new char[4][14];

    //  an array to store the count of columns for each row
    private static final int[] rowColumnCounts = {14, 12, 12, 14};

    // an array to store ticket objects, with a capacity of 52 tickets
    private static final Ticket[] ticketsArray = new Ticket[52];

    // a variable to keep track of the total number of tickets
    private static int ticketCount = 0;


    public static void main(String[] args) {
        while (true) {
            // Display the welcome message and menu options
            System.out.println(" ");
            System.out.println("‘Welcome to the Plane Management application’");
            System.out.println("*********************************");
            System.out.println("*         MENU OPTIONS          * ");
            System.out.println("*********************************");
            System.out.println(" ");
            System.out.println(" 1) Buy a seat ");
            System.out.println(" 2) Cancel a seat ");
            System.out.println(" 3) Find First available seat ");
            System.out.println(" 4) Show Seating plan ");
            System.out.println(" 5) Print ticket information and total sales ");
            System.out.println(" 6) Search ticket ");
            System.out.println(" 0) Quit ");
            System.out.println("  ");
            System.out.println("*********************************");
            System.out.println(" ");

            System.out.print(" Please Select an option:  ");

            Scanner scanner = new Scanner(System.in);
            int option;

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();

                // Validattion of the selected option
                if (option >= 0 && option <= 6) {
                    switch (option) {
                        case 1:
                            buySeat();
                            break;
                        case 2:
                            cancelSeat();
                            break;
                        case 3:
                            firstAvailableSeat();
                            break;
                        case 4:
                            showSeatingPlan();
                            break;
                        case 5:
                            printTicketInfo();
                            break;
                        case 6:
                            searchTicket();
                            break;
                        case 0:
                            System.out.println("Exiting Plane Management System........");
                            return;
                    }
                } else {
                    System.out.println("Invalid option selected. Please choose between 0-6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    /**
     * Prompts the user to enter their name, surname, and email address.
     * Reads the input from the user and validatetion the email address format.
     * Constructs a new Person object with the enter information.
     * return a Person object representing the user information.
     */
    public static Person information() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Enter your Surname: ");
        String surname = scanner.next();
        String email;
        while (true) {
            System.out.println("Enter your Email: ");
            String temp = scanner.next();
            String[] arr = temp.split("[@.]", 3);
            if (arr.length == 3) {
                email = temp;
                break;
            }
        }
        return new Person(name, surname, email);
    }
    /**
     *   Calculates the price of a seat based on its number.
     * Appllies discounts for seats in specific range.
     *  seat The number of the seat to calculate the price for.
     * return The calculated price for the seat.
     */
    private static int calculatePrice(int seat) {
        // Based price for a seat
        int basePrice = 200;

        if (seat >= 6 && seat <= 9) {
            basePrice -= 50;
        } else if (seat >= 10 && seat <= 14) {
            basePrice -= 20;
        }

        return basePrice;
    }


    //Seat purchasing option1 - buy seat option

    /**
     * Allows the user to buy a seat by specifying the row letter (A-D) and seat number.
     * Validattion the input for row and seat number, and checks if the seat is already booked.
     *  If the seat is available, books the seat, calculates the price, creates a ticket for the user.
     * Uses the information method to collect user's information and calculatePrice method to calcutae the price.
     * Stores the ticket information in the ticketsArray and changes the ticket count.
     */
    private static void buySeat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buy a seat option selected");
        char row;
        int seat = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter row letter (A-D): ");
            String rowInput = scanner.next().toUpperCase();
            // Checking the input for row is a single character between A and D
            if (rowInput.length() == 1 && (rowInput.charAt(0) >= 'A' && rowInput.charAt(0) <= 'D')) {
                row = rowInput.charAt(0);
                int Seat = (row == 'B' || row == 'C') ? 12 : 14;
                boolean isValidSeat = false;
                while (!isValidSeat) {
                    System.out.print("Enter seat number (1-" + Seat + "): ");
                    if (scanner.hasNextInt()) {
                        seat = scanner.nextInt();
                        if (seat >= 1 && seat <= Seat) {
                            int rowIndex = row - 'A';
                            if (Structure[rowIndex][seat - 1] != 'X') {
                                Structure[rowIndex][seat - 1] = 'X';
                                System.out.println("Seat " + row + "-" + seat + " has been successfully Booked.");
                                isValidInput = true;
                                isValidSeat = true;
                            } else {
                                System.out.println("Seat is already Booked, Please choose another seat.");
                            }
                        } else {
                            System.out.println("Invalid seat number. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                    }
                }
                //Stores the information in objects,arrays,and save file is called
                Person person = information();
                int price = calculatePrice(seat);
                Ticket ticket = new Ticket(row, seat, price, person);
                ticketsArray[ticketCount] = ticket;
                ticketCount++;
                ticket.save();

            } else {
                System.out.println("Invalid row letter. Please enter a letter between A-D.");
            }
        }
    }

    //seat cancellation option2 -Cancel seat

    /**
     * Allows the user to cancel a booked seat by identifying the row letter (A-D) and seat number.
     * Validates the input for row and seat number, and checks if the seat is already available.
     * If the seat is booked, cancels the seat, removes the corresponding ticket, and deletes the ticket file.
     */
    private static void cancelSeat() {
        System.out.println("Cancel a seat option selected\n");
        Scanner scanner = new Scanner(System.in);
        char row;
        int seat;
        int Seat;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter row letter (A-D): ");
            String letter = scanner.next().toUpperCase();
            // Checking the input for row is a single character between A and D
            if (letter.length() == 1 && letter.charAt(0) >= 'A' && letter.charAt(0) <= 'D') {
                row = letter.charAt(0);
                //
                Seat = (row == 'B' || row == 'C') ? 12 : 14;
                boolean isValidSeat = false;
                while (!isValidSeat) {
                    System.out.print("Enter seat number (1-" + Seat + "): ");
                    if (scanner.hasNextInt()) {
                        seat = scanner.nextInt();
                        if (seat >= 1 && seat <= Seat) {
                            int rowIndex = row - 'A';
                            if (Structure[rowIndex][seat - 1] != 'X') {
                                System.out.println("Seat is already available. No need to cancel.");
                            } else {
                                Structure[rowIndex][seat - 1] = '_';
                                System.out.println("Seat " + row + "-" + seat + " has been successfully canceled.");
                                isValidInput = true;
                                isValidSeat = true;
                                // Remove the corresponding ticket and delete the ticket file
                                for (int i = 0; i < ticketCount; i++) {
                                    if (ticketsArray[i] != null && ticketsArray[i].getRow() == row && ticketsArray[i].getSeat() == seat) {
                                        ticketsArray[i].deleteFile();
                                        ticketsArray[i] = null;
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Invalid seat number. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                    }
                }
            } else {

                System.out.println("Invalid row letter. Please enter a letter between A-D.");
            }

        }
    }

    //Find available seat-option3

    /**
     * Finds and displays the first available seat in the seating plan.
     * If an available seat is found, it prints its row and seat number.
     * If no available seats are found, it notifies the user.
     */
    private static void firstAvailableSeat() {
        System.out.println("Find First available seat option selected");
        System.out.println(" ");
        for (int i = 0; i < Structure.length; i++) {
            for (int j = 0; j < rowColumnCounts[i]; j++) {
                if (Structure[i][j] != 'X') {
                    char row = (char) ('A' + i);
                    int seat = j + 1;
                    System.out.println("First available seat is: " + row + "-" + seat);
                    return;
                }
            }
        }
        System.out.println("Sorry, no available seats.");
    }

    //Show seating plan - option4

    /**
     * Displays the seating plan, showing the booked (X) and available (O) seats.
     */
    private static void showSeatingPlan() {
        System.out.println("Show Seating plan option selected");
        for (int i = 1; i <= 14; i++) {
            System.out.print(" ");
        }
        System.out.println();

        for (int i = 0; i < Structure.length; i++) {
            for (int j = 0; j < rowColumnCounts[i]; j++) {
                System.out.print(Structure[i][j] == 'X' ? 'X' : 'O');
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println(" ");
    }

    // Search Ticket option5

    /**
     * Searches for a ticket based on the specified row and seat number.
     * If the ticket is found, it prints the ticket and person information.
     * If the ticket is not found, it notifies the user that the seat is available.
     */
    private static void searchTicket() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search for a ticket option selected");
        char row;
        int seat;
        while (true) {
            System.out.print("Enter row letter (A-D): ");
            String rowInput = scanner.next().toUpperCase();
            if (rowInput.length() == 1 && rowInput.charAt(0) >= 'A' && rowInput.charAt(0) <= 'D') {
                row = rowInput.charAt(0);
                break;
            } else {
                System.out.println("Invalid row letter. Please enter a letter between A-D.");
            }
        }
        int maxSeat = (row == 'B' || row == 'C') ? 12 : 14;
        while (true) {
            System.out.print("Enter seat number (1-" + maxSeat + "): ");
            if (scanner.hasNextInt()) {
                seat = scanner.nextInt();
                if (seat >= 1 && seat <= maxSeat) {
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a number between 1 and " + maxSeat + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        boolean found = false;
        for (int i = 0; i < ticketCount; i++) {
            if (ticketsArray[i] != null && ticketsArray[i].getRow() == row && ticketsArray[i].getSeat() == seat) {
                // If the ticket is found, print the ticket and person information
                System.out.println("Ticket and Person information:");
                ticketsArray[i].printTicketsInfo();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("This seat is available.");
        }
    }
    //Print ticket information option6
    /**
     * Prints information for each booked ticket and calculates the total price of all tickets.
     */
    private static void printTicketInfo() {
        int totalPrice = 0;
        for (Ticket ticket : ticketsArray) {
            if (ticket != null && ticket.getPerson() != null) {
                System.out.println();
                ticket.printTicketsInfo();
                totalPrice += ticket.getPrice();
            }
        }
        System.out.println();
        System.out.println("Total tickets price : £" + totalPrice);
    }
}

















