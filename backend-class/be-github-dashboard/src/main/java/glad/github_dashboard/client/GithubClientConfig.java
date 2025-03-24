package glad.github_dashboard.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubClientConfig {

    @Value("${github.token}")
    private String token;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "token " + token);
            requestTemplate.header("Accept", "application/vnd.github+json");
        };
    }
}
