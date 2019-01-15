package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MyUserRepository extends CrudRepository<MyUser,Long> {
    Optional<MyUser> findByName(String name);
}
