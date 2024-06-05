package project.timesheet.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.execption.ResourceNotFoundException;
import project.timesheet.models.Role;
import project.timesheet.models.NhanVien;
import project.timesheet.models.UserRole;
import project.timesheet.repository.RoleRepository;
import project.timesheet.repository.NhanVienRepository;
import project.timesheet.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements NhanVienService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private NhanVienRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public NhanVien saveUser(NhanVien user) {
        return userRepository.save(user);
    }
    @Override
    public List<NhanVien> getAllStaff(Integer pageNo,
                                      Integer pageSize,
                                      String sortBy){
        return userRepository.getAllStaff(pageNo, pageSize, sortBy);
    }
    public List<NhanVien> searchStaffs(String keyword){
        return userRepository.searchStaff(keyword);
    }
    @Override
    public NhanVien getOne(int id) {
        Optional<NhanVien> optional =userRepository.findById(id);
        return optional.orElse(null);
    }

    public List<NhanVien> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public NhanVien findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public NhanVien findById(int id) {
        Optional<NhanVien> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
    }

    public List<NhanVien> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(NhanVien user) {
        userRepository.deleteUserRoleByNhanVien(user);
        userRepository.delete(user);
    }

    //kiểm tra
    //user tồn tại hay không ?

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
    //Kiểm tra admin đã tồn tại hay chưa
    @Override
    public boolean existsAdmin() {
        return userRepository.existsByRoleName("ADMIN");
    }
    @Override
    public boolean existsOtherAdmin(int userId) {
        return userRoleRepository.existsByRoleNameAndUserIdNot("ADMIN", userId);
    }
}