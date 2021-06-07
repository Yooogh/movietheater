package Model;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
public class SeatVO{
	long reserve_id;
	String seat;
	LocalDate reserveDay;
	String reserveTime;
	boolean reserved;
}
