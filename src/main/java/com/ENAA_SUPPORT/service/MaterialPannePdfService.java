package com.ENAA_SUPPORT.service;

import com.ENAA_SUPPORT.model.Material;
import com.ENAA_SUPPORT.model.MaterialPanne;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class MaterialPannePdfService {

    public ByteArrayInputStream generateMaterialPannePdf(Material material, List<MaterialPanne> materialPannes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Ajouter le titre
            document.add(new Paragraph("Material Panne History")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18)
                    .setBold());

            // Ajouter les détails du matériel
            document.add(new Paragraph("Material ID: " + material.getId()));
            document.add(new Paragraph("Material Name: " + material.getName()));

            // Ajouter un tableau pour les pannes
            Table table = new Table(5);
            table.addHeaderCell("Panne Type");
            table.addHeaderCell("Panne Description");
            table.addHeaderCell("Start Date");
            table.addHeaderCell("End Date");
            table.addHeaderCell("Description");

            for (MaterialPanne panne : materialPannes) {
                table.addCell(panne.getPanne().getType().toString());
                table.addCell(panne.getPanne().getDescription().toString());
                table.addCell(panne.getStartDate().toString());
                table.addCell(panne.getEndDate().toString());
                table.addCell(panne.getDescription());
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
