public class TicketPrintingSystem {
    public static void main(String[] args) {
        ThreadGroup passengers = new ThreadGroup("Passengers");
        ThreadGroup technicians = new ThreadGroup("Technicians");
        TicketMachine ticketMachine = new TicketMachine(200,100);

        Ticket ticket1 = new Ticket(100.00);
        Ticket ticket2 = new Ticket(200.00);
        Ticket ticket3 = new Ticket(300.00);
        Ticket ticket4 = new Ticket(400.00);

        Passenger passenger1 = new Passenger("Passenger 1", ticketMachine, ticket1, 3);
        Passenger passenger2 = new Passenger("Passenger 2", ticketMachine, ticket2, 4);
        Passenger passenger3 = new Passenger("Passenger 3", ticketMachine, ticket3, 2);
        Passenger passenger4 = new Passenger("Passenger 4", ticketMachine, ticket4, 1);

        TicketTonerTechnician tonerTechnician = new TicketTonerTechnician("Toner Technician", ticketMachine);
        TicketPaperTechnician paperTechnician = new TicketPaperTechnician("Paper Technician", ticketMachine);

        Thread passengerThread1 = new Thread(passengers, passenger1, passenger1.getName());
        Thread passengerThread2 = new Thread(passengers, passenger2, passenger2.getName());
        Thread passengerThread3 = new Thread(passengers, passenger3, passenger3.getName());
        Thread passengerThread4 = new Thread(passengers, passenger4, passenger4.getName());

        Thread tonerTechnicianThread = new Thread(technicians, tonerTechnician, tonerTechnician.getName());
        Thread paperTechnicianThread = new Thread(technicians, paperTechnician, paperTechnician.getName());

        passengerThread1.start();
        passengerThread2.start();
        passengerThread3.start();
        passengerThread4.start();

        tonerTechnicianThread.start();
        paperTechnicianThread.start();

        System.out.println(ticketMachine);

    }
}
