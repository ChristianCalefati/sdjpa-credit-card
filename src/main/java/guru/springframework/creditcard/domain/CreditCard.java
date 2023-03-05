package guru.springframework.creditcard.domain;

import guru.springframework.creditcard.interceptors.EncryptedString;
import guru.springframework.creditcard.jpacallbacks.CreditCardJPACallbacks;
import jakarta.persistence.*;

/**
 * Created by jt on 6/27/22.
 */
@Entity
@EntityListeners(CreditCardJPACallbacks.class)
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EncryptedString
    private String creditCardNumber;

    private String cvv;

    private String expirationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    //@PrePersist
    public void prePersistMethod(){
        System.out.println("I am in PrePersist JPA Callback");
    }
}
