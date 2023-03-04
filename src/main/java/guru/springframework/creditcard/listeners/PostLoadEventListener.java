package guru.springframework.creditcard.listeners;

import org.hibernate.event.spi.PostLoadEvent;

public class PostLoadEventListener  implements org.hibernate.event.spi.PostLoadEventListener {
    @Override
    public void onPostLoad(PostLoadEvent event) {
        System.out.println("I am on PostLoad Event Listener");
    }
}
