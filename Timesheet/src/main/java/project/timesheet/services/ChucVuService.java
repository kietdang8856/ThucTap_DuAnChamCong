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
    public List<ChucVu> getAllPosition(Integer pageNo,
                                       Integer pageSize,
                                       String sortBy){
        return repo.getAllPosition(pageNo, pageSize, sortBy);
    }
    public List<ChucVu> getALL()
    {
        return repo.findAll();
    }
//    public ChucVu getOne(int id)
//    {
//        Optional<ChucVu > optional =repo.findById(id);
//        return optional.orElse(null);
//    }
public ChucVu getChucVuById(Integer id) {
    return repo.findById(id).orElse(null);
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
    public List<ChucVu> searchPosition(String keyword){
        return repo.searchPosition(keyword);
    }
}
