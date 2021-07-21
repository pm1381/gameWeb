package ir.ac.kntu;

import ir.ac.kntu.datas.GameData;
import ir.ac.kntu.db.GamesDataBase;
import ir.ac.kntu.menu.View;
import ir.ac.kntu.models.User;

public class Main {

    public static void main(String[] args) {
        User user = new User();
        GamesDataBase gamesDataBase = new GamesDataBase();
        View view = new View(user,gamesDataBase);
        view.drawMenu();
    }
}
