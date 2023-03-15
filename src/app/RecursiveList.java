package app;

import java.util.Iterator;
import java.util.ListIterator;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertAtBeginning(T elem) {
      //TODO: Implement this method.

      Node<T> newNode = new Node<T>(elem, head);
      newNode.setNext(head);
      head = newNode;
  }

  @Override
  public void insertAtEnd(T elem) {
      //TODO: Implement this method.

      if (head == null){ //if head is empty, then it only needs to add that element
        Node<T> firstNode = new Node<T>(elem, null);
        head = firstNode;
      }

      else if (head != null){
        Node<T> currNode = recursiveAddLast(head); //finding the node of the last element
        Node<T> newNode = new Node<T>(elem, null); //creating new node for the element after last node, if size is not 1
        currNode.setNext(newNode);
        System.out.println(newNode.getData()+"ABC");
      }
      
      
  }

  private Node<T> recursiveAddLast(Node<T> currNode){ //recursive helper method that will traverse through the linked list

    if (currNode.getNext() == null){ //if next node is null, then we know that it is the last node
      return currNode;
    }
    else{ //else continue to traverse linked list
      return recursiveAddLast(currNode.getNext());
    }
  }



  @Override
  public void insertAt(int index, T elem) {
      //TODO: Implement this method.

      Node<T> nodeAtIndex = insertIndex(0, head, index);
      Node<T> nextNode = nodeAtIndex.getNext();
      
      Node<T> newNode = new Node<T>(elem, nextNode); //pointing the next node as current index's next node
      nodeAtIndex.setNext(newNode); //current index's next node changed to the point to the new node



  }

  private Node<T> insertIndex(int i, Node<T> indexNode, int index){

    if (i == index){ //when at the target index, return the current index node

      return indexNode;
    }

    else{
      return insertIndex(i++, indexNode.getNext(), index);
    }

  }

  @Override
  public T removeFirst() {
    T removedItem = null;
      //TODO: Implement this method.

      removedItem = head.getData();
      head = head.getNext(); //changing the head to the next node
      head.setNext(head.getNext());

    return removedItem;
  }

  @Override
  public T removeLast() {
    T removedItem = null;
      //TODO: Implement this method.

      removedItem = recursiveRemoveLast(head).getNext().getData();
      recursiveRemoveLast(head).setNext(null); //node before last will now point to null, hence removing the last node

    return removedItem;
  }

  private Node<T> recursiveRemoveLast(Node<T> currNode){

    if (currNode.getNext().getNext() == null){ //if the next next node is null, then it is the node before last
      return currNode;
    }

    else{
      return recursiveRemoveLast(currNode.getNext());
    }
  }

  @Override
  public T removeAt(int i) {
    T removedItem = null;
      //TODO: Implement this method.

    removedItem = recursiveRemoveAt(i, 0, head).getNext().getData();
    recursiveRemoveAt(i, 0,head).setNext(recursiveRemoveAt(i, 0, head).getNext().getNext()); //leapfrogging so that it will skip over the desired removal node

    return removedItem;
  }

  private Node<T> recursiveRemoveAt(int i, int index, Node<T> currNode){

    if (index == i-1){
      return currNode;
    }

    else{
      return recursiveRemoveAt(i, index++, currNode.getNext());
    }

  }

  @Override
  public T getFirst() {
    T item = null;
      //TODO: Implement this method.

      item = head.getData();

    return item;
  }

  @Override
  public T getLast() {
    T item = null;
      //TODO: Implement this method
      item = getLastRecursive(head).getData();

    return item;
  }

  public Node<T> getLastRecursive(Node<T> currNode){

    if (currNode.getNext() == null){
      return currNode;
    }

    else{
      return getLastRecursive(currNode.getNext());
    }
  }

  @Override
  public T getAt(int i) {
    T item = null;
      //TODO: Implement this method.

    return indexOfRecursive(0, i, head);
  }

  private T indexOfRecursive(int index, int i, Node<T> currNode){

    if (i == index){
      
      return currNode.getData() ;
    }

    else{

      return indexOfRecursive(index++, i, currNode.getNext());
    }
  }



  @Override
  public void removeElement(T elem) {
      //TODO: Implement this method.

    Node<T> currNode = removeAtRecursive(elem, head);
    currNode.setNext(currNode.getNext().getNext()); //leapfrogging over the item needed to be removed

  }

  private Node<T> removeAtRecursive(T elem, Node<T> currNode){

    if (elem == currNode.getData()){ //if next item is target item, then return this node
      return currNode;
    }

    if (currNode.getNext() == null){ //item does not exist
      throw new ItemNotFoundException("Item was not found in the list");
    }

    else{
      return removeAtRecursive(elem, currNode.getNext());
    }
  }



  @Override
  public int indexOf(T elem) {
    int index = -1;
      //TODO: Implement this method.

    return indexOfRecursive(elem, 0, head);
  }

  private int indexOfRecursive(T elem, int i, Node<T> currNode){

    if (elem == currNode.getData()){
      
      return i;
    }

    else{

      return indexOfRecursive(elem, i++, currNode.getNext());
    }
  }


  @Override
  public boolean isEmpty() {
    boolean empty = false;
      //TODO: Implement this method.

      if (head == null){
        empty = true;
      }

    return empty;
  }


  public Iterator<T> iterator() {
    Iterator<T> iter = null;
      //TODO: Implement this method.
   
      Iterator<T> iterate = new LinkedNodeIterator<>(head);
      return iterate;
  }
}
