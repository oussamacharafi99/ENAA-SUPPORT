package com.ENAA_SUPPORT;

import com.ENAA_SUPPORT.dto.TicketDto;
import com.ENAA_SUPPORT.dto.TicketsTechnicianIdDto;
import com.ENAA_SUPPORT.enums.MaterialEtat;
import com.ENAA_SUPPORT.model.*;
import com.ENAA_SUPPORT.repository.TechnicianRepo;
import com.ENAA_SUPPORT.service.MaterialService;
import com.ENAA_SUPPORT.service.PersonService;
import com.ENAA_SUPPORT.service.TicketService;
import com.ENAA_SUPPORT.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EnaaSupportApplicationTests {

//	@Test
//	void contextLoads() {
//	}
//    @Autowired
//    private MaterialService materialService;
//    @Autowired
//    private UserService userService;


//    @Test
//    void  AddMaterial(){
//        User user = userService.getUserByUsername("ibrahim");
//        Material material = new Material("Ordinateur", "ordinateur portable", MaterialEtat.NEW , LocalDate.now(), user);
//        Material material1= materialService.addMaterial(material);
//        assertNotNull(material1.getId());
//        assertEquals(material.getName(), material1.getName());
//        assertEquals(material.getDescription(), material1.getDescription());
//        assertEquals(material1.getEtat(), material.getEtat());
//        assertEquals(material1.getInsert_date(), material.getInsert_date());
//        assertEquals(material1.getUser(), material.getUser());
//    }
//
//    @Test
//    void deleteMaterialTest() {
//        Material material = materialService.getMaterialById(6);
//        assertNotNull(material.getId());
//        String message = materialService.deleteMaterial(material.getId());
//        assertEquals("Material deleted",message);
//    }
//
//    @Test
//    void updateMaterialTest() {
//        Material savedMaterial = materialService.getMaterialById(5);
//        assertNotNull(savedMaterial.getId());
//        savedMaterial.setName("Updated Ordinateur");
//        savedMaterial.setDescription("Updated description");
//        Material updatedMaterial = materialService.updateMaterial(savedMaterial, savedMaterial.getId());
//
//        assertEquals("Updated Ordinateur", updatedMaterial.getName());
//        assertEquals("Updated description", updatedMaterial.getDescription());
//        assertEquals(savedMaterial.getId(), updatedMaterial.getId());
//    }
//
}
