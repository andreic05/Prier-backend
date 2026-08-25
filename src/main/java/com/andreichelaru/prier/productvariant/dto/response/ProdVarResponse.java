package com.andreichelaru.prier.productvariant.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class ProdVarResponse {

    private Long id;
    private long productId;
    private long colorId;
    private long sizeId;
    private double price;
    private double discount;
    private int quantity;
    private String sku;
}
