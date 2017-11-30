package com.company;

import java.io.File;
import java.util.Scanner;

public class OperatorProfile extends Profile {
    Scanner user = new Scanner(System.in);
    Employee You = null;

    public OperatorProfile(Database[] newData, Employee login){
        data = newData;
        You = login;
    }

    public boolean menu(){
        currentDate.getTime();
        System.out.println(currentDate.toString());
        System.out.println("You're a ChocAn operator");
        System.out.println("Select an option:");
        System.out.println("1. Service database operations");
        System.out.println("2. Provider database operations");
        System.out.println("3. Member database operations:");
        System.out.println("4. Operator database operations");
        System.out.println("5. Generate weekly reports");
        System.out.println("0. Exit");
        int selection = user.nextInt();
        switch(selection){
            case 0:
                System.out.println("Thank you for using the CDPS.");
                return false;
            case 1:
                editServices();
                break;
            case 2:
                editProviders();
                break;
            case 3:
                editMembers();
                break;
            case 4:
                if(isAdmin())
                    editOperators();
                else
                    System.out.println("Insufficient user privilege to access this database!");
                break;
            case 5:
                if(isAdmin()){
                    File toWrite = new File(".\\EFT.txt");
                    data[1].writeEFTReports(toWrite);
                    toWrite = new File(".\\report.txt");
                    data[1].writeCombinedEntryReports(toWrite);
                }
                break;
        }
        return true;
    }


    private void editServices() {
        while (true) {
            System.out.println("Please select the operation to perform.");
            System.out.println("1. Add a service");
            System.out.println("2. Remove a service");
            System.out.println("3. Display all available services");
            System.out.println("0. Return");
            int selection = user.nextInt();
            switch (selection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Please enter the name of the new service:");
                    user.nextLine();
                    String tempName = user.nextLine();
                    System.out.printf("Please enter the cost of service \"%s\":\n", tempName);
                    Double tempFee = user.nextDouble();
                    System.out.println("Please enter the ID number to associate with this service:");
                    int tempID = user.nextInt();
                    while(tempID > 999999 || tempID < 100000){
                        System.out.println("ID numbers must be 6 digits!");
                        System.out.println("Please enter the ID number to associate with this service:");
                        tempID = user.nextInt();
                    }
                    Service toAdd = new Service(tempID, tempName, tempFee);
                    data[0].insertEntry(toAdd);
                    break;
                case 2:
                    System.out.println("Please enter the ID of the service to remove.");
                    int id = user.nextInt();
                    if(data[0].searchEntry(id) != null) {
                        data[0].removeEntry(id);
                        System.out.printf("Removed service #%d successfully!\n", id);
                    }
                    else
                        System.out.printf("Service not found matching ID #%d\n", id);
                    break;
                case 3:
                    data[0].displayEntries();
                    break;
            }
        }
    }

    private void editProviders() {
        while (true) {
            System.out.println("Please select the operation to perform.");
            System.out.println("1. Add a provider to the network");
            System.out.println("2. Remove a provider from the network");
            System.out.println("3. Add a service to a provider's list of services");
            System.out.println("4. Remove a service from a provider's list of services");
            System.out.println("5. Add a record of a service provided");
            System.out.println("6. Update a provider's personal information");
            System.out.println("7. Show all currently registered providers");
            System.out.println("0. Return");
            int selection = user.nextInt();
            switch (selection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Please enter the 9 digit ID number of the new provider:");
                    int tempID = user.nextInt();
                    while (tempID > 999999999 || tempID < 100000000) {
                        System.out.println("ID numbers must be 9 digits!");
                        System.out.println("Please enter the ID number of the new provider:");
                        tempID = user.nextInt();
                    }
                    Provider entryCheck = (Provider) data[1].searchEntry(tempID);
                    if(entryCheck != null)
                        System.out.println("This ID is already in use!");
                    System.out.println("Please enter the first name of the new provider:");
                    user.nextLine();
                    String tempFirst = user.nextLine();
                    System.out.println("Please enter the last name of the new provider:");
                    String tempLast = user.nextLine();
                    System.out.printf("Please enter a street address for %s %s:\n", tempFirst, tempLast);
                    String tempStreet = user.nextLine();
                    System.out.println("Please enter the city this address is located in:");
                    String tempCity = user.nextLine();
                    System.out.println("Please enter the two-character state abbreviation for this address:");
                    String tempState = user.nextLine().toUpperCase();
                    System.out.println("Please enter the 5 digit ZIP code:");
                    int tempZIP = user.nextInt();
                    Provider toAdd = null;
                    try {
                        toAdd = new Provider(tempID, tempFirst, tempLast, tempStreet, tempCity, tempState, tempZIP);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    data[1].insertEntry(toAdd);
                    break;
                case 2:
                    System.out.println("Please enter the ID of the provider to remove.");
                    selection = user.nextInt();
                    if (data[1].searchEntry(selection) != null) {
                        data[1].removeEntry(selection);
                        System.out.printf("Removed provider #%d successfully!\n", selection);
                    } else
                        System.out.printf("Provider record matching ID #%d not found\n", selection);
                    break;
                case 3:
                    System.out.println("Please enter the 9 digit ID number of the provider to modify:");
                    user.nextLine();
                    selection = user.nextInt();
                    Provider toAddService = (Provider) data[1].searchEntry(selection);
                    if (toAddService == null) {
                        System.out.printf("Couldn't find provider #%d\n", selection);
                        break;
                    }
                    toAddService.displayServices();
                    System.out.printf("Please enter the service ID to add to provider #%d:\n", selection);
                    System.out.println("(Enter 0 to display a list of all services)");
                    selection = user.nextInt();
                    if (selection == 0) {
                        data[0].displayEntries();
                        System.out.printf("Please enter the service ID to add:\n");
                        selection = user.nextInt();
                    }
                    Service tempService = (Service) data[0].searchEntry(selection);
                    if (tempService != null)
                        toAddService.addService(tempService);
                    else
                        System.out.println("Invalid service ID!");
                    break;
                case 4:
                    System.out.println("Please enter the 9 digit ID number of the provider to modify:");
                    user.nextLine();
                    selection = user.nextInt();
                    Provider toRemoveService = (Provider) data[1].searchEntry(selection);
                    if (toRemoveService == null) {
                        System.out.printf("Couldn't find provider #%d\n", selection);
                        break;
                    }
                    toRemoveService.displayServices();
                    System.out.printf("Please enter the service ID to remove from provider #%d:\n", selection);
                    System.out.println("(Enter 0 to display a list of all services)");
                    selection = user.nextInt();
                    if (selection == 0) {
                        data[0].displayEntries();
                        System.out.printf("Please enter the service ID to remove:\n");
                        selection = user.nextInt();
                    }
                    tempService = (Service) data[0].searchEntry(selection);
                    if (tempService != null)
                        toRemoveService.removeService(selection);
                    else
                        System.out.println("Invalid service ID!");
                    break;
                case 5:
                    System.out.println("Please enter the 9 digit ID number of the provider to modify:");
                    user.nextLine();
                    selection = user.nextInt();
                    Provider toModify = (Provider) data[1].searchEntry(selection);
                    while (toModify == null) {
                        System.out.printf("Couldn't find provider #%d\n", selection);
                        System.out.println("Please enter the 9 digit ID number of the provider to modify:");
                        selection = user.nextInt();
                        toModify = (Provider) data[1].searchEntry(selection);
                    }
                    System.out.println("Please enter the 6 digit ID of the service provided:");
                    selection = user.nextInt();
                    tempService = (Service) data[0].searchEntry(selection);
                    while (tempService == null) {
                        System.out.println("Couldn't find that service!");
                        System.out.println("Please enter the 6 digit ID of the service provided:");
                        selection = user.nextInt();
                        tempService = (Service) data[0].searchEntry(selection);
                    }
                    System.out.println("Please enter the 9 digit ID number of the patient");
                    user.nextLine();
                    selection = user.nextInt();
                    Member getsService = (Member) data[3].searchEntry(selection);
                    while (getsService == null) {
                        System.out.printf("Couldn't find patient #%d\n", selection);
                        System.out.println("Please enter the 9 digit ID number of the the patient:");
                        selection = user.nextInt();
                        getsService = (Member) data[3].searchEntry(selection);
                    }
                    int month = 0;
                    while (month < 1 || month > 12) {
                        System.out.println("Please enter the 2 digit month of the service date:");
                        month = user.nextInt();
                    }
                    String strDate = "";
                    if (month < 10)
                        strDate = "0";
                    strDate += (Integer.toString(month) + '/');
                    int day = 0;
                    while (day < 1 || day > 31) {
                        System.out.println("Please enter the 2 digit day of the service date:");
                        day = user.nextInt();
                    }
                    if (day < 10)
                        strDate += '0';
                    strDate += (Integer.toString(day) + '/');
                    int year = 0;
                    while (year < 1999 || (year-1900) > currentDate.getYear()) {
                        System.out.println("Please enter the 4 digit year of the service date:");
                        year = user.nextInt();
                    }
                    strDate += (Integer.toString(year));
                    System.out.println("Please enter any comments related to this procedure:");
                    user.nextLine();
                    String comments = user.nextLine();
                    ServiceProvided newService = null;
                    try {
                        newService = new ServiceProvided(tempService,
                                strDate, getsService, toModify, comments);
                    } catch (Exception e) {
                        System.out.println("Error adding service");
                        break;
                    }
                    System.out.println("Service record created!");
                    toModify.addServiceProvided(newService);
                    getsService.addServiceProvided(newService);
                    break;
                case 6:
                    System.out.println("Please enter the 9 digit ID number of the provider to modify:");
                    user.nextLine();
                    selection = user.nextInt();
                    toModify = (Provider) data[1].searchEntry(selection);
                    if (toModify == null) {
                        System.out.printf("Couldn't find provider #%d\n", selection);
                        break;
                    }
                    toModify.update();
                    break;
                case 7:
                    data[1].displayEntries();
            }
        }
    }

    private void editMembers(){
        System.out.println("Please select the operation to perform.");
        System.out.println("1. Add a new member to the database");
        System.out.println("2. Remove a member from the database");
        System.out.println("3. Display all currently registered members");
        System.out.println("4. Check a member's current account status");
        System.out.println("5. Update a member's current account status");
        System.out.println("6. Add a record of a service provided");
        System.out.println("0. Return");
        int selection = user.nextInt();
        switch (selection) {
            case 0:
                return;
            case 1:
                System.out.println("Please enter a new 9 digit ID number for the new member:");
                int tempID = user.nextInt();
                while (tempID > 999999999 || tempID < 100000000) {
                    System.out.println("ID numbers must be 9 digits!");
                    System.out.println("Please enter the ID number of the new member:");
                    tempID = user.nextInt();
                }
                Member entryCheck = (Member) data[3].searchEntry(tempID);
                if(entryCheck != null)
                    System.out.println("This ID is already in use!");
                System.out.println("Please enter the first name of the new member:");
                user.nextLine();
                String tempFirst = user.nextLine();
                System.out.println("Please enter the last name of the new member:");
                String tempLast = user.nextLine();
                System.out.printf("Please enter a street address for %s %s:\n", tempFirst, tempLast);
                String tempStreet = user.nextLine();
                System.out.println("Please enter the city this address is located in:");
                String tempCity = user.nextLine();
                System.out.println("Please enter the two-character state abbreviation for this address:");
                String tempState = user.nextLine().toUpperCase();
                System.out.println("Please enter the 5 digit ZIP code:");
                int tempZIP = user.nextInt();
                Provider toAdd = null;
                try {
                    toAdd = new Provider(tempID, tempFirst, tempLast, tempStreet, tempCity, tempState, tempZIP);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                data[1].insertEntry(toAdd);
                break;
            case 2:
                System.out.println("Please enter the ID of the member to remove.");
                int id = user.nextInt();
                if(data[3].searchEntry(id) != null) {
                    data[3].removeEntry(id);
                    System.out.printf("Removed member #%d successfully!\n", id);
                }
                else
                    System.out.printf("Member not found matching ID #%d\n", id);
                break;
            case 3:
                data[3].displayEntries();
                break;
            case 4:
                System.out.println("Please enter the ID of the member to check.");
                tempID = user.nextInt();
                Member toCheck = (Member) data[3].searchEntry(tempID);
                if(toCheck != null)
                    System.out.printf("Member's account is current: %d.\n", toCheck.isCurrent());
                else
                    System.out.printf("Couldn't find member #%d.\n", tempID);
                break;
            case 5:
                System.out.println("Please enter the ID of the member to update.");
                tempID = user.nextInt();
                toCheck = (Member) data[3].searchEntry(tempID);
                if(toCheck != null) {
                    System.out.printf("Member's account is current: %d.\n", toCheck.isCurrent());
                    System.out.printf("Switch to %d? (Y/N)\n", !toCheck.isCurrent());
                    char choice = (char) user.nextByte();
                    if(choice == 'y')
                        toCheck.toggleStatus();
                }
                else
                    System.out.printf("Couldn't find member #%d.\n", tempID);
                break;
            case 6:
                System.out.println("Please enter the 9 digit ID number of the provider:");
                user.nextLine();
                selection = user.nextInt();
                Provider toModify = (Provider) data[1].searchEntry(selection);
                while (toModify == null) {
                    System.out.printf("Couldn't find provider #%d\n", selection);
                    System.out.println("Please enter the 9 digit ID number of the provider:");
                    selection = user.nextInt();
                    toModify = (Provider) data[1].searchEntry(selection);
                }
                System.out.println("Please enter the 6 digit ID of the service provided:");
                selection = user.nextInt();
                Service tempService = (Service) data[0].searchEntry(selection);
                while (tempService == null) {
                    System.out.println("Couldn't find that service!");
                    System.out.println("Please enter the 6 digit ID of the service provided:");
                    selection = user.nextInt();
                    tempService = (Service) data[0].searchEntry(selection);
                }
                System.out.println("Please enter the 9 digit ID number of the patient");
                selection = user.nextInt();
                Member getsService = (Member) data[3].searchEntry(selection);
                while (getsService == null) {
                    System.out.printf("Couldn't find patient #%d\n", selection);
                    System.out.println("Please enter the 9 digit ID number of the the patient:");
                    selection = user.nextInt();
                    getsService = (Member) data[3].searchEntry(selection);
                }
                int month = 0;
                while (month < 1 || month > 12) {
                    System.out.println("Please enter the 2 digit month of the service date:");
                    month = user.nextInt();
                }
                String strDate = "";
                if (month < 10)
                    strDate = "0";
                strDate += (Integer.toString(month) + '/');
                int day = 0;
                while (day < 1 || day > 31) {
                    System.out.println("Please enter the 2 digit day of the service date:");
                    day = user.nextInt();
                }
                if (day < 10)
                    strDate += '0';
                strDate += (Integer.toString(day) + '/');
                int year = 0;
                while (year < 1999 || (year-1900) > currentDate.getYear()) {
                    System.out.println("Please enter the 4 digit year of the service date:");
                    year = user.nextInt();
                }
                strDate += (Integer.toString(year));
                System.out.println("Please enter any comments related to this procedure:");
                user.nextLine();
                String comments = user.nextLine();
                ServiceProvided newService = null;
                try {
                    newService = new ServiceProvided(tempService,
                            strDate, getsService, toModify, comments);
                } catch (Exception e) {
                    System.out.println("Error adding service");
                    break;
                }
                System.out.println("Service record created!");
                toModify.addServiceProvided(newService);
                getsService.addServiceProvided(newService);
                break;
        }

    }

    private void editOperators(){
        System.out.println("1. Add operator");
        System.out.println("2. Remove operator");
        System.out.println("3. Update operator profile");
        System.out.println("4. Display all registered operators");
        System.out.println("0. Exit");
        int selection = user.nextInt();
        switch (selection) {
            case 0:
                return;
            case 1:
                System.out.println("Please enter a new 9 digit ID number for the new operator:");
                int tempID = user.nextInt();
                while (tempID > 999999999 || tempID < 100000000) {
                    System.out.println("ID numbers must be 9 digits!");
                    System.out.println("Please enter the ID number of the new operator:");
                    tempID = user.nextInt();
                }
                Member entryCheck = (Member) data[3].searchEntry(tempID);
                if (entryCheck != null)
                    System.out.println("This ID is already in use!");
                System.out.println("Please enter the first name of the new operator:");
                user.nextLine();
                String tempFirst = user.nextLine();
                System.out.println("Please enter the last name of the new operator:");
                String tempLast = user.nextLine();
                System.out.println("Please enter the job title of the new operator:");
                String tempCode = user.nextLine();
                char newCode = tempCode.charAt(0);
                System.out.printf("Please enter a street address for %s %s:\n", tempFirst, tempLast);
                String tempStreet = user.nextLine();
                System.out.println("Please enter the city this address is located in:");
                String tempCity = user.nextLine();
                System.out.println("Please enter the two-character state abbreviation for this address:");
                String tempState = user.nextLine().toUpperCase();
                System.out.println("Please enter the 5 digit ZIP code:");
                int tempZIP = user.nextInt();
                Employee toAdd = null;
                try {
                    toAdd = new Employee(tempID, tempFirst, tempLast, tempStreet, tempCity, tempState, tempZIP, newCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                data[2].insertEntry(toAdd);
                break;
            case 2:
                System.out.println("Please enter the ID of the operator to remove.");
                int id = user.nextInt();
                if (data[2].searchEntry(id) != null) {
                    data[2].removeEntry(id);
                    System.out.printf("Removed operator #%d successfully!\n", id);
                } else
                    System.out.printf("Operator not found matching ID #%d\n", id);
                break;
            case 3:
                System.out.println("Please enter the ID of the operator to update.");
                id = user.nextInt();
                Employee toUpdate = (Employee) data[2].searchEntry(id);
                if (toUpdate != null) {
                    toUpdate.update();
                    System.out.printf("Removed operator #%d successfully!\n", id);
                } else
                    System.out.printf("Operator not found matching ID #%d\n", id);
                break;
            case 4:
                data[2].displayEntries();
                break;
        }
    }

    private boolean isAdmin(){
        if(You.jobTitleCode == 'M')
            return true;
        else
            return false;
    }
}
