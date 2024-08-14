package com.ENAA_SUPPORT.service;

import com.ENAA_SUPPORT.model.MaterialPanne;
import com.ENAA_SUPPORT.repository.MaterialPanneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialPanneService {
    @Autowired
    private MaterialPanneRepo materialPanneRepo;

    public List<MaterialPanne> getAllMaterialPannes() {
        return materialPanneRepo.findAll();
    }

}
