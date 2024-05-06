package project.timesheet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.Entity.ChucVu;
import project.timesheet.Repository.ChucVuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChucVuService {
    @Autowired
    private ChucVuRepository repo;
    private List<ChucVu> getALL()
    {
        return repo.findAll();
    }
    private Optional<ChucVu> getOne(int id)
    {
        return repo.findById(id);
    }
    private void create(ChucVu cv)
    {
        repo.save(cv);
    }
    private void update(ChucVu cv)
    {
        repo.save(cv);
    }
    private void delete(int id)
    {
        repo.deleteById(id);
    }
}
