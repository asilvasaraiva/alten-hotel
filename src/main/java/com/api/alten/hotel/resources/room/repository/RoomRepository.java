package com.api.alten.hotel.resources.room.repository;

import com.api.alten.hotel.resources.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface class that manage the Room structure using JPA (Java Persistence API) to perform general queries such
 * save, delete,findById and many others.
 * @author Alexsandro Saraiva
 */
@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
