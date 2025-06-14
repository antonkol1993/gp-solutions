package com.hotel.mapper;

import com.hotel.model.dto.*;
import com.hotel.model.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper implements Mapper<Hotel, HotelBriefDTO> {

    @Override
    public HotelBriefDTO toDto(Hotel hotel) {
        if (hotel == null) return null;
        return new HotelBriefDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                formatAddress(hotel.getAddress()),
                hotel.getContacts() != null ? hotel.getContacts().getPhone() : null
        );
    }

    @Override
    public Hotel toEntity(HotelBriefDTO dto) {
        throw new UnsupportedOperationException("toEntity not implemented yet");
    }

    public HotelDetailDTO toDetailDto(Hotel hotel) {
        if (hotel == null) return null;
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
