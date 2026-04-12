package com.andreichelaru.prier.product;

import com.andreichelaru.prier.product.dto.ProductDTO;
import com.andreichelaru.prier.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = (ArrayList<Product>) productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = productMapper.toProductDTO(product);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::toProductDTO).orElse(null);
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Optional<Product> product = productRepository.getProductByName(name);

        return product.map(productMapper::toProductDTO).orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productRepository.save(product);

        return productMapper.toProductDTO(product);
    }

    @Override
    public List<ProductDTO> createProducts(List<ProductDTO> productDTOs) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOs) {
            products.add(productMapper.toProduct(productDTO));
        }
        products = (ArrayList<Product>) productRepository.saveAll(products);

        List<ProductDTO> newProductDTOs = new ArrayList<>();
        for (Product product : products) {
            newProductDTOs.add(productMapper.toProductDTO(product));
        }

        return newProductDTOs;
    }
}
