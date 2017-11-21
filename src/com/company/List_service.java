package com.company;

/**
 * Created by ThongTran on 11/20/17.
 */


enum List_Service_Type {
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


}
