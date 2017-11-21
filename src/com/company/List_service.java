package com.company;

/**
 * Created by ThongTran on 11/20/17.
 */
import java.util.Date;

enum  List_Service_Type {
        all_services_available_provider, all_services_provided_provider, all_services_received_customer
}

public class List_service {
        private Service head;
        private Service tail;
        private List_Service_Type type;
        public List_service  (){
               this.head = null;
               this.tail = null;
        }
        public List_service( List_Service_Type type) {
            this();
            this.type = type;
        }



    public void add_service_to_list (Service to_add)
    {
        switch (type)
        {
            case all_services_available_provider:
                add_all_available_services_for_provider(to_add);
                break;
            case all_services_provided_provider:
                add_all_services_provided_for_provider(to_add);
                break;
            case all_services_received_customer:
                add_list_services_received_for_customer(to_add);
                break;
            default:
                break;
        }
    }

    private void add_all_services_provided_for_provider ( Service to_add){
        add_all_available_services_for_provider(to_add);
    }
    private void add_all_available_services_for_provider (Service to_add) {
        Service newNode = new Service(to_add) ;
        if ( head == null){
            head = newNode;
            tail = head;
            return;
        }
        else
        {
            tail.next = newNode;
            tail = newNode;
            return;
        }

    }


    private void add_list_services_received_for_customer ( Service to_add) {
        Service newNode = new Service(to_add);
        // It is being upcasted in main
        ServiceProvided to_data = (ServiceProvided) to_add;
        if ( head == null)
        {
            newNode.next = head;
            head = newNode;
            tail = head;
            return;
        }
        else if ( head != null)
        {
            ServiceProvided data = (ServiceProvided)(head);
            if (data.get_service_date().before(to_data.get_service_date()) == true)
            {
                newNode.next = head;
                head = newNode;
                tail = head;
                return;
            }
            return;
        }
        else
        {
            Service curr = head;
            while (curr.next != null ) {
                curr.next = new ServiceProvided();
                ServiceProvided to_check = new ServiceProvided();
                if(to_check.get_service_date().after(to_data.get_service_date()) == true)
                    curr = curr.next;
            }
            newNode.next = curr.next;
            curr.next = newNode;
            tail = newNode.next;
            return;
        }
    }

    public void display_all_list_services_(){
        if ( head == null)
        {
            System.out.println("The List of services provided is currently empty");
            return;
        }
        else
            display_all_list_services(this.head);
    }
    private void display_all_list_services ( Service curr)
    {
        if(curr == null) return;
        curr.display();
        display_all_list_services(curr.next);
    }
    public boolean search_and_display_service_by_iD ( int iD){
        if ( this.head == null)
        {
            return false;
        }
        return search_and_display_service_by_iD(this.head, iD);
    }
    private boolean search_and_display_service_by_iD ( Service curr, int iD){
            if ( curr == null)
            {
                return false;
            }
            if ( curr.compare(iD) == 0)
            {
                curr.display();
                return true;
            }
            return search_and_display_service_by_iD(curr.next, iD);
    }
    public boolean search_and_update_service_by_iD ( int iD) {
            if ( this.head == null)
            {
                return false;
            }
            return search_and_update_service_by_iD(this.head, iD);
    }
    private boolean search_and_update_service_by_iD ( Service curr, int iD){
            if (curr == null)
            {
                return false;
            }
            if ( curr.compare(iD) == 0 )
            {
                curr.update();
                return true;
            }
            return search_and_update_service_by_iD(curr.next, iD);
    }

    // Delete all services
    public boolean delete_all_services_list () {
        if ( this.head == null)
        {
            return false;
        }
        else {
            this.head = null;
            return true;
        }
    }

    // Search and delete one specific list
    public boolean search_and_delete_service_by_iD ( int iD){
        if ( this.head == null)
        {
            // List is empty
            return false;
        }
        // Check if the first head is the one  to be deleted
        if ( this.head.compare(iD) == 0 )
        {
            head = head.next;
            return true;
        }
        return  search_and_delete_service_by_iD(this.head, iD);
    }
    private boolean search_and_delete_service_by_iD ( Service curr, int iD){

        if ( curr == null){
            return false;
        }
        // Check if the next node is the one to be deleted
        if ( curr.next.compare(iD) == 0)
        {
            Service tmp = curr.next.next;
            curr.next = tmp;
            return true;
        }
        return search_and_delete_service_by_iD(curr.next, iD);
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
        return total_amount_fees_of_the_list(curr.next) + curr.fee;
    }
    // Wrapper function to call the total amount of service fees
    public double total_amount_fees_of_the_list () {
        if ( this.head == null)
        {
            return 0.0;
        }
        return total_amount_fees_of_the_list(this.head);
    }


    private boolean search_and_display_services_in_between_times (Service newNode, Date x, Date y) {
            if ( newNode == null)
            {
                return false;
            }

        ServiceProvided curr = (ServiceProvided) (newNode);
            if ( curr.get_service_date().before(x) == true && curr.get_service_date().after(y) == true)
            {
                curr.display();
            }
            return search_and_display_services_in_between_times(newNode.next,x,y) ;
    }

    public boolean search_and_dispay_services_in_between_times ( Date x, Date y)
    {

        if ( type == List_Service_Type.all_services_available_provider)
        {
            System.out.println("This list shows all the available services that do not have a specific date to display");
            return false;
        }
        if ( this.head == null)
        {
            return false;
        }
        return search_and_display_services_in_between_times(head,x,y);
    }








    public static void main(String[] args) {

        // TEST 1
        /*
            Service s1 = new Service(0001, "Thong", 0.3);
            Service s2 = new Service(0002, "Thong", 0.3);
            Service s3 = new Service(0003, "Thong", 0.3);
            List_service p1 = new List_service(List_Service_Type.all_services_available_provider) ;
            p1.add_service_to_list(s1);
            p1.add_service_to_list(s2);
            p1.add_service_to_list(s3);
            System.out.println(p1.total_amount_fees_of_the_list());
            if (p1.search_and_delete_service_by_iD(3)){
                p1.display_all_list_services_();
            }
            System.out.println(p1.total_amount_fees_of_the_list());
        */


            Service s1 = new Service(0001, "Thong", 0.3);
            Service s2 = new Service(0002, "Thong", 0.3);
            List_service list = new List_service(List_Service_Type.all_services_received_customer);

            try {
                Service to_add = new ServiceProvided(s1, "11/05/2014", 00032, "Thong", 00032132, "HELLO", "DASDAD");
                Service to_add1 = new ServiceProvided(s2, "11/05/2015", 00032, "Thong", 00032132, "HELLO", "DASDAD");
                Service to_add2 = new ServiceProvided(s2, "11/05/2011", 00032, "Thong", 00032132, "HELLO", "DASDAD");
                Service to_add3 = new ServiceProvided(s2, "11/05/2019", 00032, "Thong", 00032132, "HELLO", "DASDAD");

                list.add_service_to_list(to_add);
                list.add_service_to_list(to_add1);
                list.add_service_to_list(to_add2);
                list.add_service_to_list(to_add3);
                list.display_all_list_services_();
            }
            catch (Exception x) {

                System.out.println("Here is the List ");
            }




    }




}
