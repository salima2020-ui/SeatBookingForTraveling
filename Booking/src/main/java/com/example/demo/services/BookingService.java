package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.rabbit.BoardingPassCompEvent;
import com.example.demo.rabbit.BookingCreatedEvent;
import com.example.demo.rabbit.BookingProducer;
import com.example.demo.rabbit.PaymentCompletedEvent;
import com.example.demo.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingPassengerRepo bookingPassengerRepo;
    private final SeatRepository seatRepository;
    private final UserRepo userRepository;
    private final FlightRepository flightRepository;
    private final BookingProducer bookingProducer;
    private final BoardingPassRepository boardingPassRepository;
    private final PassengerRepository passengerReposiitory;
    private final Calculation calculation;

    @Transactional
    public Booking createBooking(Long userId, Long flightId, List<Long> seatIds, List<Long> passengerIds) {
        if (seatIds.size() != passengerIds.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat count and passenger count do not match!");        }
        User user = userRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Flight flight = flightRepository.findById(flightId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingNumber("FLY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        Booking bookingg = bookingRepository.save(booking);

       for(int i = 0; i<seatIds.size(); i++){
           Long seatId = seatIds.get(i);
           Long passengerId = passengerIds.get(i);
           Seat seat = seatRepository.findById(seatId).orElseThrow(()->new RuntimeException("seat template not found"));
           Passenger passenger = passengerReposiitory.findById(passengerId).orElseThrow();
           BookingPassenger bp = new BookingPassenger();
           bp.setBooking(bookingg);
           bp.setPassenger(passenger);
           bp.setSeat(seat);
           bp.setPrice(calculation.calculatePrice(flight, seat.getSeatTemplate()));
           seat.setIsBooked(true);
           seatRepository.save(seat);
           bookingPassengerRepo.save(bp);

       }
       return booking;
    }

    public void payForBooking(Long bookingId, Long userId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        if (!booking.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This booking isn't belong to you!");        }
        bookingProducer.sendBookingCreated(new BookingCreatedEvent(booking.getId(), userId));
    }


    @Transactional
     public List<BoardingPass> crQR(PaymentCompletedEvent event) {
        List<BookingPassenger> bookingPassengers = bookingPassengerRepo.findByBookingId(event.getBookingId());
        List<BoardingPass> boardingPasses = new ArrayList<>();
         for (BookingPassenger bp : bookingPassengers) {
             Flight flight = bp.getSeat().getFlight();
             String qrCode = UUID.randomUUID().toString();
             BoardingPass boardingPass = new BoardingPass();
             boardingPass.setBookingPassenger(bp);
             boardingPass.setQrCode(qrCode);
             boardingPass.setBoardingTime(flight.getDepartureTime().minusHours(1));
             boardingPass.setGate(flight.getGate());
             boardingPass.setFlight(flight);
             BoardingPass savedPass = boardingPassRepository.save(boardingPass);
             boardingPasses.add(savedPass);
         }
         for(BoardingPass pass : boardingPasses ){
             bookingProducer.sendBoardingPassCr(new BoardingPassCompEvent(
                     pass.getId(),
                     pass.getUser().getId(),
                     pass.getFlight().getAirline().getId(),
                     pass.getBookingPassenger().getPassenger().getFirstName() + " " + pass.getBookingPassenger().getPassenger().getLastName(),
                     pass.getGate(),
                     pass.getSeat().getSeatTemplate().getSeatNumber(),
                     pass.getQrCode(),
                     pass.getBoardingTime()));
        }
       return boardingPasses;
    }

}
