package com.ENAA_SUPPORT.controller;
import com.ENAA_SUPPORT.model.Material;
import com.ENAA_SUPPORT.model.MaterialPanne;
import com.ENAA_SUPPORT.service.MaterialPannePdfService;
import com.ENAA_SUPPORT.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    @PostMapping("add")
    public String addMaterial(@RequestBody Material material) {
        materialService.addMaterial(material);
        return "the material is added";
    }

    @GetMapping("getall")
    public List<Material> getAllMaterial() {
        return materialService.getAllMaterials();
    }

    @GetMapping("get_by_id/{id}")
    public Material getMaterialById(@PathVariable Integer id) {
        return materialService.getMaterialById(id);
    }

    @DeleteMapping("remove/{id}")
    public String removeMaterial(@PathVariable Integer id) {
        materialService.deleteMaterial(id);
        return "the material is removed";
    }


    @PutMapping("update/{id}")
    public String updateMaterial(@PathVariable Integer id, @RequestBody Material material) {
        materialService.updateMaterial(material , id);
        return "the material is updated";
    }

}
