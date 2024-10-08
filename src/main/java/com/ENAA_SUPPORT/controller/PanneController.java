package com.ENAA_SUPPORT.controller;
import com.ENAA_SUPPORT.model.Panne;
import com.ENAA_SUPPORT.service.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/panne")
public class PanneController {

    @Autowired
    PanneService panneService;

    @PostMapping("add")
    public String addPanne(@RequestBody Panne panne) {
        panneService.savePanneByAdmin(panne);
        return "teh Panne added successfully";
    }

    @GetMapping("get")
    public List<Panne> getPanne() {
        return panneService.getAllPannes();
    }


    @DeleteMapping("delete/{id}")
    public String deletePanne(@PathVariable Integer id) {
        return panneService.deletePanneByAdmin(id);
    }

}
