package hv.bd.shop.controller.view;

import hv.bd.shop.controller.view.mapper.RoleView;
import lombok.Data;

import java.util.Set;

@Data
public class UserView {

    private String login;
    private String password;
    private Set<RoleView> roles;
}
