package com.andrei;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
    	if(args.length != 2)
		{
			System.out.println("Please supply an input and an output file!!\nFirst parameter is the input file, second parameter is the output file.");
			return;
		}
    	String inputPath = args[0];
    	String outputPath = args[1];
		FileWriter fileWriter = new FileWriter(outputPath);
		PrintWriter printWriter = new PrintWriter(fileWriter);
    	int numberOfQueues, numberOfClients, simulationTime, minServiceTime, maxServiceTime, minArrivalTime ,maxArrivalTime;
    	String readLine;
    	String[] split;
		List<Client> clientList;
		File inputFile = new File(inputPath);
		Scanner fileReader = new Scanner(inputFile);
		numberOfClients = fileReader.nextInt();
		numberOfQueues = fileReader.nextInt();
		simulationTime = fileReader.nextInt();
		fileReader.nextLine();
		readLine = fileReader.nextLine();
		split = readLine.split(",");
		minArrivalTime = Integer.parseInt(split[0]);
		maxArrivalTime = Integer.parseInt(split[1]);
		readLine = fileReader.nextLine();
		split = readLine.split(",");
		minServiceTime = Integer.parseInt(split[0]);
		maxServiceTime = Integer.parseInt(split[1]);
		clientList = new RandomClientGenerator().generate(numberOfClients, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
		clientList.sort(Client::compareTo);
		new Simulation(clientList, numberOfQueues, simulationTime, printWriter).start();
	}
}
