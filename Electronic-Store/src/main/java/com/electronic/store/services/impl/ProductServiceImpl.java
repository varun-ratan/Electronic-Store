package com.electronic.store.services.impl;

import com.electronic.store.controllers.PageableResponse;
import com.electronic.store.dtos.CategoryDto;
import com.electronic.store.dtos.ProductDto;
import com.electronic.store.entities.Category;
import com.electronic.store.entities.Product;
import com.electronic.store.helper.Helper;
import com.electronic.store.repositories.ProductRepository;
import com.electronic.store.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ModelMapper mapper;
    @Override
    public ProductDto create(ProductDto productDto) {
       Product product= mapper.map(productDto,Product.class);
        //product id
       String productId= UUID.randomUUID().toString();
       product.setProductId(productId);
       product.setAddedDate(new Date());
       Product saveProduct=productRepository.save(product);
       return mapper.map(saveProduct,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        Product updatedProduct=productRepository.save(product);
        return mapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(String productId) {
       Product product= productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product is not present with given id"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto getSingle(String productId) {
        Product product=productRepository.findById(productId).orElseThrow(()->new RuntimeException("Product is not present with given id"));
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);
        return  Helper.getPageableResponse(page, ProductDto.class);

    }

    @Override
    public PageableResponse<ProductDto> getLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findByLiveTrue(pageable);
        return  Helper.getPageableResponse(page, ProductDto.class);

    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Page<Product> page = productRepository.findByTitleContaining(subTitle,pageable);
        return  Helper.getPageableResponse(page, ProductDto.class);

    }
}
