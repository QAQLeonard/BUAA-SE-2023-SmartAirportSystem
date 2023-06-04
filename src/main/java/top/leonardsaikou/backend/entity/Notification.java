package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(schema = "se_db",name = "notifications")
@TableName("notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @Column(length = 55)
    private String id;

    @Column(name = "publisher_id", nullable = false)
    private String publisherId;

    @Column(name = "notification_file_path", nullable = false)
    private String notificationFilePath;

    @Column(name = "published_time", nullable = false)
    private LocalDateTime publishedTime;

}
