package com.empik.usersservice.events;

import com.empik.usersservice.entity.UserStats;
import com.empik.usersservice.repository.UserStatsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;


@ExtendWith(value = {SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureCache
@AutoConfigureDataJpa
public class UserStatsEventListenerTest {

    @Autowired
    private UserStatsEventListener underTests;

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Mock
    private UserStatsEvent event;

    @Test
    public void onApplicationEventTestUserNotFound() {
        String login = "test";
        Mockito.when(event.getLogin()).thenReturn(login);

        underTests.onApplicationEvent(event);

        Optional<UserStats> userStats = userStatsRepository.findById(login);
        Assertions.assertTrue(userStats.isPresent());
        Assertions.assertEquals(1L, userStats.get().getRequestCount().longValue());
    }

    @Test
    public void onApplicationEventTest() {
        String login = "test2";

        Mockito.when(event.getLogin()).thenReturn(login);

        underTests.onApplicationEvent(event);
        Optional<UserStats> result = userStatsRepository.findById(login);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(11, result.get().getRequestCount().longValue());
    }
}
