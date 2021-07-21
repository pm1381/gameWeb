package ir.ac.kntu.db;

import ir.ac.kntu.datas.GameData;
import ir.ac.kntu.models.Game;
import ir.ac.kntu.utils.ScannerWrapper;

import java.util.ArrayList;

public class GamesDataBase {
    private ArrayList<Game> games;

    public GamesDataBase() {
        games = new ArrayList<>();
    }

    public void allGames(GameData gameData) {
        gameData.saveAll(games);
    }

    public void addToGames(Game newGame){
        games.add(newGame);
    }

    public void makeGame(GameData gameData){
        if(gameData.loadInfo().size() != 0){
            games.clear();
            for(Game game : gameData.loadInfo()){
                addToGames(game);
            }
        }
        System.out.println("Enter game id :");
        String idStr = ScannerWrapper.getInstance().nextLine();
        int id = Integer.parseInt(idStr);
        System.out.println("Enter game name :");
        String name = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter game price :");
        String priceString = ScannerWrapper.getInstance().nextLine();
        int price = Integer.parseInt(priceString);
        System.out.println("Enter game year :");
        String yearString = ScannerWrapper.getInstance().nextLine();
        int year = Integer.parseInt(yearString);
        System.out.println("Enter game company :");
        String company = ScannerWrapper.getInstance().nextLine();
        System.out.println("Enter game quantity :");
        String quantityString = ScannerWrapper.getInstance().nextLine();
        int quantity = Integer.parseInt(quantityString);
        Game newGame = new Game(id,name,price,year,company,quantity);
        addToGames(newGame);
    }

    public ArrayList<Game> getGames() {
        return games;
    }
}
