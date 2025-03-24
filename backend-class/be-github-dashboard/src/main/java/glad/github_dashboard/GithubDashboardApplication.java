package glad.github_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubDashboardApplication.class, args);
	}

}
