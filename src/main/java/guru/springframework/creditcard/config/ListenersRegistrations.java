package guru.springframework.creditcard.config;

import guru.springframework.creditcard.listeners.PostLoadListener;
import guru.springframework.creditcard.listeners.PreInsertListener;
import guru.springframework.creditcard.listeners.PreUpdateListener;
import lombok.AllArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
public class ListenersRegistrations implements BeanPostProcessor {
    private final PreUpdateListener preUpdateListener;
    private final PreInsertListener preInsertListener;
    private final PostLoadListener postLoadListener;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       /* if(bean instanceof LocalContainerEntityManagerFactoryBean){
            LocalContainerEntityManagerFactoryBean lcemfb = (LocalContainerEntityManagerFactoryBean)  bean;
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) lcemfb.getNativeEntityManagerFactory();
            EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);

            registry.appendListeners(EventType.POST_LOAD, postLoadListener);
            registry.appendListeners(EventType.PRE_INSERT, preInsertListener);
            registry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);

        }*/
        return bean;
    }
}
