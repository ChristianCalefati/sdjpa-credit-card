package guru.springframework.creditcard.repositories;

import guru.springframework.creditcard.domain.CreditCard;
import guru.springframework.creditcard.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
class CreditCardRepositoryTest {

    final String creditCardNumber = "123456789";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EncryptionService encryptionService;


    @Test
    void saveCreditCard() {
        CreditCard cc = new CreditCard();
        cc.setCreditCardNumber(creditCardNumber);
        cc.setCvv("123");
        cc.setExpirationDate("12/2028");

        System.out.println("FREE CC: " + cc.getCreditCardNumber());
        System.out.println("ENCRYPTED: " + encryptionService.encrypt(cc.getCreditCardNumber()));

        var savedCC = creditCardRepository.saveAndFlush(cc);
        var fetchedCC = creditCardRepository.findById(savedCC.getId()).orElse(null);
        assertThat(fetchedCC.getCreditCardNumber().equals(savedCC.getCreditCardNumber())).isTrue();
    }
}