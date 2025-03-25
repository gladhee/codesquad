package glad.github_dashboard.service;

import glad.github_dashboard.dao.InMemoryPRRepository;
import glad.github_dashboard.dao.PRRepository;
import glad.github_dashboard.dto.PullRequest;
import glad.github_dashboard.dto.RepositoryInfo;
import glad.github_dashboard.dto.UserPRStats;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadService {

    private final PRRepository repository;

    public ReadService(InMemoryPRRepository inMemoryPRRepository) {
        this.repository = inMemoryPRRepository;
    }

    public Map<String, UserPRStats> getStats(String repo, String username) {
        List<PullRequest> pullRequests = repository.findPRsByRepositoryName(repo);
        Map<String, UserPRStats> statsMap = new HashMap<>();

        for (PullRequest pr : pullRequests) {
            String user = pr.user().login();

            boolean isClosed = "closed".equals(pr.state());
            boolean isMerged = pr.merged_at() != null;

            UserPRStats current = statsMap.getOrDefault(user, new UserPRStats(0, 0, 0));

            statsMap.put(user, new UserPRStats(
                    current.total() + 1,
                    current.merged() + (isMerged ? 1 : 0),
                    current.closed() + (isClosed && !isMerged ? 1 : 0))
            );
        }

        if (username != null && !username.isBlank()) {
            statsMap.entrySet().removeIf(entry -> !entry.getKey().toLowerCase().contains(username.toLowerCase()));
        }

        return statsMap;
    }

    public List<PullRequest> getUserPRs(String repo, String username) {
        return repository.findPRsByRepositoryNameAndUsernameIgnoreCase(repo, username);
    }

    public List<RepositoryInfo> getRepositories() {
        return repository.findRepositories();
    }

}
