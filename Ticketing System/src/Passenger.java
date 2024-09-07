import java.util.Random;

public class Passenger implements Runnable{
    private final TicketMachine ticketMachine;
    private final String name;
    private final Ticket ticket;
    private final int noOfTickets;

    public Passenger(String name, TicketMachine ticketMachine, Ticket ticket, int noOfTickets){
        this.name = name;
        this.ticketMachine = ticketMachine;
        this.ticket = ticket;
        this.noOfTickets = noOfTickets;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        for (int i = 0; i < noOfTickets; i++){
            ticketMachine.printTicket(ticket);
            System.out.println("\n- - - - - Ticket Printed - - - - - \nTicket printed by " + Thread.currentThread().getName() + "\n" + ticket + "\n");
//            System.out.println("Ticket printed by " + Thread.currentThread().getName() + " " + ticket);
            Random random = new Random();
            try {
                Thread.sleep((random.nextInt(10) +1) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
