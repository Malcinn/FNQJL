package com.empik.usersservice.strategies;

import com.empik.usersservice.data.UserDTO;

/**
 * Calculation Strategy
 *
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
public interface CalculationStrategy {

    Double calculate(UserDTO userDTO);
}
