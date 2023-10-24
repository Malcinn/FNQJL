package com.empik.usersservice.repository;

import com.empik.usersservice.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository handles CRUD operations on {@link UserStats} entity
 *
 * @author Marcin Kowalczyk (marcinkowalczyk1992@gmail.com)
 */
@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, String> {
}

