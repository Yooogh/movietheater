import Model.ReservationDAO;
import Service.ReservationService;
import View.LoginView;
import View.ReservationView;

public class Main {
	public static void main(String[] args) {
		ReservationDAO d = new ReservationDAO();
		ReservationService s = new ReservationService(d);
		ReservationView view = new ReservationView(s);
		view.mainMenu();
	}
}
