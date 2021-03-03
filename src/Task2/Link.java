package Task2;

import java.io.IOException;

class Link {
    private int data;
    public Link next;

    public Link(int d) {
        data = d;
    }

    public int getKey() {
        return data;
    }

    public void displayLink() {
        System.out.print(data + " ");
    }
}

class SortedList {
    private Link first;
    public SortedList() {
        first = null;
    }

    public void insert(Link theLink){
        int key = theLink.getKey();
        Link previous = null; // start at first
        Link current = first;
        // until end of list,
        //or current bigger than key,
        while (current != null && key > current.getKey()) {
            previous = current;
            current = current.next; // go to next item
        }
        if (previous == null) // if beginning of list,
            first = theLink;
        else
            // not at beginning,
            previous.next = theLink;
        theLink.next = current;
    }

    public void delete(int key){
        Link previous = null;
        Link current = first;

        while (current != null && key != current.getKey()) {
            previous = current;
            current = current.next;
        }
        // disconnect link
        if (previous == null) //   if beginning of list delete first link
            first = first.next;
        else
            //   not at beginning
            previous.next = current.next; //delete current link
    }

    public Link find(int key) {
        Link current = first;
        while (current != null && current.getKey() <= key) { // or key too small,
            if (current.getKey() == key) // found, return link
                return current;
            current = current.next; // go to next item
        }
        return null; // cannot find it
    }

    public void displayList() {
        System.out.print("List: ");
        Link current = first;
        while (current != null){
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}


