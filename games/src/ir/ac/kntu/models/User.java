package ir.ac.kntu.models;

import java.util.ArrayList;

public class User {
    private ArrayList<Game> ownGames;

    private ArrayList<Integer> counts;

    private int amount;

    public User() {
        ownGames = new ArrayList<>();
        counts = new ArrayList<>();
        amount=0;
    }

    public void addToCreditCard(int addedAmount){
        amount+=addedAmount;
    }

    public ArrayList<Game> getOwnGames() {
        return ownGames;
    }

    public int getAmount() {
        return amount;
    }

    public void pay(int payable){
        amount = amount - payable;
    }

    public ArrayList<Integer> getCounts() {
        return counts;
    }

    public void addToUserGames(Game newGame){
        if(!(ownGames.contains(newGame))){
            ownGames.add(newGame);
            counts.add(1);
            return;
        }
        int place = ownGames.indexOf(newGame);
        int gameCount = counts.get(place);
        if(newGame.getQuantity() >= gameCount+1){
            gameCount++;
        }else{
            System.out.println("running out of quantity . the last possible number has been set");
        }
        counts.set(place,gameCount);
    }

    public void removeFromUserGames(Game game){
        counts.remove(ownGames.indexOf(game));
        ownGames.remove(game);
    }

    public void updateGamesAmount(){
        for (Game game : ownGames){
            int place = ownGames.indexOf(game);
            int amount = counts.get(place);
            System.out.println("amount = " + amount);
            game.removeAQuantity(amount);
        }
    }

    public void emptyBasket(){
        ownGames.clear();
        counts.clear();
    }

    public void showUserGames(){
        for (Game game : ownGames){
            System.out.println(game);
            System.out.println("count = "+ counts.get(ownGames.indexOf(game)));
        }
    }
}
