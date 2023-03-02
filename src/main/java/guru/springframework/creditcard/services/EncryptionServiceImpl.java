package guru.springframework.creditcard.services;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    @Override
    public String encrypt(String freeText) {
        return Base64.getEncoder().encodeToString(freeText.getBytes());
    }

    @Override
    public String decrypt(String encryptedText) {
        return new String(Base64.getDecoder().decode(encryptedText));
    }
}
