import entities.Color;
import entities.Friend;
import entities.Owner;
import entities.Kotik;
import services.MainService;

import java.security.Timestamp;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {

        MainService mainService = new MainService();
        Owner owner = new Owner("Misha", LocalDate.now());
        mainService.saveOwner(owner);
        Kotik kotik1 = new Kotik("Vudi1", LocalDate.of(2004, 05, 29), "No name", Color.RED);
        kotik1.setOwnerId(owner);
        owner.addKotik(kotik1);
        Kotik kotik2 = new Kotik("Vudi2", LocalDate.of(2004, 05, 29), "No name", Color.YELLOW);
        kotik2.setOwnerId(owner);
        owner.addKotik(kotik2);
        mainService.updateOwner(owner);
        owner.setName("Sasha");
        mainService.updateOwner(owner);
        kotik1.setName("Rubi");
        mainService.updateKotik(kotik1);
        Friend friend = new Friend(kotik1.getId(), kotik2.getId());
        mainService.saveFriend(friend);
    }
}