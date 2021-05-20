package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note getNote(int noteId);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId} ")
    void deleteNote(int noteId);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription=#{noteDescription} WHERE noteID = #{noteId}")
    int updateNote(int noteId, String noteTitle, String noteDescription);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNote(Note note);
}
