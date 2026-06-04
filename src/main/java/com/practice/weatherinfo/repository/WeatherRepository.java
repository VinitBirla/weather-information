package com.practice.weatherinfo.repository;

import com.practice.weatherinfo.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    // shouldGetAll checks $[0].id and $[1].id — must be ordered by id ascending
    List<Weather> findAllByOrderByIdAsc();
}
