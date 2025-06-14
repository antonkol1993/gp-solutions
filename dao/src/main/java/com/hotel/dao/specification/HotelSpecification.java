package com.hotel.dao.specification;

import com.hotel.model.entity.Hotel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;


public class HotelSpecification implements SearchSpecification<Hotel> {

    private final String name;
    private final String brand;
    private final String city;
    private final String country;
    private final String amenity;

    public HotelSpecification(String name, String brand, String city, String country, String amenity) {
        this.name = name;
        this.brand = brand;
        this.city = city;
        this.country = country;
        this.amenity = amenity;
    }

    @Override
    public Specification<Hotel> toSpecification() {
        Specification<Hotel> spec = null;

        spec = safeAnd(spec, nameContains(name));
        spec = safeAnd(spec, brandContains(brand));
        spec = safeAnd(spec, cityContains(city));
        spec = safeAnd(spec, countryContains(country));
        spec = safeAnd(spec, hasAmenity(amenity));

        return spec;
    }


    public static Specification<Hotel> nameContains(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> brandContains(String brand) {
        return (root, query, cb) -> {
            if (brand == null || brand.isBlank()) return null;
            return cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> cityContains(String city) {
        return (root, query, cb) -> {
            if (city == null || city.isBlank()) return null;
            return cb.like(cb.lower(root.get("address").get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> countryContains(String country) {
        return (root, query, cb) -> {
            if (country == null || country.isBlank()) return null;
            return cb.like(cb.lower(root.get("address").get("country")), "%" + country.toLowerCase() + "%");
        };
    }

    public static Specification<Hotel> hasAmenity(String amenity) {
        return (root, query, cb) -> {
            if (amenity == null || amenity.isBlank()) return null;
            Join<Object, Object> amenitiesJoin = root.join("amenities", JoinType.INNER);
            return cb.like(cb.lower(amenitiesJoin.get("name")), "%" + amenity.toLowerCase() + "%");
        };
    }









    private <T> Specification<T> safeAnd(Specification<T> spec1, Specification<T> spec2) {
        if (spec1 == null) return spec2;
        if (spec2 == null) return spec1;
        return spec1.and(spec2);
    }


}
