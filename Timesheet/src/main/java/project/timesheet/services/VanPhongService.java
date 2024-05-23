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
    private VanPhongRepository vanPhongRepository;

    public List<VanPhong> searchVanPhongByName(String name) {
        return vanPhongRepository.findByNameContaining(name);
    }

    public List<VanPhong> getAllVanPhongs() {
        return vanPhongRepository.findAll();
    }

    public Optional<VanPhong> getVanPhongById(Long id) {
        return vanPhongRepository.findById(id);
    }

    public VanPhong createVanPhong(VanPhong vanPhong) {
        return vanPhongRepository.save(vanPhong);
    }

    public Optional<VanPhong> updateVanPhong(Long id, VanPhong vanPhongDetails) {
        return vanPhongRepository.findById(id).map(vanPhong -> {
            vanPhong.setName(vanPhongDetails.getName());
            vanPhong.setOfficeAddress(vanPhongDetails.getOfficeAddress());
            return vanPhongRepository.save(vanPhong);
        });
    }

    public void deleteVanPhong(Long id) {
        vanPhongRepository.deleteById(id);
    }

    // Removing redundant methods
    // If needed, rename methods to maintain consistency
}
