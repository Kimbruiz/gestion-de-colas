package essalud.gob.pe.gestionatencionservice.common.interceptors;

import essalud.gob.pe.gestionatencionservice.common.interfaces.*;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class FeignClientConfiguration {

    @Value("${feign-clients.util-service.key}")
    private String utilServiceKey;

    @Value("${feign-clients.word-template-api.key}")
    private String wordTemplateApiKey;

    @Value("${feign-clients.ide-api.key}")
    private String ideApiKey;

    @Value("${feign-clients.essi-consulta.key}")
    private String essiConsultaKey;

    @Value("${feign-clients.essi-paciente.key}")
    private String essiPacienteKey;


    private void _addHeader(RequestTemplate template, Class<? extends java.lang.annotation.Annotation> interfaceClass, String key) {
        if (template.feignTarget() != null && template.feignTarget().type().isAnnotationPresent(interfaceClass)) {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((key).getBytes(StandardCharsets.UTF_8));
            template.header("Authorization", authHeader);
        }
    }

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return template -> {
            _addHeader(template, BasicAuthForUtilService.class, utilServiceKey);
            _addHeader(template, BasicAuthForWordTemplateApi.class, wordTemplateApiKey);
            _addHeader(template, BasicAuthForIdeApi.class, ideApiKey);
            _addHeader(template, BasicAuthForEssiConsulta.class, essiConsultaKey);
            _addHeader(template, BasicAuthForEssiPaciente.class, essiPacienteKey);
        };
    }
}