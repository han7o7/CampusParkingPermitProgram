Campus Parking Permit Program

Author: Atahan Ors
Course: IT Programming

Overview
This project is a console based Java application that calculates the cost of university parking permits.

Features
- Resident and Commuter permits
- Vehicle modifiers (Car, SUV, Motorcycle)
- Optional carpool discount
- Monthly permit length from 1 to 12 months
- Campus fee calculation
- Itemized receipt output

Object Oriented Design
The program demonstrates:

Interface and Polymorphism
PricingStrategy interface implemented by:
- ResidentPricingStrategy
- CommuterPricingStrategy

RateModifier interface implemented by:
- VehicleType enum
- CarpoolDiscount class

Encapsulation
Domain logic is separated from the user interface.

Error Handling
Validation is handled in domain classes using InvalidSelectionException.

Testing
JUnit tests verify pricing calculations and polymorphic behavior.
