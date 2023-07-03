package usersService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import usersService.model.CustomUser;

@RestController
public class UserController {

	@Autowired
	private CustomUserRepository repo;
	
	private String getEmail(String authorization) {
		String base64Credentials = authorization.substring("Basic".length()).trim();
		byte[] decoded = Base64.getDecoder().decode(base64Credentials);
		String credentials = new String(decoded, StandardCharsets.UTF_8);
		String[] emailPassword = credentials.split(":", 2);
		String email = emailPassword[0];
		return email;
	}
	
	@GetMapping("/users-service/users")
	public List<CustomUser> getAllUsers(){
		return repo.findAll();
	}
	
	@PostMapping("/users-service/users")
	public ResponseEntity<?> createUser(@RequestBody CustomUser user, @RequestHeader("Authorization") String authorization ) {
		  	
		System.out.println(user.getRole());
		System.out.println(user.getEmail());
		
		String roleBody = user.getRole();
		
		System.out.println(authorization);
		String email = getEmail(authorization);
		System.out.println(email);
		
		CustomUser requestUser = repo.findByEmail(email);
		System.out.println(requestUser);
		System.out.println(requestUser.getRole());
		
		String role = requestUser.getRole();
		
		if(authorization == null) {
			return ResponseEntity.status(401).body("Unauthorized");
		}
		else if(role.equals("ADMIN") && !(roleBody.equals("USER"))) {
			return ResponseEntity.status(400).body("Admin users can only add users with USER role.");
		}
		else if(role.equals("USER")) {
			return ResponseEntity.status(403).body("You don't have permission to do that.");
		
		}
		 else if (roleBody.equals("OWNER") && repo.existsByRole("OWNER")) {
		        return ResponseEntity.status(400).body("Already exists a user with OWNER role.");
		}
		else if(repo.existsByEmail(user.getEmail()) && repo.findByEmail(user.getEmail()).getId() != user.getId()) {
			return ResponseEntity.status(400).body("Already exists user with email "+ user.getEmail());
		}
		else
		{
			CustomUser createdUser = repo.save(user);
			return ResponseEntity.status(201).body(createdUser);
		}
	}
	
	
	@PutMapping("/users-service/users")
	public ResponseEntity<?> updateUser(@RequestBody CustomUser updatedUser, @RequestHeader("Authorization") String authorization ) {
	
		System.out.println(updatedUser);
		boolean exists = repo.existsById(updatedUser.getId());
		String email = getEmail(authorization);
		CustomUser requestUser = repo.findByEmail(email);
		
		
		String roleBody = updatedUser.getRole();
		System.out.println(roleBody);

		String role = requestUser.getRole();
		System.out.println(role);

		
		if(!exists) {
			return ResponseEntity.status(204).body("User with id "+ updatedUser.getId()+"doesn't exist");
		}
		/*else if(updatedUser.getRole() != "OWNER" || updatedUser.getRole() != "ADMIN" || updatedUser.getRole() != "USER") {
			return ResponseEntity.status(400).body("Bad request");
		}*/
		else if (roleBody.equals("OWNER") && repo.countByRole("OWNER") > 1) {
	        return ResponseEntity.status(400).body("Already exists more than one user with OWNER role.");
	    }
		//else if(roleBody.equals("OWNER") && updatedUser.getId() != repo.findByRole("OWNER").getId()) {
		//	return ResponseEntity.status(409).body("Already exists user with OWNER role");
		//}
		else if(role.equals("ADMIN") && !(roleBody.equals("USER"))) {
			return ResponseEntity.status(400).body("Admin users can only update users with USER role.");
		}
		else if(role.equals("USER")) {
			return ResponseEntity.status(403).body("You don't have permission to do that.");
		}
		else if(repo.existsByEmail(updatedUser.getEmail()) && repo.findByEmail(updatedUser.getEmail()).getId() != updatedUser.getId()) {
			return ResponseEntity.status(400).body("Already exists user with email "+ updatedUser.getEmail());
		}
		else 
		{
			repo.save(updatedUser);
			return ResponseEntity.status(200).body(updatedUser);
		}

		
		}

	   @DeleteMapping("/users-service/users/{id}")
	   public ResponseEntity<?> deleteUser(@PathVariable long id, @RequestHeader("Authorization") String authorization ) {
	       Optional<CustomUser> user = repo.findById(id);
	       
	       String email = getEmail(authorization);
			CustomUser requestUser = repo.findByEmail(email);
			

			String role = requestUser.getRole();
			System.out.println(role);
			
	       if (!(user.isPresent())) {
	           return ResponseEntity.notFound().build();
	       }
	       else if(!(role.equals("OWNER"))) {
				return ResponseEntity.status(403).body("You don't have permission to do that.");
			}
	       else 
	       {
	    	   CustomUser deletedUser = user.get();
	    	   repo.delete(deletedUser);
	    	   return ResponseEntity.ok().build();
	       	}
	       
	   }
	   
	   
	   	@GetMapping("/users-service/users/{id}")
		public ResponseEntity<CustomUser> getUserById(@PathVariable long id) {
			Optional<CustomUser> user = repo.findById(id);
			if (user.isPresent()) {
				return ResponseEntity.ok(user.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	
	
 }
