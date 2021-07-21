package ir.ac.kntu.menu;

import ir.ac.kntu.datas.GameData;
import ir.ac.kntu.db.GamesDataBase;
import ir.ac.kntu.models.Game;
import ir.ac.kntu.models.User;
import ir.ac.kntu.utils.ScannerWrapper;

import java.util.ArrayList;

public class View {
    private GameData gameData;

    private User user;

    private GamesDataBase gamesDataBase;

    public View(User user,GamesDataBase gamesDataBase) {
        gameData = new GameData();
        this.gamesDataBase = gamesDataBase;
        this.user = user;
    }

    public void drawMenu(){
        Menu initMenu = new Menu();
        initMenu.gameMenu();
        GameMenuAbilities option = scanOption();
        while (option != GameMenuAbilities.EXIT){
            handleUserChoice(option);
            initMenu.gameMenu();
            option = scanOption();
        }
    }

    private void handleUserChoice(GameMenuAbilities option) {
        switch (option){
            case MAKE_GAME:
                gamesDataBase.makeGame(gameData);
                gamesDataBase.allGames(gameData);
                break;
            case ADD_GAME_TO_LIST:
                gameAdding();
                break;
            case REMOVE_GAME_FROM_LIST:
                gameRemoving();
                break;
            case CREDIT:
                creditCardHandling();
                break;
            case SHOPPING_LIST:
                listHandling();
                break;
            case EXIT:
            default:
                break;
        }
    }

    private void creditCardHandling() {
        System.out.println("how much do you add ?");
        String money = ScannerWrapper.getInstance().nextLine();
        int amount = Integer.parseInt(money);
        user.addToCreditCard(amount);
    }

    private void listHandling() {
        if(user.getOwnGames().size() == 0){
            System.out.println("there is nothing to show");
            return;
        }
        for(Game game : user.getOwnGames()){
            System.out.println("game id = "+ game.getId());
            System.out.println("game name = "+game.getName());
            System.out.println("count = "+user.getCounts().get(user.getOwnGames().indexOf(game)));
        }
        System.out.println("do you want to submit? for yes press 1");
        String input = ScannerWrapper.getInstance().nextLine();
        input=input.trim();
        int finalAmount = 0;
        if(input.equals("1")){
            for (Game game : user.getOwnGames()){
                finalAmount += game.getPrice();
            }
            if(finalAmount > user.getAmount()){
                System.out.println("not enough money");
                user.emptyBasket();
                return;
            }
            user.pay(finalAmount);
            user.updateGamesAmount();
            user.emptyBasket();
            gameData.saveAll(gamesDataBase.getGames());
            System.out.println("successful attempt");
        }
    }

    private void gameRemoving() {
        user.showUserGames();
        System.out.println("Remove Game ");
        System.out.println("Enter game id = ");
        String idStr = ScannerWrapper.getInstance().nextLine();
        int id = Integer.parseInt(idStr);
        for(int i=0 ; i < user.getOwnGames().size();i++){
            if(user.getOwnGames().get(i).getId() == id){
                user.removeFromUserGames(user.getOwnGames().get(i));
            }
        }
    }

    private void gameAdding() {
        if(gameData.loadInfo().size() == 0){
            System.out.println("first make a game please");
            return;
        }
        ArrayList<Game> allGames = gameData.loadInfo();
        showAllGames();
        System.out.println("Add Game ");
        System.out.println("Enter game id = ");
        String idStr = ScannerWrapper.getInstance().nextLine();
        int id = Integer.parseInt(idStr);
        for(Game game : allGames){
            if(game.getId() == id){
                user.addToUserGames(game);
            }
        }
    }

    private void showAllGames() {
        ArrayList<Game> games = gameData.loadInfo();
        for(Game game : games){
            System.out.println(game);
            System.out.println("-----------------------------");
        }
    }

    private GameMenuAbilities scanOption() {
        GameMenuAbilities[] options = GameMenuAbilities.values();
        System.out.println("now choose from the list above : ");
        String optionStr = ScannerWrapper.getInstance().nextLine();
        int option = Integer.parseInt(optionStr);
        while(option < 1 || option > options.length){
            System.out.println("out of index");
            System.out.println("try again");
            optionStr = ScannerWrapper.getInstance().nextLine();
            option = Integer.parseInt(optionStr);
        }
        return options[option-1];
    }
}
