package glad.github_dashboard.client;

import glad.github_dashboard.dto.PullRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "githubClient", url = "${github.api-url}", configuration = GithubClientConfig.class)
public interface GithubClient {

    @GetMapping("/repos/{owner}/{repo}/pulls?state=all&per_page=100")
    List<PullRequest> getPullRequests(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
