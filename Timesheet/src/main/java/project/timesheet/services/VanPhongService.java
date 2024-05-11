package project.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.VanPhong;
import project.timesheet.repository.VanPhongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VanPhongService {
    @Autowired
    private VanPhongRepository repo;
    public List<VanPhong> getALL()
    {
        return repo.findAll();
    }
    public VanPhong getOne(int id)
    {
        Optional<VanPhong> optional =repo.findById(id);
        return optional.orElse(null);
    }
    public void create(VanPhong vp)
    {
        repo.save(vp);
    }
    public void update(VanPhong vp)
    {
        repo.save(vp);
    }
    public void delete(int id)
    {
        repo.deleteById(id);
    }
}
