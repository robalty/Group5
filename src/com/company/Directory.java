package com.company;

/**
 * Created by Ollie on 11/10/17.
 * The Directory is an extension of the AVL tree that is simply
 * organized by a string name rather than an integer identifier.
 * All algorithms are the same.
 */


public class Directory extends Tree {
    public Directory () { super(); }

    public Directory(Entry toAdd) { super(toAdd); }

    //Wrapper function to call the recursive function to insert a new node into the tree. The address of root is then
    //reassigned after the insertion is performed.
    public void insertEntry(Entry toInsert) { this.root = insertEntry(this.root, toInsert); }

    //Recursive function that actually performs the insert. Because this is a binary search tree, all new nodes are
    //inserted at a leaf at the appropriate location to keep the tree organized.
    protected Entry insertEntry(Entry current, Entry toInsert){
        //Case #1: Insert at leaf.
        if(current == null){
            current = toInsert;
            return current;
        }

        int difference = current.compare(toInsert);

        //Case #2: Insertion is a duplicate
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

    //Removes the specified Entry from the tree. Public wrapper function.
    public void removeEntry(String toRemove) { this.root = removeEntry(this.root, toRemove); }

    //Recursive function that actually removes the specified Entry from the tree.
    protected Entry removeEntry(Entry current, String toRemove) {
        //Case #1: Entry not found.
        if(current == null) return current;

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

    //Wrapper function. Retrieves a particular node based on identification number.
    public Entry searchEntry (String toFind){
        Entry retrieve = null;
        retrieve = searchEntry(this.root, retrieve, toFind);
        return retrieve;
    }

    //Searches for and retrieves a node that matches the search term.
    protected Entry searchEntry(Entry current, Entry retrieve, String toFind){
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

    //Balances the tree after removals take place.
    protected Entry balanceTreeAfterRemoval(Entry current, String removed) {
        //If the reference is empty.
        if(current == null)
            return current;

        //Calculates the difference in height between the two subtrees.
        int difference = getHeight(current.goLeft()) - getHeight(current.goRight());

        //If the tree is balanced, return.
        if(difference < 2 && difference > -2)
            return current;

        //If tree is left-heavy.
        if(difference >= 2) {
            if((current.goLeft()).compare(removed) < 0) //If item was removed from the right.
                current = rotateLeft(current);
            else
                current = doubleRotateLeft(current);
        }

        //If tree is right-heavy.
        else if(difference <= -2) {
            if((current.goRight()).compare(removed) > 0) //If item was removed from the left.
                current = rotateRight(current);
            else
                current = doubleRotateRight(current);
        }

        return current;
    }
}
