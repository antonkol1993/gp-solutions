package com.hotel.service;

import com.hotel.dao.repository.AmenityRepository;
import com.hotel.dao.repository.HotelRepository;
import com.hotel.dao.specification.HotelSpecification;
import com.hotel.mapper.HotelMapper;
import com.hotel.model.dto.*;
import com.hotel.model.entity.Hotel;
import com.hotel.web.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class HotelService {

    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;
    private final HotelMapper hotelMapper;

    public List<HotelBriefDTO> getAllHotelsBrief() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getHotelDetail(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if (optionalHotel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.of("Hotel not found with id " + id, HttpStatus.NOT_FOUND.value()));
        }

        HotelDetailDTO dto = hotelMapper.toDetailDto(optionalHotel.get());
        return ResponseEntity.ok(dto);
    }

    public List<HotelBriefDTO> searchHotels(
            String name, String brand, String city,
            String country, String amenity) {

        List<Specification<Hotel>> specs = List.of(
                HotelSpecification.nameContains(name),
                HotelSpecification.brandContains(brand),
                HotelSpecification.cityContains(city),
                HotelSpecification.countryContains(country),
                HotelSpecification.hasAmenity(amenity)
        );

        Specification<Hotel> combinedSpec = combineSpecifications(specs);
        List<Hotel> hotels = (combinedSpec == null)
                ? hotelRepository.findAll()
                : hotelRepository.findAll(combinedSpec);

        return hotels.stream()
                .map(hotelMapper::toDto)
                .collect(Collectors.toList());
    }

    private <T> Specification<T> combineSpecifications(List<Specification<T>> specs) {
        Specification<T> result = null;
        for (Specification<T> spec : specs) {
            if (spec != null) {
                result = (result == null) ? spec : result.and(spec);
            }
        }
        return result;
    }
    public List<HotelBriefDTO> findByAmenity(String amenity) {
        Specification<Hotel> spec = HotelSpecification.hasAmenity(amenity);
        return hotelRepository.findAll(spec).stream()
                .map(hotelMapper::toDto)
                .collect(Collectors.toList());
    }
}
