package Controller;

import Model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {

    private ReservationDAO reserveDAO = new ReservationDAO();
    private MovieDAOIplm movieDAOIplm = new MovieDAOIplm();
    private PlexDAO plexDAO = new PlexDAO();

    public Long save(ReservationVO reserve){
        reserveDAO.save(reserve);
        return reserve.getReserve_id();
    }

    public void remove(Long id){
        reserveDAO.remove(id);
    }

    public void deleteAll(){
        reserveDAO.deleteAll();
    }

    public ReservationVO findOne(Long id){
        ReservationVO one = null;
        try {
            one = reserveDAO.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }

    public List<ReservationVO> findAll(String userid){
        List<ReservationVO> all = null;
        try {
            all = reserveDAO.findAll(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    public int getSalesByDay(String date){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

        int total =0;
        try {
            total = reserveDAO.getSalesByDay(localDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public int getSalesByMonth(String month){
        String startDay = month + "-01";
        LocalDate localDate = LocalDate.parse(startDay, DateTimeFormatter.ISO_LOCAL_DATE);
        int lastDayOfMonth = localDate.lengthOfMonth();
        String lastDay = month + lastDayOfMonth;

        int total =0;
        try {
            total = reserveDAO.getSalesByMonth(startDay,lastDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public int getSalesByMovie(String movieName){
        int total = 0;

        try {
            total = reserveDAO.getSalesByMovie(movieName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public List<PlexVO> loadTheater(){
        return plexDAO.selectAll();
    }

    public List<String> loadTheaterString(){
        List<String> plexList = new ArrayList<>();
        List<PlexVO> plexVos = plexDAO.selectAll();

        for(int i = 0; i <plexVos.size(); i++){
            plexList.add(plexVos.get(i).getName());
        }

        return plexList;
    }


   public List<MovieVO> loadMovie() {
        return movieDAOIplm.listMovie();
    }

    public List<String> loadMovieString() {
        List<String> movieList = new ArrayList<>();
        List<MovieVO> movieVOS = movieDAOIplm.listMovie();

        for(int i =0; i<movieVOS.size(); i++)
            movieList.add(movieVOS.get(i).getTitle());

        return movieList;
    }



}
