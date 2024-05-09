package project.timesheet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.Entity.NhanVien;
import project.timesheet.Entity.TrangThaiLamViec;
import project.timesheet.Repository.NhanVienRepository;
import project.timesheet.Repository.TrangThaiLamViecRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrangThaiLamViecService {
    @Autowired
    private TrangThaiLamViecRepository repo;
    public List<TrangThaiLamViec> getALL()
    {
        return repo.findAll();
    }
    public TrangThaiLamViec getOne(int id)
    {
        Optional<TrangThaiLamViec> optional =repo.findById(id);
        return optional.orElse(null);
    }
    public void create(TrangThaiLamViec tt)
    {
        repo.save(tt);
    }
    public void update(TrangThaiLamViec tt)
    {
        repo.save(tt);
    }
    public void delete(int id)
    {
        repo.deleteById(id);
    }
}
