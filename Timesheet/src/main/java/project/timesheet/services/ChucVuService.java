package project.timesheet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.timesheet.models.ChucVu;
import project.timesheet.repository.ChucVuRepository;

import java.util.List;

@Service
public class ChucVuService {
    @Autowired
    private ChucVuRepository chucVuRepository;
    public List<ChucVu> getAll() {
        return chucVuRepository.findAll();
    }

    public List<ChucVu> getAllPosition(Integer pageNo,
                                       Integer pageSize,
                                       String sortBy){
        return chucVuRepository.getAllPosition(pageNo, pageSize, sortBy);
    }

    public ChucVu getPositionById(int id) {
        return chucVuRepository.findById(id).orElse(null);
    }


    public ChucVu savePosition(ChucVu ChucVu) {
        return chucVuRepository.save(ChucVu);
    }

    public void deletePositionById(int id) {
        chucVuRepository.deleteById(id);
    }

    public List<ChucVu> searchPosition(String keyword){
        return chucVuRepository.searchPosition(keyword);
    }

}