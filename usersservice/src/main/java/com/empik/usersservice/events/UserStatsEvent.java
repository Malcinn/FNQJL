package com.empik.usersservice.events;

import org.springframework.context.ApplicationEvent;

public class UserStatsEvent extends ApplicationEvent {

    private String login;

    public UserStatsEvent(Object source, String login) {
        super(source);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
