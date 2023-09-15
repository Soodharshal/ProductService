package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.dtos.ProductDto;
import dev.harshal.productservice.exceptions.NotFoundException;
import dev.harshal.productservice.thirdpartyclient.productservice.fakeStore.FakeStoreProductServiceClient;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private GenricProductDTO convertProductDto(ProductDto productDto){

        GenricProductDTO product = new GenricProductDTO();
        product.setImage(productDto.getImage());
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        return product;
    }
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
    this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
}

    @Override

    public GenricProductDTO getProductById(Long id) throws NotFoundException {
        return convertProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenricProductDTO createProduct(GenricProductDTO productDTO){
        return convertProductDto(fakeStoreProductServiceClient.createProduct(productDTO));
    }

    @Override
    public GenricProductDTO deleteProductById(Long id) {
        return convertProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

    public List<GenricProductDTO> getProducts() {
        List<GenricProductDTO> genricProductList = new ArrayList<>();
        for (ProductDto ProductDto : fakeStoreProductServiceClient.getProducts()) {
            genricProductList.add(convertProductDto(ProductDto));
        }
        return  genricProductList;
    }
    public void updateProductbyId(Long id) {

    }



}
