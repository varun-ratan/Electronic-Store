package com.electronic.store.services;


import com.electronic.store.controllers.PageableResponse;
import com.electronic.store.dtos.CategoryDto;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto,String categoryId);

    //delete
    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get sing category detail
    CategoryDto get(String categoryId);

    //search
}
