import static java.lang.Thread.sleep;
import java.util.ArrayList;

public class Main {  
    
    public static int numCustomers = 17;
    public static int numTableEmployees = 2;
    public static int numPickupOrderEmployees = 1;
    public static int numTables = 3;
    public static int numSeats = 3;
    

    public static boolean isOpen;
    public static int remainingCustomers = numCustomers;

    

    public static void main(String[] args) {
        // store starts open
        // will turn false at the end.
        isOpen = true;
        System.out.println("The Diner is now open!");
        
        // Thread Creation
        // Used for loops so threads can be ID'd
        for(int i = 1; i <= numPickupOrderEmployees; i++){
            Pickup_order_employee pickUpEmployee = new Pickup_order_employee(i);
            pickUpEmployee.start();
        }

        for(int i = 1; i <= numTableEmployees; i++){
            Table_employee tableEmployee = new Table_employee(i, numTables, numSeats);
            tableEmployee.start();
        }

        for(int i = 1; i <= numCustomers; i++){
            Customer customer = new Customer(i);
            customer.start();
        }
        // sleep() while there are still customers in the store
        // numCustomers will get decremented as customers leave the Diner
        while(remainingCustomers > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        
        // store closes
        // so employee threads can stop working
        System.out.println();
        isOpen = false;
    }    
}