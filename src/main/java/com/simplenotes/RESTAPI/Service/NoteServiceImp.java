package com.simplenotes.RESTAPI.Service;

import com.simplenotes.RESTAPI.Exceptions.ResourceNotFoundException;
import com.simplenotes.RESTAPI.Models.Note;
import com.simplenotes.RESTAPI.Models.User;
import com.simplenotes.RESTAPI.Repository.NoteRepository;
import com.simplenotes.RESTAPI.Repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class NoteServiceImp extends NoteService {
    @Autowired
    private NoteRepository _noteRepository;

    @Autowired
    private UserRepository _userRepository;

    @Override
    public Note create(int userId, Note o) {
        User user = _getUserFromId(userId);
        o.getUsers().add(user);
        return _noteRepository.save(o);
    }

    @Override
    public Note update(Note o) {
        Note note = _getNoteFromId(o.getId());
        note.setTitle(o.getTitle());
        note.setContent(o.getContent());
        note.setLastEdit(o.getLastEdit());
        note.setLocation(o.getLocation());
        note.getUsers().addAll(o.getUsers());
        return _noteRepository.save(note);
    }

    @Override
    public Note getById(int id) {
        return _getNoteFromId(id);
    }

    @Override
    public Note deleteById(int id) {
        Note note = _getNoteFromId(id);
        _noteRepository.deleteById(id);
        return note;
    }

    @Override
    public Set<User> addUsersToNote(int noteId, Set<String> userIds){
        Set<User> users = new HashSet<User>();

        for (String userId : userIds) {
            User user = _getUserFromId(Integer.parseInt(userId));
            users.add(user);
        }

        Note note = _getNoteFromId(noteId);
        note.setUsers(users);

        return _noteRepository.save(note).getUsers();
    }

    private Note _getNoteFromId(int id)
    {
        if (!_noteRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( "Note: " + id + " not found." );
        else
            return _noteRepository.findById(id).get();
    }

    private User _getUserFromId(int id)
    {
        if (!_userRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( "User: " + id + " not found." );
        else
            return _userRepository.findById(id).get();
    }
}
