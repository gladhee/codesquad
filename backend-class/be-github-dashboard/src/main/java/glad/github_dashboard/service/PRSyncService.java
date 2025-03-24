package glad.github_dashboard.service;

import glad.github_dashboard.client.GithubClient;
import glad.github_dashboard.dao.InMemoryPRRepository;
import glad.github_dashboard.dao.PRRepository;
import glad.github_dashboard.dto.PullRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PRSyncService {

    private final GithubClient githubClient;
    private final PRRepository repository;
    private final String owner;
    private final String repo;

    public PRSyncService(GithubClient githubClient,
                         InMemoryPRRepository inMemoryPRRepository,
                         @Value("${github.repo-owner}") String owner,
                         @Value("${github.repo-name}") String repo) {
        this.githubClient = githubClient;
        this.repository = inMemoryPRRepository;
        this.owner = owner;
        this.repo = repo;
    }

    @PostConstruct
    public void init() {
        refreshData();
    }

    public void refreshData() {
        List<PullRequest> pullRequests = githubClient.getPullRequests(owner, repo);

        repository.deleteAll();
        repository.saveAll(pullRequests);
    }
}
