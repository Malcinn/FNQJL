package com.empik.usersservice.services;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.exceptions.FetchUserException;
import com.empik.usersservice.services.impl.DefaultUsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class DefaultUsersServiceTest {

    @InjectMocks
    private DefaultUsersService underTests;

    @Mock
    private Environment environment;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        Mockito.when(environment.getProperty("api.github.users", String.class, "https://api.github.com/users")).thenReturn("https://api.github.com/users");
    }

    @Test()
    public void fetchUserTestException() {
        String login = "test";
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any(Class.class), Mockito.anyMap())).thenThrow(new RestClientException(""));
        Assertions.assertThrows(FetchUserException.class, () -> underTests.fetchUser(login));
    }

    @Test()
    public void fetchUserTest() throws FetchUserException {
        String login = "test";
        UserDTO userDTO = new UserDTO();
        userDTO.setId(123L);
        ResponseEntity<UserDTO> response = new ResponseEntity<>(userDTO, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any(Class.class), Mockito.anyMap())).thenReturn(response);

        UserDTO result = underTests.fetchUser(login);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(123L, result.getId());
    }

}
