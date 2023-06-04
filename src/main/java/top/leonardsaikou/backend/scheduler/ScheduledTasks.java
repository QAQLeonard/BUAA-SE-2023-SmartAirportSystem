package top.leonardsaikou.backend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.leonardsaikou.backend.entity.Flight;
import top.leonardsaikou.backend.mapper.FlightMapper;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    private final FlightMapper flightMapper;

    public ScheduledTasks(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }

    @Scheduled(fixedRate = 60000)  // 每60秒执行一次
    public void updateFlightStatus() {
        // 获取所有飞行
        List<Flight> flights = flightMapper.selectList(null);

        for (Flight flight : flights) {
            LocalDateTime now = LocalDateTime.now();

            if (now.isAfter(flight.getArrivalDateTime())) {
                // 已到达目的地
                flight.setStatus(4);
            } else if (now.isAfter(flight.getDepartureDateTime())) {
                // 飞行中
                flight.setStatus(3);
            } else if (now.isAfter(flight.getDepartureDateTime().minusMinutes(90))) {
                // 检票中
                flight.setStatus(2);
            } else if (now.isBefore(flight.getDepartureDateTime().minusMinutes(90))) {
                // 未开始检票
                flight.setStatus(1);
            }
            flightMapper.updateById(flight);
        }
    }
}
