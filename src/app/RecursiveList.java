package app;

import java.util.Iterator;

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

      Node<T> currNode = recursiveAdd(head); //finding the node of the last element
      Node<T> newNode = new Node<T>(elem, null); //creating new node for the element where next node is null since it is the last

      currNode.setNext(newNode);
  }

  private Node<T> recursiveAdd(Node<T> currNode){

    if (currNode.getNext() == null){

      //Node<T> addElement = new Node<T>(elem, null);
      //return addElement;
      return currNode;

    }

    else{
      return recursiveAdd(elem, currNode.getNext());
    }
  }



  @Override
  public void insertAt(int index, T elem) {
      //TODO: Implement this method.

      Node<T> newIndexAt = insertIndex(0, head, index, elem);
      Node<T> insertNode = new Node<T>(elem, newIndexAt);

      newIndexAt.setData(elem);

  }

  private Node<T> insertIndex(int i, Node<T> indexNode, int index, T element){

    if (i == index-1){

      Node<T> nextNode = indexNode.getNext();
      //Node<T> insertNode = new Node<T>(element, nextNode);
      return nextNode;
    }

    else{
      return insertIndex(i++, indexNode.getNext(), index, element);
    }

  }

  @Override
  public T removeFirst() {
    T removedItem = null;
      //TODO: Implement this method.

      removedItem = head.getData();
      head.setNext((head.getNext()).getNext());

    return removedItem;
  }

  @Override
  public T removeLast() {
    T removedItem = null;
      //TODO: Implement this method.

    return removedItem;
  }

  @Override
  public T removeAt(int i) {
    T removedItem = null;
      //TODO: Implement this method.

    return removedItem;
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
      //TODO: Implement this method.
      Node<T> currNode = head;
      item = getLastRecursive(currNode).getData();

    return item;
  }

  public Node<T> getLastRecursive(Node<T> currNode){

    if (currNode.getNext() != null){
      return (getLastRecursive(currNode.getNext()));
    }

    else{
      return currNode;
    }
  }

  @Override
  public T getAt(int i) {
    T item = null;
      //TODO: Implement this method.

    return item;
  }

  @Override
  public void removeElement(T elem) {
      //TODO: Implement this method.

  }

  @Override
  public int indexOf(T elem) {
    int index = -1;
      //TODO: Implement this method.

    return index;
  }


  @Override
  public boolean isEmpty() {
    boolean empty = false;
      //TODO: Implement this method.

    return empty;
  }


  public Iterator<T> iterator() {
    Iterator<T> iter = null;
      //TODO: Implement this method.

   return iter;
  }
}
