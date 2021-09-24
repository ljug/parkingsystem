package com.parkit.parkingsystem.service;

import java.util.Date;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        Date inD = ticket.getInTime();
        Date outD = ticket.getOutTime();
        long diff = outD.getTime() - inD.getTime();
        
        

        //TODO: Some tests are failing here. Need to check if this logic is correct
       
        double duration;
        int d_minutes = (int) (diff / (1000*60));
        if (d_minutes < 15) duration = 0;
        else duration = d_minutes / 60.;
        System.out.println("Duration " + duration);       
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}