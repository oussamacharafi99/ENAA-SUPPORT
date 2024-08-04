package com.ENAA_SUPPORT.Service;
import com.ENAA_SUPPORT.Enum.MaterialEtat;
import com.ENAA_SUPPORT.Model.Material;
import com.ENAA_SUPPORT.Repository.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

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

    public void addMaterial(Material material) {
        material.setEtat(MaterialEtat.NEW);
        materialRepo.save(material);
    }

    public Material updateMaterial(Material material , Integer id) {
        Material material1 = materialRepo.findById(id).orElseThrow();
        material1.setName(material.getName());
        material1.setDescription(material.getDescription());
        material1.setInsert_date(material.getInsert_date());
        material1.setEtat(material.getEtat());
        return materialRepo.save(material1);
    }

    public void deleteMaterial(Integer id) {
        materialRepo.deleteById(id);
    }
}
