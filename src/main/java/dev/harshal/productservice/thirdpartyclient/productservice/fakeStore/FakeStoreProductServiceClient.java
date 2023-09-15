package dev.harshal.productservice.thirdpartyclient.productservice.fakeStore;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.dtos.ProductDto;
import dev.harshal.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient{
    private String fakeStoreProductByIdApi = "https://fakestoreapi.com/products/{id}";
    private String fakeStoreProductApi ="https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public ProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductByIdApi,ProductDto.class,id);
        ProductDto productDto = responseEntity.getBody();
        if(productDto == null){
            throw new NotFoundException("Product with id "+id+" not found");
        }
        return productDto;
    }

     public ProductDto createProduct(GenricProductDTO productDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response=  restTemplate.postForEntity(fakeStoreProductApi,productDTO,ProductDto.class);
        return response.getBody();
    }

    @Override
    public ProductDto deleteProductById(Long id) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(ProductDto.class);
        ResponseExtractor<ResponseEntity<ProductDto>> responseExtractor = restTemplate.responseEntityExtractor(ProductDto.class);
        ResponseEntity<ProductDto> response  = restTemplate.execute(fakeStoreProductByIdApi, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        ProductDto productDto = response.getBody();
        return productDto;
    }

    public List<ProductDto> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ParameterizedTypeReference<List<ProductDto>> typeRef = new ParameterizedTypeReference<List<ProductDto>>() {};
        ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange(fakeStoreProductApi, HttpMethod.GET, null, typeRef);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        return  null;
    }
    public ProductDto updateProductbyId(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto existingProduct = restTemplate.getForObject(fakeStoreProductByIdApi, ProductDto.class);

        // TODO: Update the fields of existingProduct here
        // For demonstration, I'm changing the title; replace this with actual updates
        existingProduct.setTitle("New Title");

        // Send an HTTP PUT request to update the product
        restTemplate.put(fakeStoreProductByIdApi, existingProduct);

        // Retrieve the updated product
        ProductDto updatedProduct = restTemplate.getForObject(fakeStoreProductByIdApi, ProductDto.class);

        return updatedProduct;
    }


}
