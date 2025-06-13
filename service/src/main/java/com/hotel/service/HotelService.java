package com.hotel.service;

import com.hotel.dao.repository.AmenityRepository;
import com.hotel.dao.repository.HotelRepository;
import com.hotel.model.dto.*;
import com.hotel.model.entity.Address;
import com.hotel.model.entity.Amenity;
import com.hotel.model.entity.ArrivalTime;
import com.hotel.model.entity.Contacts;
import com.hotel.model.entity.Hotel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public HotelDetailDTO getHotelDetail(Long id) {
        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hotel not found with id " + id));
        return mapToHotelDetailDTO(hotel);
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
