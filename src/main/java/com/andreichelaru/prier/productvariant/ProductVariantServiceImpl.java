package com.andreichelaru.prier.productvariant;

import com.andreichelaru.prier.common.exceptions.BusinessException;
import com.andreichelaru.prier.common.exceptions.ErrorModel;
import com.andreichelaru.prier.product.ProductRepository;
import com.andreichelaru.prier.productvariant.dto.request.ProdVarRequest;
import com.andreichelaru.prier.productvariant.dto.response.ProdVarResponse;
import com.andreichelaru.prier.productvariant.mapper.ProdVarMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductVariantServiceImpl  implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final ProdVarMapper prodVarMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductVariantServiceImpl.class);

    @Override
    public List<ProdVarResponse> getProductVariants(Long productId) {

        if (!productRepository.existsById(productId)) throw new BusinessException(
                List.of(new ErrorModel("INVALID_PRODUCT_ID", "Product with id %d doesn't exist".formatted(productId)))
        );

        LOGGER.debug("Fetching product variants for product id {}", productId);

        List<ProductVariant> productVariants = productVariantRepository.getProductVariantsByProductId(productId);

        if (productVariants.isEmpty())
            throw new BusinessException(List.of(new ErrorModel("NO_PRODUCT_VARIANTS", "Product doesn't have registered variants")));

        return prodVarMapper.toProdVarResponseList(productVariants);
    }

    @Override
    public ProdVarResponse addProductVariant(Long productId,ProdVarRequest prodVarRequest) {
        if (!productRepository.existsById(productId)) throw new BusinessException(
                List.of(new ErrorModel("INVALID_PRODUCT_ID", "Product with id %d doesn't exist".formatted(productId)))
        );

        if (productVariantRepository.existsById(prodVarRequest.getId())) throw new BusinessException(
                List.of(new ErrorModel("INVALID_VARIANT_ID", "Product variant with id %d already exists".formatted(prodVarRequest.getId())))
        );

        LOGGER.debug("Adding product variant for product id {}", productId);
        prodVarRequest.setProductId(productId);

        ProductVariant productVariant = productVariantRepository.save(prodVarMapper.toProductVariant(prodVarRequest));

        LOGGER.debug("Added product variant for product id {}", productVariant.getId());
        return prodVarMapper.toProdVarResponse(productVariant);
    }

    @Override
    public ProdVarResponse updateProductVariant(Long productId, ProdVarRequest prodVarRequest) {
        if (!productRepository.existsById(productId)) throw new BusinessException(
                List.of(new ErrorModel("INVALID_PRODUCT_ID", "Product with id %d doesn't exist".formatted(productId)))
        );

        if (!productVariantRepository.existsById(prodVarRequest.getId())) throw new BusinessException(
                List.of(new ErrorModel("INVALID_VARIANT_ID", "Product variant with id %d doesn't exists".formatted(prodVarRequest.getId())))
        );

        ProductVariant productVariant = prodVarMapper.toProductVariant(prodVarRequest);

        // not two variants with the same color validation

        LOGGER.debug("Updating product variant for product id {}", productVariant.getId());
        productVariant = productVariantRepository.save(productVariant);

        return prodVarMapper.toProdVarResponse(productVariant);
    }

    @Override
    public ProdVarResponse deleteProductVariant(Long productId, Long productVariantId) {
        if (!productRepository.existsById(productId)) throw new BusinessException(
                List.of(new ErrorModel("INVALID_PRODUCT_ID", "Product with id %d doesn't exist".formatted(productId)))
        );

        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElseThrow(
                () -> new BusinessException(List.of(new ErrorModel("INVALID_VARIANT_ID", "Product variant with id %d doesn't exists".formatted(productVariantId))))
        );

        ProdVarResponse prodVarResponse = prodVarMapper.toProdVarResponse(productVariant);

        LOGGER.debug("Deleting product variant for product id {}", productVariant.getId());
        productVariantRepository.delete(productVariant);

        return prodVarResponse;
    }

    @Override
    public long deleteAllProductVariants(Long productId) {
        if (!productRepository.existsById(productId)) throw new BusinessException(
                List.of(new ErrorModel("INVALID_PRODUCT_ID", "Product with id %d doesn't exist".formatted(productId)))
        );

        LOGGER.debug("Deleting all product variant for product id {}", productId);

        return productVariantRepository.deleteByProductId(productId);
    }
}