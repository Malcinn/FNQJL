package com.empik.usersservice.strategies;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.strategies.impl.DefaultCalculationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DefaultCalculationStrategyTest {

    @InjectMocks
    private DefaultCalculationStrategy underTests;

    @Test
    public void calculateTestNullUserDTOParam() {
        Double result = underTests.calculate(null);
        Assertions.assertNull(result);
    }

    @Test
    public void calculateTestEmptyUserDTO() {
        UserDTO userDTO = new UserDTO();
        Double result = underTests.calculate(userDTO);
        Assertions.assertNull(result);
    }

    @Test
    public void calculateTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFollowers(6L);
        userDTO.setPublic_repos(10);

        Double result = underTests.calculate(userDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(12D, result, 2);
    }

}
