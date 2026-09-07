package com.andreichelaru.prier.productvariant;

import com.andreichelaru.prier.productvariant.dto.request.ProdVarRequest;
import com.andreichelaru.prier.productvariant.dto.response.ProdVarResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/prod_var")
public class ProductVariantController {


    private final ProductVariantServiceImpl productVariantService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductVariantController.class);

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProdVarResponse>> getAllProdVarByProductId(@PathVariable Long productId) {

        LOGGER.info("Get all prod_vars by product id {}", productId);
        List<ProdVarResponse> prodVarResponseList = productVariantService.getProductVariants(productId);

        return ResponseEntity.ok(prodVarResponseList);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProdVarResponse> createProdVar(@PathVariable Long productId, @Valid @RequestBody ProdVarRequest prodVarRequest) {
        LOGGER.info("Create prod_var {}", prodVarRequest);
        ProdVarResponse prodVarResponse = productVariantService.addProductVariant(productId, prodVarRequest);

        return ResponseEntity.ok(prodVarResponse);
    }

    @PostMapping("/{productId}'/var")
    public ResponseEntity<ProdVarResponse> updateProdVar(@PathVariable Long productId, ProdVarRequest prodVarRequest) {
        LOGGER.info("Update prod_var {}", prodVarRequest);

        ProdVarResponse prodVarResponse = productVariantService.updateProductVariant(productId, prodVarRequest);

        return ResponseEntity.ok(prodVarResponse);
    }

    @DeleteMapping("/{productId}/var")
    public ResponseEntity<ProdVarResponse> deleteProdVar(@PathVariable Long productId, @RequestParam Long prodVarId) {
        LOGGER.info("Delete prod_var with id {}", prodVarId);

        ProdVarResponse prodVarResponse = productVariantService.deleteProductVariant(productId, prodVarId);

        return ResponseEntity.ok(prodVarResponse);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Long> deleteProdVarAll(@PathVariable Long productId) {
        LOGGER.info("Delete all prod_vars for product id {}", productId);
        long prodVarDeleted = productVariantService.deleteAllProductVariants(productId);
        LOGGER.debug("Deleted {} variants" , prodVarDeleted);

        return ResponseEntity.ok(prodVarDeleted);
    }
}