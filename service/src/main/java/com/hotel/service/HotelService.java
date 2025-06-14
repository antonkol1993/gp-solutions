package com.hotel.service;

import com.hotel.dao.repository.AmenityRepository;
import com.hotel.dao.repository.HotelRepository;
import com.hotel.dao.specification.HotelSpecification;
import com.hotel.model.dto.*;
import com.hotel.model.entity.Address;
import com.hotel.model.entity.Amenity;
import com.hotel.model.entity.ArrivalTime;
import com.hotel.model.entity.Contacts;
import com.hotel.model.entity.Hotel;
import com.hotel.web.ErrorResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HotelService {
    private final HotelRepository hotelRepository;
    private final AmenityRepository amenityRepository;

    public HotelService(HotelRepository hotelRepository, AmenityRepository amenityRepository) {
        this.hotelRepository = hotelRepository;
        this.amenityRepository = amenityRepository;
    }

    public List<HotelBriefDTO> getAllHotelsBrief() {
        return hotelRepository.findAll().stream()
                .map(this::mapToHotelBriefDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getHotelDetail(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if (optionalHotel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.of("Hotel not found with id " + id, HttpStatus.NOT_FOUND.value()));
        }

        HotelDetailDTO dto = mapToHotelDetailDTO(optionalHotel.get());
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
                .map(this::mapToHotelBriefDTO)
                .collect(Collectors.toList());
    }


    private HotelBriefDTO mapToHotelBriefDTO(Hotel hotel) {
        return new HotelBriefDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                formatAddress(hotel.getAddress()),
                hotel.getContacts() != null ? hotel.getContacts().getPhone() : null
        );
    }

    private HotelDetailDTO mapToHotelDetailDTO(Hotel hotel) {
        return new HotelDetailDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                hotel.getBrand(),
                toAddressDTO(hotel.getAddress()),
                toContactsDTO(hotel.getContacts()),
                toArrivalTimeDTO(hotel.getArrivalTime()),
                hotel.getAmenities() == null
                        ? List.of()
                        : hotel.getAmenities().stream().map(Amenity::getName).collect(Collectors.toList())
        );
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

    private String formatAddress(Address address) {
        if (address == null) return null;
        return String.format("%d %s, %s, %s, %s",
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getPostCode(),
                address.getCountry());
    }

    private AddressDTO toAddressDTO(Address a) {
        if (a == null) return null;
        return new AddressDTO(a.getHouseNumber(), a.getStreet(), a.getCity(), a.getCountry(), a.getPostCode());
    }

    private ContactsDTO toContactsDTO(Contacts c) {
        if (c == null) return null;
        return new ContactsDTO(c.getPhone(), c.getEmail());
    }

    private ArrivalTimeDTO toArrivalTimeDTO(ArrivalTime a) {
        if (a == null) return null;
        return new ArrivalTimeDTO(a.getCheckIn(), a.getCheckOut());
    }
}
