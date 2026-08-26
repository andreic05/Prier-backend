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

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/prod_var")
public class ProductVariantController {


    private final ProductVariantServiceImpl productVariantService;
    private static final Logger log = LoggerFactory.getLogger(ProductVariantController.class);

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProdVarResponse>> getAllProdVarByProductId(@PathVariable Long productId) {


        return null;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProdVarResponse> createProdVar(@PathVariable Long productId, @Valid @RequestBody ProdVarRequest prodVarRequest) {


        return null;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProdVarResponse> updateProdVar(@PathVariable Long productId, ProdVarRequest prodVarRequest) {


        return null;
    }

    @DeleteMapping("/{productId}/var")
    public ResponseEntity<ProdVarResponse> deleteProdVar(@PathVariable Long productId, @RequestParam Long prodVarId) {


        return null;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<List<ProdVarResponse>> deleteProdVarAll(@PathVariable Long productId) {


        return null;
    }
}