package project.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.ChucVu;
import project.timesheet.repository.ChucVuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChucVuService {
    @Autowired
    private ChucVuRepository repo;
    public List<ChucVu> getALL()
    {
        return repo.findAll();
    }
    public ChucVu getOne(int id)
    {
        Optional<ChucVu > optional =repo.findById(id);
    return optional.orElse(null);
    }
    public void create(ChucVu cv)
    {
        repo.save(cv);
    }
    public void update(ChucVu cv)
    {
        repo.save(cv);
    }
    public void delete(int id)
    {
        repo.deleteById(id);
    }
}
