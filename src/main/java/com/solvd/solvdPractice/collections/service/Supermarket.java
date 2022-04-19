package com.solvd.solvdPractice.collections.service;

import com.solvd.solvdPractice.collections.exceptions.EmptyBoxException;
import com.solvd.solvdPractice.collections.exceptions.EmptyQueueException;
import com.solvd.solvdPractice.collections.exceptions.UnableBoxesException;
import com.solvd.solvdPractice.collections.people.Cashier;
import com.solvd.solvdPractice.collections.people.Client;

import java.util.*;

public class Supermarket {
    // a queue of people
    private Map <Cashier, String> boxes = new HashMap<>();
    private Queue<Client> clients = new LinkedList<>();

    //region Boxes' operations
    public void addCashierInBox(Cashier cashier) {
        boxes.put(cashier, "Empty");
    }

    public ArrayList<Integer> getBoxCashier() throws EmptyBoxException {
        //Get cashiers id
        if (boxes.isEmpty()) {
            throw new EmptyBoxException("There aren't cashiers.");
        }

        ArrayList<Integer> cashiers = new ArrayList<>();
        boxes.forEach((c, p) -> cashiers.add(c.getCashierId()));

        return cashiers;
    }

    public void finishServingClient(Cashier box) throws EmptyBoxException {
        if (boxes.get(box).equals("Serving")) {
            boxes.put(box, "Empty");
        } else {
            throw new EmptyBoxException("The box is empty.");
        }
    }

    private void boxServing(Cashier cashier) {
        boxes.put(cashier, "Serving");
    }
    //endregion

    //region Client's operations
    public void addClientInQueue(Client client) {
        clients.add(client);
    }


    public void removeClient() throws EmptyQueueException {
        if (clients.isEmpty()) {
            throw new EmptyQueueException("There aren't clients in the queue.");
        }
        System.out.println(clients.poll().getName() + " was removed.");
    }
    //endregion

    public void serveClient() throws EmptyQueueException, EmptyBoxException, UnableBoxesException {
        boolean emptyBox = false;

        if (clients.isEmpty()) {
            //Evaluate if there are at least one client in the queue
            throw new EmptyQueueException("There aren't clients in the queue.");
        }
        if (getBoxCashier().size() != 0) {
            //Evaluate if there are at lest one box with cashier

            for (Map.Entry<Cashier, String> box : boxes.entrySet()) {
                //Look for an empty box.
                if (box.getValue().equals("Empty")) {
                    emptyBox = true;
                    System.out.println("Serving to a client.");
                    boxServing(box.getKey());
                    removeClient();
                    break;
                }
            }
        }
        if (!emptyBox) {
            throw new UnableBoxesException("All boxes are serving.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supermarket that = (Supermarket) o;
        return Objects.equals(boxes, that.boxes) && Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxes, clients);
    }
}
