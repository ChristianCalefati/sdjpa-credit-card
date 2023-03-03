package guru.springframework.creditcard.repositories;

import guru.springframework.creditcard.domain.CreditCard;
import guru.springframework.creditcard.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

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

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void saveCreditCard() {
        CreditCard cc = new CreditCard();
        cc.setCreditCardNumber(creditCardNumber);
        cc.setCvv("123");
        cc.setExpirationDate("12/2028");

        System.out.println("FREE CC: " + cc.getCreditCardNumber());
        System.out.println("ENCRYPTED: " + encryptionService.encrypt(cc.getCreditCardNumber()));

        var savedCC = creditCardRepository.saveAndFlush(cc);

        Map<String, Object> resultMap = jdbcTemplate.queryForMap("SELECT * FROM credit_card cc WHERE cc.id ="
                + savedCC.getId());
        String ccValue = (String) resultMap.get("credit_card_number");

        assertThat(ccValue).isNotEqualTo(savedCC.getCreditCardNumber());
        assertThat(ccValue).isEqualTo(encryptionService.encrypt(savedCC.getCreditCardNumber()));

        var fetchedCC = creditCardRepository.findById(savedCC.getId()).orElse(null);
        assertThat(fetchedCC.getCreditCardNumber().equals(savedCC.getCreditCardNumber())).isTrue();
    }
}