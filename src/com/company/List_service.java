package com.company;

/**
 * Created by ThongTran on 11/20/17.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


// READ ME //
// This Java file contains the implementation of creating the List of services. There are three types of Services that will be used
// in this program, The list of all available services that provider can offer, the List of the services that providers have been
// provided, and the list of services that the customers have been received from the the providers.
//     There are three types of addition algorithms
// 1: List of all Available Services will be sorted in the alphabet order
// 2: List of services that customers received will be sorted in the order of service date
// 3: List of services that provider provided will be appended at the last position of the list
//Notice : When you want to add a Service into the List (2) or (3), I recommend to check whether or not the Service is available in
// list (1) by calling the function check_service_exist_in_list. The reason is that it does not make sense to add a Service that is
// not existed in list(1) to List(2) and (3).





// Enumeration that shows the list type of three different type of list
enum  List_Service_Type {
        all_services_available_provider, all_services_provided_provider, all_services_received_customer
}

public class List_service {
        private Scanner read;           // Use for reading the file from scanner
        private Service head;           // Start of the List
        private Service tail;           // Last of the list
        private List_Service_Type type; // Type of the list
        public List_service  (){
               this.head = null;
               this.tail = null;
               this.type = null;
        }
        public List_service( List_Service_Type type) {
            this();
            this.type = type;
        }


    // Public addition method
    // Return -2 , and -1 means fail to add
    // Return 1 means success

    public int add_service_to_list (Service to_add)
    {
        if ( to_add == null)
        {
            return -2;
        }
        int result = -1 ;
        switch (type)
        {
            case all_services_available_provider:
               result =  add_all_available_services_for_provider(to_add);
                break;
            case all_services_provided_provider:
                result = add_all_services_provided_for_provider(to_add);
                break;
            case all_services_received_customer:
                result = add_list_services_received_for_customer(to_add);
                break;
            default:
                break;
        }
        return result;
    }


    // This addition function will append the Service in their tail
    // Return -2 if the argument is null
    // Return 1 if success adding
    // return -1 if something wrong happen
    private int add_all_services_provided_for_provider ( Service to_add){

        if ( to_add == null)
        {
            return -2;
        }
        if ( head == null){
            head = to_add;
            tail = head;
            return 1;
        }
        else if ( head != null)
        {
            tail.setNext(to_add);
            tail = to_add;
            return 1;
        }
        else {
            return -1;
        }
    }






    // This function addition will sort in the alphabet order from A to Z
    // If you need to change the order, simply switch -1 and 1 in if statement
    // Return -1  and - 2 means add not success or service already exists
    // Return 1 means add success
    private int add_all_available_services_for_provider (Service to_add) {
        // Check if the list is already available in the list
        if ( this.check_service_exist_in_list(to_add.idNum) == true)
        {
            System.out.println("The Service id(" + to_add.idNum + ") is already existed in the list");
            return -1;
        }
        if(to_add == null)
        {
            return -2;
        }

        if ( head == null)
        {
            to_add.setNext(head);
            head = to_add;
            tail = head;
            return 1;
        }
        else if ( head != null) {
                if ( head.compareByName(to_add)  == 1 || head.compareByName(to_add) == 0)
                {
                    to_add.setNext(head);
                    head = to_add;
                    tail = head;
                    return 1;
                }
                else
                {
                    Service curr = head;
                    while ( curr.goNext() != null && curr.goNext().compareByName(to_add) == -1)
                    {
                        curr = curr.goNext();
                    }
                    to_add.setNext( curr.goNext());
                    curr.setNext(to_add);
                    tail = to_add.goNext();
                    return 1;
                }
        }
        else
        {
            return -1;
        }


    }

    // This addition function will sort by Service Date  from the Newsest to Lowest
    // If you want to switch to from lowest to newest, simply change the compareByServiceDate from first and second if
    // statement to 1 and -1
    // Return 1 means success
    // Return -1 means not sucess
    private int add_list_services_received_for_customer ( Service to_add) {
        // It is being upcasted in main
        ServiceProvided to_data = (ServiceProvided) to_add;
        if (head == null) {
            to_add.setNext(head);
            head = to_add;
            tail = head;
            return 1;
        } else if (head != null) {
            // Using the  RTTI
            ServiceProvided data = (ServiceProvided) (head);

            if (data.compareByServiceDate(to_data) == -1) {
                {
                    to_add.setNext(head);
                    head = to_add;
                    tail = head;
                    return 1;
                }
            }
            else {
                Service curr = head;
                // Going through the list to sort
                while (curr.goNext() != null) {
                        ServiceProvided tmp = (ServiceProvided)(curr.goNext());
                        if (tmp.compareByServiceDate(to_data) == 1) {
                            curr = curr.goNext();
                        }
                }
                to_add.setNext( curr.goNext());
                curr.setNext(to_add);
                tail = to_add.goNext();
                return 1;
            }
        }
        else
        {
            return -1;
        }
    }


    // This function will display all services in the list
    // Return -1 if the list is empty
    // Return the number of Services in the list alongside with display
    public int display_all_list_services_(){
        if ( head == null)
        {
            System.out.println("The List of services provided is currently empty");
            return -1;
        }
          return  display_all_list_services(this.head);
    }
    private int display_all_list_services ( Service curr)
    {
        if(curr == null) return 0;
            curr.display();
        return display_all_list_services(curr.goNext())  + 1;
    }

    // This function will search and display the specific service based on ID
    // Return -2 means iD is 0 or List is empty
    // Return 1 means successfull found
    // Return -1 means service is not found
    public int search_and_display_service_by_iD ( int iD){
        if ( this.head == null || iD == 0)
        {
            return -2;
        }
        return search_and_display_service_by_iD(this.head, iD);
    }
    private int search_and_display_service_by_iD ( Service curr, int iD){
            if ( curr == null)
            {
                return -1;
            }
            if ( curr.compare(iD) == 0)
            {
                curr.display();
                return 1;
            }
            return search_and_display_service_by_iD(curr.goNext(), iD);
    }

    // This function will search and update the specific iD
    // Return -2 if iD is 0 or List is empty
    // Return 1 means success found and update
    // Return -1 means service is not found
    public int search_and_update_service_by_iD ( int iD) {
            if ( this.head == null || iD == 0 )
            {
                return -2;
            }
            return search_and_update_service_by_iD(this.head, iD);
    }
    private int search_and_update_service_by_iD ( Service curr, int iD){
            if (curr == null)
            {
                return -1;
            }
            if ( curr.compare(iD) == 0 )
            {
                curr.update();
                return 1;
            }
            return search_and_update_service_by_iD(curr.goNext(), iD);
    }

    // Delete all services function
    // Return -1 if List is empty
    // Return 1 if success delete all
    public int delete_all_services_list () {
        if ( this.head == null)
        {
            return -1;
        }
        else {
            this.head = null;
            return 1;
        }
    }

    // Search and delete one specific list
    // Return -2 if iD = 0 or List is empty
    // Return 1 if success found and delete
    // Return -1 if service is not found
    public int search_and_delete_service_by_iD ( int iD){
        if ( this.head == null || iD == 0 )
        {
            // List is empty
            return -2;
        }
        // Check if the first head is the one  to be deleted
        if ( this.head.compare(iD) == 0 )
        {
            head = head.goNext();
            return 1;
        }
        return  search_and_delete_service_by_iD(this.head, iD);
    }
    private int search_and_delete_service_by_iD ( Service curr, int iD){

        if ( curr == null || curr.goNext() == null){
            return -1;
        }
        // Check if the goNext() node is the one to be deleted
        if ( curr.goNext().compare(iD) == 0)
        {
            Service tmp = curr.goNext().goNext();
            curr.setNext(tmp);
            return 1;
        }
        return search_and_delete_service_by_iD(curr.goNext(), iD);
    }



    // Recursive function call for getting the total amount of fees
    private double total_amount_fees_of_the_list (Service curr){
        // If traversing to the end of the list
        // Return 0.0
        if( curr == null)
        {
            return 0.0;
        }
        // traverse the list and add it to the total amount
        return total_amount_fees_of_the_list(curr.goNext()) + curr.fee;
    }
    // Wrapper function to call the total amount of service fees
    // Return -1 if list is empty
    // Return something other than -1 is good
    public double total_amount_fees_of_the_list () {
        if ( this.head == null)
        {
            return -1.0;
        }
        return total_amount_fees_of_the_list(this.head);
    }


    // Write into the text file
    // Return 0 - SUCCESS
    // Return -1 if txt_name is null or the current list is empty
    // Return the number of Services that has been written in the text file
    public int write_Text_file (File newFile){
        if ( newFile == null)
        {
            return -1;
        }
        if ( this.head == null){
            System.out.println("The list is empty ");
            return -1;
        }
        return write_Text_file(newFile, this.head);
    }
    private int write_Text_file (File newFile, Service curr) {
        if ( curr == null) {
            return 0;
        }

            if ( curr.writeToFile(newFile) != 1) {
                System.out.println("System occurs errors");
                return -1;
            }
        return write_Text_file(newFile,curr.goNext()) + 1;
    }



    // Load from text file
    // Return -2 means the text is null
    // Return -1 means the type is not suitable
    public int load_Services_from_text_file (File file) throws Exception{
        Scanner text_name = new Scanner(file);
        int number_reads = 0 ;
        if ( type == List_Service_Type.all_services_available_provider){
            while (text_name.hasNext()) {
                try {
                    Service newService = new Service();
                    int result = newService.loadFromFile(text_name);
                    if (result == -1){
                        System.out.println("Data not found for all fields");
                    }
                    if (result == 1)
                    {
                       ++number_reads;
                    }
                    add_service_to_list(newService);
                }
                catch (NumberFormatException e) {
                    System.out.println("unable to parse number");
                }
            }
        }
        else
        {
            while(text_name.hasNext()) {
                try {
                    Service newService = new ServiceProvided();
                    int result = newService.loadFromFile(text_name);
                    if (result == 1){
                        ++ number_reads;
                    }
                    else {
                        System.out.println("Data not found to read");
                    }
                    add_service_to_list(newService) ;
                }catch (NumberFormatException e){
                    System.out.println("Unable to parse number");
                }
            }
        }
        return number_reads;
    }


    // This function will display the service in between certain times which is X and Y (X< Y)
    // Return -2 means X or Y empty or X > = Y
    // Return -3 because this is the type of Service
    // Return the number of services that found
    // Return 0 means not found any
    // Return -1 means List empty
    private int search_and_display_services_in_between_service_date (Service newNode, String x, String y)  throws ParseException{
            if ( newNode == null)
            {
               return 0;
            }
            ServiceProvided curr = (ServiceProvided) (newNode);
            try {
                if (curr.compareByServiceDate(x) == 1 && curr.compareByServiceDate(y) == -1) {
                    curr.display();
                    return search_and_display_services_in_between_service_date(newNode.goNext(), x, y )  + 1;
                }

            }catch (Exception e){

            }
                    return search_and_display_services_in_between_service_date(newNode.goNext(),x,y)  ;
    }

    public int search_and_display_services_in_between_service_date ( String date1, String date2) throws ParseException
    {

        if ( date1 == null || date2 == null){
            return -2;
        }

        //convert argument string to date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateX = new Date (formatter.parse(date1).getTime());
        Date dateY = new Date ( formatter.parse(date2).getTime());
        // Check if X happens after Y
        if ( dateX.after(dateY))
        {
            return -2;
        }

        if ( type == List_Service_Type.all_services_available_provider)
        {
            System.out.println("This list shows all the available services that do not have a specific date to display");
            return -3;
        }
        if ( this.head == null)
        {
            return -1;
        }
        return search_and_display_services_in_between_service_date(head,date1,date2);
    }


    // This function will search and update services between certain times which is X and Y (X< Y)
    // Return -2 means X or Y empty or X > = Y
    // Return -3 because this is the type of Service
    // Return the number of services that found
    // Return 0 means not found any
    // Return -1 means List empty
    public int search_and_update_services_in_between_service_date ( String date1, String date2 ) throws ParseException{
        if ( date1 == null || date2 == null){
            return -2;
        }
        if ( type == List_Service_Type.all_services_available_provider)
        {
            return -3;
        }
        //convert argument string to date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateX = new Date (formatter.parse(date1).getTime());
        Date dateY = new Date ( formatter.parse(date2).getTime());
        // Check if X happens after Y
        if ( dateX.after(dateY))
        {
            return -2;
        }
        if ( this.head == null) {
            return -1;
        }
        return search_and_update_services_in_between_service_date(this.head, date1, date2);
    }
    private int search_and_update_services_in_between_service_date ( Service newNode, String x , String y ) {
       if ( newNode == null) {
           return 0;
       }
        ServiceProvided curr = (ServiceProvided) (newNode);
        try {
            if (curr.compareByServiceDate(x) == 1 && curr.compareByServiceDate(y) == -1) {
                curr.update();
                return search_and_update_services_in_between_service_date(newNode.goNext(), x, y) + 1;
            }

        }
       catch (Exception e){

       }
        return search_and_update_services_in_between_service_date(newNode.goNext(), x, y) ;
    }


    // Return the number of Services in the list
    // Return -1 if the List is empty
    public int number_of_services_in_the_list ( ){
        if ( this.head == null) {
            return -1;
        }
        return number_of_services_in_the_list(this.head);
    }
    private int number_of_services_in_the_list ( Service curr){
        if ( curr == null){
            return 0;
        }
        return number_of_services_in_the_list(curr.goNext()) + 1;
    }

    // This function will search and display all services after one service date
    // Return -2 if date is empty
    // Return -3 because this is the type of Service
    // Return the number of services that found
    // Return 0 means not found any
    // Return -1 means List empty
    public int search_and_display_all_services_after_one_service_date ( String date){
        if ( this.head == null) {
            return -1;
        }
        if (type == List_Service_Type.all_services_available_provider){
            return -3;
        }
        if( date == null){
            return -2;
        }
        return search_and_display_all_services_after_one_service_date(this.head, date);
    }
    private int search_and_display_all_services_after_one_service_date ( Service curr, String x){
        if ( curr == null) {
            return 0;
        }
        ServiceProvided newNode = (ServiceProvided) curr;
        try {
            if (newNode.compareByServiceDate(x) == 1) {
                    newNode.display();
                    return search_and_display_all_services_after_one_service_date(curr.goNext(), x) + 1;
            }
        }catch (Exception e){}
                    return search_and_display_all_services_after_one_service_date(curr.goNext(), x) ;
    }




    // This function will search and update all servies after one service date
    // Return -2 if date is empty
    // Return -3 because this is the type of Service
    // Return the number of services that found
    // Return 0 means not found any
    // Return -1 means List empty
    public int search_and_update_all_services_after_one_service_date ( String date) {
          if (type == List_Service_Type.all_services_available_provider){
            return -3;
        }
         if ( this.head == null) {
            return -1;
        }
        if( date == null){
            return -2;
        }
        return search_and_update_all_services_after_one_service_date(this.head, date);
    }

    private int search_and_update_all_services_after_one_service_date ( Service curr, String x){
         if ( curr == null) {
            return 0;
        }
        ServiceProvided newNode = (ServiceProvided) curr;
        try {
            if (newNode.compareByServiceDate(x) == 1) {
                newNode.update();
                    return search_and_update_all_services_after_one_service_date(curr.goNext(), x) + 1;
            }
        }catch (Exception e){}
        return search_and_update_all_services_after_one_service_date(curr.goNext(), x);
    }



    // this function will be used to check whether or not the service is already available in the list
    public boolean check_service_exist_in_list ( int iD){
            if(this.head == null) {
                return false;
            }
            return check_service_exist_in_list(this.head, iD);
    }
    private boolean check_service_exist_in_list ( Service curr, int iD){
        if ( curr == null){
            return false;
        }
        if ( curr.compare(iD) == 0){
            return true;
        }
        return check_service_exist_in_list(curr.goNext(),iD);
    }


    // This function will help to copy one list to another List
    // Return -1 if the source list is empty
    // Return 1 means if it copies itself
    public int copy_list ( List_service to_copy){

        if (to_copy.head == null){
            System.out.println("The list you want to copy is empty. Cannot copy");
            return -1;
        }
        // This will make sure that if the type is the same
        if ( this.type != to_copy.type){
            System.out.println("This is two different type of list. Please check the list type before copy");
            return -1;
        }

        // Deallocate all memory
        delete_all_services_list();
        // Meaning it copies it self
            if (this.compare_two_list(to_copy) == true )
            {
                return 1;
            }
        this.type = to_copy.type;
        // Catch
        this.head = copy_list(to_copy.head,this.tail, this.head, to_copy.type);
        return 1;
    }


       // Assume that the original list is sorted, we can just simply add to the end
    private Service copy_list ( Service src, Service dst_tail,Service dst, List_Service_Type type){
        if (src == null)
        {
            return dst;
        }
            if (dst == null) {
                dst = src;
                dst_tail = dst;
            } else if (dst != null) {
                dst_tail.setNext(src);
                dst_tail = src;

            }
        return copy_list(src.goNext(),dst_tail, dst, type);
    }

    // This function will help to check if two list equals
    public boolean compare_two_list ( List_service to_copy)
    {
        // Check if two type is different
            if ( this.type != to_copy.type) {
                return false;
            }
            // if the number of the list is not the same
            if ( number_of_services_in_the_list(to_copy.head) != number_of_services_in_the_list(this.head))
            {
                return false;
            }
            // Check if the source list is empty
            if ( to_copy.head == null)  {
                return false;
            }
            return compare_two_list(to_copy.head, this.head);
    }
    private boolean compare_two_list ( Service src, Service dst){
            if ( src == null || dst == null) {
                return true;
            }
            if ( src.compare(dst.idNum) != 0)
            {
                return false;
            }
            return compare_two_list(src.goNext(), dst.goNext());
    }
    public static void main(String[] args) {

        // TEST 1
        /*
            Service s1 = new Service(0001, "Kim", 0.3);
            Service s2 = new Service(0001, "Take Care", 0.3);
            Service s3 = new Service(0003, "Done", 0.3);
            Service s4 = new Service(0004, "Aapplle", 0.3);
            List_service p1 = new List_service(List_Service_Type.all_services_available_provider) ;
            p1.add_service_to_list(s1);
            p1.add_service_to_list(s2);
            p1.add_service_to_list(s3);
            p1.add_service_to_list(s4);
            p1.display_all_list_services_();
        System.out.println("HELLOOOOOOOOOOOOOOOOOOOO");
            List_service p2 = new List_service();
            p2.copy_list(p1);
            List_service p3 = new List_service();
            p3.copy_list(p2) ;
            */
/*
            // TEST 2
            Service s1 = new Service(0001, "Thong", 0.3);
            Service s2 = new Service(0002, "Thong", 0.3);
            List_service list = new List_service(List_Service_Type.all_services_received_customer);

        try {
            Service to_add = new ServiceProvided(s1, "11/05/2014", 00032, "Thong", 00032132, "HELLO", "123");
            Service to_add1 = new ServiceProvided(s2, "11/05/2015", 00032, "Thong", 00032132, "HELLO", "456");
            Service to_add2 = new ServiceProvided(s2, "11/05/2011", 00032, "Thong", 00032132, "HELLO", "789");
            Service to_add3 = new ServiceProvided(s2, "11/05/2019", 00032, "Thong", 00032132, "HELLO", "0901");

            list.add_service_to_list(to_add);
            list.add_service_to_list(to_add1);
            list.add_service_to_list(to_add2);
            list.add_service_to_list(to_add3);
           // list.search_and_display_all_services_after_one_service_date("10/23/2015")        ;
        }
        catch (Exception x) {

            System.out.println("Here is the List ");
        }

        list.display_all_list_services_();
        List_service p2 = new List_service() ;

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        p2.copy_list(list);
        p2.display_all_list_services_();

*/
        // TEST 3


    }

}
