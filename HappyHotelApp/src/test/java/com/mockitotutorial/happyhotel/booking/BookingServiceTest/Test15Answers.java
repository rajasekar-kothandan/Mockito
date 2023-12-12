package com.mockitotutorial.happyhotel.booking.BookingServiceTest;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class Test15Answers {
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
            double expected = 300.0 * 0.8;
            mockedStatic.when(() -> CurrencyConverter.toEuro(anyDouble()))
                    .thenAnswer(invocationOnMock -> (double) invocationOnMock.getArgument(0) * 0.8);

            //when
            double actual = bookingService.calculatePriceEuro(bookingRequest);

            //then
            assertEquals(expected,actual);

        }
    }
}
