package dev.alejandro.taskmanager.user.domain;

public interface PasswordEncryptor {


    String encrypt(String password);


    String decrypt(String encryptedPassword);
}

