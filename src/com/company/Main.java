package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//Roland Ballinger
//Main.java

public class Main {
    protected static Scanner user = new Scanner(System.in);
    public static void main(String[] args) {
        Profile[] cool = new Profile[2];
        Tree[] test = new Tree[2];
        test[0] = new Tree();
        Entry temp;
        /*
        for(int i = 0; i < 100; ++i){
            n = rnd.nextInt(900000);
            temp = new Service(n);
            System.out.println(temp);
            test[0].insertEntry(temp);
        }*/
        File dirLoad = new File(".\\data_files\\serviceDB.txt");
        temp = new Service();
        Scanner reader = null;
        try {
            reader = new Scanner(dirLoad);
        }catch(FileNotFoundException e){
            System.out.println("Disk read error!");
        }
        while(temp.loadFromFile(reader) > 0){
            test[0].insertEntry(temp);
            temp = new Service();
        }
        cool[0] = new ProviderProfile(test);
        cool[1] = new OperatorProfile(test);
        while(cool[1].menu());
    }
}
