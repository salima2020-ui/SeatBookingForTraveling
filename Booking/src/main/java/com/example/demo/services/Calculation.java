package com.example.demo.services;

import com.example.demo.entities.Airport;
import com.example.demo.entities.Flight;
import com.example.demo.entities.Seat;
import com.example.demo.entities.SeatTemplate;
import com.example.demo.repositories.AirportRepository;
import com.example.demo.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class Calculation {
private final FlightRepository flightRepo;
private final AirportRepository airportRepository;
    public BigDecimal calculatePrice(Flight flight, SeatTemplate seat){
      BigDecimal flightPrice = flight.getBasePrice();
      double airportDepPrice = flight.getDepartureAirport().getServiceFee();
      double arrivalAirportPrice = flight.getArrivalAirport().getServiceFee();
      double seatPrice = seat.getSeatClass().getPrice().doubleValue();
      double sum = airportDepPrice + arrivalAirportPrice + seatPrice;
      return flightPrice.add(BigDecimal.valueOf(sum));
    }
}
