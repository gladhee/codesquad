package glad.github_dashboard.dao;

import glad.github_dashboard.dto.PullRequest;
import glad.github_dashboard.dto.RepositoryInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryPRRepository implements PRRepository {

    private final List<RepositoryInfo> repositories;
    private final Map<String, List<PullRequest>> repositoryPullRequests;

    public InMemoryPRRepository() {
        this.repositories = new ArrayList<>();
        this.repositoryPullRequests = new HashMap<>();
    }

    @Override
    public List<RepositoryInfo> findRepositories() {
        return repositories;
    }

    @Override
    public boolean saveAllRepositories(List<RepositoryInfo> repositories) {
        this.repositories.addAll(repositories);
        return true;
    }

    @Override
    public boolean deleteAllRepositories() {
        repositories.clear();
        return true;
    }

    @Override
    public List<PullRequest> findPRsByRepositoryName(String repositoryName) {
        return repositoryPullRequests.get(repositoryName);
    }

    @Override
    public List<PullRequest> findPRsByRepositoryNameAndUsernameIgnoreCase(String repo, String username) {
        return repositoryPullRequests.get(repo).stream()
                .filter(pr -> pr.user().login().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveAll(String repositoryName, List<PullRequest> pullRequests) {
        repositoryPullRequests.put(repositoryName, new ArrayList<>(pullRequests));
        return true;
    }

    @Override
    public boolean deleteAll(String repositoryName) {
        repositoryPullRequests.remove(repositoryName);
        return true;
    }

}
