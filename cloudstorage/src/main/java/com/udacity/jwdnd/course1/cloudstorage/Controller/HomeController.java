package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Entity.File;
import com.udacity.jwdnd.course1.cloudstorage.Entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    NoteMapper noteMapper;
    FileMapper fileMapper;
    CredentialMapper credentialMapper;
    UserMapper userMapper;


    public HomeController(NoteMapper noteMapper, FileMapper fileMapper, CredentialMapper credentialMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.fileMapper = fileMapper;
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("notes", noteMapper.getAllNotes());
        model.addAttribute("credentials", credentialMapper.getAllCredentials());
        model.addAttribute("files", fileMapper.getAllFiles());
        return "home";
    }



    @PostMapping("/note")
    public String newNote(@ModelAttribute("note") Note note, Authentication auth, Model model){
        if (note.getNoteId() == null){
            Note theNote = new Note(null, note.getNoteTitle(),note.getNoteDescription(),userMapper.getUserByName(auth.getName()).getUserId());
            noteMapper.createNote(theNote);
        }else{
            noteMapper.updateNote(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription());
        }
        model.addAttribute("notes", noteMapper.getAllNotes());
        model.addAttribute("credentials", credentialMapper.getAllCredentials());
        model.addAttribute("files", fileMapper.getAllFiles());
       return "home";
    }

    @PostMapping("/deletenote")
    public String deleteNote(Model model, @ModelAttribute("note") Note note){
        noteMapper.deleteNote(note.getNoteId());
        model.addAttribute("notes", noteMapper.getAllNotes());
        model.addAttribute("credentials", credentialMapper.getAllCredentials());
        model.addAttribute("files", fileMapper.getAllFiles());
        return "home";
    }

    @PostMapping("/credential")
    public String newCredential(@ModelAttribute("credential") Credential credential, Authentication auth, Model model){
        if (credential.getCredentialId() == null){
            Credential theCredential = new Credential(null, credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getPassword(), userMapper.getUserByName(auth.getName()).getUserId());
            credentialMapper.insert(theCredential);
        }else{
            credentialMapper.updateCredential(userMapper.getUserByName(auth.getName()).getUserId(), credential.getUrl(),credential.getUsername() ,credential.getPassword() );
        }
        model.addAttribute("notes", noteMapper.getAllNotes());
        model.addAttribute("credentials", credentialMapper.getAllCredentials());
        model.addAttribute("files", fileMapper.getAllFiles());
        return "home";
    }

    @PostMapping("/deletecredential")
    public String deleteCredential(Model model, @ModelAttribute("credential") Credential credential){
        credentialMapper.deleteCredential(credential.getCredentialId());
        model.addAttribute("notes", noteMapper.getAllNotes());
        model.addAttribute("credentials", credentialMapper.getAllCredentials());
        model.addAttribute("files", fileMapper.getAllFiles());
        return "home";
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable int id, Authentication authentication){
        File file = fileMapper.getFile(id);
        ByteArrayResource byteArrayResource = new ByteArrayResource(file.getFileData());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\\\"\" + files.getFilename() + \"\\\"")
                .body(new ByteArrayResource(file.getFileData()));
    }
    @PostMapping("/file")
    public String newFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, Model model) throws IOException {
        File theFile = new File(null,fileUpload.getOriginalFilename(),fileUpload.getContentType(),String.valueOf(fileUpload.getSize()),userMapper.getUserByName(auth.getName()).getUserId(),fileUpload.getBytes());
        fileMapper.insertFile(theFile);
        model.addAttribute("notes", noteMapper.getAllNotes());
        model.addAttribute("credentials", credentialMapper.getAllCredentials());
        model.addAttribute("files", fileMapper.getAllFiles());
        return "home";
    }

    @PostMapping("/deletefile")
    public String deleteFile(Model model, @ModelAttribute("file") File file){
        fileMapper.deleteFile(file.getFileId());
        model.addAttribute("notes", noteMapper.getAllNotes());
        model.addAttribute("credentials", credentialMapper.getAllCredentials());
        model.addAttribute("files", fileMapper.getAllFiles());
        return "home";
    }


}
