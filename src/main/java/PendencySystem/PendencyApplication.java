package PendencySystem;

import PendencySystem.Services.DriverClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PendencyApplication {

    @Autowired
    DriverClass driverClass;

    public static void main(String args[]){
        ConfigurableApplicationContext appContext = SpringApplication.run(PendencyApplication.class, args);

    }

    @PostConstruct
    public void init() {
        driverClass.start();
    }
}
