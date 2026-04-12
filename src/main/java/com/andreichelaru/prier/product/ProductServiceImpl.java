package com.andreichelaru.prier.product;

import com.andreichelaru.prier.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Product 1");
        productDTOS.add(productDTO);
        productDTO = new ProductDTO();
        productDTO.setId(2L);
        productDTO.setName("Product 2");
        productDTOS.add(productDTO);

        return productDTOS;
    }
}
