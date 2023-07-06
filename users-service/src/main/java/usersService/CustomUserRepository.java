package usersService;

import org.springframework.data.jpa.repository.JpaRepository;

import usersService.model.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    
    boolean existsByRole(String role);
    CustomUser findByRole(String role);
    CustomUser findByEmail(String email);
    boolean existsByEmailAndRole(String email, String role);
    boolean existsByEmail(String email);
}
