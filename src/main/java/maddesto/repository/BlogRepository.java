package maddesto.repository;

import java.util.List;

import maddesto.entity.Blog;
import maddesto.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	public List<Blog> findByUser(User user);
	
}
