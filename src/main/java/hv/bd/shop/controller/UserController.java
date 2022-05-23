package hv.bd.shop.controller;

import hv.bd.shop.controller.view.UserView;
import hv.bd.shop.controller.view.mapper.RoleView;
import hv.bd.shop.user.Role;
import hv.bd.shop.user.User;
import hv.bd.shop.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Boolean createUser(@RequestBody UserView user){
        User entity = new User();
        entity.setUsername(user.getLogin());
        entity.setPassword(user.getPassword());
        entity.setRoles(
                user.getRoles()
                        .stream()
                        .map(RoleView::getRole)
                        .map(Role::new)
                        .collect(Collectors.toSet())
        );
        userService.create(entity);
        return true;
    }
}
