package com.iwellsite.homework.bootstrap;

import com.iwellsite.homework.domain.model.ChatRoom;
import com.iwellsite.homework.domain.model.Role;
import com.iwellsite.homework.domain.model.User;
import com.iwellsite.homework.domain.repo.ChatRoomRepository;
import com.iwellsite.homework.services.RoleService;
import com.iwellsite.homework.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mshields on 2017-06-16.
 */
@Component
public class SeedDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ChatRoomRepository chatRoomRepository;
    private UserService userService;
    private RoleService roleService;

    private Logger log = Logger.getLogger(SeedDataLoader.class);

    @Autowired
    public void setChatRoomRepository(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadChatRooms();
        loadUsers();
        loadRoles();
        assignUsersToUserRole();
        assignUsersToAdminRole();
    }

    private void loadChatRooms() {
        // TODO: NOT for Production use
        loadChatRoom("Organic", "Talk about organic petrol engine parts");
        loadChatRoom("Diesel", "Talk about diesel engine parts");
        loadChatRoom("Random", "Talk about random engine parts");
    }

    private void loadChatRoom(String name, String description) {

        ChatRoom aRoom = new ChatRoom();
        aRoom.setName(name);
        aRoom.setDescription(description);
        chatRoomRepository.save(aRoom);

        log.info("Saved ChatRoom - id: " + aRoom.getId());
    }


    private void loadUsers() {

        User user1 = new User();
        user1.setUsername("chat1");
        user1.setPassword("chat1");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUsername("chat2");
        user2.setPassword("chat2");
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUsername("chat3");
        user3.setPassword("chat3");
        userService.saveOrUpdate(user3);

        User user4 = new User();
        user4.setUsername("admin");
        user4.setPassword("admin");
        userService.saveOrUpdate(user4);
    }

    private void loadRoles() {

        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role" + role.getRole());

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }
    private void assignUsersToUserRole() {

        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (!user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
    private void assignUsersToAdminRole() {

        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
}