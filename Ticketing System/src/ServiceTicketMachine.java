public interface ServiceTicketMachine {

    int FULL_PAPER_TRAY = 250;
    int FULL_TONER_LEVEL = 100;

    int MINIMUM_TONER_LEVEL = 10;
    int SHEETS_PER_PACK = 50;

    void refillToner();
    void refillPaper();
    void printTicket(Ticket ticket);
}
