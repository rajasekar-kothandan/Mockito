package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Test03ReturningCustomValues {
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
    void should_CountAvailablePlaces_When_OneRoomAvailable(){
        //given
        when(this.roomService.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("1",5)));
        int expected = 5;
        /*Negative case
        int expected = 1;*/
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }

    @Test
    void should_CountAvailablePlaces_When_MultipleRoomAvailable(){
        //given
        List<Room> rooms = Arrays.asList(new Room("1",5), new Room("2",10));
        when(this.roomService.getAvailableRooms())
                .thenReturn(rooms);
        int expected = 15;
        /*Negative case
        int expected = 4;*/
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }
}
