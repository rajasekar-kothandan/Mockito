package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test13StictStubbing {
    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private RoomService roomService;
    @Spy
    private BookingDAO bookingDAO;
    @Mock
    private MailSender mailSender;
    @Captor
    private ArgumentCaptor<Double> doubleArgumentCaptor;

    @Test
    void should_Invoke_payment_when_Prepaid(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02),
                3,
                false);
        /* Unnecessary stubbings detected, for below code..........
        when(paymentService.pay(any(),anyDouble())).thenReturn("1");*/
        lenient().when(paymentService.pay(any(),anyDouble())).thenReturn("1");

        //when
        bookingService.makeBooking(bookingRequest);
        //then
        //no exception is thrown

    }
}
