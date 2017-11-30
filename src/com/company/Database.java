package com.company;
import java.io.File;
import java.util.*;
import java.text.*;

/**
 * Created by Ollie on 11/10/17.
 * The Database, which extends the Tree, is designed to write out
 * information when it is requested in the form of plaintext reports.
 */
public class Database extends Tree {

    //Writes an EFT report for a specified ID number.
    public boolean writeEntryReport(int idNumber, File reportFile) {
        //Attempts to retrieve a matching Entry.
        Entry temp = this.searchEntry(idNumber);

        //If it exists, write a report and return true.
        if(temp != null){
            temp.writeReport(reportFile);
            return true;
        }
        //Otherwise return false.
        else return false;
    }

    //Wrapper function.
    //Writes separate EFT reports for all Entries in the Tree. Also displays each node in-order. This is intended for mass
    //reporting at the end of the week. The current date is needed for naming the individual files.
    public int writeSeparateEntryReports(String directoryName) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("_MMddyyyy"); //Format date with an underscore delimiter.
        return writeSeparateEntryReports(this.root, directoryName, dateFormat.format(currentDate));
    }

    protected int writeSeparateEntryReports(Entry current, String directoryName, String currentDate) {
        int count;
        String fileName = "";

        //If there is no Entry to read, return
        if(current == null)
            return 0;

        //Add the path to the file name
        if(directoryName != null) fileName = directoryName;

        //The format for the file name is "lastNamefirstName_currentDate.txt"
        fileName += current.idNum + currentDate + ".txt";

        //Create new file for each Entry
        File reportName = new File(fileName);

        //Traverse, display, and write to the report
        count = writeSeparateEntryReports(current.goLeft(), directoryName, currentDate);
        current.display();
        current.writeReport(reportName);
        count += writeSeparateEntryReports(current.goRight(), directoryName, currentDate);

        //Increment total number of Entries written
        return ++count;
    }

    //Wrapper function.
    //Writes a combined report for all Entries in the company and displays in order. Also adds all EFTs in the company
    // and displays a summary at the end. Returns the number of Entries written. Intended for manager reporting.
    public int writeEFTReports(File eftReports) {
        int count = writeEFTReports(this.root, eftReports);
        return count;
    }

    protected int writeEFTReports(Entry current, File eftReports) {
        int count;
        if(current == null)
            return 0;

        //Traverse left
        count = writeEFTReports(current.goLeft(), eftReports);

        //Display the current node
        current.display();

        //Downcast to check if Entry is a Provider instance. Then write EFT report
        if(current instanceof Provider) ((Provider)current).writeEftReport(eftReports);


        //Traverse right
        count += writeEFTReports(current.goRight(), eftReports);

        return ++count;
    }

    //Writes a combined report for all Entries in the company and displays in order. Also adds all EFTs in the company
    // and displays a summary at the end. Returns the number of Entries written. Intended for manager reporting.
    public int writeCombinedEntryReports(File combinedReport) {
        EFT total = new EFT();
        int count = writeCombinedEntryReports(this.root, combinedReport, total);

        //Display totals for the traversal
        System.out.println("SUMMARY\nNumber of Providers: " + count
                + "\nNumber of Services Provided: " + total.servicesCount
                + "\nTotal transfer amount:" + total.transferTotal);

        return count;
    }

    protected int writeCombinedEntryReports(Entry current, File combinedReport, EFT total) {
        int count;
        if(current == null)
            return 0;

        //Traverse left
        count = writeCombinedEntryReports(current.goLeft(), combinedReport, total);

        //Display the current node
        current.display();

        //Write the report out
        current.writeReport(combinedReport);

        //Downcast to check if Entry is a Provider instance. Increment the total for the EFT object.
        if(current instanceof Provider) total = retrieveEFT((Provider)current, total);

        //Traverse right
        count += writeCombinedEntryReports(current.goRight(),combinedReport, total);

        return ++count;
    }

    protected EFT retrieveEFT(Provider toRetrieve, EFT total) {
        total.servicesCount = toRetrieve.getNumberOfServicesProvided();
        total.transferTotal = toRetrieve.getTotalFee();
        return total;
    }

}