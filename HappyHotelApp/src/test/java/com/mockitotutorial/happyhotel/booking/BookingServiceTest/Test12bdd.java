package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/* Syntax:
*   when......thenReturn
*   given.....willReturn
* */

@ExtendWith(MockitoExtension.class)
public class Test12bdd {
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

    /*@Test
    void should_CountAvailablePlaces_When_OneRoomAvailable(){
        //given
        when(this.roomService.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("1",5)));
        int expected = 5;
        //Negative case
        //int expected = 1;
    //when
    int actual = bookingService.getAvailablePlaceCount();
    //then
    assertEquals(expected,actual);
}
*/

    @Test
    void should_CountAvailablePlaces_When_OneRoomAvailable(){
        //given
        given(this.roomService.getAvailableRooms())
                .willReturn(Collections.singletonList(new Room("1",5)));
        int expected = 5;
        /*Negative case
        int expected = 1;*/
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }

/*@Test
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

    }*/

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
        then(paymentService).should(times(1)).pay(bookingRequest,300.0);
        verifyNoMoreInteractions(paymentService);

    }
}
