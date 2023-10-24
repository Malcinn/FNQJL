package com.empik.usersservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
@Entity
@Table(name = UserStats.TABLE_NAME)
public class UserStats {

    public static final String TABLE_NAME = "USER_STATS";
    @Id
    private String login;

    private BigDecimal requestCount;

    protected UserStats() {
    }

    public UserStats(String login, BigDecimal requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    public String getLogin() {
        return login;
    }

    public BigDecimal getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(BigDecimal requestCount) {
        this.requestCount = requestCount;
    }
}
