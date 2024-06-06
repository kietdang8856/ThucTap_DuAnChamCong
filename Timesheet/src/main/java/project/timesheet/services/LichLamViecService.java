package project.timesheet.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.LichLamViec;
import project.timesheet.models.NhanVien;
import project.timesheet.repository.LichLamViecRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LichLamViecService {
    @Autowired
    private LichLamViecRepository repo;
    public List<LichLamViec> getAll()
    {
        return repo.findAll();
    }
    public LichLamViec getOne(int id)
    {
        Optional<LichLamViec> optional =repo.findById(id);
        return optional.orElse(null);
    }
    public List<LichLamViec> getAllBetweenDate(Date tuNgay,Date denNgay)
    {
        return repo.findAllByNgayLamBetween(tuNgay,denNgay);
    }
    public List<LichLamViec> getAllByNhanVienId(int nvid)
    {
        return repo.findAllByNhanVienId(nvid);
    }
    public List<LichLamViec> getAllByVanPhongId(int vpid)
    {
        return repo.findAllByVpCongTacId(vpid);
    }
    public List<LichLamViec> getAllByTrangThaiId(int ttid)
    {
        return repo.findAllByTrangThaiId(ttid);
    }
    public void create(LichLamViec nv)
    {
        repo.save(nv);
    }
    public void update(LichLamViec nv)
    {
        repo.save(nv);
    }
    public void delete(int id)
    {
        repo.deleteById(id);
    }
    public boolean existsByNhanVien(NhanVien nhanVien) {
        return repo.existsByNhanVien(nhanVien);
    }
    @Transactional
    public void deleteByNhanVien(NhanVien nhanVien) {
        repo.deleteByNhanVien(nhanVien);
    }
    public List<LichLamViec> getEventsByUserId(int userId) {
        return repo.findAllByNhanVienId(userId);
    }
}
