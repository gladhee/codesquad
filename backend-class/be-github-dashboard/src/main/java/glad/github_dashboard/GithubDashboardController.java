package glad.github_dashboard;

import glad.github_dashboard.client.GithubClient;
import glad.github_dashboard.dto.PullRequest;
import glad.github_dashboard.dto.UserPRStats;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class GithubDashboardController {

    private final Logger logger;
    private final GithubClient githubClient;
    private final String owner;
    private final String repo;

    @Autowired
    public GithubDashboardController(
            GithubClient githubClient,
            @Value("${github.repo-owner}") String owner,
            @Value("${github.repo-name}") String repo
    ) {
        this.logger = LoggerFactory.getLogger(GithubDashboardController.class);
        this.githubClient = githubClient;
        this.owner = owner;
        this.repo = repo;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/github-stats")
    public String showStats(
            @RequestParam(required = false) String username,
            @RequestParam(required = false, defaultValue = "false") boolean onlyStep,
            @RequestParam(required = false, defaultValue = "false") boolean onlySuccess,
            Model model
    ) {
        List<PullRequest> pullRequests = githubClient.getPullRequests(owner, repo);
        Map<String, UserPRStats> statsMap = new HashMap<>();

        for (PullRequest pr : pullRequests) {
            logger.info(pr.toString());
            String user = pr.user().login();
            boolean isMerged = Boolean.TRUE.equals(pr.merged());
            boolean isClosed = "closed".equals(pr.state());

            boolean hasStep = pr.labels().stream().anyMatch(label -> label.name().startsWith("step"));
            boolean hasSuccess = pr.labels().stream().anyMatch(label -> label.name().contains("정상"));

            UserPRStats current = statsMap.getOrDefault(user, new UserPRStats(0, 0, 0, false, false));

            UserPRStats updated = new UserPRStats(
                    current.total() + 1,
                    current.merged() + (isMerged ? 1 : 0),
                    current.closed() + (isClosed && !isMerged ? 1 : 0),
                    current.stepLabelExists() || hasStep,
                    current.successLabelExists() || hasSuccess
            );

            statsMap.put(user, updated);
        }

        if (username != null && !username.isBlank()) {
            statsMap = statsMap.entrySet().stream()
                    .filter(entry -> entry.getKey().equalsIgnoreCase(username))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        if (onlyStep) {
            statsMap = statsMap.entrySet().stream()
                    .filter(entry -> entry.getValue().stepLabelExists())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        if (onlySuccess) {
            statsMap = statsMap.entrySet().stream()
                    .filter(entry -> entry.getValue().successLabelExists())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        model.addAttribute("stats", statsMap);

        return "stats";
    }

}
