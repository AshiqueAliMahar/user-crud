package ali.ashique.usercrud.repository;

import ali.ashique.usercrud.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "userapi")
public interface UserRepo extends JpaRepository<User,Long> {
}
