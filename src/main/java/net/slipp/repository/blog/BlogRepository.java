package net.slipp.repository.blog;

import net.slipp.domain.blog.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long>{

}
