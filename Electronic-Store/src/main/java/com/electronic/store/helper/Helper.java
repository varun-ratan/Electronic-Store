package com.electronic.store.helper;

import com.electronic.store.controllers.PageableResponse;
import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {
    public static<U,V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type)
    {
        List<U> entity=page.getContent();
        List<V> dtoList=entity.stream().map(object->new ModelMapper().map(object,type)).collect(Collectors.toList());
        PageableResponse<V> pageableresponse=new PageableResponse<>();
        pageableresponse.setContent(dtoList);
        pageableresponse.setPageNumber(page.getNumber());
        pageableresponse.setPageSize(page.getSize());
        pageableresponse.setLastPage(page.isLast());
        pageableresponse.setTotalElements(page.getTotalElements());
        pageableresponse.setTotalPages(page.getTotalPages());
        return  pageableresponse;
    }
}
