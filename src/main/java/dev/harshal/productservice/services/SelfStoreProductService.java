package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfStoreProductService")
public class SelfStoreProductService implements ProductService{
    @Override
    public List<GenricProductDTO> getProducts() {
        return null;
    }

    @Override
    public GenricProductDTO getProductById(Long id) {
        return new GenricProductDTO();
    }

    @Override
    public GenricProductDTO createProduct(GenricProductDTO productDTO) {
        return null;
    }
}
