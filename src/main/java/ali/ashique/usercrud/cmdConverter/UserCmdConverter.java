package ali.ashique.usercrud.cmdConverter;

import ali.ashique.usercrud.commands.UserCommand;
import ali.ashique.usercrud.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCmdConverter {
    User userCmd2User(UserCommand userCommand);
    UserCommand user2UserCmd(User user);
}
