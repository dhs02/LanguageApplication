package langapp.LanguageApplication.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import langapp.LanguageApplication.domain.User;
import langapp.LanguageApplication.exception.EntryExistsException;
import langapp.LanguageApplication.exception.EntryNotFoundException;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
		if (!userRepository.findById(id).isPresent()) {
			throw new EntryNotFoundException("User not found in user repository.");
		}
		System.out.println("User found in database");
		return userRepository.findById(id).get();
	}
	
	public User addUser(User user) {
		if (userRepository.existsUserByUsername(user.getUsername())) {
			throw new EntryExistsException("This username has already been registered.");
		}
		if (userRepository.existsUserByEmail(user.getEmail())) {
			throw new EntryExistsException("This email address has already been registered.");
		}
		if (userRepository.existsUserByFirstNameAndLastNameAndDateOfBirth(
					user.getFirstName(), 
					user.getLastName(), 
					user.getDateOfBirth())) {
			throw new EntryExistsException("User already exists in database.");
		}
		System.out.println("User added to database");
		return userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		if (!userRepository.findById(id).isPresent()) {
			throw new EntryNotFoundException("User not found in user repository.");
		}
		System.out.println("User deleted from database");
		userRepository.deleteById(id);
	}
	
	public void updateUser(Long id, User userDetails) {
		User user = userRepository.findById(id).get();
		if (userDetails.getFirstName() != null && userDetails.getFirstName() != "") {
			if (!userDetails.getFirstName().equals(user.getFirstName())) {
				user.setDateLastModified(LocalDateTime.now());
			}
			user.setFirstName(userDetails.getFirstName());
		}
		if (userDetails.getLastName() != null && userDetails.getLastName() != "") {
			if (!userDetails.getLastName().equals(user.getLastName())) {
				user.setDateLastModified(LocalDateTime.now());
			}
			user.setLastName(userDetails.getLastName());
		}
		if (userDetails.getDateOfBirth() != null && userDetails.getDateOfBirth() != "") {
			if (!userDetails.getDateOfBirth().equals(user.getDateOfBirth())) {
				user.setDateLastModified(LocalDateTime.now());
			}
			user.setDateOfBirth(userDetails.getDateOfBirth());
		}
		if (userDetails.getEmail() != null && userDetails.getEmail() != "") {
			if (!userDetails.getEmail().equals(user.getEmail())) {
				user.setDateLastModified(LocalDateTime.now());
			}
			user.setEmail(userDetails.getEmail());
		}
		if (userDetails.getUsername() != null && userDetails.getUsername() != "") {
			if (!userDetails.getUsername().equals(user.getUsername())) {
				user.setDateLastModified(LocalDateTime.now());
			}
			user.setUsername(userDetails.getUsername());
		}
		if (userDetails.getPassword() != null && userDetails.getPassword() != "") {
			if (!userDetails.getPassword().equals(user.getPassword())) {
				user.setDateLastModified(LocalDateTime.now());
			}
			user.setPassword(userDetails.getPassword());
		}
	    System.out.println("User information updated.");
	    userRepository.save(user);
	}
}