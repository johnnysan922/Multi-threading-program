import static java.lang.Thread.sleep;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Pickup_order_employee extends Thread {
    public int pickupOrderEmployeeID;  
    public static Queue<Customer> pickupQueue;

    public Pickup_order_employee(int pickupOrderEmployeeID){
        this.pickupOrderEmployeeID = pickupOrderEmployeeID;
        pickupQueue = new LinkedList<>();
    }
    public static long time = System.currentTimeMillis();

    public void msg(String m) {
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

    @Override
    public void run() {
        msg("Pickup Order Employee " + pickupOrderEmployeeID + " has started.");

        // while the diner is open
        while(Main.isOpen){
            while(pickupQueue.size() > 0){
                try {
                    Thread.sleep(500); // order takes time to make
                } catch (InterruptedException e) {
                }
                // pickup employee removes a customer from the pickupQueue(line)
                // customer is created with the first int from queue
                Customer customer = pickupQueue.poll(); // poll() returns first element from list
                customer.interrupt();
            }
        }
    }
    
}
