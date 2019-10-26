package ali.ashique.usercrud.dtos;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Data
public class UserListDTOS {
    private List<UserDTO> userDtoList;
}
