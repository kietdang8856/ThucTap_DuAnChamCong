package project.timesheet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.Entity.NhanVien;
import project.timesheet.Entity.VanPhong;
import project.timesheet.Repository.NhanVienRepository;
import project.timesheet.Repository.VanPhongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VanPhongService {
    @Autowired
    private VanPhongRepository repo;
    private List<VanPhong> getALL()
    {
        return repo.findAll();
    }
    private Optional<VanPhong> getOne(int id)
    {
        return repo.findById(id);
    }
    private void create(VanPhong vp)
    {
        repo.save(vp);
    }
    private void update(VanPhong vp)
    {
        repo.save(vp);
    }
    private void delete(int id)
    {
        repo.deleteById(id);
    }
}
