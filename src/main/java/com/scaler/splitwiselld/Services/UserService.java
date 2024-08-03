package com.scaler.splitwiselld.Services;


import com.scaler.splitwiselld.Exception.UserAlreadyFoundException;
import com.scaler.splitwiselld.Exception.UserNotFoundException;
import com.scaler.splitwiselld.Models.User;
import com.scaler.splitwiselld.Models.UserStatus;
import com.scaler.splitwiselld.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserService(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }
    public User registerUser(String username, String password,String phoneNumber) throws UserAlreadyFoundException {

        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);

        if (user.isPresent()) {

            if(user.get().getUserStatus().equals(UserStatus.ACTIVE)){
                throw new UserAlreadyFoundException("User already exists");
            }else{

                User updateUser = user.get();
                updateUser.setPassword(password);
                updateUser.setPhoneNumber(phoneNumber);
                updateUser.setUserStatus(UserStatus.ACTIVE);
                updateUser.setUsername(username);
                return userRepository.save(updateUser);
            }
        }
        //return user;

        User RegisterUser = new User();
        RegisterUser.setUsername(username);
        RegisterUser.setPassword(password);
        RegisterUser.setPhoneNumber(phoneNumber);
        RegisterUser.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(RegisterUser);

    }

    public User updateUser(long userId,String password) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found for update");
        }
        else{
            User updateUser = user.get();
            updateUser.setPassword(password);
            return userRepository.save(updateUser);

        }

    }
}
