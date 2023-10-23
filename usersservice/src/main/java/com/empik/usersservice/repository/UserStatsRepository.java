package com.empik.usersservice.repository;

import com.empik.usersservice.entity.UserStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserStatsRepository extends CrudRepository<UserStats, String> {
}
