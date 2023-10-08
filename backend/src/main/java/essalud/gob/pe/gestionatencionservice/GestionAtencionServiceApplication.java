package essalud.gob.pe.gestionatencionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients
//@EnableEurekaClient
@SpringBootApplication
public class GestionAtencionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionAtencionServiceApplication.class, args);
    }

}
