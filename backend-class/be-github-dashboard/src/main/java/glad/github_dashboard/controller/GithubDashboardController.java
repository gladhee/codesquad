package glad.github_dashboard.controller;

import glad.github_dashboard.dto.RepositoryInfo;
import glad.github_dashboard.service.SyncService;
import glad.github_dashboard.service.ReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class GithubDashboardController {

    private final ReadService readService;
    private final SyncService syncService;

    public GithubDashboardController(ReadService readService,
                                     SyncService syncService) {
        this.readService = readService;
        this.syncService = syncService;
    }

    @GetMapping("/")
    public String showAllRepository(Model model) {
        List<RepositoryInfo> repositories = readService.getRepositories();
        model.addAttribute("repos", repositories);
        return "index";
    }

    @GetMapping("/{repo}")
    public String showStats(
            @PathVariable String repo,
            @RequestParam(required = false) String username,
            Model model) {
        model.addAttribute("repo", repo);
        model.addAttribute("stats", readService.getStats(repo, username));
        return "stats";
    }

    @GetMapping("/{repo}/{username}")
    public String showUserDetails(@PathVariable String repo,
                                  @PathVariable String username,
                                  Model model) {
        model.addAttribute("username", username);
        model.addAttribute("prs", readService.getUserPRs(repo, username));
        return "detail";
    }

    @GetMapping("/refresh-all")
    public String refreshAll() {
        syncService.refreshAllRepo();
        return "redirect:/";
    }

    @GetMapping("/{repo}/refresh")
    public String refresh(@PathVariable String repo) {
        syncService.refreshRepo(repo);
        return "redirect:/" + repo;
    }

}
