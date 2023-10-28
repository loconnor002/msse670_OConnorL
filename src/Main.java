import com.lodgereservation.model.domain.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Lodge lodge = new Lodge("Alyeska");
        lodge.addGuest("Arthur", "Dent");

        Reservation res = new Reservation();
        System.out.println(res);
        Reservation res2 = new Reservation();
        System.out.println(res2);

        Composite composite = new Composite();
        composite.addUpdate(LocalDateTime.now());
        composite.addUpdate(LocalDateTime.now());

        System.out.println("updates: " + composite);
        System.out.println(lodge);
    }
}