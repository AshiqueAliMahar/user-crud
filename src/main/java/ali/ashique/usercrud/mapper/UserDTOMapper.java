package ali.ashique.usercrud.mapper;

import ali.ashique.usercrud.dtos.UserDTO;
import ali.ashique.usercrud.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    UserDTO user2UserDTO(User user);
    User userDTO2User(UserDTO userDTO);
}
