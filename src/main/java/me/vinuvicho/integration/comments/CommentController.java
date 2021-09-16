package me.vinuvicho.integration.comments;

import lombok.AllArgsConstructor;
import me.vinuvicho.integration.appuser.AppUser;
import me.vinuvicho.integration.appuser.AppUserRepository;
import me.vinuvicho.integration.post.Post;
import me.vinuvicho.integration.post.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;


}
