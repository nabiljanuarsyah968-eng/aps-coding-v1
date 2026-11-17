# Leave Schedule and Flight Ticket System (Version 1 - Partial)

A console-based Java application generated through vibe coding, based on a set of user stories.
This is the **first submission (partially completed version)**.

## Features

Implemented:
- View employee leave schedules by year and month (HR manager)
- Search flight schedules by one or multiple destinations
- Book a flight ticket

Not implemented yet (marked with `TODO` in the code):
- Employee leave history and remaining leave balance
- Cancel a ticket booking
- Multi-destination search for frequent traveller members
- Member profile update and ticket validation
- Flight price comparison
- Booking status filter for travel managers
- Ticket authorization using a booking reference
- Visitor tickets and e-ticket delivery

## Requirements

- Java JDK 17 or later

## How to Run

```
javac Main.java
java Main
```

Then choose a menu number from the console.

## Notes

- All data is stored in memory, so it resets every time the program restarts.
- No database or external library is used.
