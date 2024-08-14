package com.ENAA_SUPPORT.controller;

import com.ENAA_SUPPORT.model.Material;
import com.ENAA_SUPPORT.model.MaterialPanne;
import com.ENAA_SUPPORT.service.MaterialPannePdfService;
import com.ENAA_SUPPORT.service.MaterialPanneService;
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
@RequestMapping("api/historic")
public class HistoricController {


    @Autowired
    private MaterialPanneService materialPanneService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialPannePdfService materialPannePdfService;


    @GetMapping("all")
    public List<MaterialPanne> historic() {
        return materialPanneService.getAllMaterialPannes();
    }

    @GetMapping("/{id}/panne/pdf")
    public ResponseEntity<byte[]> generateMaterialPannePdf(@PathVariable Integer id) {
        Material material = materialService.getMaterialById(id);

        if (material == null) {
            System.out.println("Material not found for ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<MaterialPanne> materialPannes = material.getMaterialPannes();

        if (materialPannes == null || materialPannes.isEmpty()) {
            System.out.println("No pannes found for Material ID: " + id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        try (ByteArrayInputStream pdf = materialPannePdfService.generateMaterialPannePdf(material, materialPannes)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=material_panne_history_" + id + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
