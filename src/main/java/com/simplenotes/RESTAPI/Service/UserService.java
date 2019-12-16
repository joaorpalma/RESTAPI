package com.simplenotes.RESTAPI.Service;

import com.simplenotes.RESTAPI.Models.Note;
import com.simplenotes.RESTAPI.Models.User;

import java.util.List;
import java.util.Set;

public abstract class UserService implements MainService<User> {
    public User create(User o) {
        return null;
    }

    public List<User> getAll(){
        return null;
    };

    public Set<Note> getUserNotes(int userId){
        return  null;
    }
}
