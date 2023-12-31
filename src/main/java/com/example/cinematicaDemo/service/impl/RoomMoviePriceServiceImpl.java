package com.example.cinematicaDemo.service.impl;

import com.example.cinematicaDemo.exceptions.RoomMoviePriceNotFoundException;
import com.example.cinematicaDemo.models.responses.*;
import com.example.cinematicaDemo.service.PriceService;
import com.example.cinematicaDemo.service.RoomMoviePriceService;
import com.example.cinematicaDemo.service.RoomMovieService;
import com.example.cinematicaDemo.dao.RoomMoviePriceRep;
import com.example.cinematicaDemo.enums.PriceType;
import com.example.cinematicaDemo.mappers.RoomMoviePriceMapper;
import com.example.cinematicaDemo.models.dto.PriceDto;
import com.example.cinematicaDemo.models.dto.RoomMovieDto;
import com.example.cinematicaDemo.models.dto.RoomMoviePriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RoomMoviePriceServiceImpl implements RoomMoviePriceService {

    RoomMoviePriceMapper mapper = RoomMoviePriceMapper.INSTANCE;

    private final RoomMoviePriceRep rep;
    private final RoomMovieService roomMovieService;
    private final PriceService priceService;
    private PriceType priceType;


    @Autowired
    public RoomMoviePriceServiceImpl(RoomMoviePriceRep rep, RoomMovieService roomMovieService,
                                     PriceService priceService) {
        this.rep = rep;
        this.roomMovieService = roomMovieService;
        this.priceService = priceService;
    }

    @Override
    public RoomMoviePriceDto save(RoomMoviePriceDto roomMoviePriceDto) {
        return mapper.toDto(rep.save(mapper.toEntity(roomMoviePriceDto)));
    }


    @Override
    public Response create(Long roomMovieId, List<Long> priceList) {
        RoomMovieDto roomMovieDto = roomMovieService.findById(roomMovieId);

        for (Long id : priceList) {
            PriceDto priceDto = priceService.findById(id);

            RoomMoviePriceDto roomMoviePriceDto = new RoomMoviePriceDto();
            roomMoviePriceDto.setRoomMovie(roomMovieDto);
            roomMoviePriceDto.setPrice(priceDto);
            save(roomMoviePriceDto);
        }
        return new Response("Saved successfully!");
    }

    @Override
    public RoomMoviePriceDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RoomMoviePriceNotFoundException("RoomMoviePrice not found!")));
    }

    @Override
    public RoomMoviePriceDto delete(Long id) {
        RoomMoviePriceDto roomMoviePriceDto = findById(id);
        roomMoviePriceDto.setActive(false);
        return save(roomMoviePriceDto);
    }

    @Override
    public List<RoomMoviePriceDto> findAll() {
        return mapper.toDtos(rep.findAll());
    }

    @Override
    public List<RoomMoviePriceDto> findPriceByMovieId(Long movieId, LocalDate startDate) {
        return mapper.toDtos(rep.findPriceByMovieId(movieId, startDate));
    }

    @Override
    public List<RoomMoviePriceDto> findPriceByCinemaId(Long cinemaId, LocalDate startDate) {
        return mapper.toDtos(rep.findPriceByCinemaId(cinemaId, startDate));
    }


    @Override
    public GetRoomMovieResponse getRoomMovieByMovieId(Long movieId, LocalDate startDate) {

        List<RoomMoviePriceDto> roomMoviePriceList = findPriceByMovieId(movieId, startDate);
        // Collections.sort(roomMoviePriceList); // данные об одном сеансе идут последовательно
        List<RoomMovieDto> roomMovieDtos = roomMovieService.findRoomMovieByMovieId(movieId, startDate);
        List<RoomResponse> roomResponses = new ArrayList<>();

        for (RoomMovieDto roomMovieItem : roomMovieDtos) {

            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setRoomId(roomMovieItem.getRoom().getId());
            roomResponse.setName(roomMovieItem.getRoom().getName());

            List<RoomMovieResponse> roomMovieResponseList = new ArrayList<>();

            RoomMovieResponse roomMovieResp = new RoomMovieResponse();

            for (RoomMoviePriceDto item : roomMoviePriceList) {

                if (roomMovieItem.getSchedule().getId().equals(item.getRoomMovie().getSchedule().getId())) {
                    if (item.getRoomMovie().getId().equals(roomMovieItem.getId()) && item.getRoomMovie().isActive()) {
                        //проверка на время сеанса
                        if (!roomMovieResponseList.isEmpty() &&
                                roomMovieResponseList.get((int) roomMovieResponseList.stream().count() - 1).getRoomMovieId() == item.getRoomMovie().getId()) {
                            roomMovieResp = roomMovieResponseList.get((int) roomMovieResponseList.stream().count() - 1);
                            //проверка есть ли в списке уже этот зал с ценой
                            //если да, то обновляем его прайс, если нет то создаем новый

                            roomMovieResponseList.remove((int) roomMovieResponseList.stream().count() - 1);//удаляем чтобы не было дубликатов
                        } else {
                            roomMovieResp.setRoomMovieId(item.getRoomMovie().getId());
                        }
                        priceType = item.getPrice().getPriceType();

                        switch (priceType) {
                            case CHILD:
                                roomMovieResp.setChildPrice(item.getPrice().getPrice());
                                roomMovieResp.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                                break;
                            case STANDARD:
                                roomMovieResp.setStandardPrice(item.getPrice().getPrice());
                                roomMovieResp.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                                break;
                        }
                    }
                }
            }
            roomMovieResponseList.add(roomMovieResp);
//проверка на дубли сенансов
            for (RoomResponse rp : roomResponses) {
                if (rp.getRoomId().equals(roomResponse.getRoomId())) {
                    roomMovieResponseList.addAll(rp.getRoomMovie());
                    roomResponses.remove(rp);
                    break;
                }
            }
            roomResponse.setRoomMovie(roomMovieResponseList);
            roomResponses.add(roomResponse);
        }
        List<CinemaResponse> cinemaResponses = new ArrayList<>();
        boolean inList = false;
        for (RoomMovieDto item : roomMovieDtos) {

            CinemaResponse cinemaResponse = new CinemaResponse();
            cinemaResponse.setName(item.getRoom().getCinema().getName());
            List<RoomResponse> newRoomResp = new ArrayList();

            for (RoomResponse roomResponseItem : roomResponses) {
                if (item.getRoom().getId().equals(roomResponseItem.getRoomId()) && item.getRoom().isActive()) {
                    for (RoomResponse rp : newRoomResp) {
                        if (rp.getRoomId().equals(item.getRoom().getId())) {
                            List<RoomMovieResponse> list = rp.getRoomMovie();
                            list.addAll(roomResponseItem.getRoomMovie());
                            newRoomResp.remove(rp);

                            roomResponseItem.setRoomMovie(list);
                            break;
                        }
                    }
                    newRoomResp.add(roomResponseItem);
                }
            }
//проверка на дубли кинотеатров
            for (CinemaResponse rp : cinemaResponses) {
                if (rp.getName().equals(cinemaResponse.getName())) {
                    inList = true;
                    newRoomResp.addAll(rp.getRooms());
                    cinemaResponses.remove(rp);
                    break;
                }
            }
            Set<RoomResponse> hashSet = new HashSet<RoomResponse>(newRoomResp);
            newRoomResp.clear();
            newRoomResp.addAll(hashSet);

            cinemaResponse.setRooms(newRoomResp);
            cinemaResponses.add(cinemaResponse);
        }
        GetRoomMovieResponse getRoomMovieResponse = new GetRoomMovieResponse();
        for (RoomMoviePriceDto item : roomMoviePriceList) {
            getRoomMovieResponse.setCinema(cinemaResponses);
            getRoomMovieResponse.setMovieName(item.getRoomMovie().getMovie().getName());
            getRoomMovieResponse.setMoviePg(item.getRoomMovie().getMovie().getPg());
            getRoomMovieResponse.setMovieImage(item.getRoomMovie().getMovie().getImage());
            getRoomMovieResponse.setDefinition(item.getRoomMovie().getMovie().getDefinition());
            getRoomMovieResponse.setMovieRating(item.getRoomMovie().getMovie().getRating());
        }
        return getRoomMovieResponse;
    }


    @Override
    public GetRoomMovieByCinemaResponse getRoomMovieByCinemaId(Long cinemaId, LocalDate startDate) {

        List<RoomMovieDto> roomMovieList = roomMovieService.findRoomMovieByCinemaId(cinemaId, startDate);
        List<RoomResp> roomResponseList = getRoomResp(cinemaId, startDate);
        if (roomResponseList.isEmpty()) {
            System.out.println("Лист пустой");
        }
        GetRoomMovieByCinemaResponse getRoomMovieResponse = new GetRoomMovieByCinemaResponse();

        for (RoomMovieDto item : roomMovieList) {
            getRoomMovieResponse.setRooms(roomResponseList);
            getRoomMovieResponse.setLogo(item.getRoom().getCinema().getLogo());
            getRoomMovieResponse.setAddress(item.getRoom().getCinema().getAddress());
            getRoomMovieResponse.setName(item.getRoom().getCinema().getName());
            getRoomMovieResponse.setId(item.getRoom().getCinema().getId());

        }
        return getRoomMovieResponse;
    }

    @Override
    public List<RoomResp> getRoomResp(Long cinemaId, LocalDate startDate) {
        List<RoomMovieDto> roomMovieDtos = roomMovieService.findRoomMovieByCinemaId(cinemaId, startDate);
        List<RoomResp> roomResponseList = new ArrayList<>();
        List<MovieResponse> movieResponseList = getMovieResponse(cinemaId, startDate);

        for (RoomMovieDto roomMovieItem : roomMovieDtos) {

            RoomResp roomResp = new RoomResp();
            roomResp.setRoomId(roomMovieItem.getRoom().getId());
            roomResp.setName(roomMovieItem.getRoom().getName());
            for (MovieResponse rmResponse : movieResponseList) {
//                if (roomMovieItem.getId().equals(rmResponse.getRoomMovieId())) {
//
//                    roomResp.setMovieResponse(movieResponseList);
//                    roomResponseList.add(roomResp);
//                }
            }
        }
        return roomResponseList;
    }

    @Override
    public List<MovieResponse> getMovieResponse(Long cinemaId, LocalDate startDate) {
        List<RoomMovieDto> roomMovieDtos = roomMovieService.findRoomMovieByCinemaId(cinemaId, startDate);
        List<RoomMovieResponse> roomMovieResponseList = getRoomMovieResponse(cinemaId, startDate);

        List<MovieResponse> movieResponseList = new ArrayList<>();

        for (RoomMovieDto itemDto : roomMovieDtos) {

                    MovieResponse movieResponse = new MovieResponse();
                    movieResponse.setMovieId(itemDto.getMovie().getId());
                    movieResponse.setName(itemDto.getMovie().getName());

                    for (RoomMovieResponse rmResponse : roomMovieResponseList) {
                        if (itemDto.getId().equals(rmResponse.getRoomMovieId())) {
                            List<RoomMovieResponse> newRmResp = new ArrayList<>();
                            newRmResp.add(rmResponse);
                            movieResponse.setRoomMovie(newRmResp);
                            break;
                        }
                    }
                    movieResponseList.add(movieResponse);
                }



//      List<MovieResponse> newMovieResponseList = movieResponseList
//              .stream()
//              .collect(Collectors.toSet())
//              .stream()
//              .collect(Collectors.toList());
        
        return movieResponseList;
    }


    @Override
    public List<RoomMovieResponse> getRoomMovieResponse(Long cinemaId, LocalDate startDate) {

        List<RoomMoviePriceDto> roomMoviePriceList = findPriceByCinemaId(cinemaId, startDate);
        List<RoomMovieDto> roomMovieDtos = roomMovieService.findRoomMovieByCinemaId(cinemaId, startDate);
        List<RoomMovieResponse> roomMovieResponseList = new ArrayList<>();

        for (RoomMovieDto roomMovieItem : roomMovieDtos) {

            RoomMovieResponse roomMovieResp = new RoomMovieResponse();

            for (RoomMoviePriceDto item : roomMoviePriceList) {

                if (roomMovieItem.getSchedule().getId().equals(item.getRoomMovie().getSchedule().getId())) {
                    if (item.getRoomMovie().getId().equals(roomMovieItem.getId()) && item.getRoomMovie().isActive()) {
                        //проверка на время сеанса
                        if (!roomMovieResponseList.isEmpty() &&
                                roomMovieResponseList.get((int) roomMovieResponseList.stream().count() - 1).getRoomMovieId() == item.getRoomMovie().getId()) {
                            roomMovieResp = roomMovieResponseList.get((int) roomMovieResponseList.stream().count() - 1);
                            //проверка есть ли в списке уже этот зал с ценой
                            //если да, то обновляем его прайс, если нет то создаем новый

                            roomMovieResponseList.remove((int) roomMovieResponseList.stream().count() - 1);//удаляем чтобы не было дубликатов
                        } else {
                            roomMovieResp.setRoomMovieId(item.getRoomMovie().getId());
                        }
                        priceType = item.getPrice().getPriceType();

                        switch (priceType) {
                            case CHILD:
                                roomMovieResp.setChildPrice(item.getPrice().getPrice());
                                roomMovieResp.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                                break;
                            case STANDARD:
                                roomMovieResp.setStandardPrice(item.getPrice().getPrice());
                                roomMovieResp.setStartTime(item.getRoomMovie().getSchedule().getStartTime());
                                break;
                        }
                    }
                }
            }
            roomMovieResponseList.add(roomMovieResp);
        }
        return roomMovieResponseList;
    }
}