package project.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.timesheet.models.CustomUserDetails;
import project.timesheet.models.Role;
import project.timesheet.models.NhanVien;
import project.timesheet.models.UserRole;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private NhanVienService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NhanVien user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Không tìm thấy user");
        }
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<UserRole> roles = user.getUserRoles(); //Lấy quyền
        for (UserRole userRole: roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return new CustomUserDetails(user,grantedAuthorities);
    }
}
