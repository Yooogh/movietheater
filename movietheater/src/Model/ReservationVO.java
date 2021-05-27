package Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationVO {

    private Long reserve_id;

    private String theaterName;
    private String movieName;
    private String seat; //좌석위치 수정필요

    private LocalDate reserveDay;
    private String reserveTime;

    private int people;

    private int totalPrice;

    private String userId;

}
