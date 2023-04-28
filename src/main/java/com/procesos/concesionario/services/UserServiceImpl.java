package com.procesos.concesionario.services;

import com.procesos.concesionario.models.User;
import com.procesos.concesionario.repository.UserRepository;
import com.procesos.concesionario.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userBD = userRepository.findById(id).get();
        userBD.setFirstName(user.getFirstName());
        userBD.setLastName(user.getLastName());
        userBD.setAddress(user.getAddress());
        userBD.setBirthday(user.getBirthday());
        return userRepository.save(userBD);
    }

    @Override
    public String login(User user) {
        Optional<User> userBd = userRepository.findByEmailAndPassword(user.getEmail()
                                                                    , user.getPassword());
        if(userBd.isEmpty()){
            throw new RuntimeException("Usuario no encontrado!");
        }
        return jwtUtil.create(String.valueOf(userBd.get().getId()),
                                String.valueOf(userBd.get().getEmail()));
    }


}





