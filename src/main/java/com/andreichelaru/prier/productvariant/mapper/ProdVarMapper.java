package com.andreichelaru.prier.productvariant.mapper;

import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import com.andreichelaru.prier.product.Product;
import com.andreichelaru.prier.product.ProductRepository;
import com.andreichelaru.prier.productvariant.ProductVariant;
import com.andreichelaru.prier.productvariant.dto.request.ProdVarRequest;
import com.andreichelaru.prier.productvariant.dto.response.ProdVarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class ProdVarMapper {

    @Autowired
    private ProductRepository productRepository;

    public ProductVariant toProductVariant(ProdVarRequest prodVarRequest) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(prodVarRequest.getId());

        Product product = productRepository.findById(prodVarRequest.getId())
                .orElseThrow(() -> new BusinessException(List.of(new ErrorModel("INCORECT_PRODUCT_ID",
                        "Incorrect product id"))));
        productVariant.setProduct(product);

        // ProductColor
        // ProductSize

        productVariant.setPrice(BigDecimal.valueOf(prodVarRequest.getPrice()));
        productVariant.setDiscount(BigDecimal.valueOf(prodVarRequest.getDiscount()));
        productVariant.setQuantity(prodVarRequest.getQuantity());
        productVariant.setSku(prodVarRequest.getSku());

        return productVariant;
    }

    public ProdVarResponse toProdVarResponse(ProductVariant productVariant) {
        ProdVarResponse prodVarResponse = new ProdVarResponse();
        prodVarResponse.setId(productVariant.getId());
        prodVarResponse.setProductId(productVariant.getProduct().getId());

        // ProductColor
        // ProductSize

        prodVarResponse.setPrice(productVariant.getPrice().doubleValue());
        prodVarResponse.setDiscount(productVariant.getDiscount().doubleValue());
        prodVarResponse.setQuantity(productVariant.getQuantity());
        prodVarResponse.setSku(productVariant.getSku());

        return prodVarResponse;
    }

    public List<ProductVariant> toProductVariantList(List<ProdVarRequest> prodVarRequests) {

        return prodVarRequests.stream()
                .map(this::toProductVariant).toList();

    }

    public List<ProdVarResponse> toProdVarResponseList(List<ProductVariant> productVariantList) {

        return productVariantList.stream()
                .map(this::toProdVarResponse).toList();

    }

}
