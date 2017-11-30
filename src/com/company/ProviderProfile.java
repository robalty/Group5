package com.company;

import java.util.Scanner;

public class ProviderProfile extends Profile {
    Scanner user = new Scanner(System.in);
    Provider You = null;

    ProviderProfile(Database[] newData, Provider login){
        data = newData;
        You = login;
    }

    public boolean menu(){
        System.out.println("Please select an option:");
        System.out.println("1. Member lookup/billing");
        System.out.println("2. Display available services");
        System.out.println("0. Exit");
        int temp = user.nextInt();
        switch(temp){
            case 0:
                System.out.println("Thank you for using the CDPS.");
                return false;
            case 1:
                Member member = null;
                do {
                    System.out.println("Please enter the 9 digit Member ID#, or enter 0 to return to previous menu");
                    temp = user.nextInt();
                    if(temp == 0)
                        break;
                    member = searchMembers(temp);
                }while(member == null);
                memberMenu(member);
                break;
            case 2:
                You.displayServices();
                break;
        }
    return true;
    }

    public void memberMenu(Member patient){
        patient.display();
        System.out.println("Please select an option from the list below:");
        System.out.println("1. Add a new service record");
        System.out.println("0. Return");
        int temp = user.nextInt();
        switch(temp){
            case 0:
                break;
            case 1:
                System.out.println("Please enter the 6 digit Service ID number");
                temp = user.nextInt();
                Service tempService = (Service) data[0].searchEntry(temp);
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
                            strDate, patient, You, comments);
                } catch (Exception e) {
                    System.out.println("Error adding service");
                    break;
                }
                System.out.println("Service record created!");
                You.addServiceProvided(newService);
                patient.addServiceProvided(newService);
                break;
        }
    }
}