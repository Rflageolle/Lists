/**
 *  
 * Countries September 11, 2017
 *
 * Ryan Flageolle Computer Science 2 
 * 
 *  This program allows you to choose between 3 different operations:
 *  1. Print the countries that border an inputted country.
 *  2. Print the countries with a population greater than an inputed population.
 *  3. Print the countries that border an inputted country as well as having a
 *     population greater than an inputed population,
 *  4. Stop the program.
 * 
 */


package classprojects;

/**
 *
 * @author ryanflage
 */

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.ArrayList;
import java.util.Scanner;


public class HW2 {
    
    CountriesLinkedList countriesLL = new CountriesLinkedList();
    Countries[] countriesArray;
    public static Scanner in = new Scanner(System.in);
    String[] knownCountries = {"Germany", "Netherlands", "Belgium", "Luxemburg", "Poland", "Czechia", "Austria", "France", "Switzerland"};
    
    HW2(String fileName) {
        int counter = 0;
        this.countriesArray = populateArray(fileName); // calls a method to initialize the objects array
        for (Countries country : countriesArray) {
            this.countriesLL.addCountry(country);
        }     
    }
    
    // This method populates an array of countries from a file.
    public static Countries[] populateArray(String fileName) {
	//ArrayList<Countries> fromFile = new ArrayList<>();
        Countries[] fromFile = new Countries[9];
        
        int currentCountry = 0;
	try{
            File text = new File(fileName);
            Scanner inFile = new Scanner(text);//.useDelimiter(",\n\n");
            while(inFile.hasNextLine()){
                String full = inFile.nextLine();       
                //System.out.println(full);
		// countryName (string), latitude String, longitude String, countryArea int
		// countryPopulation int, countryGDP double, countryYear int
                Scanner breaks = new Scanner(full).useDelimiter(", ");
		String[] country = new String[8];
		int currentString = 0;
                while(breaks.hasNext()) {
                    country[currentString] = breaks.next();
                    //System.out.println(country[currentString]);
                    currentString ++;	
                }
                breaks.close();
//                System.out.println(country[0]);
//                System.out.println(country[1]);
//                System.out.println(country[2]);
//                System.out.println(country[3]);
//                System.out.println(country[4]);
//                System.out.println(country[5]);
//                System.out.println(country[6]);
//                System.out.println(country[7]);
		Countries x = new Countries(country[0], country[1], country[2], Integer.parseInt(country[3]), 
                        Integer.parseInt(country[4]), Double.parseDouble(country[5]), Integer.parseInt(country[6]), country[7]);
		//fromFile.add(x);
                //System.out.println(x);
                
                fromFile[currentCountry] = x;
                currentCountry++; //this is the one line of code that made me crazy for several hours
            }
           
        } catch( FileNotFoundException e) {
            System.out.print(e.getMessage());
        }    
    return fromFile;
        
    }
    
    // class to make make a linked list
    
    public class CountriesLinkedList{
        
        private Node head;
        private int index = 0;
        
        public class Node{
            
            private Countries data;
            private Node next;
            
            public Node(Countries data){
                this.data = data;
                this.next = null;
                // no need to increase index because we want the head to be 0
            }
 
            public Node getNext() {
		return next;
            }
            
            public void setNext(Node nextValue) {
		next = nextValue;
            }
            
            public String getName(){
                return data.countryName;
            }
            
            public int getPopulation(){
                return data.countryPopulation;
            }

        }
        
        public CountriesLinkedList(){
            
        }
        
        public void addCountry(Countries data){
            
            if (head == null) {
                head = new Node(data);
            }
            
            Node added = new Node(data);
            Node current = head;
         
            if (current != null){
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(added);
            }
            index++;
        }
        
        public int getSize(){
            return index + 1;
        }
        
//        public String returnCountryName(String data){
//            Node current = head;
//            String str = "";
//            
//            if (current != null){
//                
//                if (current.getName().equals(data)){
//                    return current.getName();
//                }
//                else {
//                    while (current.next != null){
//                        if (current.getName().equals(data)){
//                            return current.getName();
//                        }
//                    }
//                }
//                    
//            }
//            return str;
//        }
//        
//        public int returnCountryPopulation(int data) {
//            int rtn = 0;
//            while (current.next != null) {
//                if (current.getPopulation() == data){
//                    rtn = current.getPopulation();
//                }
//                else {current = current.next;}
//            }
//            return rtn;
//        }
    }
    
    // method to display the countries to border the inputted string
    public static void displayBorderCountries(String name, HW2 in){
        String[] borders = null;
        String str = "Countries that border " + name + ": \n";
        
        for (Countries x : in.countriesArray) {
            if (x.matches(name)){
                borders = x.borders.bordersArray;
            }
        }
        if (borders == null) {
            str += " - Country not Found";
        }
        else {
            for (String item : borders) {
                str += (" -" + item + "\n") ;
            }
        }    
        System.out.println(str);
        
    }
    
    // the gui and guts of the program
    // prints instructions as well as prompting the user
    
    public void userOption(){
        boolean cont = false;
        
        System.out.print("This program allows you to choose one of 4 operations: \n"
                        + "1 - Display the countries that border another Country. \n"
                        + "2 - Display all countries that have a population greater "
                        + "than a given population. \n"
                        + "3 - Display the countries that border another Country "
                        + "with a population greater than a given population. \n"
                        + "4 - Quit the program. \n\n"
                        + "To select the program simply type the number corresponding to "
                        + "the operation you want to be completed and then follow the instructions \n"
                        + "on screen. (When entering Countries use upper-case first letter followed by lower-case form) \n"
                        + "------------------------------------------------------------------------------------------------- \n\n");
        
        do{
            System.out.print("Which Operation would you like to perform: ");
            int operation = in.nextInt();
            boolean moveOn = false;
            
            switch (operation) {
                case 1:
                    String country = "";
                    
                    while (!moveOn){
                        System.out.println("Which country: ");
                        country = in.next();
                        moveOn = validCountry(this.knownCountries, country);
                    }
                    
                    displayBorderCountries(country, this);
                    cont = doContinue();
                    break;
                case 2:
                    int pop = 0;
                    
                    do {
                        in.nextLine();
                        System.out.println("What population would you like to test? (whole number): ");
                        moveOn = in.hasNextInt();
                        pop = Integer.parseInt(in.next());
                    } while (!moveOn || (pop > 80594017));
                    
                    aboveMinimumPopulation(this.countriesArray, pop);
                    cont = doContinue();
                    break;
                case 3:
                    int pop2 = 0;
                    String country2 = "";
                    do {
                        in.nextLine();
                        System.out.println("What Country's borders would you like to check?");
                        country2 = in.next();
                        moveOn = validCountry(this.knownCountries, country2);
                    } while (!moveOn);
                    
                    do {
                        System.out.println("What population? (whole number): ");
                        moveOn = in.hasNextInt();
                    } while (!moveOn);
                    pop2 = in.nextInt();
                    greaterPopulationAndBorder(pop2, country2, this);
                    cont = doContinue();
                    break;
                    
                case 4:
                    cont = false;        
            }
            
        } while (cont);
    }
    
    // prints the countries that have a population greater than an inputted population
    
    public static void aboveMinimumPopulation(Countries[] data, int min){
        String str = "Countries with Population over " + min + ":\n";
        String chstr = "";
        
        for (Countries x : data) {
            int check = x.countryPopulation;
            
            if (check > min){
                chstr += " -" + x.countryName + "\n";
            }
        }
        System.out.println(str + chstr);
    }
    
    // checks whether the user inputs a country that the program knows
    
    public static Boolean validCountry(String[] knownCountries, String countryStr){
        Boolean valid = false;
        for (String country : knownCountries) {
            if (country.equals(countryStr)) {
                valid = true;
            }
        }
        return valid;
    }
    
    // does both operations
    
    public static void greaterPopulationAndBorder(int min, String name, HW2 in){
        String[] borders = null;
        String fin = "Countries that border " + name + " and have a population greater than " + min + ": \n";
        
        for (Countries x : in.countriesArray) {
            if (x.matches(name)){
                borders = x.borders.bordersArray;
            }
        }
        
        for (Countries thing : in.countriesArray) {
            if (thing.countryPopulation > min) {
                for (String str : borders) {
                    if (thing.countryName.equals(str)) {
                        fin += " -" + thing.countryName + "\n";
                    }
                }
            }
        }
        
        System.out.println(fin);
    }
    
    // checks the y and n input from the user 
    
    public static Boolean doContinue(){
        Boolean b = true;
        Boolean rtn = false;
        
        do {
            System.out.print("\n" + "Would you like to Continue? (Y or N): ");
            switch (in.next()) {
                case "Y":
                case "y":
                    b = true;
                    rtn = true;
                    break;
                case "N":
                case "n":
                    b = true;
                    rtn = false;
                    break;
                default:
                    b = false;
                    break;
            }
            in.nextLine();
        } while (!b);
              
        return rtn;        
    }
    
    public static void main(String[] args) {
        HW2 current = new HW2("/Users/FlageMac/School/Fall2017/ComputerScience2/Project2/countries_data.txt");
        current.userOption();
    }
    
    

}
