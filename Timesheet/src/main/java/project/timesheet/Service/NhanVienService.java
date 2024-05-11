package project.timesheet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.NhanVien;
import project.timesheet.repository.NhanVienRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository repo;
    public List<NhanVien> getALL()
    {
        return repo.findAll();
    }
    public NhanVien getOne(int id)
    {
        Optional<NhanVien> optional =repo.findById(id);
        return optional.orElse(null);

    }
    public void create(NhanVien nv)
    {
        repo.save(nv);
    }
    public void update(NhanVien nv)
    {
        repo.save(nv);
    }
    public void delete(int id)
    {
        repo.deleteById(id);
    }
}
