package glad.github_dashboard.dto;

public record UserPRStats(
        int total,
        int merged,
        int closed,
        boolean stepLabelExists,
        boolean successLabelExists
) {
}
