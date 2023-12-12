package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/* Final and Private methods:
*   Final methods    >> Use mockito-inline
*   Private methods  >> Don't try to mock them.
* */

@ExtendWith(MockitoExtension.class)
public class Test16FinalMethod {
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

    @Test
    void should_CountAvailablePlaces_When_OneRoomAvailable() {
        //given
        /* Before run the method Make it final of getAvailableRooms() in RoomService.class then run and check it.*/
        when(this.roomService.getAvailableRooms())
                .thenReturn(Collections.singletonList(new Room("1", 5)));
        int expected = 5;

        //when
        int actual = bookingService.getAvailablePlaceCount();

        //then
        assertEquals(expected, actual);
    }
}
