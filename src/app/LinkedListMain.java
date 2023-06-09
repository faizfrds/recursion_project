package app;

import java.util.Iterator;

public class LinkedListMain {
    public static void main(String[] args) {
        ListInterface<Integer> list = new RecursiveList<Integer>();
        /*list.insertAtEnd(22);
        list.insertAtEnd(900);
        list.insertAtEnd(143);
        list.insertAtEnd(8);*/

        /*list.insertAtEnd(3);
		list.insertAtEnd(5);
		list.insertAt(1,4);
		list.insertAt(1, 3);
		list.insertAtEnd(7);
		list.insertAtBeginning(1);
        list.insertAt(5,6);*/

        list.insertAtBeginning(99);
        list.insertAtEnd(11);
        System.out.println(printList(list));
        list.removeElement(99);
        System.out.println(list.size());
        //System.out.println(list.isEmpty());
        /*list.removeLast();
        System.out.println(printList(list));
        list.removeAt(1);
        System.out.println(printList(list));
        list.removeElement(100); // should throw an ItemNotFoundException*/
    }

    public static String printList(ListInterface<Integer> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> listIter = list.iterator();
        while (listIter.hasNext())
            sb.append(listIter.next()).append(" ");
        return sb.toString();
    }

}
