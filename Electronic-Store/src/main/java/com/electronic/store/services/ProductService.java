package com.electronic.store.services;

import com.electronic.store.controllers.PageableResponse;
import com.electronic.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto, String productId);

    //delete
    void delete(String productId);

    //getSingle
    ProductDto getSingle(String productId);

    //getAll
    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get live
    PageableResponse<ProductDto> getLive(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search
    PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber, int pageSize, String sortBy, String sortDir);

    //other
}
