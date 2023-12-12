package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/*
    * mock = dummy object with no real logic.
    * spy = real object with real logic that we can modify.
    * mock_Syntax : when(mock.method()).thenReturn()
    * mock_spies : doReturn().when(spy).method()
* */

/*
    mock(partial mock) = a real object with real methods that we can modify.
*/

public class Test08Spies {
    private BookingService bookingService;
    private PaymentService paymentService;
    private RoomService roomService;
    private BookingDAO bookingDAO;
    private MailSender mailSender;

    @BeforeEach
    void setup(){

        this.paymentService = mock(PaymentService.class);
        this.roomService = mock(RoomService.class);
//        this.bookingDAO = mock(BookingDAO.class);
        this.bookingDAO = spy(BookingDAO.class);
        this.mailSender = mock(MailSender.class);

        this.bookingService = new BookingService(paymentService, roomService, bookingDAO, mailSender);
    }

    @Test
    void should_MakePayment_When_InputOk(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02),
                3,
                true);

        //when
        String bookingID = bookingService.makeBooking(bookingRequest);
        //then
        verify(bookingDAO).save(bookingRequest);
        System.out.println("Booking_Id : "+bookingID);
    }

    @Test
    void should_CancelBooking_When_InputOk(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1",
                LocalDate.of(2023,11,30),
                LocalDate.of(2023,12,02),
                3,
                true);
        bookingRequest.setRoomId("1.3");
        String bookingId = "1";

        doReturn(bookingRequest).when(bookingDAO).get(bookingId);
        //when
        bookingService.cancelBooking(bookingId);
        //then

    }
}
