package com.api.alten.hotel.resources.room.service;


import com.api.alten.hotel.resources.room.entity.Room;
import com.api.alten.hotel.resources.room.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RoomServiceImpl {
    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public void save(Room room){
        try {
            log.info("Saving new room in database");
            roomRepository.save(room);
            log.info("New room saved successfully with id {}", room.getId());
        }catch (Exception e){
            log.info("Error to save reservation to client {} in database, error {}", room.getId(), e.getMessage());
        }
    }
}
