package maddesto.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import maddesto.entity.Blog;
import maddesto.entity.Item;
import maddesto.entity.Role;
import maddesto.entity.User;
import maddesto.repository.BlogRepository;
import maddesto.repository.ItemRepository;
import maddesto.repository.RoleRepository;
import maddesto.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User find(int id){
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user = find(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for(Blog blog: blogs){
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 25, Direction.DESC, "publishDate"));
			blog.setItems(items);
			
		}
		user.setBlogs(blogs);
		return user;
	}
	
	@Transactional
	public User findOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
	}

	public void save(User user) {
		List<Role> userRoles = new ArrayList<Role>();
		userRoles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(userRoles);
		user.setEnabled(true);		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		
	}

	public void delete(int id) {
		userRepository.delete(id);
	}

	public User find(String username) {
		return userRepository.findByName(username);
		
	}
}
