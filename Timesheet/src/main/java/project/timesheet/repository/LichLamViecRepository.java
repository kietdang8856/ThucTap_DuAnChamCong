package project.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.timesheet.models.LichLamViec;

import java.util.Date;
import java.util.List;

@Repository
public interface LichLamViecRepository extends JpaRepository<LichLamViec,Integer> {
    List<LichLamViec> findAllByNgayLamBetween(Date tuNgay, Date denNgay);
    List<LichLamViec> findAllByNhanVienId(int id);
    List<LichLamViec> findAllByVpCongTacId(int id);
    List<LichLamViec> findAllByTrangThaiId(int id);
}
