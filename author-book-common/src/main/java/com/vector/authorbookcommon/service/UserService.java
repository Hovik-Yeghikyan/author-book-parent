package com.vector.authorbookcommon.service;





import com.vector.authorbookcommon.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);


    Optional<User> findByEmail(String email);
}
