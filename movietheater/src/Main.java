import Model.ReservationDAO;
import Service.ReservationService;
import View.LoginView;
import View.ReservationView;

public class Main {
	public static void main(String[] args) {
		ReservationDAO dao = new ReservationDAO();
		ReservationService service = new ReservationService(dao);
		ReservationView view = new ReservationView(service);
		view.mainMenu();
	}
}
