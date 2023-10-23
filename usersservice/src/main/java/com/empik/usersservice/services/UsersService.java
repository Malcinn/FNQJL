package com.empik.usersservice.services;

import com.empik.usersservice.data.UserDTO;
import com.empik.usersservice.exceptions.FetchUserException;

public interface UsersService {
    UserDTO fetchUser(String login) throws FetchUserException;
    
}
