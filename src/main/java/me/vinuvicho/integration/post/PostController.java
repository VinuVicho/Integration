package me.vinuvicho.integration.post;

import lombok.AllArgsConstructor;
import me.vinuvicho.integration.appuser.AppUser;
import me.vinuvicho.integration.appuser.AppUserRepository;
import me.vinuvicho.integration.comments.Comment;
import me.vinuvicho.integration.comments.CommentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final AppUserRepository appUserRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @GetMapping()
    public String getPosts(Model model) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get().getNickName();
            model.addAttribute("authorized", username);
        } catch (Exception e) {
            model.addAttribute("authorized", "Click to Register");
        }
        model.addAttribute("newPost", new Post());
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "post/all";
    }

    @PostMapping("/{postId}/comment/new")
    public String newComment(@ModelAttribute("newComment") Comment comment, @PathVariable("postId") Long postId) {
        System.out.println("comment start");
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AppUser user = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get();
            comment.setCreatedBy(user.getId());
            comment.setCreatedByNickName(user.getNickName());
        } catch (Exception e) {
            return "redirect:/registration";
        }
        commentRepository.save(comment);
//        Post post = postRepository.getPostById(postId);
//        post.addComment(comment);
//        postRepository.save(post);
        return ("redirect:/post/" + postId);
    }

    @GetMapping("/{id}")
    public String getPostById(Model model, @PathVariable Long id) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get().getNickName();
            model.addAttribute("authorized", username);
        } catch (Exception e) {
            model.addAttribute("authorized", "Click to Register");
        }
        Post post = postRepository.getPostById(id);
        List<Comment> comments = commentRepository.findAllByPostId(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());
        return "post/post";
    }

    @PostMapping("/new")
    public String createPost(Model model, @ModelAttribute("newPost") Post post) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get().getNickName();
            model.addAttribute("authorized", username);
        } catch (Exception e) {
            model.addAttribute("authorized", "Click to Register");
        }
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = appUserRepository.findByEmail(((UserDetails) principal).getUsername()).get().getId();
            post.setCreatedBy(userId);
            post = postRepository.save(post);
            return "redirect:/post/" + post.getId();
//            List<Comment> comments = post.getComments();
//            model.addAttribute("post", post);
//            model.addAttribute("comments", comments);
//            model.addAttribute("newComment", new Comment());
//            return "post/post";
        } catch (Exception e) {
            return "redirect:/registration";
        }
    }
}
