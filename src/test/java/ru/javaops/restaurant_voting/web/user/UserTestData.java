package ru.javaops.restaurant_voting.web.user;

import ru.javaops.restaurant_voting.model.Role;
import ru.javaops.restaurant_voting.model.User;
import ru.javaops.restaurant_voting.util.JsonUtil;
import ru.javaops.restaurant_voting.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int USER2_ID = 3;
    public static final int NOT_FOUND = 100;

    public static final String USER_MAIL = "user@yandex.ru";
    public static final String USER2_MAIL = "user2@yandex.ru";
    public static final String ADMIN_MAIL = "admin@gmail.com";


    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User user2 = new User(3, "User2", "user2@yandex.ru", "password2", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);

    public static final Map<Integer, User> users = Map.of(1, user, 2, admin, 3, user2);


    public static User getNewUser() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdatedUser() {
        return new User(USER_ID, "UpdatedUserName", USER_MAIL, "newPass", false, new Date(), Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
