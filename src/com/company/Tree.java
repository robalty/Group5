package com.company;
import java.io.File;

/**
 * Created by Ollie on 11/8/17.
 * This is an AVL tree of Entries. Serves as a parent class for the derived trees Database
 * and Directory, which contain additional functionality.
 */


public class Tree {
    Entry root;

    //Default constructor.
    public Tree(){
        this.root = null;
    }

    //Constructor that takes an Entry object with which to initialize the tree.
    public Tree(Entry toInsert){
        this.root = toInsert;
    }

    //Wrapper function to call the recursive function to insert a new node into the tree. The address of root is then
    //reassigned after the insertion is performed.
    public void insertEntry(Entry toInsert){ this.root = insertEntry(this.root, toInsert); }

    //Recursive function that actually performs the insert. All new nodes are inserted at a leaf.
    protected Entry insertEntry(Entry current, Entry toInsert){
        //Case #1: Insert at leaf.
        if(current == null){
            current = toInsert;
            return current;
        }

        //Compares the Entry to insert to the current node.
        int difference = current.compare(toInsert);

        //Case #2: New node is a duplicate. Nothing is inserted
        if(difference == 0) return current;

        //Case #3: Decide which subtree to check next.
        //If the item to insert is smaller than the current node.
        if(difference > 0){
            current.setLeft(insertEntry(current.goLeft(), toInsert)); //Recursive call.
            current = balanceTreeAfterInsert(current, toInsert);
        }

        //Case #4: If the item to insert is larger than the current node.
        else {
            current.setRight(insertEntry(current.goRight(), toInsert)); //Recursive call.
            current = balanceTreeAfterInsert(current, toInsert);
        }

        return current;
    }

    //Removes the specified Entry from the tree. Public wrapper function. Returns a boolean that indicates whether the
    //the removal was successful. The only case a removal would not be successful is if it could not be found.
    public void removeEntry(int toRemove) {
        this.root = removeEntry(this.root, toRemove);

    }

    //Recursive function that actually removes the specified Entry from the tree.
    protected Entry removeEntry(Entry current, int toRemove) {
        //Case #1: Entry not found.
        if(current == null){
            System.out.printf("Couldn't find entry %d", toRemove);//putting this here because the wrapper didn't return a bool> -Roland
            return current;
        }

        //Compares the Entry to insert to the current node.
        int difference = current.compare(toRemove);

        //Case #2: Match found, remove.
        if(difference == 0) {
            current = findSuccessor(current);
            current = balanceTreeAfterRemoval(current, toRemove);
        }

        //Decide which subtree to check next.
        //If the item to remove is smaller than the current node.
        else if (difference > 0) {
            current.setLeft(removeEntry(current.goLeft(), toRemove)); //Recursive call.
            current = balanceTreeAfterRemoval(current, toRemove);
        }

        //If the item to insert is larger than the current node.
        else {
            current.setRight(removeEntry(current.goRight(), toRemove)); //Recursive call.
            current = balanceTreeAfterRemoval(current, toRemove);
        }
        return current;
    }


    //Finds the successor of a specified node and sets the reference to remove to null.
    protected Entry findSuccessor(Entry toRemove) {
        Entry temp = null; //Holds the in-order successor.
        Entry hold = null; //Needed for one of the removal cases.

        //Case #1: Node to remove has no children
        if(toRemove.goLeft() == null && toRemove.goRight() == null)
            return null;

        //Case #2: Node has only one child.
        else if(toRemove.goLeft() == null || toRemove.goRight() == null) {
            if(toRemove.goLeft() == null)
                temp = toRemove.goRight();
            else
                temp = toRemove.goLeft();
        }

        //Case #3: Node has two children, but the right has no left children.
        else if((toRemove.goRight()).goLeft() == null) {
            temp = toRemove.goRight();
            temp.setLeft(toRemove.goLeft());
        }

        //Case #4: Node has two children, and the right has two children.
	    else {
            temp = toRemove.goRight();
            while (temp.goLeft() != null) {
                hold = temp;
                temp = temp.goLeft();
            }
            hold.setLeft(temp.goRight());
            temp.setLeft(toRemove.goLeft());
            temp.setRight(toRemove.goRight());
        }

        toRemove = null;
        return temp;
    }

    //Wrapper function to call the recursive function to display the tree. It serves only the purpose of calling
    //the other function and does not serve any other purpose.
    public int displayEntries(){ return displayEntries(root); }

    //Recursively displays the entire tree and returns the number of nodes displayed.
    protected int displayEntries(Entry current) {
        int count;
        if(current == null)
            return 0;

        count = displayEntries(current.goLeft());
        current.display();
        count += displayEntries(current.goRight());

        return ++count;
    }

    //Wrapper function. Retrieves a particular node based on identification number.
    public Entry searchEntry (int toFind) {
        Entry retrieve = null;
        retrieve = searchEntry(this.root, retrieve, toFind);
        return retrieve;
    }

    //Searches for and retrieves a node that matches the search term.
    protected Entry searchEntry(Entry current, Entry retrieve, int toFind) {
        //Case #1: Match not found
        if(current == null){
            return retrieve;
        }

        int difference = current.compare(toFind);

        //Case #2: Match found
        if(difference == 0)
            return current;

        //Case #3: Search term is smaller than current Entry
        if(difference > 0)
            return searchEntry(current.goLeft(), retrieve, toFind);

        //Case #4: Search term is larger than current Entry
        else
            return searchEntry(current.goRight(), retrieve, toFind);
    }

    //Wrapper function.
    //Writes the entire tree to a single file that can be loaded upon startup.
    public int writeEntries(File treeFile) { return writeEntries(this.root, treeFile); }

    //Write the entire tree out to a file in pre-order.
    protected int writeEntries(Entry current, File treeFile) {
        int count;
        if(current == null)
            return 0;

        current.writeToFile(treeFile);
        count = displayEntries(current.goLeft());
        count += displayEntries(current.goRight());

        return ++count;
    }


    //Balances the tree after insertions take place.
    protected Entry balanceTreeAfterInsert(Entry current, Entry inserted) {
        //If the reference is empty.
        if(current == null)
            return current;

        //Calculates the difference in height between the two subtrees.
        int difference = getHeight(current.goLeft()) - getHeight(current.goRight());

        //If the tree is balanced, return.
        if(difference < 2 && difference > -2)
            return current;

        //If tree is left-heavy.
        if(difference == 2){
            if((current.goLeft()).compare(inserted) > 0) //If item was inserted to the left.
                current = rotateLeft(current);
            else
                current = doubleRotateLeft(current);
        }

        //If tree is right-heavy.
        else if(difference == -2){
            if((current.goRight()).compare(inserted) < 0) //If item was inserted to the right.
                current = rotateRight(current);
            else
                current = doubleRotateRight(current);
        }

        return current;
    }


    //The following functions are concerned with balancing the tree.


    //Balances the tree after removals take place.
    protected Entry balanceTreeAfterRemoval(Entry current, int removed) {
        //If the reference is empty.
        if(current == null)
            return current;

        //Calculates the difference in height between the two subtrees.
        int difference = getHeight(current.goLeft()) - getHeight(current.goRight());

        //If the tree is balanced, return.
        if(difference < 2 && difference > -2)
            return current;

        //If tree is left-heavy.
        if(difference == 2) {
            if((current.goLeft()).compare(removed) < 0) //If item was removed from the right.
                current = rotateLeft(current);
            else
                current = doubleRotateLeft(current);
        }

        //If tree is right-heavy.
        else if(difference == -2) {
            if((current.goRight()).compare(removed) > 0) //If item was removed from the left.
                current = rotateRight(current);
            else
                current = doubleRotateRight(current);
        }

        return current;
    }

    //Finds the height of a tree.
    protected int getHeight(Entry current){
        if(current == null)
            return 0;

        int left = getHeight(current.goLeft());
        int right = getHeight(current.goRight());
        if(left > right)
            return left + 1;
        else
            return right + 1;
    }

    //Rotates the tree so the left child becomes the root.
    protected Entry rotateLeft(Entry current){
        if(current == null)
            return current;

        Entry temp = current.goLeft();
        current.setLeft(temp.goRight());
        temp.setRight(current);
        return temp;
    }

    //Rotates the tree so the right child becomes the root.
    protected Entry rotateRight(Entry current){
        if(current == null)
            return current;

        Entry temp = current.goRight();
        current.setRight(temp.goLeft());
        temp.setLeft(current);
        return temp;
    }

    //First rotates the left subtree, then rotates the tree.
    protected Entry doubleRotateLeft(Entry current){
        if(current == null)
            return current;

        current.setLeft(rotateRight(current.goLeft()));
        return rotateLeft(current);
    }

    //Rotates the right subtree, then the tree.
    protected Entry doubleRotateRight(Entry current){
        if(current == null)
            return current;

        current.setRight(rotateLeft(current.goRight()));
        return rotateRight(current);
    }

    public void test(){
        System.out.println(this);
    }
}