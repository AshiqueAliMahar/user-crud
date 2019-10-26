package ali.ashique.usercrud.bootstrap;

import ali.ashique.usercrud.models.Employee;
import ali.ashique.usercrud.models.User;
import ali.ashique.usercrud.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class UserBootstrap implements CommandLineRunner {
    private UserRepo userRepo;
    @Override
    public void run(String... args) throws Exception {


        User user = new User(0, "Ashique Ali", "Mahar", "Hakim Ali", null);
        List<Employee> employees = Arrays.asList(new Employee("AshiqueE",user));
        user.setEmployees(employees);
        userRepo.save(user);

        userRepo.save(new User(0,"Habibullah","Mahar","Ghulam Nabi",null));
        userRepo.save(new User(0,"Nisar Ahmed","Mahar","Mohammad Taghial",null));
    }
}
