package com.empik.usersservice.services;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.exceptions.FetchUserException;

/**
 * Service for fetching users data.
 *
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
public interface UsersService {
    UserDTO fetchUser(String login) throws FetchUserException;

}
