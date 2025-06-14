package com.hotel.controller;

import com.hotel.model.dto.HotelBriefDTO;
import com.hotel.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
