package project.timesheet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.Entity.ChucVu;
import project.timesheet.Entity.NhanVien;
import project.timesheet.Repository.ChucVuRepository;
import project.timesheet.Repository.NhanVienRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository repo;
    private List<NhanVien> getALL()
    {
        return repo.findAll();
    }
    private Optional<NhanVien> getOne(int id)
    {
        return repo.findById(id);
    }
    private void create(NhanVien nv)
    {
        repo.save(nv);
    }
    private void update(NhanVien nv)
    {
        repo.save(nv);
    }
    private void delete(int id)
    {
        repo.deleteById(id);
    }
}
