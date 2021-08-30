package sample;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Club implements Serializable {
    private List<Player> data;
    private String name;
    private int playerCount;
    public Club(String name){
        setName(name);
        data = new ArrayList();
        playerCount = 0;
    }

    public List<Player> getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }



    public List<Player> maxSalary(){
        double max = 0;
        for (Player p : data){
            if(max < p.getWeeklySalary()) max = p.getWeeklySalary();
        }
        List<Player> temp = new ArrayList();
        for(Player p : data){
            if(max == p.getWeeklySalary())temp.add(p);
        }
        return temp;
    }

    public boolean addPlayer(Player p){
        data.add(p);
        playerCount++;
        return true;
    }

    public void removePlayer(Player p){
        data.remove(p);
        playerCount--;
    }

    public List<Player> maxAge(){
        int max = 0;
        for (Player p : data){
            if(max < p.getAge()) max = p.getAge();
        }
        List<Player> temp = new ArrayList();
        for(Player p : data){
            if(max == p.getAge())temp.add(p);
        }
        return temp;
    }

    public List<Player> maxHeight(){
        double max = 0;
        for(Player p : data){
            if(max < p.getHeight()) max = p.getHeight();
        }
        List<Player> temp = new ArrayList();
        for(Player p : data){
            if(max == p.getHeight())temp.add(p);
        }
        return temp;
    }

    public double totalSalary(){
        double salary = 0;
        for(Player p : data){
            salary += p.getWeeklySalary();
        }
        return salary*52;
    }
    public Map countryWiseCount(){
        Map count = new HashMap<String, Integer>();
        for(Player p : data){
            Integer val = (Integer) count.getOrDefault(p.getCountry(), 0);
            count.put(p.getCountry(), val + 1);
        }
        return count;
    }

    public List<Player> searchPosition(String position){
        List<Player> temp = new ArrayList();
        for(Player p : data){
            if(position.equalsIgnoreCase(p.getPosition()))temp.add(p);
        }
        return temp;
    }
    public List<Player> searchSalary(int low, int high){
        List<Player> temp = new ArrayList();
        for(Player p : data){
            if(low <= p.getWeeklySalary() && p.getWeeklySalary() <= high)temp.add(p);
        }
        return temp;
    }


    public List<Player> searchName(String name){
        List<Player> temp = new ArrayList();
        for(Player p : data){
            if(name.equalsIgnoreCase(p.getName())) temp.add(p);
        }
        return temp;
    }

    public List<Player> searchCountry(String country){
        List<Player> temp = new ArrayList();
        for(Player p : data){
            if(country.equalsIgnoreCase(p.getCountry())){
                temp.add(p);
            }
        }
        return temp;
    }

    public void print(){
        for(Player player : data){
            player.print();
        }
    }

}
