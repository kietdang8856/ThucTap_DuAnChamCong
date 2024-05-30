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

//    public VanPhong getOne(int id)
//    {
//        Optional<VanPhong> optional =repo.findById(id);
//        return optional.orElse(null);
//    }
public VanPhong getVanPhongById(Integer id) {
    return repo.findById(id).orElse(null);
}

    public List<VanPhong> searchVanPhongByName(String name) {
        return repo.findByNameContaining(name);
    }
    public Optional<VanPhong> getVanPhongByid(Integer id) {
        return repo.findById(id);
    }
    public VanPhong createVanPhong(VanPhong vanPhong) {
        return repo.save(vanPhong);
    }

    public Optional<VanPhong> updateVanPhong(Integer id, VanPhong vanPhongDetails) {
        return repo.findById(id).map(vanPhong -> {
            vanPhong.setTenVP(vanPhongDetails.getTenVP());
            vanPhong.setDiaChi(vanPhongDetails.getDiaChi());
            return repo.save(vanPhong);
        });
    }

    public void deleteVanPhong(Integer id) {
        repo.deleteById(id);
    }
}
