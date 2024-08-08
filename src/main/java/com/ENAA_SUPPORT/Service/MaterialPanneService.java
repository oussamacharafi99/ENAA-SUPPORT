package com.ENAA_SUPPORT.Service;

import com.ENAA_SUPPORT.Model.MaterialPanneId;
import com.ENAA_SUPPORT.Repository.MaterialPanneRepo;
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
