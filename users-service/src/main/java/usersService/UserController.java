package usersService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import usersService.model.CustomUser;

@RestController
public class UserController {

	@Autowired
	private CustomUserRepository repo;
	
	@GetMapping("/users-service/users")
	public List<CustomUser> getAllUsers(){
		return repo.findAll();
		
	}
	@GetMapping("/users-service/users/{userID}")
	public ResponseEntity<CustomUser> getUserByID(@PathVariable long userID){
		CustomUser user= repo.getById(userID);
		 if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	}
	
	@PostMapping("/users-service/users")
	public ResponseEntity<CustomUser> createUser(@RequestBody CustomUser user) {
		CustomUser createdUser = repo.save(user);
		return ResponseEntity.status(201).body(createdUser);
	}
	
	@PutMapping("/users-service/users/{userID}")
	public ResponseEntity<CustomUser> updateUser(@PathVariable long userID,@RequestBody CustomUser updateUser){
		CustomUser existingUser= repo.findById(userID).orElse(null);
		 if (existingUser != null) {
	            // User with the provided userId does not exist
	            existingUser.setId(userID);
	            existingUser.setEmail(updateUser.getEmail());
	            existingUser.setPassword(updateUser.getPassword());
	            existingUser.setRole(updateUser.getRole());
	            return ResponseEntity.ok(existingUser);
	        }
		 else {
	            return ResponseEntity.notFound().build();
		 }
	}
	
	@DeleteMapping("/users-service/users/{userID}")
	public ResponseEntity<Void> deleteUser(@PathVariable long userID){
		CustomUser existingUser = repo.findById(userID).orElse(null);
		if (existingUser != null) {
            repo.delete(existingUser);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
	}
 }
