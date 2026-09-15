import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// =====================================================
// ROOM CLASS
// =====================================================
class Room {

    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayRoom() {

        System.out.printf(
                "%-10d %-15s ₹%-12.2f %-12s%n",
                roomNumber,
                category,
                price,
                available ? "Available" : "Booked"
        );
    }
}


// =====================================================
// RESERVATION CLASS
// =====================================================
class Reservation {

    private int bookingId;
    private String customerName;
    private String phoneNumber;
    private int roomNumber;
    private String roomCategory;
    private double amount;
    private String paymentStatus;

    public Reservation(
            int bookingId,
            String customerName,
            String phoneNumber,
            int roomNumber,
            String roomCategory,
            double amount,
            String paymentStatus) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void displayBooking() {

        System.out.println("\n======================================");
        System.out.println("          BOOKING DETAILS");
        System.out.println("======================================");

        System.out.println("Booking ID      : " + bookingId);
        System.out.println("Customer Name   : " + customerName);
        System.out.println("Phone Number    : " + phoneNumber);
        System.out.println("Room Number     : " + roomNumber);
        System.out.println("Room Category   : " + roomCategory);
        System.out.printf("Amount Paid     : ₹%.2f%n", amount);
        System.out.println("Payment Status  : " + paymentStatus);

        System.out.println("======================================");
    }

    // Convert reservation to file format
    public String toFileString() {

        return bookingId + "|" +
                customerName + "|" +
                phoneNumber + "|" +
                roomNumber + "|" +
                roomCategory + "|" +
                amount + "|" +
                paymentStatus;
    }

    // Create reservation from file
    public static Reservation fromFileString(String data) {

        String[] parts = data.split("\\|");

        return new Reservation(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Integer.parseInt(parts[3]),
                parts[4],
                Double.parseDouble(parts[5]),
                parts[6]
        );
    }
}


// =====================================================
// HOTEL CLASS
// =====================================================
class Hotel {

    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    private final String FILE_NAME =
            "hotel_bookings.txt";

    private int nextBookingId = 1001;

    public Hotel() {

        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        initializeRooms();
        loadBookings();
    }

    // -------------------------------------------------
    // Initialize hotel rooms
    // -------------------------------------------------
    private void initializeRooms() {

        // Standard rooms
        for (int i = 101; i <= 105; i++) {

            rooms.add(
                    new Room(
                            i,
                            "Standard",
                            2000
                    )
            );
        }

        // Deluxe rooms
        for (int i = 201; i <= 205; i++) {

            rooms.add(
                    new Room(
                            i,
                            "Deluxe",
                            3500
                    )
            );
        }

        // Suite rooms
        for (int i = 301; i <= 305; i++) {

            rooms.add(
                    new Room(
                            i,
                            "Suite",
                            6000
                    )
            );
        }
    }

    // -------------------------------------------------
    // Search rooms
    // -------------------------------------------------
    public void searchRooms(String category) {

        boolean found = false;

        System.out.println("\n==============================================");
        System.out.println("             AVAILABLE ROOMS");
        System.out.println("==============================================");

        System.out.printf(
                "%-10s %-15s %-15s %-12s%n",
                "Room",
                "Category",
                "Price/Night",
                "Status"
        );

        System.out.println(
                "----------------------------------------------------"
        );

        for (Room room : rooms) {

            if (room.getCategory()
                    .equalsIgnoreCase(category)
                    && room.isAvailable()) {

                room.displayRoom();
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No available rooms in this category."
            );
        }
    }

    // -------------------------------------------------
    // Display all rooms
    // -------------------------------------------------
    public void displayAllRooms() {

        System.out.println("\n==============================================");
        System.out.println("                 HOTEL ROOMS");
        System.out.println("==============================================");

        System.out.printf(
                "%-10s %-15s %-15s %-12s%n",
                "Room",
                "Category",
                "Price/Night",
                "Status"
        );

        System.out.println(
                "----------------------------------------------------"
        );

        for (Room room : rooms) {

            room.displayRoom();
        }
    }

    // -------------------------------------------------
    // Find room
    // -------------------------------------------------
    private Room findRoom(int roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber) {

                return room;
            }
        }

        return null;
    }

    // -------------------------------------------------
    // Make reservation
    // -------------------------------------------------
    public void makeReservation(Scanner scanner) {

        scanner.nextLine();

        System.out.println("\n======================================");
        System.out.println("          ROOM RESERVATION");
        System.out.println("======================================");

        System.out.print(
                "Enter customer name: "
        );

        String name = scanner.nextLine();

        System.out.print(
                "Enter phone number: "
        );

        String phone = scanner.nextLine();

        System.out.println("\nSelect room category:");

        System.out.println("1. Standard - ₹2000/night");
        System.out.println("2. Deluxe   - ₹3500/night");
        System.out.println("3. Suite    - ₹6000/night");

        System.out.print("Enter choice: ");

        int categoryChoice =
                scanner.nextInt();

        String category;

        switch (categoryChoice) {

            case 1:
                category = "Standard";
                break;

            case 2:
                category = "Deluxe";
                break;

            case 3:
                category = "Suite";
                break;

            default:
                System.out.println(
                        "Invalid category!"
                );
                return;
        }

        searchRooms(category);

        System.out.print(
                "\nEnter room number: "
        );

        int roomNumber =
                scanner.nextInt();

        Room room =
                findRoom(roomNumber);

        if (room == null) {

            System.out.println(
                    "Room does not exist!"
            );

            return;
        }

        if (!room.isAvailable()) {

            System.out.println(
                    "Sorry, this room is already booked."
            );

            return;
        }

        if (!room.getCategory()
                .equalsIgnoreCase(category)) {

            System.out.println(
                    "Selected room does not belong "
                    + "to the selected category."
            );

            return;
        }

        System.out.print(
                "Enter number of nights: "
        );

        int nights =
                scanner.nextInt();

        if (nights <= 0) {

            System.out.println(
                    "Number of nights must be greater than zero."
            );

            return;
        }

        double totalAmount =
                room.getPrice() * nights;

        System.out.println(
                "\nTotal amount: ₹"
                + totalAmount
        );

        System.out.println(
                "Proceed with payment?"
        );

        System.out.println("1. Yes");
        System.out.println("2. No");

        System.out.print("Enter choice: ");

        int paymentChoice =
                scanner.nextInt();

        if (paymentChoice != 1) {

            System.out.println(
                    "Booking cancelled."
            );

            return;
        }

        // Payment simulation
        System.out.println(
                "\nProcessing payment..."
        );

        try {

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Payment successful!"
        );

        int bookingId =
                nextBookingId++;

        Reservation reservation =
                new Reservation(
                        bookingId,
                        name,
                        phone,
                        roomNumber,
                        room.getCategory(),
                        totalAmount,
                        "PAID"
                );

        reservations.add(reservation);

        room.setAvailable(false);

        saveBookings();

        System.out.println(
                "\n======================================"
        );

        System.out.println(
                "       BOOKING CONFIRMED!"
        );

        System.out.println(
                "======================================"
        );

        System.out.println(
                "Booking ID: " + bookingId
        );

        System.out.println(
                "Room Number: " + roomNumber
        );

        System.out.printf(
                "Amount Paid: ₹%.2f%n",
                totalAmount
        );

        System.out.println(
                "======================================"
        );
    }

    // -------------------------------------------------
    // Cancel reservation
    // -------------------------------------------------
    public void cancelReservation(
            Scanner scanner) {

        System.out.print(
                "\nEnter Booking ID: "
        );

        int bookingId =
                scanner.nextInt();

        Reservation reservation =
                findReservation(bookingId);

        if (reservation == null) {

            System.out.println(
                    "Booking not found!"
            );

            return;
        }

        Room room =
                findRoom(
                        reservation.getRoomNumber()
                );

        if (room != null) {

            room.setAvailable(true);
        }

        reservations.remove(reservation);

        saveBookings();

        System.out.println(
                "\nBooking cancelled successfully."
        );

        System.out.println(
                "Room "
                + reservation.getRoomNumber()
                + " is now available."
        );
    }

    // -------------------------------------------------
    // Find reservation
    // -------------------------------------------------
    private Reservation findReservation(
            int bookingId) {

        for (Reservation reservation :
                reservations) {

            if (reservation.getBookingId()
                    == bookingId) {

                return reservation;
            }
        }

        return null;
    }

    // -------------------------------------------------
    // View booking
    // -------------------------------------------------
    public void viewBooking(
            Scanner scanner) {

        System.out.print(
                "\nEnter Booking ID: "
        );

        int bookingId =
                scanner.nextInt();

        Reservation reservation =
                findReservation(bookingId);

        if (reservation == null) {

            System.out.println(
                    "Booking not found!"
            );

            return;
        }

        reservation.displayBooking();
    }

    // -------------------------------------------------
    // Display all bookings
    // -------------------------------------------------
    public void displayAllBookings() {

        if (reservations.isEmpty()) {

            System.out.println(
                    "\nNo active bookings."
            );

            return;
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "             ACTIVE BOOKINGS"
        );

        System.out.println(
                "=============================================="
        );

        for (Reservation reservation :
                reservations) {

            reservation.displayBooking();
        }
    }

    // -------------------------------------------------
    // Save bookings to file
    // -------------------------------------------------
    private void saveBookings() {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME);

            for (Reservation reservation :
                    reservations) {

                writer.write(
                        reservation.toFileString()
                        + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving bookings: "
                    + e.getMessage()
            );
        }
    }

    // -------------------------------------------------
    // Load bookings from file
    // -------------------------------------------------
    private void loadBookings() {

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {

            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine())
                    != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                Reservation reservation =
                        Reservation.fromFileString(line);

                reservations.add(reservation);

                // Mark room as booked
                Room room =
                        findRoom(
                                reservation.getRoomNumber()
                        );

                if (room != null) {

                    room.setAvailable(false);
                }

                // Update booking ID
                if (reservation.getBookingId()
                        >= nextBookingId) {

                    nextBookingId =
                            reservation.getBookingId()
                            + 1;
                }
            }

            reader.close();

        } catch (IOException |
                 NumberFormatException e) {

            System.out.println(
                    "Error loading bookings: "
                    + e.getMessage()
            );
        }
    }
}


// =====================================================
// MAIN CLASS
// =====================================================
public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        Hotel hotel =
                new Hotel();

        int choice;

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "        WELCOME TO JAVA HOTEL SYSTEM"
        );

        System.out.println(
                "=============================================="
        );

        do {

            System.out.println(
                    "\n=============================================="
            );

            System.out.println(
                    "                 MAIN MENU"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "1. View All Rooms"
            );

            System.out.println(
                    "2. Search Available Rooms"
            );

            System.out.println(
                    "3. Book a Room"
            );

            System.out.println(
                    "4. Cancel Reservation"
            );

            System.out.println(
                    "5. View Booking Details"
            );

            System.out.println(
                    "6. View All Bookings"
            );

            System.out.println(
                    "7. Exit"
            );

            System.out.print(
                    "\nEnter your choice: "
            );

            choice =
                    scanner.nextInt();

            switch (choice) {

                case 1:

                    hotel.displayAllRooms();

                    break;

                case 2:

                    scanner.nextLine();

                    System.out.println(
                            "\nSelect category:"
                    );

                    System.out.println(
                            "1. Standard"
                    );

                    System.out.println(
                            "2. Deluxe"
                    );

                    System.out.println(
                            "3. Suite"
                    );

                    System.out.print(
                            "Enter choice: "
                    );

                    int searchChoice =
                            scanner.nextInt();

                    String category;

                    if (searchChoice == 1) {

                        category = "Standard";

                    } else if (searchChoice == 2) {

                        category = "Deluxe";

                    } else if (searchChoice == 3) {

                        category = "Suite";

                    } else {

                        System.out.println(
                                "Invalid choice!"
                        );

                        break;
                    }

                    hotel.searchRooms(category);

                    break;

                case 3:

                    hotel.makeReservation(scanner);

                    break;

                case 4:

                    hotel.cancelReservation(scanner);

                    break;

                case 5:

                    hotel.viewBooking(scanner);

                    break;

                case 6:

                    hotel.displayAllBookings();

                    break;

                case 7:

                    System.out.println(
                            "\nThank you for using "
                            + "Java Hotel Reservation System!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }

        } while (choice != 7);

        scanner.close();
    }
}