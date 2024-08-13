package com.ENAA_SUPPORT.service;

import com.ENAA_SUPPORT.repository.MaterialPanneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialPanneService {
    @Autowired
    private MaterialPanneRepo materialPanneRepo;

    public Integer maxId(){
        return materialPanneRepo.findMaxHistoryId();
    }
}
