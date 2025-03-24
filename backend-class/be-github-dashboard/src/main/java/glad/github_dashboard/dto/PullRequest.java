package glad.github_dashboard.dto;

import java.util.List;

public record PullRequest(
        String html_url,
        String title,
        int number,
        String state,
        Boolean merged,
        User user,
        List<Label> labels
) {
}
