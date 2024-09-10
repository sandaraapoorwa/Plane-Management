Plane Management Application

A console-based Java program for managing plane seat bookings.

## Features:

1. **Buy a Seat**: 
   - Select a seat by row (A-D) and seat number.
   - Validates and books the seat if available.
   - Stores user info (name, surname, email) and applies discounts.

2. **Cancel a Seat**: 
   - Cancel an existing booking by row and seat number.

3. **Find First Available Seat**: 
   - Displays the first available seat.

4. **Show Seating Plan**: 
   - Displays the seating layout (booked and available seats).

5. **Print Ticket Info**: 
   - Displays booked tickets and total sales.

6. **Search Ticket**: 
   - Search for a ticket by row and seat number.

---

## How to Use:

Run the program and follow the menu prompts to manage seat bookings.



#Person Class  🧑
The Person class represents an individual with basic personal information (name, surname, and email) in the Plane Management System.

Features:
Fields:

name: Stores the person's first name.
surname: Stores the person's surname.
email: Stores the person's email address.
Methods:

Constructor: Initializes the Person object with name, surname, and email.
Getters/Setters: Access and modify the fields (name, surname, email).
printPersonInfo(): Prints the person's name, surname, and email to the console.
How to Use:
Create a new Person object by passing the required details (name, surname, email).
Use the provided methods to access or modify the details as needed.
Call printPersonInfo() to display the stored information.



#Ticket Class 🎫
The Ticket class represents a ticket for a seat in the Plane Management System, associating a person with a seat and price.

Features:
Fields:

row: Represents the seat's row (A-D).
seat: The seat number.
price: The price of the seat.
person: A Person object representing the ticket holder.
Methods:

Constructor: Initializes a Ticket with row, seat number, price, and a Person.
Getters/Setters: Access and modify ticket details (row, seat, price, person).
printTicketsInfo(): Prints detailed ticket and person information to the console.
save(): Saves the ticket information to a text file named after the row and seat (e.g., A1.txt).
deleteFile(): Deletes the file corresponding to the ticket if it exists.
How to Use:
Create a Ticket object by passing row, seat number, price, and a Person.
Use save() to store the ticket details to a file and deleteFile() to remove the ticket file if needed.
Call printTicketsInfo() to display ticket details along with the person’s information.
