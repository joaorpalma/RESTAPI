package com.simplenotes.RESTAPI.Service;

import com.simplenotes.RESTAPI.Models.Note;
import com.simplenotes.RESTAPI.Models.User;

import java.util.List;
import java.util.Set;

public abstract class NoteService implements MainService<Note> {
    public Set<Note>  create(int userId, Note note){
        return null;
    }

    public Set<User> addUsersToNote(int noteId, Set<String> userIds){
        return null;
    }
}
