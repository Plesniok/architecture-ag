package com.project.products.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "prices")
public class Price {
    /**
     * Entity error mapping rules.
     *
     * Value of invalid argument error should equal one of keys number part in Constant.getDetectionResponseHashMap.
     * Constant class is located in com.project.products.models.Constant
     */

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @NotNull()
//    @Min(1)
    private int productId;

    @NotNull(message = "9")
    private double value;

    private Date validFrom;

    private Date validTo;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
