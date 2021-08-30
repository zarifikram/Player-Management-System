package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private String name, country, club, position, faceImageSource, clubImageSource, countryImageSource, firstName, lastName;
    private int age, number;
    private double height, weeklySalary;
    private final String IMG_DIR = "img/";
    private boolean isListed;
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public boolean isListed() {
        return isListed;
    }

    public void setListed(boolean listed) {
        isListed = listed;
    }

    public Player(String name, String country, int age, double height, String club, String position, int number, double weeklySalary) {
        this.name = name;
        this.country = country;
        this.club = club;
        this.position = position;
        this.age = age;
        this.number = number;
        this.height = height;
        this.weeklySalary = weeklySalary;
        splitName();
        setFaceImageSource();
        setClubImageSource();
        setCountryImageSource();
        this.isListed = false;
    }

    private void splitName(){
        int lastSpacePosition = name.lastIndexOf(' ');
        if(lastSpacePosition == -1){
            lastName = name;
            firstName = "";
        }
        else{
            firstName = name.substring(0, lastSpacePosition);
            lastName = name.substring(lastSpacePosition + 1);
        }

    }
    public String getFaceImageSource() {
        return faceImageSource;
    }

    public String getClubImageSource() {
        return clubImageSource;
    }

    public String getCountryImageSource() {
        return countryImageSource;
    }

    private void setFaceImageSource(){
        this.faceImageSource = IMG_DIR + name.replace(" ", "") + ".png";
    }

    private void setClubImageSource(){
        this.clubImageSource = IMG_DIR + club.replace(" ", "") + ".png";
    }

    private void setCountryImageSource(){
        this.countryImageSource = IMG_DIR + country.replace(" ", "") + ".png";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club; setClubImageSource();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public void print(){
        System.out.println(this);
        System.out.println("Is listed : " + isListed);
        System.out.println("Name : " + name);
        System.out.println("Country : " + country);
        System.out.println("Club : " + club);
        System.out.println("Age : " + age);
        System.out.println("Position : " + position);
        System.out.println("Height : " + height);
        System.out.println("Jersey Number : " + number);
        System.out.println("Weekly Salary : " + weeklySalary);
    }

}
