package com.empik.usersservice.strategies.impl;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.strategies.CalculationStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Default implementation of {@link CalculationStrategy}.
 *
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
@Component
public class DefaultCalculationStrategy implements CalculationStrategy {
    @Override
    public Double calculate(UserDTO userDTO) {
        if (Objects.nonNull(userDTO) && Objects.nonNull(userDTO.getFollowers()) && userDTO.getFollowers() > 0 &&
                Objects.nonNull(userDTO.getPublic_repos())) {
            return 6.0 / userDTO.getFollowers() * (2 + userDTO.getPublic_repos());
        }
        return null;
    }
}
