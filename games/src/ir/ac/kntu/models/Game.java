package ir.ac.kntu.models;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {
    private int id;

    private String name;

    private int price;

    private int year;

    private String company;

    private int quantity;

    public Game(int id, String name, int price, int year, String company,int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.year = year;
        this.company = company;
        this.quantity=quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void removeAQuantity(int amount){
        if(quantity > 0){
            quantity = quantity - amount;
        }else {
            System.out.println("without enough quantity");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getId() == game.getId() && getPrice() == game.getPrice() && year == game.year &&
                Objects.equals(getName(), game.getName()) &&
                Objects.equals(company, game.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), year, company, getQuantity());
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", year=" + year +
                ", company='" + company + '\'' +
                "Quantity=" + quantity +
                '}';
    }
}
