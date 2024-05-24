package project.timesheet.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.timesheet.models.ChucVu;

import java.util.List;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {

    default List<ChucVu> getAllPosition(Integer pageNo,
                                        Integer pageSize,
                                        String sortBy){
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }

    @Query("""
        SELECT n
        FROM ChucVu n
        WHERE n.TenChucVu LIKE %:keyword%
        """)
    List<ChucVu> searchPosition(@Param("keyword") String keyword);
}