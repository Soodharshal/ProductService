package dev.harshal.productservice.controllers;

import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.models.Product;
import dev.harshal.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Value("${productservice.type}")
    private String productServiceType;
private ProductService productService;


//    public ProductController(@Qualifier(productServiceType) ProductService productService){
//        productService = productService;
//    }
    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping()
    public void getAllProducts(){

    }
    @GetMapping("{id}")
    public GenricProductDTO getProductById(@PathVariable("id") Long id){
//        System.out.println("Inside getProductById method");
//        return "Here is product " + id;
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    @PostMapping()
    public GenricProductDTO createProduct(@RequestBody GenricProductDTO productDTO){
        return productService.createProduct(productDTO);
      }
    @PutMapping("{id}")
    public void updateProductbyId(){

    }
}
