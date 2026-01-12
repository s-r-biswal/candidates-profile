package com.profiles.candiadate.repository;

import com.profiles.candiadate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,String> {

}
