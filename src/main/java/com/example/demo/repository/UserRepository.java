package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface UserRepository extends ReactiveCassandraRepository<UserEntity, String> {
}
