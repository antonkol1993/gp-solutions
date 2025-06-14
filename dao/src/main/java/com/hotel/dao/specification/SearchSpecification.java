package com.hotel.dao.specification;


import org.springframework.data.jpa.domain.Specification;

public interface SearchSpecification<T> {
    Specification<T> toSpecification();
}
