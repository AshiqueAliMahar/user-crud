package ali.ashique.usercrud.controller;

import ali.ashique.usercrud.cmdConverter.UserCmdConverter;
import ali.ashique.usercrud.commands.UserCommand;
import ali.ashique.usercrud.models.User;
import ali.ashique.usercrud.service.UserService;
import ali.ashique.usercrud.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping({"/user"})
public class UserController {
    private UserService userService;
    private UserCmdConverter userCmdConverter;
    @GetMapping("")
    public String getUser(Model model){
        List<User> all = userService.findAll();
        List<UserCommand> userCommands=new LinkedList<>();
        userCommands = all.stream().map(user -> {
            return userCmdConverter.user2UserCmd(user);
        }).collect(Collectors.toList());
        model.addAttribute(userCommands);
        return "index";
    }
    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable String id){
        User user = userService.findById(Long.valueOf(id));
        UserCommand userCommand = userCmdConverter.user2UserCmd(user);
        model.addAttribute(userCommand);
        return "edit";
    }
    @GetMapping("/add-user")
    public String addUser(Model model){
        UserCommand userCommand = new UserCommand();
        model.addAttribute(userCommand);
        return "edit";
    }
    @PostMapping({""})
    public String saveUser(Model model, @ModelAttribute UserCommand userCommand){
        User user = userService.save(userCmdConverter.userCmd2User(userCommand));
        UserCommand newUser = userCmdConverter.user2UserCmd(user);
        model.addAttribute(newUser);
        return "redirect:/user/view/"+newUser.getId();
    }
    @GetMapping("/view/{id}")
    public String viewUser(Model model, @PathVariable String id){
        User user = userService.findById(Long.valueOf(id));
        UserCommand userCommand = userCmdConverter.user2UserCmd(user);
        model.addAttribute(userCommand);
        return "view-user";
    }
    @GetMapping("/delete/{id}")
    public String delUser(Model model, @PathVariable String id){
        userService.deleteById(Long.valueOf(id));
        return "redirect:/user";
    }
}
