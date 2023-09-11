package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.dtos.ProductDto;
import dev.harshal.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private String fakeStoreProductByIdApi = "https://fakestoreapi.com/products/{id}";
    private String fakeStoreProductApi ="https://fakestoreapi.com/products";
      private RestTemplateBuilder restTemplateBuilder;
public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
    this.restTemplateBuilder = restTemplateBuilder;
}


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

    @Override

    public GenricProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductByIdApi,ProductDto.class,id);
        ProductDto productDto = responseEntity.getBody();
        if(productDto == null){
            throw new NotFoundException("Product with id "+id+" not found");
        }
    return convertProductDto(productDto);
    }

    @Override
    public GenricProductDTO createProduct(GenricProductDTO productDTO){
       RestTemplate restTemplate = restTemplateBuilder.build();
       ResponseEntity<GenricProductDTO> response=  restTemplate.postForEntity(fakeStoreProductApi,productDTO,GenricProductDTO.class);
       return response.getBody();
    }

    @Override
    public GenricProductDTO deleteProductById(Long id) {

    RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(ProductDto.class);
        ResponseExtractor<ResponseEntity<ProductDto>> responseExtractor = restTemplate.responseEntityExtractor(ProductDto.class);
         ResponseEntity<ProductDto> response  = restTemplate.execute(fakeStoreProductByIdApi, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        ProductDto productDto = response.getBody();
        return convertProductDto(productDto);
    }

    public List<GenricProductDTO> getProducts() {
       RestTemplate restTemplate = restTemplateBuilder.build();
       ParameterizedTypeReference<List<ProductDto>> typeRef = new ParameterizedTypeReference<List<ProductDto>>() {};
       ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange(fakeStoreProductApi, HttpMethod.GET, null, typeRef);
        List<GenricProductDTO> products = new ArrayList<>();
       if (responseEntity.getStatusCode().is2xxSuccessful()) {
           List<ProductDto> productDtoList = responseEntity.getBody();
    for (ProductDto ProductDto : productDtoList) {
           products.add(convertProductDto(ProductDto));
           }
       }

        return products;
    }
    public void updateProductbyId(Long id) {

    }



}
