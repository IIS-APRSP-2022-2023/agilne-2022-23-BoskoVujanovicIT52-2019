package usersService;

import org.springframework.data.jpa.repository.JpaRepository;

import usersService.model.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByRole(String role);
    CustomUser findByEmail(String email);
    boolean existsByEmail(String email);
    int countByRole(String role);
    boolean existsByRole(String role);
}