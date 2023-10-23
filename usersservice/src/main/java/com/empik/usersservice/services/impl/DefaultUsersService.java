package com.empik.usersservice.services.impl;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.exceptions.FetchUserException;
import com.empik.usersservice.services.UsersService;
import io.vavr.control.Try;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.cache.annotation.CacheResult;
import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultUsersService implements UsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUsersService.class);

    private static final String GITHUB_USERS_API_KEY = "api.github.users";
    private static final String DEFAULT_GITHUB_USERS_API = "https://api.github.com/users";

    private static final String LOGIN_PARAM = "login";
    @Resource
    private Environment environment;

    @Resource
    private RestTemplate restTemplate;

    @CacheResult(cacheName = "users")
    @Override
    public UserDTO fetchUser(String login) throws FetchUserException {
        Map<String, Object> params = new HashMap<>();
        params.put(LOGIN_PARAM, login);
        String usersAPI = environment.getProperty(GITHUB_USERS_API_KEY, String.class, DEFAULT_GITHUB_USERS_API);
        return Try.of(() -> restTemplate.getForEntity(usersAPI + "/{" + LOGIN_PARAM + "}", UserDTO.class, params))
                .onFailure(throwable -> LOGGER.error("Error occurred during fetching user data from API, exception message:{}", throwable.getMessage()))
                .map(ResponseEntity::getBody)
                .getOrElseThrow(throwable -> new FetchUserException(throwable.getMessage()));
    }

}
