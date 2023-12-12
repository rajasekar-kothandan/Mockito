package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test05ThrowingExceptions {

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
    void should_ThrowException_When_NoRoomAvailable(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023,11,30),LocalDate.of(2023,12,02),4,false);
        when(roomService.findAvailableRoomId(bookingRequest))
                .thenThrow(BusinessException.class);
        //String expected = "none";
        //when
        //String actual = bookingService.makeBooking(bookingRequest);
        Executable executable = () -> bookingService.makeBooking(bookingRequest);
        //then
        //assertEquals(expected,executable);
        assertThrows(BusinessException.class,executable);
    }
}
