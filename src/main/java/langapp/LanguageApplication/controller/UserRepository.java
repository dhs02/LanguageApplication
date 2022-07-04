package langapp.LanguageApplication.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import langapp.LanguageApplication.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	boolean existsUserByUsername(String username);
	boolean existsUserByEmail (String email);
	boolean existsUserByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, String dateOfBirth);
}