package com.company;
import java.util.Date;
import java.util.Scanner;
/**
 * Created by angry on 11/22/2017.
 */
abstract public class Profile {
    protected Database[] data;
    Date currentDate = new Date();
    public Profile() {
        data = null;
    }

    public Profile(Database[] info) {
        data = info;
    }

    abstract public boolean menu();

    public void test() {
        data[0].test();
    }

    public Member searchMembers(int id) {
        if (data != null) {
                Member temp = (Member) data[3].searchEntry(id);
                if (temp != null)
                    return temp;
        }
        System.out.println("Entry not found");
        return null;
    }
}
