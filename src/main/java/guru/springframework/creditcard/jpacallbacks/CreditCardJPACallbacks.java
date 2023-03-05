package guru.springframework.creditcard.jpacallbacks;

import guru.springframework.creditcard.config.SpringContextHelper;
import guru.springframework.creditcard.domain.CreditCard;
import guru.springframework.creditcard.services.EncryptionService;
import guru.springframework.creditcard.services.EncryptionServiceImpl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CreditCardJPACallbacks {

    @PrePersist
    @PreUpdate
    public void onPersistAndUpdate(CreditCard creditCard){
        System.out.println("Entity persisted or Updated.....");
        creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void onLoad(CreditCard creditCard){
        System.out.println("Entity loaded....");
        creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
    }

    private EncryptionService getEncryptionService(){
        return SpringContextHelper.getApplicationContext().getBean(EncryptionServiceImpl.class);
    }
}
