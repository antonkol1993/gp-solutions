package com.hotel.dao.specification;

import com.hotel.model.entity.Hotel;
import jakarta.persistence.criteria.Join;
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

        Specification<Hotel> nameSpec = nameContains(name);
        Specification<Hotel> brandSpec = brandContains(brand);
        Specification<Hotel> citySpec = cityContains(city);
        Specification<Hotel> countrySpec = countryContains(country);
        Specification<Hotel> amenitySpec = hasAmenity(amenity);

        spec = nameSpec;
        spec = spec.and(brandSpec);
        spec = spec.and(citySpec);
        spec = spec.and(countrySpec);
        spec = spec.and(amenitySpec);

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

    public static Specification<Hotel> hasAmenity(String amenityName) {
        return (root, query, cb) -> {
            if (amenityName == null || amenityName.isBlank()) return null;
            if (query != null) {
                query.distinct(true);
            }
            Join<Object, Object> amenities = root.join("amenities");
            return cb.like(cb.lower(amenities.get("name")), "%" + amenityName.toLowerCase() + "%");
        };
    }
}
