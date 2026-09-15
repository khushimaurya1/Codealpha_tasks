# Hotel Reservation System

A Java-based console application for managing hotel room reservations, bookings, and customer information.

## Features

- **Room Management**: View all hotel rooms with their categories, prices, and availability status
- **Room Search**: Search for available rooms by category (Standard, Deluxe, Suite)
- **Booking System**: 
  - Make new reservations with customer details
  - Automatic payment processing simulation
  - Unique booking ID generation for each reservation
- **Reservation Cancellation**: Cancel existing bookings and release rooms
- **Booking Details**: View detailed information for specific bookings
- **Persistent Storage**: Save and load bookings from file for data persistence

## Project Structure

```
HotelReservationSystem.java  - Main application file containing:
                               - Room class: Represents a hotel room
                               - Reservation class: Represents a booking
                               - Hotel class: Manages rooms and reservations
                               - HotelReservationSystem class: Main menu interface
```

## Room Categories & Pricing

| Category | Room Numbers | Price per Night |
|----------|-------------|-----------------|
| Standard | 101-105     | ₹2,000         |
| Deluxe   | 201-205     | ₹3,500         |
| Suite    | 301-305     | ₹6,000         |

**Total Rooms**: 15 rooms (5 of each category)

## Compilation & Execution

### Compile
```bash
javac HotelReservationSystem.java
```

### Run
```bash
java HotelReservationSystem
```

## How to Use

### Main Menu Options

1. **View All Rooms** - Displays all hotel rooms with their current status (Available/Booked)
2. **Search Available Rooms** - Find available rooms in a specific category
3. **Book a Room** - Make a new reservation
   - Enter customer name and phone number
   - Select room category
   - Choose room from available options
   - Enter number of nights
   - Confirm payment
4. **Cancel Reservation** - Cancel an existing booking using Booking ID
5. **View Booking Details** - Retrieve details of a specific booking using Booking ID
6. **View All Bookings** - Display all active reservations
7. **Exit** - Close the application

## Data Persistence

- Bookings are automatically saved to `hotel_bookings.txt` file
- Previous bookings are loaded when the application starts
- Booking data includes: Booking ID, Customer Name, Phone Number, Room Number, Room Category, Amount Paid, and Payment Status

## Booking ID Format

Booking IDs start from **1001** and increment with each new reservation.

## Technical Details

### File Format
Bookings are stored in `hotel_bookings.txt` with pipe-separated values:
```
BookingID|CustomerName|PhoneNumber|RoomNumber|RoomCategory|Amount|PaymentStatus
```

### Key Classes

- **Room**: Manages room properties (number, category, price, availability)
- **Reservation**: Handles booking information and serialization
- **Hotel**: Core business logic for room and reservation management
- **HotelReservationSystem**: Main application with user interface

## Notes

- The system prevents double-booking by marking rooms as unavailable
- Payment processing includes a 1-second simulation delay
- The application validates all user inputs
- Rooms must match their category when booking