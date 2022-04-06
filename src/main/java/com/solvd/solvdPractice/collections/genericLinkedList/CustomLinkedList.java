package com.solvd.solvdPractice.collections.genericLinkedList;

import com.solvd.solvdPractice.collections.exceptions.ElementNotFound;
import com.solvd.solvdPractice.collections.exceptions.EmptyLinkedListException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CustomLinkedList <T> {

    private Node head;
    private int size;

    public CustomLinkedList() {
        size = 0;
    }

    public void addElementAtStart(T data) {
        Node<T> newNode = new Node(data);

        newNode.setNextNode(head);
        head = newNode;
        size++;
    }

    public void insertElement(T data, int index) {
        if (index == 0) {
            addElementAtStart(data);
        } else {
            Node <T> newNode = new Node(data);
            Node <T> currentNode = head;

            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNextNode();
            }

            newNode.setNextNode(currentNode.getNextNode());
            currentNode.setNextNode(newNode);
            size++;
        }
    }

    public void addElementAtTheEnd(T data) {
        Node <T> newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node <T> currentNode = head;
            while(currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
        size++;
    }

    public ArrayList<Node> getAll() throws EmptyLinkedListException {
        Node <T> currentNode = head;
        ArrayList<Node> elements = new ArrayList<>();

        if (currentNode == null) {
            throw new EmptyLinkedListException("The LinkedList is empty.");
        }

        while(currentNode != null) {
            elements.add(currentNode);
            currentNode = currentNode.getNextNode();
        }

        return elements;
    }

    public Node<T> getOne(int index) throws ElementNotFound {
        if (index == 0) {
            return head;
        }
        Node <T> currentNode = head;

        if (index >= getSize()) {
            throw new ElementNotFound("Element not found.");
        }
        for (int i = 0; i < getSize(); i++) {
            if (index == i) {
                return currentNode;
            }
        }
        throw new ElementNotFound("Element not found.");
    }

    public boolean deleteElement(T element) throws EmptyLinkedListException {
        Node<T> currentNode = head;
        if (currentNode == null) {
            throw new EmptyLinkedListException("The LinkedList is empty.");
        }
        if (containsElement(element)) {
            while(currentNode.getNextNode() != null) {
                if (currentNode.equals(element)) {
                    currentNode = currentNode.getNextNode();
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteAt(int index) throws ElementNotFound {

        if (index >= getSize()) {
            throw new ElementNotFound("Element not found.");
        }
        if (index == 0) {
            head = head.getNextNode();
            size--;
        } else if(index == getSize() - 1) {
            getOne(index - 1).setNextNode(null);
            size--;
        }
        else {
            Node <T> currentNode = head;


            for (int i = 0; i < index - 1; i++) {

                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(currentNode.getNextNode().getNextNode());
            size--;
        }
    }

    public boolean containsElement(T element) throws EmptyLinkedListException {
        Node <T> currentNode = head;

        if (currentNode == null) {
            throw new EmptyLinkedListException("The LinkedList is empty.");
        }
        if (currentNode.equals(element)) {
            return true;
        }
        while(currentNode.getNextNode() != null) {
            if (currentNode.equals(element)) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomLinkedList<?> that = (CustomLinkedList<?>) o;
        return Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }
}
