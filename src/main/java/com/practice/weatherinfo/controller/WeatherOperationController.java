package com.practice.weatherinfo.controller;

import com.practice.weatherinfo.model.Weather;
import com.practice.weatherinfo.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherOperationController {

    @Autowired
    private WeatherRepository weatherRepository;

    // POST /weather — 201 + saved record with generated id
    @PostMapping
    public ResponseEntity<Weather> create(@RequestBody Weather weather) {
        return ResponseEntity.status(HttpStatus.CREATED).body(weatherRepository.save(weather));
    }

    // GET /weather — 200 + all records ordered by id asc
    @GetMapping
    public ResponseEntity<List<Weather>> getAll() {
        return ResponseEntity.ok(weatherRepository.findAllByOrderByIdAsc());
    }

    // GET /weather/{id} — 200 + record, or 404
    @GetMapping("/{id}")
    public ResponseEntity<Weather> getById(@PathVariable Integer id) {
        Optional<Weather> weather = weatherRepository.findById(id);
        return weather.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /weather/{id} — 204 if deleted, 404 if not found
    // "/weather" + MAX_VALUE (no slash) = "/weather2147483647" → no mapping → Spring 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (!weatherRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        weatherRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
