package applicationclasses;

import java.util.*;


import static applicationclasses.Date.*;

import static applicationclasses.ServiceProvider.logger;
import static applicationclasses.User.*;
import static Main.ProductionCode.*;



public class Operations {
    public static final String SEPARATOR = "------------------------------------------------------";


    public static boolean createAccountPage()
    {

        logger.info("Enter your Email:");
        String email = input.nextLine();
        logger.info("Enter your username:");
        String username = input.nextLine();
        logger.info("Enter your Gender : ");
        String gen = input.nextLine();
        logger.info("Enter your Phone number:");
        String phnum = input.nextLine();
        logger.info("Enter your Address:");
        String address = input.nextLine();
        logger.info("Enter your Password:");
        String password = input.nextLine();

        User r = new User(username, password, address, phnum, email, gen);
        boolean create = addUser(r);
        if (create) {
            logger.info("A new account was created successfully");
            Logging.getQ().put(email, password);
        }
        else
            logger.info("This account already exists");
        logger.info(SEPARATOR);



        homePage();

        return create;
    }
    public static boolean addUser(User c) {
        for (User user : allUsers) {
            if (c.getEmail().equals(user.getEmail()) ||
                    (c.getUsername().equals(user.getUsername()) &&
                            c.getAddress().equals(user.getAddress()) &&
                            c.getPhone().equals(user.getPhone()))) {
                return false;
            }
        }
        allUsers.add(c);
        return true;
    }



    public static void changeUserInformation() {
        logger.info("\nAll User Accounts:");
        List<User> allUsers = User.getUserList();
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            logger.info((i + 1) + ". Username: " + user.getUsername() + ", Email: " + user.getEmail());
        }

        logger.info("Enter the number corresponding to the user you want to update (enter '0' to go back to the menu):");
        int userNumberToUpdate = input.nextInt();


        input.nextLine();

        if (userNumberToUpdate == 0) {
            return;
        }

        if (userNumberToUpdate < 1 || userNumberToUpdate > allUsers.size()) {
            logger.info("Invalid user number. Please enter a valid number.");
            return;
        }


        User userToUpdate = allUsers.get(userNumberToUpdate - 1);

        logger.info("\nUser found. Update information:");

        logger.info("Enter new username:");
        String newUsername = input.nextLine().trim();
        userToUpdate.setUsername(newUsername);

        logger.info("Enter new password:");
        String newPassword = input.nextLine().trim();
        userToUpdate.setPassword(newPassword);

        logger.info("User information updated successfully.");
    }





    public static void addNewUser() {
        logger.info("\nEnter new user details:");

        try {
            logger.info("Email:");
            String email = input.next();

            boolean added = addUserCheck(email);
            if (added) {

                logger.info("Username:");
                String username = input.next();

                logger.info("Password:");
                String password = input.next();

                logger.info("Address:");
                String address = input.next().trim();

                logger.info("Phone:");
                String phone = input.next();

                logger.info("Gender:");
                String gender = input.next();

                User newUser = new User(username, password, address, phone, email, gender);
                User.getUserList().add(newUser);
                logger.info("User added successfully.");
            } else {
                logger.info("Failed to add user. User already exists.");
            }
        } catch (Exception e) {
            logger.severe("An error occurred while adding a new user: " + e.getMessage());
        }
    }


    public static boolean addUserCheck(String email) {

        if (getUserByEmail(email) != null) {

            return false;
        }

        return true;
    }



    public static void seeAllUsers() {
        logger.info("\nAll User Accounts:");

        Collections.sort(allUsers, Comparator.comparing(User::getUsername));

        for (User user : allUsers) {
            StringBuilder userDetails = new StringBuilder();
            userDetails.append("Username: ").append(user.getUsername()).append(", ");
            userDetails.append("Email: ").append(user.getEmail()).append(", ");
            logger.info(userDetails.toString());
        }
    }


    public static void deleteAccount() {
        logger.info("\nAll User Accounts:");
        List<User> allUsers = User.getUserList();
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            logger.info((i + 1) + ". Username: " + user.getUsername() + ", Email: " + user.getEmail());
        }

        logger.info("Enter the number of the user you want to delete, or enter '0' to go back to the menu:");
        int userNumber = input.nextInt();


        if (userNumber == 0) {
            logger.info("Returning to the admin menu.");
            return;
        }

        if (userNumber < 1 || userNumber > allUsers.size()) {
            logger.info("Invalid user number. Please enter a valid number.");
            return;
        }


        User userToDelete = allUsers.get(userNumber - 1);


        boolean deleted = deleteUserByEmail(userToDelete.getEmail());
        if (deleted) {
            logger.info("User account deleted successfully.");
        } else {
            logger.info("User not found.");
        }
    }

    public static boolean deleteUserByEmail(String email) {
        User userToDelete = getUserByEmail(email);
        if (userToDelete != null) {

            User.getUserList().remove(userToDelete);

            return true;
        } else {
            return false;
        }
    }


    public static void addEvent() {
        Scanner scanner = new Scanner(System.in);

        logger.info("\nEnter the name of the event:");
        String name = scanner.nextLine();

        int price = 0;
        boolean validPriceInput = false;
        while (!validPriceInput) {
            try {
                logger.info("Enter the price of the total service:");
                price = Integer.parseInt(scanner.nextLine());
                validPriceInput = true;
            } catch (NumberFormatException e) {
                logger.info("Invalid input. Please enter a valid integer for price.");
            }
        }

        int availability = 0;
        boolean validAvailabilityInput = false;
        while (!validAvailabilityInput) {
            try {
                logger.info("Enter the number of available venue:");
                availability = Integer.parseInt(scanner.nextLine());
                validAvailabilityInput = true;
            } catch (NumberFormatException e) {
                logger.info("Invalid input. Please enter a valid integer for availability.");
            }
        }

        logger.info("Enter a description for the hall:");
        String description = scanner.nextLine();

        logger.info("Enter a location for the event:");
        String location = scanner.nextLine();

        int time = 0;
        boolean validTimeInput = false;
        while (!validTimeInput) {
            try {
                logger.info("Enter the time the event will happen at:");
                time = Integer.parseInt(scanner.nextLine());
                validTimeInput = true;
            } catch (NumberFormatException e) {
                logger.info("Invalid input. Please enter a valid integer for time.");
            }
        }

        logger.info("Enter a theme for the event:");
        String theme = scanner.nextLine();

        Event newEvent = new Event(name, price, availability, description, location, time);
        Event.addEvent(newEvent);

        logger.info("Event added successfully.");
    }






    public static void listAllEvents() {
        List<Event> allEvents = Event.getAllEvents();

        if (allEvents.isEmpty()) {
            logger.info("\nNo events available.");
        } else {
            logger.info("\nList of all events:");
            for (Event event : allEvents) {
                String eventDetails = "Event Name: " + event.getEventName() + ", " +
                        "Price: " + event.getPrice() + ", " +
                        "Availability: " + event.getAvailability() + ", " +
                        "Description: " + event.getDescription() + ", " +
                        "Location: " + event.getLocation() + ", " +
                        "Time: " + event.getTime();

                logger.info(eventDetails);
            }
        }
    }


    public static void searchEventByName() {
        logger.info("\nEnter the name of the event to search for:");
        String searchName = input.next();

        boolean found = false;

        for (Event event : Event.getAllEvents()) {
            if (event.getEventName().equalsIgnoreCase(searchName)) {
                StringBuilder eventDetails = new StringBuilder();
                eventDetails.append("Event found: ");
                eventDetails.append("Event Name: ").append(event.getEventName()).append(", ");
                eventDetails.append("Price: ").append(event.getPrice()).append(", ");
                eventDetails.append("Availability: ").append(event.getAvailability()).append(", ");
                eventDetails.append("Description: ").append(event.getDescription()).append(", ");
                eventDetails.append("Location: ").append(event.getLocation()).append(", ");
                eventDetails.append("Time: ").append(event.getTime()).append(", ");


                logger.info(eventDetails.toString());
                found = true;
                break;
            }
        }


        if (!found) {
            logger.info("No event with the name '" + searchName + "' found.");
        }
    }


    public static void searchEventByPrice() {

        logger.info("\nEnter the price of the event to search for:");
        int searchPrice = input.nextInt();

        boolean found = false;


        for (Event event : Event.getAllEvents()) {
            if (event.getPrice() == searchPrice) {
                StringBuilder eventDetails = new StringBuilder();
                eventDetails.append("Event found: ");
                eventDetails.append("Event Name: ").append(event.getEventName()).append(", ");
                eventDetails.append("Price: ").append(event.getPrice()).append(", ");
                eventDetails.append("Availability: ").append(event.getAvailability()).append(", ");
                eventDetails.append("Description: ").append(event.getDescription()).append(", ");
                eventDetails.append("Location: ").append(event.getLocation()).append(", ");
                eventDetails.append("Time: ").append(event.getTime()).append(", ");


                logger.info(eventDetails.toString());
                found = true;
            }
        }

        if (!found) {
            logger.info("No event with the price '" + searchPrice + "' found.");
        }
    }



    public static void deleteEvent() {

        logger.info("\nList of events:");
        List<Event> allEvents = Event.getAllEvents();
        for (int i = 0; i < allEvents.size(); i++) {
            logger.info((i + 1) + ". " + allEvents.get(i).getEventName());
        }


        logger.info("Enter the number corresponding to the event you want to delete (press 0 to go back to the menu):");
        int eventNumberToDelete = input.nextInt();

        if (eventNumberToDelete == 0) {
            return;
        }

        if (eventNumberToDelete < 1 || eventNumberToDelete > allEvents.size()) {
            logger.info("Invalid event number.");
            return;
        }


        Event eventToDelete = allEvents.get(eventNumberToDelete - 1);
        allEvents.remove(eventToDelete);
        logger.info("Event '" + eventToDelete.getEventName() + "' deleted successfully.");
    }



    public static void editEvent() {

        logger.info("\nList of events:");
        List<Event> allEvents = Event.getAllEvents();
        for (int i = 0; i < allEvents.size(); i++) {
            logger.info((i + 1) + ". " + allEvents.get(i).getEventName());
        }


        logger.info("Enter the number corresponding to the event you want to edit:");
        int eventNumberToEdit = scanner();

        if (eventNumberToEdit < 1 || eventNumberToEdit > allEvents.size()) {
            logger.info("Invalid event number.");
            return;
        }


        Event eventToEdit = allEvents.get(eventNumberToEdit - 1);


        logger.info("Enter the new name of the event:");
        String newName = input.nextLine().trim();


        int newPrice = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                logger.info("Enter the new price of the event:");
                newPrice = Integer.parseInt(input.nextLine().trim());
                validPrice = true;
            } catch (NumberFormatException e) {
                logger.info("Invalid input for price. Please enter a valid integer.");
            }
        }


        eventToEdit.setEventName(newName);
        eventToEdit.setPrice(newPrice);


        logger.info("Event edited successfully.");

    }
















    //User Menu

    public static void viewUserProfile(User user) {
        logger.info("User Profile:");
        logger.info("Username: " + user.getUsername());
        logger.info("Address: " + user.getAddress());
        logger.info("Phone: " + user.getPhone());
        logger.info("Email: " + user.getEmail());
        logger.info("Gender: " + user.getGender());
    }




    public static void reserveWedding(User loggedInUser) {
        logger.info("\nReserve Wedding:");

        logger.info("Available Venues:");
        List<Venue> availableVenues = Venue.getAvailableVenues();
        for (int i = 0; i < availableVenues.size(); i++) {
            logger.info((i + 1) + ". " + availableVenues.get(i).toString());
        }

        logger.info("Choose a venue by entering the corresponding number:");
        int venueChoice = scanner();

        if (venueChoice < 1 || venueChoice > availableVenues.size()) {
            logger.info("Invalid choice. Please enter a valid venue number.");
            return;
        }

        Venue selectedVenue = availableVenues.get(venueChoice - 1);


        logger.info("Available Dates for " + selectedVenue.getName() + ":");
        List<Date> availableDates = getAvailableDatesForVenue(venueChoice);
        for (int i = 0; i < availableDates.size(); i++) {
            logger.info((i + 1) + ". " + availableDates.get(i));
        }


        logger.info("Choose a date by entering the corresponding number, or enter 0 to go back to choosing a venue:");
        int dateChoice = scanner();


        if (dateChoice == 0) {

            reserveWedding(loggedInUser);
            return;
        } else if (dateChoice < 1 || dateChoice > availableDates.size()) {
            logger.info("Invalid choice. Please enter a valid date number.");
            return;
        }


        Date selectedDate = availableDates.get(dateChoice - 1);



        if (AdditionalService.getAvailableServices().isEmpty()) {
            AdditionalService.initializeAdditionalService();
        }

        logger.info("Available Additional Services:");
        List<AdditionalService> availableServices = AdditionalService.getAvailableServices();


        for (int i = 0; i < availableServices.size(); i++) {
            AdditionalService service = availableServices.get(i);
            logger.info((i + 1) + ". " + service.getServiceName() + " - Cost: $" + service.getCost());
        }
        logger.info("0. None");

        logger.info("Choose additional services (enter numbers separated by commas, or 0 for none):");
        String additionalServiceChoicesStr = input.nextLine();
        String[] additionalServiceChoices = additionalServiceChoicesStr.split(",");


        List<AdditionalService> selectedServices = new ArrayList<>();
        for (String choice : additionalServiceChoices) {
            int serviceChoice = Integer.parseInt(choice.trim());
            if (serviceChoice > 0 && serviceChoice <= availableServices.size()) {
                selectedServices.add(availableServices.get(serviceChoice - 1));
            }


        }
        logger.info("Reservation Details:");
        logger.info("Venue: " + selectedVenue.toString());
        logger.info("Date: " + selectedDate);
        if (!selectedServices.isEmpty()) {
            logger.info("Additional Services:");
            for (AdditionalService service : selectedServices) {
                logger.info("- " + service.getServiceName() + " - Cost: $" + service.getCost());
            }
        } else {
            logger.info("Additional Services: None");
        }

        logger.info("1. Submit Reservation");
        logger.info("2. Go back to Menu");
        logger.info("Enter your choice:");
        int choice = scanner();
        switch (choice) {
            case 1:

                submitReservation(loggedInUser, selectedVenue, selectedDate, selectedServices);
                break;
            case 2:
                userActivities();
                break;
            default:
                logger.info("Invalid choice. Returning to the main menu.");
                break;
        }


    }

    private static int requestIdCounter = 1;

    private static int generateRequestId() {

        return requestIdCounter++;
    }
    public static void submitReservation(User loggedInUser, Venue venue, Date date, List<AdditionalService> services) {
        ReservationRequest request = new ReservationRequest(generateRequestId(), loggedInUser, venue, date, services);
        ReservationRequest.addReservationRequest(request);

        logger.info("Reservation submitted successfully! Check your email for confirmation.");
    }

    public static void viewReservationRequests() {
        List<ReservationRequest> requests = ReservationRequest.getAllReservationRequests();

        if (requests.isEmpty()) {
            logger.info("There are no pending reservation requests.");
        } else {
            logger.info("Pending Reservation Requests:");
            for (int i = 0; i < requests.size(); i++) {
                ReservationRequest request = requests.get(i);

                logger.info((i + 1) + ". Request ID: " + request.getRequestId());
                logger.info("   Customer Name: " + request.getUser().getUsername());
                logger.info("   Venue: " + request.getVenue());
                logger.info("   Date: " + request.getDate());
                logger.info("   Additional Services: " + request.getServices());

            }
        }
    }




    private static List<Date> getAvailableDatesForVenue(int venueChoice) {
        switch (venueChoice) {
            case 1:
                return availableDatesV1;
            case 2:
                return availableDatesV2;

            case 3:
                return availableDatesV3;
            case 4:
                return availableDatesV4;
            case 5:
                return availableDatesV5;


            default:
                return new ArrayList<>(); // Return an empty list if venue choice is invalid
        }
    }






    public static void addNewVenue() {
        String name = "";
        String location = "";
        int capacity = 0;
        double cost = 0.0;

        Scanner scanner = new Scanner(System.in);

        logger.info("Enter Venue Name:");
        name = scanner.nextLine();

        logger.info("Enter Venue Location:");
        location = scanner.nextLine();

        logger.info("Enter Venue Capacity:");

        boolean validCapacityInput = false;

        while (!validCapacityInput) {
            try {
                logger.info("PLEASE ENTER A NUMBER:");
                capacity = Integer.parseInt(scanner.nextLine());
                validCapacityInput = true;
            } catch (NumberFormatException e) {
                logger.info("Invalid input. Please enter a valid number for capacity.");
            }
        }

        logger.info("Enter Venue Reserving Price:");

        boolean validCostInput = false;

        while (!validCostInput) {
            try {
                logger.info("PLEASE ENTER A NUMBER:");
                cost = Double.parseDouble(scanner.nextLine());
                validCostInput = true;
            } catch (NumberFormatException e) {
                logger.info("Invalid input. Please enter a valid number for cost.");
            }
        }

        Venue venue = new Venue(name, location, capacity, cost);
        Venue.addVenueToTheList(venue);
        logger.info("Venue added successfully.");
    }



    public static void addService() {
        String name = "";
        String provider_name = "";
        double price = 0.0;

        Scanner scanner = new Scanner(System.in);

        logger.info("Enter Service Name :");
        name = scanner.nextLine();

        logger.info("Enter your name :");
        provider_name = scanner.nextLine();

        logger.info("Enter Service Price : ");

        boolean validInput = false;

        while (!validInput) {
            try {
                logger.info("PLEASE ENTER A NUMBER :");
                price = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }


        AdditionalService service = new AdditionalService(name, price);
        AdditionalService.add_servicetoTheArray(service);
        logger.info("Service added successfully.");


    }


    public static void edit_info() {
        boolean cont = false;

        while (!cont) {

            logger.info("choose what you want to edit :");
            logger.info("1- Name");
            logger.info("2- E-Mail");
            logger.info("3- Address");
            logger.info("4- Phone");

            Scanner scanner = new Scanner(System.in);

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    cont = true;
                    break;
                case "2":
                    cont = true;
                    break;
                case "3":
                    cont = true;
                    break;
                case "4":
                    cont = true;
                    break;
                default:
                    logger.info("Please Enter A number.");
            }


        }


    }





}