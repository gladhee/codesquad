package glad.github_dashboard.dao;

import glad.github_dashboard.dto.PullRequest;
import glad.github_dashboard.dto.RepositoryInfo;

import java.util.List;

public interface PRRepository {
    List<RepositoryInfo> findRepositories();
    boolean saveAllRepositories(List<RepositoryInfo> repositories);
    boolean deleteAllRepositories();
    List<PullRequest> findPRsByRepositoryName(String repositoryName);
    List<PullRequest> findPRsByRepositoryNameAndUsernameIgnoreCase(String repo, String username);
    boolean saveAll(String repositoryName, List<PullRequest> pullRequests);
    boolean deleteAll(String repositoryName);
}
