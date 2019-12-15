package com.simplenotes.RESTAPI.Service;

import com.simplenotes.RESTAPI.Exceptions.ResourceNotFoundException;
import com.simplenotes.RESTAPI.Models.Note;
import com.simplenotes.RESTAPI.Repository.NoteRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class NoteServiceImp extends NoteService {
    @Autowired
    private NoteRepository _noteRepository;

    @Override
    public List<Note> getAll() {
        return _noteRepository.findAll();
    }

    @Override
    public Note add(Note o) {
        return _noteRepository.save(o);
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
        return _getNoteFromId(id);
    }

    @Override
    public Note deleteById(int id) {
        Note note = _getNoteFromId(id);
        _noteRepository.deleteById(id);
        return note;
    }

    private Note _getNoteFromId(int id)
    {
        if (!_noteRepository.findById(id).isPresent())
            throw new ResourceNotFoundException( "Note: " + id + " not found." );
        else
            return _noteRepository.findById(id).get();
    }
}
