package me.vinuvicho.integration.appuser;

import lombok.AllArgsConstructor;
import me.vinuvicho.integration.post.Post;
import me.vinuvicho.integration.post.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class AppUserController {

    private final AppUserRepository appUserRepository;
    private final PostRepository postRepository;

    @GetMapping("/{id}")
    public String findUserById(Model model, @PathVariable Long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AppUser user = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get();
            if (user.getId() == id) return "redirect:/account";
            model.addAttribute("authorized", user.getNickName());
        } catch (Exception e) {
            model.addAttribute("authorized", "Click to Register");
        }

        try {
            AppUser user = appUserRepository.findById(id).get();
            model.addAttribute("user", user);
            List<Post> posts = postRepository.findAllByCreatedBy(id);
            model.addAttribute("posts", posts);
        } catch (Exception e) {
            return "notFound";
        }
        return "userPage/userPage";
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get().getNickName();
            model.addAttribute("authorized", username);
        } catch (Exception e) {
            model.addAttribute("authorized", "Click to Register");
        }
        List<AppUser> users = appUserRepository.findAll();
        model.addAttribute("users", users);
        return "userPage/all";
    }

    @GetMapping()
    public String confirm(Model model) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AppUser user = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get();
            model.addAttribute("user", user);
            List<Post> posts = postRepository.findAllByCreatedBy(user.getId());
            model.addAttribute("posts", posts);
            model.addAttribute("newPost", new Post());
        } catch (Exception e) {
            return "redirect:/registration";
        }
        return "userPage/me";
    }
}
