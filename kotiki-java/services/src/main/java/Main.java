
import entities.Color;
import entities.Friend;
import entities.Kotik;
import entities.Owner;
import services.MainService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        MainService mainService = new MainService();
        Owner owner = new Owner("Masha", LocalDate.now());
        mainService.saveOwner(owner);
        Kotik kotik1 = new Kotik("Vudi1", LocalDate.of(2004, 05, 29), "No name", Color.GREY);
        kotik1.setOwnerId(owner);
        owner.addKotik(kotik1);
        Kotik kotik2 = new Kotik("Vudi2", LocalDate.of(2004, 05, 29), "No name", Color.YELLOW);
        kotik2.setOwnerId(owner);
        owner.addKotik(kotik2);
        mainService.updateOwner(owner);

        Owner owner2 = new Owner("Dasha", LocalDate.now());
        mainService.saveOwner(owner2);
        Kotik kotikFriend = new Kotik("Kuki", LocalDate.of(2004, 05, 29), "No name", Color.GREY);
        kotikFriend.setOwnerId(owner2);
        owner2.addKotik(kotikFriend);
        mainService.updateOwner(owner2);

        owner.setName("Sasha");
        mainService.updateOwner(owner);
        kotik1.setName("Rubi");
        mainService.updateKotik(kotik1);
        Friend friend = kotik1.addFriend(kotik2);
        mainService.saveFriend(friend);
        Friend friend2 = kotik1.addFriend(kotikFriend);
        mainService.saveFriend(friend2);
        Owner findOwner = mainService.findOwnerById(kotik1.getOwnerId().getId());
        System.out.println(findOwner.getName());


        /*mainService.deleteOwner(owner);
        mainService.deleteOwner(owner2);*/
    }
}