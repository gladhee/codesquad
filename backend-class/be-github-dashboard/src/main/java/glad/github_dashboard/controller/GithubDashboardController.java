package glad.github_dashboard.controller;

import glad.github_dashboard.service.PRSyncService;
import glad.github_dashboard.service.PRReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GithubDashboardController {

    private final PRReadService readService;
    private final PRSyncService syncService;

    public GithubDashboardController(PRReadService readService, PRSyncService syncService) {
        this.readService = readService;
        this.syncService = syncService;
    }

    @GetMapping("/")
    public String showStats(
            @RequestParam(required = false) String username,
            @RequestParam(required = false, defaultValue = "false") boolean onlyStep,
            @RequestParam(required = false, defaultValue = "false") boolean onlySuccess,
            Model model
    ) {
        model.addAttribute("stats", readService.getStats(username, onlyStep, onlySuccess));
        return "index";
    }

    @GetMapping("/{username}")
    public String showUserDetails(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("prs", readService.getUserPRs(username));
        return "detail";
    }

    @GetMapping("/refresh")
    public String refresh() {
        syncService.refreshData();
        return "redirect:/";
    }

}
