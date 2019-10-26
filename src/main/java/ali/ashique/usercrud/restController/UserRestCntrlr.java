package ali.ashique.usercrud.restController;

import ali.ashique.usercrud.dtos.UserDTO;
import ali.ashique.usercrud.dtos.UserListDTOS;
import ali.ashique.usercrud.exceptionHandler.NotFoundException;
import ali.ashique.usercrud.mapper.UserDTOMapper;
import ali.ashique.usercrud.mapper.UserListDTOsMapper;
import ali.ashique.usercrud.models.User;
import ali.ashique.usercrud.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserRestCntrlr {
    private UserService userService;
    private UserListDTOsMapper userListDTOsMapper;
    private UserDTOMapper userDTOMapper;
    @GetMapping("")
    public MappingJacksonValue getUserDTOS(){
        UserListDTOS userListDTOS=new UserListDTOS();
        userListDTOS.setUserDtoList(userListDTOsMapper.users2UserDTOList(userService.findAll()));
        /**
         * HATEOAS
         */
        Resource<UserListDTOS> resource=new Resource<>(userListDTOS);
        ControllerLinkBuilder controllerLinkBuilder = linkTo(methodOn(this.getClass()).getUserDTOS());
        resource.add(controllerLinkBuilder.withRel("self"));

        /**
         * Filter Mapping
         */
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(resource);
        mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter("userFilter",SimpleBeanPropertyFilter.filterOutAllExcept("name","fatherName","caste")));

        return mappingJacksonValue;
    }
    @GetMapping("/{id}")
    public MappingJacksonValue getUserDTOS(@PathVariable Long id){
        User user = userService.findById(id);
        if (user==null) throw new NotFoundException("User Not Found id:"+id);
        UserDTO userDTO=userDTOMapper.user2UserDTO(user);
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(userDTO);
        mappingJacksonValue.setFilters(new SimpleFilterProvider().addFilter("userFilter",SimpleBeanPropertyFilter.filterOutAllExcept("name","fatherName")));
        return mappingJacksonValue;

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id) {
        userService.deleteById(Long.valueOf(id));
    }
    @PostMapping({"/",""})
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@Valid @RequestBody UserDTO userDTO){
        User user = userService.save(userDTOMapper.userDTO2User(userDTO));
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUriString();
    }
    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO,@PathVariable String id){
        User user = userDTOMapper.userDTO2User(userDTO);
        user.setId(Long.valueOf(id));
        return userDTOMapper.user2UserDTO(userService.save(user));
    }
    @PatchMapping("/{id}")
    public UserDTO patchUser(@RequestBody UserDTO userDTO,@PathVariable String id){
        User byId = userService.findById(Long.valueOf(id));
        if (userDTO.getName()!=null) byId.setName(userDTO.getName());
        if (userDTO.getFatherName()!=null) byId.setFatherName(userDTO.getFatherName());
        if (userDTO.getCaste()!=null) byId.setCaste(userDTO.getCaste());

        return userDTOMapper.user2UserDTO(userService.save(byId));
    }
}
