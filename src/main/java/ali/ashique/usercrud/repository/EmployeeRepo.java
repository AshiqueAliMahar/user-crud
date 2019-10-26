package ali.ashique.usercrud.repository;

import ali.ashique.usercrud.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employeeapi")
public interface EmployeeRepo extends CrudRepository<Employee,Long> {
}
