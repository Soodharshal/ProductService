package dev.harshal.productservice.controllers;

import dev.harshal.productservice.dtos.ExceptionDTO;
import dev.harshal.productservice.dtos.GenricProductDTO;
import dev.harshal.productservice.exceptions.NotFoundException;
import dev.harshal.productservice.models.Product;
import dev.harshal.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public List<GenricProductDTO> getAllProducts(){
        return productService.getProducts();
    }
    @GetMapping("{id}")
    public ResponseEntity<GenricProductDTO> getProductById(@PathVariable("id") Long id) throws NotFoundException {
      GenricProductDTO product = productService.getProductById(id);

        ResponseEntity<GenricProductDTO> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }
    @DeleteMapping("{id}")
    public GenricProductDTO deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }
    @PostMapping()
    public GenricProductDTO createProduct(@RequestBody GenricProductDTO productDTO){
        return productService.createProduct(productDTO);
      }
    @PutMapping("{id}")
    public void updateProductbyId(@RequestBody GenricProductDTO productDTO){
//         productService.updateProductbyId(id);
    }


}
