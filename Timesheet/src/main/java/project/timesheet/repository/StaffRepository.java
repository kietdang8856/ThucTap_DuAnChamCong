package project.timesheet.repository;

import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.timesheet.models.NhanVien;
import project.timesheet.models.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Repository
public interface StaffRepository extends JpaRepository<NhanVien, Integer> {
    default List<NhanVien> getAllStaff(Integer pageNo,
                                       Integer pageSize,
                                       String sortBy){
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }

    @Query("""
        SELECT n
        FROM NhanVien n
        WHERE n.tenNV LIKE %:keyword%
        """)
    List<NhanVien> searchStaff(@Param("keyword") String keyword);
}