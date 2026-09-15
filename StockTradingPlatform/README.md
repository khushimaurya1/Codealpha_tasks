# Stock Trading Platform

A simple Java-based stock trading console application that lets a user:

- view available market stocks
- buy and sell shares
- track portfolio value and profit/loss
- view transaction history
- save portfolio data to a file

## Features

- Market data display for listed stocks
- Buy stock with balance validation
- Sell stock from the user's portfolio
- Portfolio summary with invested value, current value, and profit/loss
- Transaction history log
- Save portfolio and transaction data to `portfolio.txt`

## Project Structure

- `StockTradingPlatform.java` - main application logic
- `portfolio.txt` - generated when the user saves portfolio data

## Requirements

- Java JDK 8 or later
- Command line/terminal

## How to Run

1. Open a terminal in the project folder.
2. Compile the program:

```bash
javac StockTradingPlatform.java
```

3. Run the program:

```bash
java StockTradingPlatform
```

## Example Usage

After launching the app, the menu allows you to:

1. Display Market Data
2. Buy Stock
3. Sell Stock
4. View Portfolio
5. Transaction History
6. Save Portfolio
7. Exit

You will be prompted to enter your name and then choose actions from the menu.

## Notes

- The application starts with a sample user balance of ₹100000.
- Stock symbols supported by default include:
  - RELIANCE
  - TCS
  - INFY
  - HDFC
  - ITC

## Author

This project is a beginner-friendly Java stock trading simulation created for learning object-oriented programming concepts.
