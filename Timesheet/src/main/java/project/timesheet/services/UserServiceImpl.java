    package project.timesheet.services;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import project.timesheet.models.User;
    import project.timesheet.repository.UserRepository;

    @Service
    public  class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;
        @Override
        public User findByUsername(String username) {
            return userRepository.findByUsername(username);
        }
    }
