package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Entity.Credential;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAllCredentials();

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredential(int credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId} ")
    void deleteCredential(int credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username=#{username},password=#{password} WHERE credentialId = #{credentialId}")
    int updateCredential(int credentialId, String url, String username, String password);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);
}
