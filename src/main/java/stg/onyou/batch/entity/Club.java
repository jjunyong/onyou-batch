package stg.onyou.batch.entity;

import lombok.*;
import stg.onyou.batch.entity.enums.RecruitStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString(of={"id", "name", "shortDesc", "longDesc", "delYn", "thumbnail", "announcement", "recruitStatus", "maxNumber", "isApproveRequired"})
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDesc;
    private String longDesc;
    private char delYn;
    private String thumbnail;
    @Enumerated(EnumType.STRING)
    private RecruitStatus recruitStatus;
    private int maxNumber;
    private int recruitNumber;
    private int clubLikesNumber;
    private int feedNumber;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String isApproveRequired;
    private String contactPhone;

}
