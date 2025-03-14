#Overview
This project is a Point of Sale (POS) system built using Java, designed for managing orders, products, and staff in a restaurant or cafe environment. The system provides a user-friendly Graphical User Interface (GUI) to facilitate interactions with the core functions, such as placing orders, generating receipts, and managing menu items.

#Features
Order Management: Allows users to create, modify, and delete orders.
Product Catalog: Manage food and beverage items with unique IDs and categories.
Staff Management: Handles staff-related functionalities.
ID Generation: Automated order and product ID generation.
POS Interface: Displays a dynamic menu with the ability to handle table orders.
Database Connectivity: Supports interaction with a backend database for persistence (via Connect.java).

#Project Structure
src/main/java: Contains the main Java classes for the application.

App.java: Main entry point of the application.
MenuDisplay.java: Handles the GUI representation of the menu.
Orders.java, Product.java: Core classes for managing orders and products.
OrderIdGenerator.java, ProductIdGenerator.java: Utility classes for generating unique IDs.
Connect.java: Handles database connections.
src/main/resources: Includes external resources like CSS styles and images.

#Requirements
Java 8+
Maven (build tool)
Database: MySQL or any compatible relational database
