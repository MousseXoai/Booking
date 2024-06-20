package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long>{
    @Query("SELECT bill.billId FROM Bill bill JOIN bill.bookedId b JOIN b.user u JOIN b.rooms r WHERE u.userId = :userId AND r.roomId = :roomId")
    List<Long> getBillIdByUserId(@Param("userId") Long userId, @Param("roomId") Long roomId);
}
