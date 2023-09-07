package dev.harshal.productservice.services;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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


    @Override

    public GenricProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity(fakeStoreProductByIdApi,ProductDto.class,id);
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
       ResponseEntity<GenricProductDTO> response=  restTemplate.postForEntity(fakeStoreProductApi,productDTO,GenricProductDTO.class);
       return response.getBody();
    }
   public List<GenricProductDTO> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
       ParameterizedTypeReference<List<ProductDto>> typeRef = new ParameterizedTypeReference<List<ProductDto>>() {};
       ResponseEntity<List<ProductDto>> responseEntity = restTemplate.exchange(fakeStoreProductApi, HttpMethod.GET, null, typeRef);

        List<GenricProductDTO> products = new ArrayList<>();
       if (responseEntity.getStatusCode().is2xxSuccessful()) {
           List<ProductDto> productDtoList = responseEntity.getBody();
    for (ProductDto ProductDto : productDtoList) {
               GenricProductDTO product = new GenricProductDTO();
               product.setImage(ProductDto.getImage());
               product.setDescription(ProductDto.getDescription());
               product.setTitle(ProductDto.getTitle());
               product.setPrice(ProductDto.getPrice());
               product.setCategory(ProductDto.getCategory());
               product.setId(ProductDto.getId());
               products.add(product);
           }
       }

        return products;
    }
    public void updateProductbyId(Long id) {

    }



}
