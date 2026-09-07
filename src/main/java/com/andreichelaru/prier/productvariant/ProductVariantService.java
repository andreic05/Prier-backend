package com.andreichelaru.prier.productvariant;

import com.andreichelaru.prier.productvariant.dto.request.ProdVarRequest;
import com.andreichelaru.prier.productvariant.dto.response.ProdVarResponse;

import java.util.List;

public interface ProductVariantService {
    List<ProdVarResponse> getProductVariants(Long productId);
    ProdVarResponse addProductVariant(Long productId, ProdVarRequest prodVarRequest);
    ProdVarResponse updateProductVariant(Long productId, ProdVarRequest prodVarRequest);
    ProdVarResponse deleteProductVariant(Long productId, Long productVariantId);
    long deleteAllProductVariants(Long productId);
}