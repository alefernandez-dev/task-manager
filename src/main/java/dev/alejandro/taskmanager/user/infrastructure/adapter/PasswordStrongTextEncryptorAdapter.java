package dev.alejandro.taskmanager.user.infrastructure.adapter;

import dev.alejandro.taskmanager.user.domain.PasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

public class PasswordStrongTextEncryptorAdapter implements PasswordEncryptor {

    private final StrongTextEncryptor strongTextEncryptor;

    public PasswordStrongTextEncryptorAdapter() {
        this.strongTextEncryptor = new StrongTextEncryptor();
        strongTextEncryptor.setPassword("secret_key");
    }

    @Override
    public String encrypt(String password) {
        return strongTextEncryptor.encrypt(password);
    }

    @Override
    public String decrypt(String encryptedPassword) {
        return strongTextEncryptor.decrypt(encryptedPassword);
    }
}
