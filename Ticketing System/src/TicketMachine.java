import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TicketMachine implements ServiceTicketMachine{
    private int ticketsPrinted = 0;
    private int tonerLevel;
    private int paperLevel;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition refillToner = lock.newCondition();
    private final Condition refillPaper = lock.newCondition();
    private final Condition resourcesLacking = lock.newCondition();


    public TicketMachine(int paperLevel, int tonerLevel) {
        this.tonerLevel = tonerLevel;
        this.paperLevel = paperLevel;
    }


    @Override
    public void refillToner() {
        try {
            lock.lock();
            while (tonerLevel >= MINIMUM_TONER_LEVEL) {
                    System.out.println("No need to refill toner at the moment ");
                    refillToner.await();
//                    break;
            }
                tonerLevel = FULL_TONER_LEVEL;
                System.out.println("Toner refilled by " + Thread.currentThread().getName() + ". Toner level : " + tonerLevel);
                resourcesLacking.signalAll();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void refillPaper() {
        try {
            lock.lock();
            while (paperLevel + SHEETS_PER_PACK > FULL_PAPER_TRAY) {
                System.out.println("No Need to refill paper at the moment");
                refillPaper.await();
            }
            paperLevel += SHEETS_PER_PACK;
            System.out.println("Paper refilled by " + Thread.currentThread().getName() + ". Paper level : " + paperLevel);
            resourcesLacking.signalAll();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void printTicket(Ticket ticket) {
        try{
            lock.lock();
            while (tonerLevel < MINIMUM_TONER_LEVEL || paperLevel < 1) {
                System.out.println("Unable to print the ticket due to resource unavailability");
                resourcesLacking.await();
            }
            tonerLevel = tonerLevel-5;
            paperLevel--;
            ticketsPrinted++;
            ticket.setTicketNumber();
            refillToner.signalAll();
            refillPaper.signalAll();
            }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "TicketMachine{" +
                "ticketsPrinted=" + ticketsPrinted +
                ", tonerLevel=" + tonerLevel +
                ", paperLevel=" + paperLevel +
                '}';
    }
}
