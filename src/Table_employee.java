import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;
import java.util.LinkedList;
import java.util.Queue;

public class Table_employee extends Thread {  
    public int tableEmployeeID;
    public int numTables;
    public int numSeats;
    public static Queue<Integer> dineInQueue = new LinkedList<>();

    public Table_employee(int tableEmployeeID, int numTables, int numSeats){
        this.tableEmployeeID = tableEmployeeID;
        this.numTables = numTables;
        this.numSeats = numSeats;
    }

    public static long time = System.currentTimeMillis();

    public void msg(String m) {
    System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

    
    @Override
    public void run(){
        msg("Table Employee " + tableEmployeeID + " has started.");

        while(Main.isOpen){
        }
    }
}
