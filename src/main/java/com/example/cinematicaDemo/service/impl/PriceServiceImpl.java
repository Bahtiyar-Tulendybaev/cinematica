package com.example.cinematicaDemo.service.impl;

import com.example.cinematicaDemo.exceptions.PriceNotFoundException;
import com.example.cinematicaDemo.service.PriceService;
import com.example.cinematicaDemo.dao.PriceRep;
import com.example.cinematicaDemo.enums.PriceType;
import com.example.cinematicaDemo.mappers.PriceMapper;
import com.example.cinematicaDemo.models.dto.PriceDto;
import com.example.cinematicaDemo.models.request.SavePriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceServiceImpl implements PriceService {

    PriceMapper mapper = PriceMapper.INSTANCE;

    private final PriceRep rep;
    @Autowired
    public PriceServiceImpl(PriceRep rep) {
        this.rep = rep;
    }

    @Override
    public PriceDto save(PriceDto priceDto) {
        return mapper.toDto(rep.save(mapper.toEntity(priceDto)));
    }

    @Override
    public PriceDto create(SavePriceRequest price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(price.getPrice());
        priceDto.setPriceType(price.getPriceType());

        return save(priceDto);
    }

    @Override
    public PriceDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(()->new PriceNotFoundException("Price not found!")));
    }

    @Override
    public PriceDto delete(Long id) {
        PriceDto priceDto = findById(id);
        priceDto.setActive(false);
        return save(priceDto);
    }

    @Override
    public List<PriceDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<PriceDto> findPrice(PriceType priceType) {
        List<PriceDto> list =mapper.toDtos(rep.findPrice(priceType));
        return list;
    }

    @Override
    public Double getPrice(PriceType priceType) {
        List<PriceDto> price = findPrice(priceType);
        if (price==null)
            return 0.0;
        return price.get(0).getPrice();
    }


}
