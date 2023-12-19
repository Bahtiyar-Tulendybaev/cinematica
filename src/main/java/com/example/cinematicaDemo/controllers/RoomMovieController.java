package com.example.cinematicaDemo.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.example.cinematicaDemo.models.dto.RoomMovieDto;
import com.example.cinematicaDemo.models.request.SaveRoomMovieRequest;
import com.example.cinematicaDemo.service.RoomMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Сеанс")
@RestController
@RequestMapping("/api/v1/roomMovie")
public class RoomMovieController {

    @Autowired
    private RoomMovieService service;


    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody RoomMovieDto roomMovieDto) {
            return new ResponseEntity<>(service.save(roomMovieDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@ModelAttribute SaveRoomMovieRequest roomMovie) {
        return new ResponseEntity<>(service.create(roomMovie), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех сеансов")
    ResponseEntity<List<RoomMovieDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        return ResponseEntity.ok(service.delete(id));
    }




}
