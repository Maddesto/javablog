package maddesto.repository;

import java.util.List;

import maddesto.entity.Blog;
import maddesto.entity.Role;
import maddesto.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	public List<Role> findByUsers(User user);

	public Role findByName(String name);
}
