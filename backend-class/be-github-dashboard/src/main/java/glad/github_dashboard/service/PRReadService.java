package glad.github_dashboard.service;

import glad.github_dashboard.dao.InMemoryPRRepository;
import glad.github_dashboard.dao.PRRepository;
import glad.github_dashboard.dto.PullRequest;
import glad.github_dashboard.dto.UserPRStats;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PRReadService {

    private final PRRepository repository;

    public PRReadService(InMemoryPRRepository inMemoryPRRepository) {
        this.repository = inMemoryPRRepository;
    }

    public Map<String, UserPRStats> getStats(String username) {
        List<PullRequest> pullRequests = repository.findAll();
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
            statsMap = statsMap.entrySet().stream()
                    .filter(entry -> entry.getKey().toLowerCase().contains(username.toLowerCase()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return statsMap;
    }

    public List<PullRequest> getUserPRs(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }

}
