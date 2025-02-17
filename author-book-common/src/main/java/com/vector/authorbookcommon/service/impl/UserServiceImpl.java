package com.vector.authorbookcommon.service.impl;



import com.vector.authorbookcommon.entity.User;
import com.vector.authorbookcommon.repository.UserRepository;
import com.vector.authorbookcommon.service.MailService;
import com.vector.authorbookcommon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public User save(User user) {
        User savedUser = userRepository.save(user);
        mailService.sendMail(savedUser.getEmail(),"Welcome","Welcome to the Author Book Common Service");
        return savedUser;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
