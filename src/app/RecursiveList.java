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

      if (elem == null){
        throw new NullPointerException();
      }


      Node<T> newNode = new Node<T>(elem, head);
      head = newNode;
      size++;

  }

  @Override
  public void insertAtEnd(T elem) {
      //TODO: Implement this method.

      if (elem == null){
        throw new NullPointerException("A");
      }

      else if (head == null){ //if head is empty, then it only needs to add that element
        Node<T> firstNode = new Node<T>(elem, head);
        head = firstNode;
        size++;
      }

      else if (head != null){
        
        Node<T> currNode = recursiveAddLast(head); //finding the node of the last element
        Node<T> newNode = new Node<T>(elem, null); //creating new node for the element after last node, if size is not 1
        currNode.setNext(newNode);
        size++;
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
      

      if (index == 0){
        insertAtBeginning(elem);
      }
      else{
        Node<T> nodeAtIndex = insertAtRecursive(index, head);
        Node<T> nextNode = nodeAtIndex.getNext() == null? null : nodeAtIndex.getNext();
        Node<T> newNode = new Node<T>(elem, nextNode); //pointing the next node as current index's next node
        nodeAtIndex.setNext(newNode); //current index's next node changed to the point to the new node
        size++;
      }
      
  }

  private Node<T> insertAtRecursive(int index, Node<T> currNode){
    
    if (index == 1){ //when at the target index, return the current index node
      return currNode;
    }

    else{
      return insertAtRecursive(index-1, currNode.getNext());
    }
  }

  @Override
  public T removeFirst() {
    T removedItem = null;
      //TODO: Implement this method.

      if (head == null){
        throw new IllegalStateException();
      }

      else if (head.getNext() == null){
        
        removedItem = head.getData();
        head = null;
        size--;
      }

      else{
        removedItem = head.getData();
        head = head.getNext(); //changing the head to the next node
        size--;
      }

    return removedItem;
  }

  @Override
  public T removeLast() {
    T removedItem = null;
      //TODO: Implement this method.

      if (isEmpty() == true){
        throw new IllegalStateException();
      }

      else if (head.getNext() == null){
        removedItem = head.getData();
        head = null;
        size--;
        return removedItem;
      }

      else{
        removedItem = recursiveRemoveLast(head).getNext().getData();
        recursiveRemoveLast(head).setNext(null); //node before last will now point to null, hence removing the last node
        size--;
      }
      

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

    if (head == null){
      throw new IndexOutOfBoundsException();
    }

    else if (i == 0){
      return removeFirst();
    }

    else{
      Node<T> removedItemNode = removeAtRecursive(i-1, 0, head);

      if (removedItemNode.getNext().getNext() == null){
        return removeLast();
      }

      else{
        Node<T> nextItem = removedItemNode.getNext().getNext();
        removedItem = removedItemNode.getNext().getData();
        removedItemNode.setNext(nextItem); //leapfrogging so that it will skip over the desired removal node
        size--;
      }
    }
    return removedItem;
  }

  private Node<T> removeAtRecursive(int i, int index, Node<T> currNode){

    if(currNode.getNext() == null){
      throw new IndexOutOfBoundsException();
    }

    else if (index == i){
      return currNode;
    }

    else{
      return removeAtRecursive(i, index+1, currNode.getNext());
    }
  }

  @Override
  public T getFirst() {
    T item = null;
      //TODO: Implement this method.

      if (isEmpty() == true){
        throw new IllegalStateException();
      }

      item = head.getData();

    return item;
  }

  @Override
  public T getLast() {
    T item = null;
      //TODO: Implement this method

      if (isEmpty() == true){
        throw new IllegalStateException();
      }
      
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
      if (isEmpty() == true){
        return item;
      }

    return getAtRecursive(0, i, head);
  }

  private T getAtRecursive(int index, int i, Node<T> currNode){

    if (i == index){
      return currNode.getData() ;
    }

    else{
      return getAtRecursive(index+1, i, currNode.getNext());
    }
  }

  @Override
  public void removeElement(T elem) {
      //TODO: Implement this method.

    if (isEmpty()){
      throw new NullPointerException();
    }

    else if(elem == null){
      throw new NullPointerException();
    }

    else if(head.getNext() == null && elem != head.getData()){
      throw new ItemNotFoundException();
    }

    else if (head.getData() == elem){
      head = null;
    }

    else{
      Node<T> currNode = removeElementRecursive(elem, head);

      if (currNode.getNext().getNext() == null){
        
        currNode.setNext(null);
        size--;
      }
      else{
        currNode.setNext(currNode.getNext().getNext()); //leapfrogging over the item needed to be removed
        size--;
      }
      
    }
  }

  private Node<T> removeElementRecursive(T elem, Node<T> currNode){

    if (elem == currNode.getNext().getData()){ //if next item is target item, then return this node
      return currNode;
    }

    if (currNode.getNext().getNext() == null){ //item does not exist
      throw new ItemNotFoundException("Item was not found in the list");
    }

    else{
      return removeElementRecursive(elem, currNode.getNext());
    }
  }

  @Override
  public int indexOf(T elem) {

      //TODO: Implement this method.

    if (head == null){
      throw new NullPointerException("inf");
    }

    else if (head.getData() == elem){
      return 0;
    }

    else if (elem == null){
      throw new NullPointerException("npe");
    }

    else{
      return indexOfRecursive(elem, 0, head);
    }
  }

  private int indexOfRecursive(T elem, int i, Node<T> currNode){

    if (currNode.getNext() == null && elem != currNode.getData()){
      return -1;
    }
    else if (elem == currNode.getData()){
      return i;
    }

    else{
      return indexOfRecursive(elem, i+1, currNode.getNext());
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
