package project.timesheet.services;

import project.timesheet.models.User;

public interface UserService {
    User findByUsername(String username);
}
