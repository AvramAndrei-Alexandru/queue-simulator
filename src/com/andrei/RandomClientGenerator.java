package com.andrei;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class RandomClientGenerator {

    List<Client> generate(int numberOfClients, int minArrival, int maxArrival, int minService, int maxService) {
        List<Client> clientList = new ArrayList<>();

        for(int i = 1; i <= numberOfClients; i++) {
            clientList.add(new Client(i, ThreadLocalRandom.current().nextInt(minArrival, maxArrival + 1), ThreadLocalRandom.current().nextInt(minService, maxService + 1)));
        }

        return clientList;
    }
}
