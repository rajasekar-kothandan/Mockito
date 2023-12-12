package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01MockAnnotation {
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private RoomService roomService;
    @Mock
    private BookingDAO bookingDAO;
    @Mock
    private MailSender mailSender;

    @BeforeEach
    void setup(){
        this.bookingService = new BookingService(paymentService, roomService, bookingDAO, mailSender);
    }

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
