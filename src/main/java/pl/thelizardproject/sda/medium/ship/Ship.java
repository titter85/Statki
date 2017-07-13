package pl.thelizardproject.sda.medium.ship;

import pl.thelizardproject.sda.medium.exception.BrokenShipException;
import pl.thelizardproject.sda.medium.tourist.Tourist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public abstract class Ship {

    int endurance;
    ShipType shipType;
    int capacity;
    ArrayList<Tourist> touristsList = new ArrayList<>();

    Ship(int endurance) {
        this.endurance = endurance;
    }

    public void sail() throws BrokenShipException {
        this.endurance--;
        if (this.endurance == 0) {
            throw new BrokenShipException(this.getName());
        }
    }

    public int getEndurance() {
        return endurance;
    }

    public abstract String getName();

    public ShipType getShipType() {
        return shipType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addTourist(Tourist tourist) throws IllegalStateException{
        if (touristsList.size() != this.getCapacity()){
            touristsList.add(tourist);
        }else{
            throw new IllegalStateException("Too much touristsList!");
        }
    }

    public int getTouristCount() {
        return touristsList.size();
    }

    public Optional<Tourist> getFirstTourist() {
        return (!touristsList.isEmpty())? Optional.of(touristsList.get(0)):null;
    }

    public boolean isNotEmpty(){
        return (touristsList.isEmpty())?true:false;
    }

    public Optional<Tourist> getLastTourist() {
        return (!touristsList.isEmpty())?Optional.of(touristsList.get(touristsList.size()-1)):null;
    }

    public List<Tourist> getTouristsSortedByAgeAsc() {
//        touristsList.sort((t1, t2)-> Integer.compare(t2.getAge(), t1.getAge()));
        return touristsList.stream().sorted((t1, t2)-> Integer.compare(t1.getAge(), t2.getAge())).collect(Collectors.toList());
//        return touristsList;
    }
}