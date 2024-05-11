package project.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.LichLamViec;
import project.timesheet.repository.LichLamViecRepository;

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
