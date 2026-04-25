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

    @Transactional
    public Booking createBooking(Long userId, Long flightId, List<Long> seatIds, List<Passenger> passenger) {
        if (seatIds.size() != passenger.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat count and passenger count do not match!");        }
        User user = userRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Flight flight = flightRepository.findById(flightId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingNumber("FLY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        Booking bookin = bookingRepository.save(booking);

       for(int i = 0; i<seatIds.size(); i++){
           Long id = seatIds.get(i);
           Passenger p = passenger.get(i);
           Seat seat = seatRepository.findById(id).get();
           BookingPassenger bp = new BookingPassenger();
           bp.setBooking(bookin);
           bp.setPassenger(p);
           bp.setSeat(seat);
           bp.setPrice(flight.getPrice());
           seat.setIsBooked(true);
           seatRepository.save(seat);
           bookingPassengerRepo.save(bp);

       }
       bookingProducer.sendBookingCreated(new BookingCreatedEvent(booking.getId(), userId));
       return booking;
    }

    public void crQR(PaymentCompletedEvent event){
 List<BookingPassenger> bookingPassengers = bookingPassengerRepo.findByBookingId(event.getBookingId());
 for(BookingPassenger bp : bookingPassengers){
     Flight flight = bp.getSeat().getFlight();
     String qrCode = UUID.randomUUID().toString();
     BoardingPass boardingPass = new BoardingPass();
     boardingPass.setPassenger(bp);
     boardingPass.setQrCode(qrCode);
     boardingPass.setBoardingTime(flight.getDepartureTime().minusHours(1));
     boardingPass.setGate(flight.getGate());
     BoardingPass boardingPasss = boardingPassRepository.save(boardingPass);
     bookingProducer.sendBoardingPassCr(new BoardingPassCompEvent(boardingPasss.getId(), boardingPasss.getPassenger().getId(), boardingPasss.getQrCode(), boardingPasss.getBoardingTime()));
 }
    }


}
