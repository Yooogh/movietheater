package VO;

import java.time.LocalDate;

public class ReservationVO {

    private Long reserve_id;

    private String theaterName;
    private String movieName;
    private String seat; //좌석위치 수정필요

    private LocalDate reserveDay;
    private String reserveTime;

    private int people;

    private int totalPrice;

    public Long getReserve_id() {
		return reserve_id;
	}

	public void setReserve_id(Long reserve_id) {
		this.reserve_id = reserve_id;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public LocalDate getReserveDay() {
		return reserveDay;
	}

	public void setReserveDay(LocalDate reserveDay) {
		this.reserveDay = reserveDay;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String userId;

}
