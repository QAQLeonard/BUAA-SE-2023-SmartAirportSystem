package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(schema = "se_db",name = "flights")
@TableName("flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight
{
    @Id
    private String id;
    //航班号
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
    private int status;
    // Total Seats
    @Column(name = "total_seats")
    private int totalSeats;
    // Remaining Seats
    @Column(name = "remaining_seats")
    private int remainingSeats;
}
