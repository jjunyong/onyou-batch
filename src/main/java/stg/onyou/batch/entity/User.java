package stg.onyou.batch.entity;

import lombok.*;

import java.time.LocalDateTime;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString(of={"id","name","birthday", "thumbnail", "sex", "email", "role"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    private String birthday;
    private String thumbnail;
    private char sex;
    @Column(unique=true)
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String phoneNumber;
    private String password;
    private String targetToken;
    private char userPushAlarm;
    private char clubPushAlarm;
    private LocalDateTime lastLoginDate;

}
