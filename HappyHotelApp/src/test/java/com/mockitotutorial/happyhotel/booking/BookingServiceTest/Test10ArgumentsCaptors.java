package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class Test10ArgumentsCaptors {
    private BookingService bookingService;
    private PaymentService paymentService;
    private RoomService roomService;
    private BookingDAO bookingDAO;
    private MailSender mailSender;
    private ArgumentCaptor<Double> doubleArgumentCaptor;

    @BeforeEach
    void setup(){
        this.paymentService = mock(PaymentService.class);
        this.roomService = mock(RoomService.class);
        this.bookingDAO = mock(BookingDAO.class);
        this.mailSender = mock(MailSender.class);

        this.bookingService = new BookingService(paymentService, roomService, bookingDAO, mailSender);

        this.doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
    }

    @Test
    void should_PayCorrectPrice_When_InputOK(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02), 3,
                true);

        //when
        bookingService.makeBooking(bookingRequest);
        //then
        verify(paymentService,times(1)).pay(eq(bookingRequest),doubleArgumentCaptor.capture());
        double caputureArgument = doubleArgumentCaptor.getValue();

        assertEquals(300.0, caputureArgument);

    }

    @Test
    void should_PayCorrectPrice_When_MultipleCalls(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02), 3,
                true);
        BookingRequest bookingRequest2 = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02), 4,
                true);
        List<Double> expectedValues = Arrays.asList(300.0,400.0);

        //when
        bookingService.makeBooking(bookingRequest);
        bookingService.makeBooking(bookingRequest2);

        //then
        verify(paymentService,times(2)).pay(any(),doubleArgumentCaptor.capture());
        List<Double> caputureArguments = doubleArgumentCaptor.getAllValues();

        assertEquals(expectedValues, caputureArguments);

    }
}
