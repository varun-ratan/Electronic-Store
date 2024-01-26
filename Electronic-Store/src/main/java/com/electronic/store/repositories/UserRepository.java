package com.electronic.store.repositories;

import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<UserDto> findByEmail(String email);

    Optional<UserDto> findByEmailAndPassword(String email, String Password);

    List<UserDto> findByNameContaining(String keywords);

}
