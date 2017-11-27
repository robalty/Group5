package com.company;
import java.io.File;

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
    //reporting at the end of the week. Client can specify the directory that the report will be written to.
    public int writeSeparateEntryReports(String directoryName) { return writeSeparateEntryReports(this.root, directoryName); }

    protected int writeSeparateEntryReports(Entry current, String directoryName) {
        int count;

        if(current == null)
            return 0;

        //ROLAND: Here's where you insert the naming convention that you would prefer
        String fileName = directoryName;
        File reportName = new File(fileName);

        count = writeSeparateEntryReports(current.goLeft(), directoryName);
        current.display();
        current.writeReport(reportName);
        count += writeSeparateEntryReports(current.goRight(), directoryName);

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

        //Increment the total for the EFT object. Hannah gave me these as interfaces.
        total.servicesCount = current.getNumberOfServices();
        total.transferTotal = current.getTotalFee();

        //Traverse right
        count += writeCombinedEntryReports(current.goRight(),combinedReport, total);

        return ++count;
    }

}
