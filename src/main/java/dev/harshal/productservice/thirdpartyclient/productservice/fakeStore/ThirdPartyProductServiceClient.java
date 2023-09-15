package dev.harshal.productservice.thirdpartyclient.productservice.fakeStore;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.dtos.ProductDto;
import dev.harshal.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {
    List<ProductDto> getProducts();

    ProductDto getProductById(Long id) throws NotFoundException;

    ProductDto createProduct(GenricProductDTO productDTO);
    ProductDto deleteProductById(Long id);
}
