package rs.ac.singidunum.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms //Ukljucivanje poruka(ActiveMQ)
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);

    }
}
