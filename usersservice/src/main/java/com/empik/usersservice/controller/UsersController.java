package com.empik.usersservice.controller;

import com.empik.usersservice.data.ErrorWsDTO;
import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.data.UserWsDTO;
import com.empik.usersservice.events.UserStatsEvent;
import com.empik.usersservice.exceptions.FetchUserException;
import com.empik.usersservice.services.UsersService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Resource
    private UsersService usersService;

    @Resource
    private ConversionService conversionService;

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * TO TO
     * ADD TESTS
     *
     * @param login
     * @return
     */
    @GetMapping(value = "/users/{login}")
    public ResponseEntity<UserWsDTO> getUser(@PathVariable String login) throws FetchUserException {
        LOGGER.info("Getting user data for login {}", login);
        applicationEventPublisher.publishEvent(new UserStatsEvent(this, login));
        UserDTO userDTO = usersService.fetchUser(login);
        return new ResponseEntity<>(conversionService.convert(userDTO, UserWsDTO.class), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({FetchUserException.class})
    public ErrorWsDTO handleFetchUserException(final Exception ex) {
        ErrorWsDTO error = new ErrorWsDTO();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("User does not exist or service temporarily unavailable");
        return error;
    }

}
