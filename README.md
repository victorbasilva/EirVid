# ÉirVid Movie Rental System

## Introduction
The ÉirVid Movie Rental System is a collaborative academic project designed for a fictional video streaming platform. The main focus is implementing a Java-based movie rental system, exemplifying the integration of software design and development skills learned in class - Algorithms, Architecture and Design Patterns & Object Oriented Analysis and Design Modules. The system is conceptualized to simulate real-world streaming and rental functionalities, providing a practical context for applying theoretical knowledge in software engineering.

## Key Features
- **Account Management**: Allows users to create and log into their accounts using an email and a password.
- **Movie Rental**: Enables users to rent movies, with details and pricing provided via a CSV file.
- **Rental Tracking**: Tracks each user's movie rentals.
- **Simple Billing**: Outputs the customer's details, movie title, and price to the console upon renting a movie.
- **Recommendation Engine**: Recommends the top 5 most rented movies of the past 5 minutes.

## Note
- **No GUI**: The prototype operates without a Graphical User Interface (GUI), with all interactions occurring through the console.

## Team Members
- Victor Batista - [victorbasilva](https://github.com/victorbasilva)
- Alessandra Caballero - [missalexityy](https://github.com/missalexityy)
- Joelma Rodrigues - [joelmarodrigues](https://github.com/joelmarodrigues)
- Rafael Rosa - [rafael30121987](https://github.com/rafael30121987)

## Prerequisites
Before setting up the project, ensure you have the following installed:
- Java Development Kit (JDK) 20 (NetBeans 20 is recommended but NetBeans 19 is also compatible)
- NetBeans IDE (version 20 or 19)

## Libraries Used
This project makes use of several libraries:
- `opencsv-5.9.jar`: An open-source CSV parser library for Java.
- `commons-lang3-3.12.0.jar`: Provides extra functionality for classes in `java.lang`.
- `h2-2.2.224.jar`: A lightweight Java database that is used for the project's data persistence.

Make sure these libraries are included in your project's classpath.

## Setup Instructions
1. Clone the repository or download the source code.
2. Open the project in NetBeans IDE.
3. Add the above libraries to your project's library folder if they are not already included.
4. In `ReadCSVFile.java`, update the path to the `MoviesEirVid.csv` file to match your local setup.
5. Compile and run `EirVidMaster.java` from the NetBeans IDE.

## Configuration
Some parts of the application may require configuration. Here's what you need to know:
- **Database Connection**: Ensure that the H2 database is set up correctly and that the application has the correct credentials to access it.
- **CSV File Path**: Update the CSV file path in `ReadCSVFile.java` to where you have stored the `MoviesEirVid.csv` file locally.

## Latest Release
You can download the latest release of the ÉirVid Movie Rental System from [ÉirVid V1.0.0](https://github.com/victorbasilva/EirVid/releases).

## Video Demonstration
For a detailed walkthrough and demonstration of the ÉirVid Movie Rental System, please view our video demo at [this link](https://www.yourvideodemonstrationlink.com).

## License
This project is licensed under the MIT License. See the `LICENSE` file in the project root for full license text.
