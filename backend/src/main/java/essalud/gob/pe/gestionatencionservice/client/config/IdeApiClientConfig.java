package essalud.gob.pe.gestionatencionservice.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Request;
import feign.Retryer;

import java.util.concurrent.TimeUnit;

@Configuration
public class IdeApiClientConfig {

    @Value("${feign-clients.ide-api.timeout-connect}")
    private int connectTimeout;

    @Value("${feign-clients.ide-api.timeout-read}")
    private int readTimeout;

    @Bean
    public Request.Options feignOptions() {
        // Establece un timeout de 10 segundos para la conexión y la respuesta.
        return new Request.Options(connectTimeout, TimeUnit.SECONDS, readTimeout, TimeUnit.SECONDS, true);
    }
    @Bean
    public Retryer feignRetryer() {
        // Deshabilita los reintentos.
        return Retryer.NEVER_RETRY;
    }

}
