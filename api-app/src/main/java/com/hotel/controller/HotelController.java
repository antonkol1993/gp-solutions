package com.hotel.controller;

import com.hotel.dao.specification.HotelSpecification;
import com.hotel.model.dto.HotelBriefDTO;
import com.hotel.model.entity.Hotel;
import com.hotel.service.HotelService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/property-view")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public List<HotelBriefDTO> getAllHotels() {
        return hotelService.getAllHotelsBrief();
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable("id") Long id) {
        return hotelService.getHotelDetail(id);
    }
    @GetMapping("/search")
    public List<HotelBriefDTO> searchHotels(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "amenities", required = false) String amenities
    ) {
        return hotelService.searchHotels(name, brand, city, country, amenities);
    }



}
