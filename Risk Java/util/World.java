package util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class World {
    private String name;
    private Set<Continent> continents;
    private Set<Province> provinces;

    public World(String name){
        this.name = name;
        continents = new HashSet<Continent>();
        provinces = new HashSet<Province>();
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }


    public Set<Continent> getContinents() {
        return continents;
    }
    protected void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }
    public Set<Province> getProvinces() {
        return provinces;
    }
    protected void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }
    protected void addProvince(Province province) {
        this.provinces.add(province);
    }

    @Override
    public String toString() {
        String returnStr = name + " [continents=(";
        for(Continent continent: continents){
            returnStr += continent.getName() + ", ";
        }
        returnStr = returnStr.substring(0, returnStr.length()-2);
        returnStr += "), provinces=(";
        for(Province province: provinces){
            returnStr += province.getName() + ", ";
        }
        returnStr = returnStr.substring(0, returnStr.length()-2);
        returnStr += ")]";
        return returnStr;
    }

    public static void main(String[] args) {
        Player player1 = new Player("uno", null);
        Player player2 = new Player("dos", null);
        Player player3 = new Player("tres", null);
        Province upperright = new Province("upperright", player2, 0);
        Province upperleft = new Province("upperleft", player1, 1);
        Province lowermiddle = new Province("lowermiddle", player3, 2);
        Province middle = new Province("middle", player1, 3);
        Province outsideright = new Province("outsideright", player1, 4);
        Province outsideleft = new Province("outsideleft", player3, 5);
        Province outsidedown = new Province("outsidedown", player3, 6);
        Province outsidetop = new Province("outsidetop", player1, 7);

        upperright.setAdjacent(new HashSet<Province>(Arrays.asList(outsideright, middle)));
        upperleft.setAdjacent(new HashSet<Province>(Arrays.asList(outsideleft, middle)));
        lowermiddle.setAdjacent(new HashSet<Province>(Arrays.asList(outsidedown, middle)));
        middle.setAdjacent(new HashSet<Province>(Arrays.asList(upperright, upperleft, lowermiddle)));
        outsideright.setAdjacent(new HashSet<Province>(Arrays.asList(upperright, outsideleft, outsidedown, outsidetop)));
        outsideleft.setAdjacent(new HashSet<Province>(Arrays.asList(upperleft, outsideright, outsidedown, outsidetop)));
        outsidedown.setAdjacent(new HashSet<Province>(Arrays.asList(outsideright, outsideleft, lowermiddle)));
        outsidetop.setAdjacent(new HashSet<Province>(Arrays.asList(outsideright, outsideleft)));
        Set<Province> testprovincesforcontinent = new HashSet<Province>(Arrays.asList(upperleft, upperright, lowermiddle, middle));
        Continent test = new Continent("guy", 2, testprovincesforcontinent);
        World world = new World("Worldy World");
        world.setContinents(new HashSet<Continent>(Arrays.asList(test)));
        world.setProvinces(new HashSet<Province>(Arrays.asList(upperleft, upperright, middle, lowermiddle, outsidedown, outsideleft, outsideright, outsidetop)));
        System.out.println(test);
        System.out.println(test.getProvinces());
        System.out.println(test.calculateAdjacent());
        System.out.println(world);

        System.out.println("Possible Destinations: ");
        for(Province p: world.getProvinces()){
            System.out.println(p.getName());
            System.out.println(Game.possibleDestinations(p));
        }

    }



    
    
    
}
