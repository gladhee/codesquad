package glad.github_dashboard.dao;

import glad.github_dashboard.dto.PullRequest;

import java.util.List;

public interface PRRepository {
    List<PullRequest> findPRsByRepositoryName(String repositoryName);
    List<PullRequest> findPRsByRepositoryNameAndUsernameIgnoreCase(String username);
    boolean saveAll(String repositoryName, List<PullRequest> pullRequests);
    boolean deleteAll(String repositoryName);
}
