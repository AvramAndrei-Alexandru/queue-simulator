package com.andrei;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class Queue extends Thread {
    private int ID;
    private AtomicInteger waitingTime;
    private volatile BlockingQueue<Client> clients;
    private volatile boolean close;
    private int isRemaining = 2;
    private PrintWriter printWriter;

    Queue(int ID, PrintWriter printWriter) {
        this.ID = ID;
        this.waitingTime = new AtomicInteger(0);
        this.close = false;
        this.clients = new LinkedBlockingQueue<>();
        this.printWriter = printWriter;

    }

    @Override
    public void run() {
        Client currentClient;
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!close)
        {
            isRemaining = 2;
            if(clients.size() == 0)
            {
                try {
                    printWriter.println("\nQueue " + ID + " is closed");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    currentClient = clients.take();
                    isRemaining = 2;
                    for(int i = currentClient.getServiceTime() - 1; i >=0 ; i--)
                    {
                        printWriter.println("\nQueue " + ID + ": (" + currentClient.getID() + "," + currentClient.getTrueArrivalTime() + "," + currentClient.getServiceTime() + "); ");
                        isRemaining = i;
                        sleep(1000);
                    }
                    waitingTime.addAndGet( -1 * currentClient.getServiceTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    void addClient(Client client)
    {
        if(isRemaining == 0)
        {
            client.setWaitingTime(0);
        }else
        client.setWaitingTime(waitingTime.get());
        waitingTime.addAndGet(client.getServiceTime());
        clients.add(client);
    }

    void close()
    {
        this.close = true;
    }

    AtomicInteger getWaitingTime()
    {
        return waitingTime;
    }


}
