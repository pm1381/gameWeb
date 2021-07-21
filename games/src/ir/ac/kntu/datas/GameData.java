package ir.ac.kntu.datas;

import ir.ac.kntu.models.Game;

import java.io.*;
import java.util.ArrayList;

public class GameData {
    public ArrayList<Game> loadInfo() {
        File file=new File("src/infos.txt");
        ArrayList<Game> games =new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream input = new ObjectInputStream(fileInputStream)) {
            while(true) {
                try {
                    games.add((Game) input.readObject());
                } catch(EOFException e){
                    return games;
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("No Such File found!!!!!");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Nothing exists in file!!!!!");
        }
        return games;
    }

    public void saveAll(ArrayList<Game> list) {
        File file=new File("src/infos.txt");
        try(FileOutputStream fileOutputStream=new FileOutputStream(file,false);
            ObjectOutputStream output=new ObjectOutputStream(fileOutputStream)){
            for(Game game:list){
                output.writeObject(game);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


/*
        ArrayList<Game> games;
        games = loadInfo();
        for (int i=0;i<games.size();i++){
            for(int j=0;j< list.size();j++){
                if(list.get(j).equals(games.get(i))){
                    games.remove(i);
                    games.add(list.get(j));
                }else{
                    if(!games.contains(list.get(j))){
                        games.add(list.get(j));
                    }
                }
            }
        }
        if(games.size()==0){
            games.addAll(list);
        }
 */
