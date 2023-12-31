package com.example.cinematicaDemo.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.example.cinematicaDemo.models.dto.PriceDto;
import com.example.cinematicaDemo.models.request.SavePriceRequest;
import com.example.cinematicaDemo.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Цена")
@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    @Autowired
    private PriceService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody PriceDto priceDto) {

            return new ResponseEntity<>(service.save(priceDto), HttpStatus.CREATED);
    }

    @PostMapping("/create")
    @ApiOperation("Создание")
    ResponseEntity<?> create(@ModelAttribute SavePriceRequest price) {
            return new ResponseEntity<>(service.create(price), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    @ApiOperation("Поиск цены по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех цен")
    ResponseEntity<List<PriceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
            return ResponseEntity.ok(service.delete(id));
    }
}
