package com.company;

/**
 * Created by ThongTran on 11/20/17.
 */


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
            if (data.get_service_date().before(to_data.get_service_date()))
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
                if(to_check.get_service_date().after(to_data.get_service_date()))
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
    public boolean search_and_display_service ( int iD){
        if ( this.head == null)
        {
            return false;
        }
        return search_and_display_service(this.head, iD);
    }
    private boolean search_and_display_service ( Service curr, int iD){
            if ( curr == null)
            {
                return false;
            }
            if ( curr.compare(iD) == 0)
            {
                curr.display();
                return true;
            }
            return search_and_display_service(curr.next, iD);
    }
    public boolean search_and_update_service ( int iD) {
            if ( this.head == null)
            {
                return false;
            }
            return search_and_update_service(this.head, iD);
    }
    private boolean search_and_update_service ( Service curr, int iD){
            if (curr == null)
            {
                return false;
            }
            if ( curr.compare(iD) == 0 )
            {
                curr.update();
                return true;
            }
            return search_and_display_service(curr.next, iD);
    }

    public static void main(String[] args) {
            Service s1 = new Service(0001, "Thong", 0.3);
            List_service p1 = new List_service(List_Service_Type.all_services_available_provider) ;
            p1.add_service_to_list(s1);
            p1.display_all_list_services_();




    }




}
