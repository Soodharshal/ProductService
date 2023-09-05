package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("SelfStoreProductService")
public class SelfStoreProductService implements ProductService{
    @Override
    public GenricProductDTO getProductById(Long id) {
        return new GenricProductDTO();
    }
}
