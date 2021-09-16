package me.vinuvicho.integration.comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.vinuvicho.integration.post.Post;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Comment")
//@Table(name = "comment")
public class Comment {
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private Long id;
    private Long createdBy;
    private String createdByNickName;
    private String text;
    private Long postId;
//    @OneToMany(mappedBy = "app_user")
//    private Set<AppUser> likedBy;
//    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
//    private Set<AppUser> dislikedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;

        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return 860659860;
    }
}
