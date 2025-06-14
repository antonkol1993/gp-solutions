package com.hotel.mapper;

public interface Mapper<S, D> {
    D toDto(S source);
    S toEntity(D dto);
}
