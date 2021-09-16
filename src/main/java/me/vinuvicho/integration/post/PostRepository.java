package me.vinuvicho.integration.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCreatedBy(Long id);
    Post getPostById(Long id);

}
