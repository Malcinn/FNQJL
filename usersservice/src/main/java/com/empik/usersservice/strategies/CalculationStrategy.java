package com.empik.usersservice.strategies;

import com.empik.usersservice.data.UserDTO;

public interface CalculationStrategy {

    Double calculate(UserDTO userDTO);
}
