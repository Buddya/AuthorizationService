package ru.netology.autorizationService.repository;

import org.springframework.stereotype.Repository;
import ru.netology.autorizationService.enums.Authorities;

import java.util.*;

import static ru.netology.autorizationService.enums.Authorities.*;

@Repository
public class UserRepository {
    private final Map<String, UserData> users = getUsersMap();

    public List<Authorities> getUserAuthorities(String user, String password) {
        final UserData userData = users.get(user);
        return (userData == null || !userData.password.equals(password)) ? Collections.emptyList() : userData.authorities;
    }

    private Map<String, UserData> getUsersMap() {
        final Map<String, UserData> users = new HashMap<>();
        users.put("adminFullAccess", new UserData("full access", Arrays.asList(READ, WRITE, DELETE)));
        users.put("admin", new UserData("admin", Arrays.asList(READ, WRITE)));
        users.put("user", new UserData("password", List.of(READ)));
        return users;
    }

    private static class UserData {
        private final String password;
        private final List<Authorities> authorities;

        public UserData(String password, List<Authorities> authorities) {
            this.password = password;
            this.authorities = authorities;
        }
    }
}
