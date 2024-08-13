package com.ENAA_SUPPORT.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialPanneId implements Serializable {
    private Integer historiesId;
    private Integer materialId;
    private Integer panneId;

    public MaterialPanneId() {}

    public MaterialPanneId(Integer materialId, Integer panneId, Integer historiesId) {
        this.materialId = materialId;
        this.panneId = panneId;
        this.historiesId = historiesId;
    }
    public MaterialPanneId(Integer materialId, Integer panneId) {
        this.materialId = materialId;
        this.panneId = panneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialPanneId that = (MaterialPanneId) o;
        return Objects.equals(historiesId, that.historiesId) && Objects.equals(materialId, that.materialId) && Objects.equals(panneId, that.panneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historiesId, materialId, panneId);
    }
}
