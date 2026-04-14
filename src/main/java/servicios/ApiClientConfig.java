package servicios;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utilidades.ApiClient;
import utilidades.api.EmailApi;

@Configuration
public class ApiClientConfig {
    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setHost("localhost");
        apiClient.setPort(8080);
        apiClient.setScheme("http");
        return apiClient;
    }

    @Bean
    public utilidades.api.SolicitudApi solicitudApi(ApiClient apiClient) {
        return new utilidades.api.SolicitudApi(apiClient);
    }

    @Bean
    public utilidades.api.ResultadosApi resultadosApi(ApiClient apiClient) {
        return new utilidades.api.ResultadosApi(apiClient);
    }

    @Bean
    public EmailApi emailApi(ApiClient apiClient) {
        return new EmailApi(apiClient);
    }
}
