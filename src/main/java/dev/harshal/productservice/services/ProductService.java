package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<GenricProductDTO> getProducts();

    GenricProductDTO getProductById(Long id);

    GenricProductDTO createProduct(GenricProductDTO productDTO);
}

