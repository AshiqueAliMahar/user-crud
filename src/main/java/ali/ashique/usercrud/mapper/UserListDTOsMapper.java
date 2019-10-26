package ali.ashique.usercrud.mapper;

import ali.ashique.usercrud.dtos.UserDTO;
import ali.ashique.usercrud.dtos.UserListDTOS;
import ali.ashique.usercrud.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserListDTOsMapper {
    List<UserDTO> users2UserDTOList(List<User> users);
}
