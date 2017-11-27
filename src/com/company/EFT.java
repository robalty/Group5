package com.company;

/**
 * Created by olive on 11/26/17. Designed to be used with summing primitives during Database traversals.
 */
public class EFT {
    public int servicesCount;
    public double transferTotal;

    public EFT() {
        this.servicesCount = 0;
        this.transferTotal = 0;
    }

    public EFT(EFT toCopy) {
        this.servicesCount = toCopy.servicesCount;
        this.transferTotal = toCopy.transferTotal;
    }

}
