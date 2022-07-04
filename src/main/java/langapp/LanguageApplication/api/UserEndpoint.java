package langapp.LanguageApplication.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import langapp.LanguageApplication.controller.UserService;
import langapp.LanguageApplication.domain.User;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public Iterable<User> getUsers() {
		return userService.getAllUsers();
	}
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		return userService.getUserById(Long.parseLong(id));
	}
	@PostMapping("/")
	public User addSingleUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	@DeleteMapping("/{id}")
	public void deleteSingleUser(@PathVariable String id) {
		userService.deleteUser(Long.parseLong(id));
	}
	@PutMapping("/{id}")
	public void updateSingleUser(@PathVariable String id, @RequestBody User userDetails) {
		userService.updateUser(Long.parseLong(id), userDetails);
	}
}