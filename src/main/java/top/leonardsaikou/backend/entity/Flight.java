package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;
@Entity
<<<<<<< HEAD
@Table(schema = "se_db", name = "flights")
=======
@Table(schema = "se_db",name = "flights")
@TableName("flights")
>>>>>>> 549d4caebafe3d50ba62c5a041dd796254c61175
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight
{
    @Id
    private String id;
    @Column(name = "flight_id")
    private String FlightId;
    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;
    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;
    @Column(name = "departure_city")
    private String departureCity;
    @Column(name = "arrival_city")
    private String arrivalCity;
    private float fare;
    /**
     * status:
     * -1: Deleted
     * 0: Unpublished
     * 1: Published but not yet checked-in
     * 2: Check-in in progress
     * 3: In-flight, yet to arrive
     * 4: Arrived at destination
     * 5: Delayed
     */
    private String status;
    // Total Seats
    @Column(name = "available_seats")
    private int availableSeats;
    // Remaining Seats
    @Column(name = "remaining_seats")
    private int remainingSeats;
}
