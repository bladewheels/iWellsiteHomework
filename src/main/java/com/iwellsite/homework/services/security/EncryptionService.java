package com.iwellsite.homework.services.security;

/**
 * Created by mshields on 2017-06-19.
 */
public interface EncryptionService {

    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
