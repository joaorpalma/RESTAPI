package com.simplenotes.RESTAPI.Service;

import com.simplenotes.RESTAPI.Exceptions.ResourceNotFoundException;
import com.simplenotes.RESTAPI.Models.Note;
import com.simplenotes.RESTAPI.Models.User;
import com.simplenotes.RESTAPI.Repository.NoteRepository;
import com.simplenotes.RESTAPI.Repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Set<Note> create(int userId, Note note) {
        User user = _getUserFromId(userId);

        Note newNote = new Note();
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        newNote.setLocation(note.getLocation());
        newNote.setLastEdit(note.getLastEdit());
        newNote.getUsers().add(user);

        System.out.println(newNote);

        Set<Note> userNotes = new HashSet<>();
        userNotes.addAll(user.getNotes());
        userNotes.add(newNote);

        user.setNotes(userNotes);

        return _userRepository.save(user).getNotes();
    }

    @Override
    public Note update(Note o) {
        Note note = _getNoteFromId(o.getId());
        note.setTitle(o.getTitle());
        note.setContent(o.getContent());
        note.setLastEdit(o.getLastEdit());
        note.setLocation(o.getLocation());
        return _noteRepository.save(note);
    }

    @Override
    public Note getById(int id) {
        Note note = _getNoteFromId(id);
        System.out.println(note.getUsers());
        return _getNoteFromId(id);
    }

    @Override
    public Note deleteById(int id) {
        Note note = _getNoteFromId(id);
        _noteRepository.deleteById(id);
        return note;
    }

    @Override
    @Transactional
    public Set<User> addUsersToNote(int noteId, Set<String> userIds){
        Set<User> users = new HashSet<>();
        Note note = _getNoteFromId(noteId);

        for (String userId : userIds) {
            User user = _getUserFromId(Integer.parseInt(userId));
            note.getUsers().add(user);
            user.getNotes().add(note);
            _userRepository.save(user);
            users.add(_userRepository.save(user));
        }

        return users;
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
