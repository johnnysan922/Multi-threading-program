import java.lang.Math;
import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;
import java.util.Queue;
import java.util.LinkedList;

public class Customer extends Thread {
    public int customerID;
    private int randomint;
    private boolean isSeated = false;

    public Customer(int customerID){
        this.customerID = customerID;
    }

    public static long time = System.currentTimeMillis();

    public void msg(String m) {
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

    @Override
    public void run(){
        this.commute();
        this.arrives();
        int randomInt = (randomint = (int) (Math.random()*10));
        this.dineInOrPickup(randomInt);
    }

    // Customer commutes to diner
    public void commute(){
        try {
            Thread.sleep((long) (Math.random() * 1000));
            msg("Customer " + customerID + " is commuting to the Diner.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
    }
    
    // Customer arrives to diner
    public void arrives(){
        try {
            Thread.sleep((long) (Math.random()* 1000));
            msg("Customer " + customerID + " has arrived to the Diner.");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
    }

    public void dineInOrPickup(int dineInNumber){
        // customer will dine in 
        if (dineInNumber > 2){ 
            msg("Customer " + customerID + " will dine in. (added to Dine In line)");
            Table_employee.dineInQueue.add(customerID);
            msg("Dine in line is:" + Table_employee.dineInQueue);
            // busy wait until customer is seated
            while(!isSeated){ 
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            try {
                this.setPriority(7);
                Thread.sleep(100);
                this.setPriority(5);
            } catch (InterruptedException e) {
            }
        }
        // customer will pick up
        else{ 
            msg("Customer " + customerID + " will pick up.(added to Pick Up line)");
            Pickup_order_employee.pickupQueue.add(this);
            //to see the line
            // System.out.println("Pick-up customers are: " + Pickup_order_employee.pickupQueue);
            //
            msg("Customer " + customerID + " is on the line.(in Busy Wait)");
            while (!this.isInterrupted()) {
                try {
                    Thread.sleep(100);
    
                }catch (InterruptedException e) {
                    msg("Customer " + customerID + " receives their order and leaves the store. (is interrupted and leaves Busy Wait)");
                    break;
                }
                // decrease number of customers in Diner.
            Main.remainingCustomers--;
            }
        }
    }
}
