package com.Artur;

import java.util.*;

public class Theatre {
    private final String theaterName;
    private List<Seat> seats = new ArrayList<>();

    //We are not instantiating Comparator interface.
    //Anonymous inner Class implementing Comparator and providing an implementation of a single compare method.
    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if(seat1.getPrice() < seat2.getPrice()){
                return -1;
            }else if (seat1.getPrice() > seat2.getPrice()){
                return  1;
            }else{
                return 0;
            }
        }
    };


    public Theatre(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;

        int lastRow = 'A' + (numRows -1);

        for(char row = 'A'; row <= lastRow; row++){
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++){
                double price = 12.00;

                if((row < 'D') && (seatNum >=4 && seatNum <= 9)){
                    price = 14.00;
                }else if((row > 'F') || (seatNum >=4 || seatNum <= 9)){
                    price = 7.00;
                }

                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheaterName() {
        return theaterName;
    }



    public boolean reserveSeat(String seatNumber){
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);

//==========================================================================
        //How Does Binary Search Works!!!!
//        int low = 0;
//        int high = seats.size()-1;
//
//        while (low <= high) {
//            System.out.print(".");
//            int mid = (low + high) / 2;
//            Seat midVal = seats.get(mid);
//            int cmp = midVal.getSeatNumber().compareTo(seatNumber);
//
//            if (cmp < 0) {
//                low = mid + 1;
//            } else if (cmp > 0) {
//                high = mid - 1;
//            } else {
//                return seats.get(mid).reserve();
//            }
//        }
//        System.out.println("There is no seat " + seatNumber);
//        return false;
//==========================================================================

        if(foundSeat >= 0){
            return seats.get(foundSeat).reserve();
        }else{
            System.out.println("There is no seat "+ seatNumber);
            return false;
        }
        //OLD  CODE
//        for (Seat seat : seats){
//            System.out.print(".");
//            if(seat.getSeatNumber().equals(seatNumber)){
//                requestedSeat = seat;
//                break;
//            }
//        }
//        if(requestedSeat == null){
//            return false;
//        }
//
//        return requestedSeat.reserve();
    }


    public Collection<Seat> getSeats(){
        return seats;
    }




    public class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve(){
            if(!this.reserved){
                this.reserved = true;
                System.out.println("Seat " + seatNumber+" reserved.");
                return true;
            }
            return false;
        }

        public boolean cancel(String seatNumber){
            if(this.reserved){
                this.reserved = false;
                System.out.println("Reservation of seat "+ seatNumber+ " cancelled.");
                return true;
            }
            System.out.println("Cancellation unsuccessful.");
            return false;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }
    }

}
