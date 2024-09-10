
//Ticket Class

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(char row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public char getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    void printTicketsInfo() {
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        person.printPersonInfo();
    }

    /**
     * Saves the ticket information to a file.
     * The file name is constructed using the row and seat number.
     */
    public void save() {
        String filename = row + String.valueOf(seat) + ".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            // Writing ticket information to the file
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price: £" + price + "\n");
            writer.write("Person: " + person.getName() + " " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();
            System.out.println("Ticket information saved to " + filename);
        } catch (
                IOException e) { // Catching IO exception ( indicates a problem while performing Input/Output (I/O) operations.)
            System.out.println("An error occurred while saving the ticket information.");
        }
    }

    /**
     * Deletes the ticket file information
     * when the user enters a row and a seat number
     */
    public void deleteFile() {
        String filename = row + String.valueOf(seat) + ".txt";
        File file = new File(filename);
        // Check if the file exists
        if (file.exists()) {
            // Delete the file if it exists
            if (file.delete()) {
                System.out.println("Ticket file " + filename + " deleted successfully.");
            } else {
                System.out.println("Failed to delete ticket file " + filename);
            }
        } else {
            System.out.println("Ticket file " + filename + " does not exist.");
        }
    }
}








