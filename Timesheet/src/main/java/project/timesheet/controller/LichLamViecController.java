package project.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import project.timesheet.Entity.LichLamViec;
import project.timesheet.Service.LichLamViecService;

@Controller
@RequestMapping("/lichlamviec")
public class LichLamViecController {
    @Autowired
    private LichLamViecService service;

//    @PostMapping("/lichlamviec/create")
//    public ResponseEntity<LichLamViec> create(@RequestBody LichLamViec lichlam) {
//
//    }
}
