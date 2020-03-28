package com.andrei;

public class Client implements Comparable<Client>{
    private int ID;
    private int arrivalTime;
    private int serviceTime;
    private int waitingTime;
    private int trueArrivalTime;

    Client(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.waitingTime = -1;
        this.trueArrivalTime = arrivalTime;
    }

    int getID() {
        return ID;
    }

    int getArrivalTime() {
        return arrivalTime;
    }

    int getServiceTime() {
        return serviceTime;
    }

    int getWaitingTime() {
        return waitingTime;
    }

    void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }


    void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(Client client) {
        return Integer.compare(this.arrivalTime, client.getArrivalTime());
    }

    int getTrueArrivalTime() {
        return trueArrivalTime;
    }
}
