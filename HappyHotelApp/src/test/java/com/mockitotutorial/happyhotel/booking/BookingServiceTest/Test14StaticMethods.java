package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mockStatic;

/* Older:
        <dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>3.5.13</version>
			<scope>test</scope>
		</dependency>
* */

/* New:
        <dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-inline</artifactId>
			<version>3.5.13</version>
			<scope>test</scope>
		</dependency>
* */

@ExtendWith(MockitoExtension.class)
public class Test14StaticMethods {
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
        try(MockedStatic<CurrencyConverter> mockedStatic = mockStatic(CurrencyConverter.class)){
            //given
            BookingRequest bookingRequest = new BookingRequest("1",
                    LocalDate.of(2023,11,30),
                    LocalDate.of(2023,12,02),
                    3,
                    false);
            double expected = 400.0;
            mockedStatic.when(() -> CurrencyConverter.toEuro(anyDouble())).thenReturn(400.0);

            //when
            double actual = bookingService.calculatePriceEuro(bookingRequest);

            //then
            assertEquals(expected,actual);

        }
    }
}
