package project.timesheet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.Entity.ChucVu;
import project.timesheet.Entity.LichLamViec;
import project.timesheet.Entity.NhanVien;
import project.timesheet.Repository.LichLamViecRepository;
import project.timesheet.Repository.NhanVienRepository;

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
}
