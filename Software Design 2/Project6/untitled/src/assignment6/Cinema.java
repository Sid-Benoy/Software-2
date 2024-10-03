package assignment6;

import  assignment6.Seat.SeatType;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Cinema {
    List<String> nameBooths = new ArrayList<>();
    Map<String, Integer> seatSizeTrack = new HashMap<>();
    Map<String, List<Integer>> customerIndex = new HashMap<>();
    Map<String, SeatType[]> booths;
    MovieTheater theater;
    int seatsSize;
    int customers;
    int customer =1;
    int totalSeats;

    /**
     * Constructor to initilize the simulation based on starter parameters. 
     * 
     * @param booths maps ticket booth id to seat type preferences of customers in line.
     * @param movieTheater the theater for which tickets are sold.
     */
    public Cinema(Map<String, SeatType[]> booths, MovieTheater movieTheater) {
        List<Integer> yo = new ArrayList<>();
        int index = 0;
        int totalCustomers = 0;
        theater = movieTheater;
        totalSeats = movieTheater.totalSeats;
        this.booths = booths;
        for(Map.Entry<String, SeatType[]> entry : booths.entrySet()){
            nameBooths.add(entry.getKey());
            seatsSize = entry.getValue().length;
            yo.clear();
            for(int i = 0; i < entry.getValue().length; i++){
                yo.add(index);
                index++;
            }
            customerIndex.put(entry.getKey(), yo);
            seatSizeTrack.put(entry.getKey(), 0);
            totalCustomers += seatsSize;
        }
        customers = totalCustomers;
        ArrayBlockingQueue<Seat> movieTheatre = new ArrayBlockingQueue<>(totalSeats);
        // TODO: Implement this constructor
    }

    /**
     * Starts the ticket office simulation by creating (and starting) threads
     * for each ticket booth to sell tickets for the given movie.
     *
     * @return list of threads used in the simulation,
     *   should have as many threads as there are ticket booths.
     */
    public List<Thread> simulate(){
        int i = 0;
        Object o1 = new Object();
        Object o2 = new Object();
        List<Thread> threads = new ArrayList<>();
        for(String string : nameBooths){
            Thread booth = new Thread(new Runnable() {
                @Override
                public void run() {
                    int seatSize = seatSizeTrack.get(string);
                    while(customer!= customers && seatSizeTrack.get(string)!= booths.get(string).length){
                        Seat temp;
                        synchronized (o1) {
                            temp = theater.getNextAvailableSeat(booths.get(string)[seatSizeTrack.get(string)]);
                            if (temp != null) {
                                theater.printTicket(string, temp, customer);
                                customer++;
                                seatSize++;
                                seatSizeTrack.replace(string, seatSize);
                            }
                        }
                    }
                }

            }, string);
            booth.start();
            threads.add(booth);
        }


        for(Thread thread : threads){
            try {
                thread.join();
            }
            catch(Exception e){
            }
        }

        // TODO: Implement this method.
        return threads;
    }

    public static void main(String[] args) {

        Map<String, SeatType[]> booths = new HashMap<String, SeatType[]>();
        booths.put("TO1", new SeatType[] { SeatType.COMFORT, SeatType.COMFORT,
                SeatType.COMFORT });
        booths.put("TO3", new SeatType[] { SeatType.COMFORT, SeatType.STANDARD,
                SeatType.STANDARD });
        booths.put("TO2", new SeatType[] { SeatType.RUMBLE, SeatType.COMFORT,
                SeatType.STANDARD, SeatType.STANDARD });
        booths.put("TO5", new SeatType[] { SeatType.COMFORT, SeatType.COMFORT,
                SeatType.COMFORT });
        booths.put("TO4", new SeatType[] { SeatType.STANDARD, SeatType.STANDARD,
                SeatType.STANDARD });
        Cinema client = new Cinema(booths, new MovieTheater(1, 1, 1));
        client.simulate();


        // For your testing purposes. We will not call this method.        
    }
}
