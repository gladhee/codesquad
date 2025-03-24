package glad.github_dashboard.dao;

import glad.github_dashboard.dto.PullRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryPRRepository implements PRRepository {

    private final List<PullRequest> pullRequests;

    public InMemoryPRRepository() {
        this.pullRequests = new ArrayList<>();
    }

    @Override
    public List<PullRequest> findAll() {
        return pullRequests;
    }

    @Override
    public List<PullRequest> findByUsernameIgnoreCase(String username) {
        return pullRequests.stream()
                .filter(pr -> pr.user().login().equalsIgnoreCase(username))
                .toList();
    }

    @Override
    public boolean saveAll(List<PullRequest> pullRequests) {
        return this.pullRequests.addAll(pullRequests);
    }

    @Override
    public boolean deleteAll() {
        pullRequests.clear();
        return true;
    }

}
