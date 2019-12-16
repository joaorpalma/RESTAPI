package com.simplenotes.RESTAPI.Controller;

import com.simplenotes.RESTAPI.Models.Note;
import com.simplenotes.RESTAPI.Models.User;
import com.simplenotes.RESTAPI.Results.ResponseWrapper;
import com.simplenotes.RESTAPI.Service.NoteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.util.List;
import java.util.Set;

import static com.simplenotes.RESTAPI.Constants.ApiConstants.MESSAGE_FOR_REGEX_NUMBER_MISMATCH;
import static com.simplenotes.RESTAPI.Constants.ApiConstants.REGEX_FOR_NUMBERS;

@Validated
@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    @Autowired
    private NoteService _noteService;

    @GetMapping(value = "/{id}")
    public ResponseWrapper<Note> getNoteById(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(_noteService.getById(Integer.parseInt(id)), HttpStatus.OK);
    }

    @PostMapping(value = "create/{userid}")
    public ResponseWrapper<Set<Note>> createNote(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH) @PathVariable(value = "userid") String userid, @Valid @RequestBody Note note) {
        return new ResponseWrapper<>(_noteService.create(Integer.parseInt(userid), note), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseWrapper<Set<User>> addUsersToNote(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH) @PathVariable(value = "id") String id, @Valid @RequestBody Set<String> userIds) {
        return new ResponseWrapper<>(_noteService.addUsersToNote(Integer.parseInt(id), userIds), HttpStatus.OK);
    }

    @PutMapping
    public ResponseWrapper<Note> updateNote(@Valid @RequestBody Note note){
        return new ResponseWrapper<>(_noteService.update(note), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<Note> deleteNote(@Valid @Pattern(regexp = REGEX_FOR_NUMBERS, message = MESSAGE_FOR_REGEX_NUMBER_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(_noteService.deleteById(Integer.parseInt(id)), HttpStatus.OK);
    }
}
