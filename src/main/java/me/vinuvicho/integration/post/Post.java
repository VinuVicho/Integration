package me.vinuvicho.integration.post;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.vinuvicho.integration.appuser.AppUser;
import me.vinuvicho.integration.comments.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Post")
//@Table(name = "post")
public class Post {
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private AppUser appUser;
    private Long createdBy;                      //id of creator
    private String title;
    private String imageUrl;
    private String text;
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)                               //FIXME normal connection
//    private List<Comment> comments = new ArrayList<>();
//    @OneToMany(mappedBy = "post")
//    private Set<AppUser> likedBy;
//    @OneToMany(mappedBy = "post")
//    private Set<AppUser> dislikedBy;
//
//    public void addComment(Comment comment) {
//        comments.add(comment);
//        comment.setPost(this);
//    }
}
