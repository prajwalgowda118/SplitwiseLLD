package com.scaler.splitwiselld.Repository;

import com.scaler.splitwiselld.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findByPhoneNumber(String phoneNumber);

     Optional<User> findById(Long id);

     User save(User user);





}
