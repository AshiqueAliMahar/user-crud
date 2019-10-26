package ali.ashique.usercrud.service;

import ali.ashique.usercrud.models.User;
import ali.ashique.usercrud.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    @Override
    public User save(User user) {
        User savedUser=new User();
        savedUser=userRepo.save(user);
        return savedUser;
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
