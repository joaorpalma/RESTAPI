package com.simplenotes.RESTAPI.Service;

import com.simplenotes.RESTAPI.Exceptions.ResourceNotFoundException;
import com.simplenotes.RESTAPI.Models.User;
import com.simplenotes.RESTAPI.Repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class UserServiceImp extends UserService {
    @Autowired
    private UserRepository _userRepository;

    @Override
    public List<User> getAll() {
        return _userRepository.findAll();
    }

    @Override
    public User add(User o) {
        return _userRepository.save(o);
    }

    @Override
    public User update(User o) {
        User user = _getUserFromId(o.getId());
        user.setName(o.getName());
        user.setEmail(o.getEmail());
        user.setPhoto(o.getPhoto());
        user.setPushNotificationId(o.getPushNotificationId());
        return _userRepository.save(user);
    }

    @Override
    public User getById(int id) {
        return _getUserFromId(id);
    }

    @Override
    public User deleteById(int id) {
        User user = _getUserFromId(id);
        _userRepository.deleteById(id);
        return user;
    }

    private User _getUserFromId(int id)
    {
        if (!_userRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( "User: " + id + " not found." );
        else
            return _userRepository.findById(id).get();
    }
}
