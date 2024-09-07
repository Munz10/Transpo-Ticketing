import java.util.Random;

public class TicketPaperTechnician implements Runnable {
    private final TicketMachine ticketMachine;
    private final String name;
    private final int refillAttempts = 3;

    public TicketPaperTechnician(String name, TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        for(int i = 0; i < refillAttempts; i++){
            System.out.println("\n- - - Paper Technician Attempt " + i + " - - - \n");
            ticketMachine.refillPaper();
            try{
                Random random = new Random();
                Thread.sleep((random.nextInt(10) +1) * 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}


