package com.udacity.jwdnd.course1.cloudstorage.Mapper;


import com.udacity.jwdnd.course1.cloudstorage.Entity.File;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFile(int fileId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId} ")
    void deleteFile(int fileId);


    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES (#{fileName}, #{contentType},#{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);
}
