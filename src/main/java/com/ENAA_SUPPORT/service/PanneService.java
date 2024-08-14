package com.ENAA_SUPPORT.service;
import com.ENAA_SUPPORT.exeptions.EnaaSupportException;
import com.ENAA_SUPPORT.model.Panne;
import com.ENAA_SUPPORT.repository.PanneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PanneService {

    @Autowired
    private PanneRepo panneRepo;

    public Panne savePanneByAdmin(Panne panne) {
        panne.setDate(LocalDate.now());
        return panneRepo.save(panne);
    }

    public List<Panne> getAllPannes() {
        return panneRepo.findAll();
    }

    public Panne updatePanneByAdmin(Panne panne , Integer id) {
        Panne panne1 = panneRepo.findById(id).orElseThrow();
        panne1.setId(panne.getId());
        panne1.setDescription(panne.getDescription());
        panne1.setDate(panne.getDate());
        panne1.setType(panne.getType());
        return panneRepo.save(panne1);
    }

    public String deletePanneByAdmin(Integer id) {
        panneRepo.deleteById(id);
        return "Panne deleted Successfully";
    }
}

