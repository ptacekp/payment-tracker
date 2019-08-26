# Payment Tracker

Payment Tracker is a simple CLI application that allows user to track payments with in different currency.

# Getting started
## Prerequisites
Before you start building the application, you need to:
 * Install the latest version of [Java](https://java.com)
 * Donwload and unpact latest version of [Maven](https://maven.apache.org/download.html).
 * Set the environment variable `JAVA_HOME`.
 * Set you favorite IDE.

## Installing
Once you have installed and configured our environment, you can build the application.

Download or fork this project from GitHub repository and jump to the project director.
```
$ cd payment-tracker
```
In this directory run maven installation goal:
```
$ mvn clean install
```
This will build the application and create also one executable jar package.

## Running the application
After installation move to the target directory:
```
$ cd target
```
and start the application
```
$ java -jar payment-tracker-1.0-SNAPSHOT-with-dependencies.jar
```

# How to use
When you start the application and welcome screen shows, you can enter commands or payment directly.

## Startup parameters
You can start the application without any parameter or use one of this: 
 * `-h` - prints help message and quit the application.
 * `-v` - prints version description of the application and quit the application.
 * `-f` - load payments from text file. You have to specify full file path.
Every other startup parameter are invalid. In that case the application prints help message and quits.
 
## Commands
The application command line interface read every user input. Possible commands are:
 * `add` - add new payment. You have to enter payment in right format. Payment format is three capital letters, space and decimal number.
 * `help` - prints help message.
 * `version` - prints version description of the application.
 * `load` - load payments from text file. You have to specify full file path.
 * `quit` - end the application.

You can also enter single payment directly in right format. If you insert payment in wrong format or enter any other command, the application will print Invalid command message to the error output. This does not terminate the application. 

# License
Payment Tracker application is distributed under MIT license.