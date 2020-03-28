package com.andrei;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

    class Simulation extends Thread{
    private volatile List<Client> clientList;
    private int simulationTime;
    private List<QueueManagement> queueList = new ArrayList<>();
    private PrintWriter printWriter;

    Simulation(List<Client> clientList, int numberOfQueues, int simulationTime, PrintWriter printWriter) {
        this.clientList = clientList;
        this.simulationTime = simulationTime;
        this.printWriter = printWriter;
        for(int i = 0; i < numberOfQueues; i++)
        {
            queueList.add(new QueueManagement(i + 1, printWriter));
        }
    }

    @Override
    public void run() {
        System.out.println("Simulation started");
        for(int i = 0; i <= simulationTime; i++)
        {
            while(clientList.get(0).getArrivalTime() <= i)
            {
                    int index = findOptimalQueue();
                    clientList.get(0).setArrivalTime(simulationTime + 1);
                    queueList.get(index).addClient(clientList.get(0));
                    clientList.sort(Client::compareTo);
            }
            if(clientList.get(0).getArrivalTime() == simulationTime + 1)
            {
                if(clientList.get(0).getWaitingTime() + clientList.get(0).getTrueArrivalTime() + clientList.get(0).getServiceTime() == i)
                    break;
            }
            printStatus(i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        printWriter.println("\nAverage waiting time: " + calculateAverage());
        printWriter.close();
        for(QueueManagement queueManagement : queueList)
            queueManagement.stop();
        System.out.println("Simulation ended");
    }



    private int findOptimalQueue() {
        int minTime = queueList.get(0).getQueue().getWaitingTime().get();
        int index = 0;
        for(int i = 1; i < queueList.size(); i++)
        {
            if(minTime > queueList.get(i).getQueue().getWaitingTime().get())
            {
                minTime = queueList.get(i).getQueue().getWaitingTime().get();
                index = i;
            }
        }
        return index;
    }

    private void printStatus(int time)
    {
        printWriter.println("\nTime " + time);
        printWriter.print("Waiting clients: ");
        for(Client client : clientList)
        {
            if(client.getArrivalTime() != simulationTime + 1)
            {
                printWriter.print(" (" + client.getID() + "," + client.getArrivalTime() + "," + client.getServiceTime() + "); ");
            }
        }
    }

    private double calculateAverage()
    {
        double average = 0;

        for(Client client : clientList)
        {
            average += client.getServiceTime() + client.getWaitingTime();
        }

        average = average  / clientList.size();

        return average;
    }

}
