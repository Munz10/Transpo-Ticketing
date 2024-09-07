import java.util.Random;

public class TicketTonerTechnician implements Runnable {
    private final TicketMachine ticketMachine;
    private final String name;
    private final int refillAttempts = 3;

    public TicketTonerTechnician(String name, TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        for(int i = 1; i < refillAttempts+1; i++){
            System.out.println("\n- - - Toner Technician Attempt " + i + " - - - \n");
            ticketMachine.refillToner();
            try{
                Random random = new Random();
                Thread.sleep((random.nextInt(10) +1) * 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
