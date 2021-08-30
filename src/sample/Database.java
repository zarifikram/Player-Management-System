package sample;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String inputDir, outputDir;
    private List<Player> data;
    private List<Club> clubData;
    private List<Player> transferPlayers;
    public Database(String inputDirectory){
        inputDir = inputDirectory;
        outputDir = inputDirectory;
        data = new ArrayList();
        clubData = new ArrayList();
        transferPlayers = new ArrayList();
    }

    public void readFromSource() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader((inputDir)));
        while(true){
            String lineFeed = br.readLine();
            if(lineFeed == null) break;
            String[] tokens = lineFeed.split(",");
            Player p = new Player(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], Integer.parseInt(tokens[6]), Double.parseDouble(tokens[7]));
            addToDatabase(p);
        }
        br.close();
    }


    public void writeToSource() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputDir));
        for(Player p : data){
            bw.write(p.getName() + "," + p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getNumber()+","+p.getWeeklySalary()+"\n");
        }
        bw.close();
    }

    public boolean clubExists(String clubName){
        for(Club c: clubData){
            if(clubName.equalsIgnoreCase(c.getName())) return true;
        }
        return false;
    }

    public boolean playerExists(String playerName){
        for(Player p: data){
            if(playerName.equalsIgnoreCase(p.getName())) return true;
        }
        return false;
    }

    public boolean numberExists(Player player){
        String playerClub = player.getClub();
        int playerNumber = player.getNumber();
        for(Player p: data){
            if(playerClub.equalsIgnoreCase(p.getClub()) && p.getNumber() == playerNumber) return true;
        }
        return false;
    }

    public void addToDatabase(Player p){
        if(numberExists(p)){
            System.out.println("Jersey number already exists in " + p.getClub() + "!");
            return;
        }
        if(!playerExists(p.getName())) { // if that player does not exist

            // need to check if that club exists in db or not
            if(!clubExists(p.getClub())){
                Club c = new Club(p.getClub());
                clubData.add(c);
            }
            if(getClub(p.getClub()).addPlayer(p)) data.add(p);
        }
        else{
            System.out.println("Player already exists in Database.");
        }
        return;
    }

    public Club getClub(String club){
        for(Club c : clubData){
            if(club.equalsIgnoreCase(c.getName())) return c;
        }
        return null;
    }

    public void listPlayer(Player player){
        for(Player p : data)if(p.getName().equals(player.getName())){
            p.setListed(true);
            transferPlayers.add(p);
        }
    }

    public void unListPlayer(Player player) {
        for(Player p : data)if(p.getName().equals(player.getName())){
            p.setListed(false);
            transferPlayers.remove(p);
        }
    }

    public List<Player> getTransferPlayers(){
        return transferPlayers;
    }

    public void transferPlayer(Player player, String clubName){
        for(Player p : data)if(p.getName().equals(player.getName())){
            p.setListed(false);
            transferPlayers.remove(p);
            getClub(p.getClub()).removePlayer(p);
            p.setClub(clubName);
            getClub(clubName).addPlayer(p);
        }

    }


}
