package com.ENAA_SUPPORT.service;
import com.ENAA_SUPPORT.enums.MaterialEtat;
import com.ENAA_SUPPORT.model.Material;
import com.ENAA_SUPPORT.repository.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepo materialRepo;

    public List<Material> getAllMaterials() {
        return materialRepo.findAll();
    }

    public Material getMaterialById(Integer id) {
        return materialRepo.findById(id).orElseThrow();
    }

    public Material addMaterial(Material material) {
        material.setEtat(MaterialEtat.NEW);
        return materialRepo.save(material);
    }

    public Material updateMaterial(Material material , Integer id) {
        Material material1 = materialRepo.findById(id).orElseThrow();
        material1.setName(material.getName());
        material1.setDescription(material.getDescription());
        material1.setInsert_date(LocalDate.now());
        material1.setEtat(MaterialEtat.NEW);
        return materialRepo.save(material1);
    }

    public String deleteMaterial(Integer id) {
        materialRepo.deleteById(id);
        return "Material deleted";
    }

    public long getTotalMaterials() {
        return materialRepo.count();
    }

}

