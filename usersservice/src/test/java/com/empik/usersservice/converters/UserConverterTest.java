package com.empik.usersservice.converters;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.data.UserWsDTO;
import com.empik.usersservice.strategies.CalculationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter underTests;

    @Mock
    private CalculationStrategy calculationStrategy;

    @Mock
    private UserDTO source;

    @Test
    public void convertTestSourceIsNull() {
        UserWsDTO result = underTests.convert(null);

        Assertions.assertNotNull(result);
    }

    @Test
    public void convertTest() {
        Mockito.when(source.getId()).thenReturn(123L);
        Mockito.when(source.getLogin()).thenReturn("test");
        Mockito.when(source.getName()).thenReturn("Test");
        Mockito.when(source.getAvatar_url()).thenReturn("testURL");
        Mockito.when(source.getType()).thenReturn("testType");
        Mockito.when(source.getCreated_at()).thenReturn(new Date());
        Mockito.when(calculationStrategy.calculate(source)).thenReturn(5.0);

        UserWsDTO result = underTests.convert(source);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(123L, result.getId().longValue());
        Assertions.assertEquals("test", result.getLogin());
        Assertions.assertEquals("Test", result.getName());
        Assertions.assertEquals("testURL", result.getAvatarUrl());
        Assertions.assertEquals("testType", result.getType());
        Assertions.assertNotNull(result.getCreatedAt());
        Assertions.assertEquals(5.0, result.getCalculations().doubleValue(), 0);
    }
}
