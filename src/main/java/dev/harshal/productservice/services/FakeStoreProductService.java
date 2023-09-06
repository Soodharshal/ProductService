package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private String requesturl = "https://fakestoreapi.com/products/{id}";
    private String fakeStoreCreateApi ="https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;
public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
    this.restTemplateBuilder = restTemplateBuilder;
}


    @Override

    public GenricProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity(requesturl,ProductDto.class,id);
ProductDto productDto = responseEntity.getBody();
        //        responseEntity.getStatusCode();
GenricProductDTO product = new GenricProductDTO();
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        return product;
    }

    @Override
    public GenricProductDTO createProduct(GenricProductDTO productDTO){
       RestTemplate restTemplate = restTemplateBuilder.build();
       ResponseEntity<GenricProductDTO> response=  restTemplate.postForEntity(fakeStoreCreateApi,productDTO,GenricProductDTO.class);
       return response.getBody();
    }
}
