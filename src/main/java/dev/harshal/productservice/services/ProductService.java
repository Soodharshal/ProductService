package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.models.Product;
import org.springframework.stereotype.Service;


public interface ProductService {

    GenricProductDTO getProductById(Long id);

    GenricProductDTO createProduct(GenricProductDTO productDTO);
}
