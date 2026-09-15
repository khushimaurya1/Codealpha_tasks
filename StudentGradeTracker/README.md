# Student Grade Tracker

A simple Java console application for tracking student grades, computing academic summaries, and generating a report for multiple students.

## Features

- Add multiple students
- Enter the number of subjects and grades for each student
- Validate grades between 0 and 100
- Calculate:
  - average grade
  - highest grade
  - lowest grade
- Display a summary report for all students

## Project Structure

- `StudentGradeTracker.java` - contains the full program logic

## Requirements

- Java JDK 8 or later
- A terminal or command prompt

## How to Run

1. Open a terminal in the project folder.
2. Compile the Java file:

```bash
javac StudentGradeTracker.java
```

3. Run the application:

```bash
java StudentGradeTracker
```

## Example

The program will prompt for:

- number of students
- each student's name
- number of subjects
- grades for each subject

It then prints a summary table like:

```text
Student Name         Grades               Average      Highest      Lowest
--------------------------------------------------------------------------
Alice                [90.0, 85.0, 95.0]   90.00        95.00        85.00
Bob                  [78.0, 88.0]         83.00        88.00        78.00
```

## Notes

- Invalid grades are rejected until a value between 0 and 100 is entered.
- The program uses a simple command-line interface and stores student data only in memory during execution.
