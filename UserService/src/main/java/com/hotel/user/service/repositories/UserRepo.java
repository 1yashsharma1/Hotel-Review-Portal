package com.hotel.user.service.repositories;

import com.hotel.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
