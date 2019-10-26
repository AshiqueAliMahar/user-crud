package ali.ashique.usercrud.service;

import ali.ashique.usercrud.models.User;

import javax.validation.constraints.Max;
import java.util.List;

public interface UserService {
    User save(User user);
    User findById(Long id);
    void deleteById(Long id);
    List<User> findAll();
}
