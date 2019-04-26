package com.decathlon.Batch;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long> {
// le Long en 2 eme parametre correspond au type de l'ID dans BookingModel
	
    @Transactional
    @Modifying
	@Query("delete from BookingModel b where b.endDate < ?1 ")
    void deleteOldBookings(LocalDateTime endDate);
	
} 