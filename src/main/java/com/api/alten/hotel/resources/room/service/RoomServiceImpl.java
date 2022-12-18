package com.api.alten.hotel.resources.room.service;


import com.api.alten.hotel.exceptions.UnavailableDateException;
import com.api.alten.hotel.resources.room.entity.Room;
import com.api.alten.hotel.resources.room.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public Room create(){
        try {
            log.info("Creating new room in database");
            var room = Room.builder().name("Room One").build();
            return roomRepository.save(room);
        }catch (Exception e){
            throw new UnavailableDateException("Error to save new room in database");
        }
    }

    @Override
    public Room getRoom() {
        Optional<Room> room = roomRepository.findById(1L);
        if(room.isPresent()){
            return room.get();
        }else{
            return create();

        }
    }
}
