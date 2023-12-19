package com.example.cinematicaDemo.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.example.cinematicaDemo.models.dto.CinemaDto;
import com.example.cinematicaDemo.models.request.SaveCinemaRequest;
import com.example.cinematicaDemo.models.responses.GetAllCinemasResponse;
import com.example.cinematicaDemo.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Кинотеатр")
@RestController
@RequestMapping("/api/v1/cinema")
public class CinemaController {

    @Autowired
    private CinemaService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody CinemaDto cinemaDto) {
            return new ResponseEntity<>(service.save(cinemaDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@ModelAttribute SaveCinemaRequest cinema) {
            return new ResponseEntity<>(service.create(cinema), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation(value = "Поиск кинотеатра по id",notes = "Возвращает кинотеатр по его Id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех кинотеатров")
    ResponseEntity<List<CinemaDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
            return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/getAllCinemas")
    @ApiOperation("Вывод всех кинотеатров (limit/offset)")
    ResponseEntity<List<GetAllCinemasResponse>> getAllCinemas(@RequestParam int limit, @RequestParam int offset) {
        return ResponseEntity.ok(service.getAllCinemas(limit, offset));
    }


}
