package dev.harshal.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public void getAllProducts(){

    }
    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id){
        System.out.println("Inside getProductById method");
        return "Here is product " + id;
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    @PostMapping()
    public void createProduct(){

    }
    @PutMapping("{id}")
    public void updateProductbyId(){

    }
}
