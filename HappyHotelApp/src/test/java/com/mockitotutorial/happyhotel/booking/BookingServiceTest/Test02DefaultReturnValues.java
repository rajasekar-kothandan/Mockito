package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test02DefaultReturnValues {
    private BookingService bookingService;
    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup(){

        this.paymentServiceMock = mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock,roomServiceMock,bookingDAOMock,mailSenderMock);

        /* Nice mocks default values:
         *  1.Empty list
         *  2.Null object
         *  3.0/false primitives */
        System.out.println("List returned "+ roomServiceMock.getAvailableRooms());
        System.out.println("Object returned "+ roomServiceMock.findAvailableRoomId(null));
        System.out.println("Primitive returned "+ roomServiceMock.getRoomCount());
    }

    /*calculate_AvailablePlaceCount test method was return null without any error*/
    @Test
    void calculate_AvailablePlaceCount(){
        //given
        int expected = 0;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }
}
