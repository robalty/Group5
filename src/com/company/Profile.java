package com.company;

import java.util.Scanner;
/**
 * Created by angry on 11/22/2017.
 */
abstract public class Profile {
    protected Tree[] data;

    public Profile() {
        data = null;
    }

    public Profile(Tree[] info) {
        data = info;
    }

    abstract public boolean menu();

    public void test() {
        data[0].test();
    }

    public Entry search(int id) {
        if (data != null) {
            for (int i = 0; i < 4; ++i) {
                Entry temp = data[0].searchEntry(id);
                if (temp != null)
                    return temp;
            }
        }
        System.out.println("Entry not found");
        return null;
    }
}
