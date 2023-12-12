package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

public class Test07VerifyingBehaviour {
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

    @Test
    void should_Invoke_payment_when_Prepaid(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02),
                3,
                true);

        //when
        bookingService.makeBooking(bookingRequest);
        //then
        verify(paymentService,times(1)).pay(bookingRequest,300.0);
        verifyNoMoreInteractions(paymentService);

    }

    @Test
    void should_NotInvoke_payment_when_NotPrepaid(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02),
                4,
                false);

        //when
        bookingService.makeBooking(bookingRequest);
        //then
        verify(paymentService,never()).pay(any(),anyDouble());
    }
}
