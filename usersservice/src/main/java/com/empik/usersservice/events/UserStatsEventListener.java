package com.empik.usersservice.events;

import com.empik.usersservice.entity.UserStats;
import com.empik.usersservice.repository.UserStatsRepository;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Listener of {@link UserStatsEvent} events
 *
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
@Component
public class UserStatsEventListener implements ApplicationListener<UserStatsEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatsEventListener.class);

    @Resource
    private UserStatsRepository userStatsRepository;

    @Override
    public void onApplicationEvent(UserStatsEvent event) {
        updateUserStatsData(event.getLogin());
    }

    private void updateUserStatsData(String login) {
        LOGGER.info("UserStatsEventListener invoked");
        UserStats userStats = userStatsRepository.findById(login).orElse(new UserStats(login, new BigDecimal(0)));
        userStats.setRequestCount(userStats.getRequestCount().add(new BigDecimal(1)));
        userStatsRepository.save(userStats);
    }
}
