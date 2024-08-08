package com.ENAA_SUPPORT.Controller;
import com.ENAA_SUPPORT.Model.Material;
import com.ENAA_SUPPORT.Model.MaterialPanne;
import com.ENAA_SUPPORT.Service.MaterialPannePdfService;
import com.ENAA_SUPPORT.Service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialPannePdfService materialPannePdfService;

    @GetMapping("/{id}/panne/pdf")
    public ResponseEntity<byte[]> generateMaterialPannePdf(@PathVariable Integer id) {
        Material material = materialService.getMaterialById(id);
        List<MaterialPanne> materialPannes = material.getMaterialPannes();

        ByteArrayInputStream pdf = materialPannePdfService.generateMaterialPannePdf(material, materialPannes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=material_panne_history_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

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
