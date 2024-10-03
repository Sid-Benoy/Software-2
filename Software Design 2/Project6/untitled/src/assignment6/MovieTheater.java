package assignment6;

import assignment6.Seat.SeatType;
import assignment6.Seat.SeatLetter;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class MovieTheater {

    private int printDelay;
    private SalesLogs log;
    int rumbleRowNum;
    int comfortRowNum;
    int standardRowNum;
    int rumbleRow = 0;
    int comfortRow;
    int standardRow;
    int totalSeats;
    int comfortRowIndex;
    int customerNumber = 0;
    
    /**
     * Constructs a assignment6.MovieTheater, where there are a set number of rows per seat type.
     *
     * @param rumbleNum the number of rows with rumble seats.
     * @param comfortNum the number of rows with comfort seats.
     * @param standardNum the number of rows with standard seats.
     */
    public MovieTheater(int rumbleNum, int comfortNum, int standardNum){
        printDelay = 10;
        log = new SalesLogs();
        rumbleRowNum = rumbleNum*6;
        comfortRowNum = comfortNum*6 + rumbleRowNum;
        standardRowNum = standardNum*6 + comfortRowNum + rumbleRowNum;
        totalSeats = rumbleNum*6 + comfortNum*6 + standardNum*6;
        comfortRow = rumbleRowNum;
        standardRow = comfortRowNum;
        // TODO: Finish implementing this constructor.
    }

    /**
     * Returns the next available seat not yet reserved for a given seat type.
     *
     * @param seatType the type of seat (RUMBLE, COMFORT, STANDARD).
     * @return the next available seat or null if the theater is full.
     */
    public Seat getNextAvailableSeat(SeatType seatType) {

        if (seatType.getIntValue() == 0) {
            Seat returnSeat;
            rumbleRow++;
            String letter = letterOf(rumbleRow % 6);
            SeatLetter place = SeatLetter.valueOf(letter);
            if (rumbleRow <= rumbleRowNum) {
                if(rumbleRow == rumbleRowNum){
                    returnSeat = new Seat(seatType, rumbleRow/6, place);
                }
                else {
                    returnSeat = new Seat(seatType, (rumbleRow / 6) + 1, place);
                }
                return returnSeat;
            } else {
                comfortRow++;
                if (comfortRow <= comfortRowNum) {
                    String letter1 = letterOf(comfortRow%6);
                    SeatLetter place1 = SeatLetter.valueOf(letter1);
                    SeatType comfort = SeatType.COMFORT;
                    if(comfortRow == comfortRowNum){
                        returnSeat = new Seat(comfort, comfortRow/6, place1);
                    }
                    else {
                        returnSeat = new Seat(comfort, (comfortRow / 6) + 1, place1);
                    }
                    return returnSeat;
                } else {
                    standardRow++;
                    if (standardRow <= standardRowNum) {
                        String letter2 = letterOf(standardRow % 6);
                        SeatLetter place2 = SeatLetter.valueOf(letter2);
                        SeatType standard = SeatType.STANDARD;
                        if(standardRow == standardRowNum) {
                            returnSeat = new Seat(standard, (standardRow / 6), place2);
                        }
                        else{
                            returnSeat = new Seat(standard, (standardRow/6)+1, place2);
                        }
                        comfortRow--;
                        return returnSeat;
                    }
                    standardRow--;
                }
                comfortRow--;
            }
            rumbleRow--;
        }

        if (seatType.getIntValue() == 1) {
            Seat returnSeat;
            comfortRow++;
            SeatLetter place;
            String letter = letterOf(comfortRow % 6);
            place = SeatLetter.valueOf(letter);
            if (comfortRow <= comfortRowNum) {
                if(comfortRow == comfortRowNum) {
                    returnSeat = new Seat(seatType, (comfortRow / 6) , place);
                }
                else{
                    returnSeat = new Seat(seatType, (comfortRow/6) +1 , place);
                }
                return returnSeat;
            } else {
                standardRow++;
                if (standardRow <= standardRowNum) {
                    String letter1 = letterOf(standardRow % 6);
                    SeatLetter place1 = SeatLetter.valueOf(letter1);
                    SeatType standard = SeatType.STANDARD;
                    if(standardRow == standardRowNum) {
                        returnSeat = new Seat(standard, (standardRow / 6) , place1);
                    }
                    else{
                        returnSeat = new Seat(standard,(standardRow/6)+1, place1);
                    }
                    comfortRow--;
                    return returnSeat;
                }
                standardRow--;
            }
            comfortRow--;
        }

        if (seatType.getIntValue() == 2) {
            Seat returnSeat;
            standardRow++;
            String letter = letterOf(standardRow % 6);
            SeatLetter place = SeatLetter.valueOf(letter);
            if (standardRow <= standardRowNum) {
                if(standardRow == standardRowNum) {
                    returnSeat = new Seat(seatType, standardRow / 6, place);
                }
                else{
                    returnSeat = new Seat(seatType, (standardRow/6)+1, place);
                }
                return returnSeat;
            }
            standardRow--;
        }

        return null;
    }
    public boolean isSeatAvailable(SeatType seat){
        if(seat.getIntValue() == 0){
            if(rumbleRow++ <= rumbleRowNum){
                rumbleRow--;
                return true;
            }
        }
        if(seat.getIntValue() == 1){
            if(comfortRow++ <= comfortRowNum){
                comfortRow--;
                return true;
            }
        }
        if(seat.getIntValue() == 2){
            if(standardRow++ <= standardRowNum){
                standardRow--;
                return true;
            }
        }
        return false;
    }
    /**
     * Prints a ticket to the console for the customer after they reserve a seat.
     *
     * @param boothId id of the ticket booth.
     * @param seat a particular seat in the theater.
     * @return a movie ticket or null if a ticket booth failed to reserve the seat.
     */
    public Ticket printTicket(String boothId, Seat seat, int customer) {
        Ticket returnTicket = new Ticket(boothId, seat, customer);
        if(this.isSeatAvailable(seat.getSeatType())){
            log.addTicket(returnTicket);
            System.out.println(returnTicket);
            return returnTicket;
        }

        else {
            return null;
        }
    }
    
    /**
     * Lists all seats sold for the movie in the order of reservation.
     *
     * @return list of seats sold.
     */
    public List<Seat> getSeatLog() {
        return log.getSeatLog();
        // TODO: Implement this method.
    }

    /**
     * Lists all tickets sold for the movie in order of printing.
     *
     * @return list of tickets sold.
     */
    public List<Ticket> getTransactionLog() {
        return log.getTicketLog();
        // TODO: Implement this method.

    }

    public String letterOf(int number){
        if(number == 1){
            return "A";
        }
        if(number == 2){
            return "B";
        }
        if(number == 3){
            return "C";
        }
        if(number == 4){
            return "D";
        }
        if(number == 5){
            return "E";
        }
        if(number == 0){
            return "F";
        }
        return null;
    }

    static class SalesLogs {

        private ArrayList<Seat> seatLog;
        private ArrayList<Ticket> ticketLog;

        private SalesLogs() {
            seatLog = new ArrayList<Seat>();
            ticketLog = new ArrayList<Ticket>();
        }

        public List<Seat> getSeatLog() {
            return (List<Seat>)(seatLog.clone());
        }

        public List<Ticket> getTicketLog() {
            return (List<Ticket>)(ticketLog.clone());
        }

        public void addSeat(Seat s) {
            seatLog.add(s);
        }

        public void addTicket(Ticket t) {
            ticketLog.add(t);
        }
    }
}
