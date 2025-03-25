package glad.github_dashboard.service;

import glad.github_dashboard.client.GithubClient;
import glad.github_dashboard.dao.InMemoryPRRepository;
import glad.github_dashboard.dao.PRRepository;
import glad.github_dashboard.dto.PullRequest;
import glad.github_dashboard.dto.RepositoryInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncService {

    private final GithubClient githubClient;
    private final PRRepository repository;
    private final String owner;

    public SyncService(GithubClient githubClient,
                       InMemoryPRRepository inMemoryPRRepository,
                       @Value("${github.repo-owner}") String owner) {
        this.githubClient = githubClient;
        this.repository = inMemoryPRRepository;
        this.owner = owner;
    }

    @PostConstruct
    public void init() {
        refreshAllRepo();
    }

    @Async
    public void refreshAllRepo() {
        List<RepositoryInfo> repositories = githubClient.getRepositories(owner);
        repository.deleteAllRepositories();
        repository.saveAllRepositories(repositories);
        for (RepositoryInfo info : repositories) {
            refreshRepo(info.name());
        }
    }

    @Async
    public void refreshRepo(String repo) {
        List<PullRequest> pullRequests = githubClient.getPullRequests(owner, repo);

        repository.deleteAll(repo);
        repository.saveAll(repo, pullRequests);
    }

}
