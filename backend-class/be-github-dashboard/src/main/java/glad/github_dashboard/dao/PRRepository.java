package glad.github_dashboard.dao;

import glad.github_dashboard.dto.PullRequest;

import java.util.List;

public interface PRRepository {
    List<PullRequest> findAll();
    List<PullRequest> findByUsernameIgnoreCase(String username);
    boolean saveAll(List<PullRequest> pullRequests);
    boolean deleteAll();
}
