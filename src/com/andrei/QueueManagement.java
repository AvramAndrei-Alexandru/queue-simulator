package com.andrei;

import java.io.PrintWriter;

class QueueManagement {
    private Queue queue;

    QueueManagement(int ID, PrintWriter printWriter) {
        this.queue = new Queue(ID, printWriter);
        this.queue.start();

    }

    Queue getQueue() {
        return this.queue;
    }

    void addClient(Client client)
    {
        this.queue.addClient(client);
    }
    void stop()
    {
        this.queue.close();
    }
}
