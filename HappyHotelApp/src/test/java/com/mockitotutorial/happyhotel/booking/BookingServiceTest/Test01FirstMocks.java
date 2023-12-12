package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test01FirstMocks {
    private BookingService bookingService;
    private PaymentService paymentService;
    private RoomService roomService;
    private BookingDAO bookingDAO;
    private MailSender mailSender;

    @BeforeEach
    void setup(){
        this.paymentService = mock(PaymentService.class);
        this.roomService = mock(RoomService.class);
        this.bookingDAO = mock(BookingDAO.class);
        this.mailSender = mock(MailSender.class);

        this.bookingService = new BookingService(paymentService, roomService, bookingDAO, mailSender);
    }

    /* Syntax to write Test cases */
    /*@Test
    void should_calculatePrice(){
        //given
        double expected = 50.0 * 4 * 2;
        //when
        BookingRequest bookingRequest = null;
        double actual = bookingService.calculatePrice(bookingRequest);
        //Then
        assertEquals(expected,actual);
    }*/

    @Test
    void should_calculatePrice(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023,11,30),LocalDate.of(2023,12,02),4,false);
        double expected = 50.0 * 4 * 2;
        //when
        double actual = bookingService.calculatePrice(bookingRequest);
        System.out.println("Expected :"+expected + " Actual "+actual);
        //Then
        assertEquals(expected,actual);
    }
}
