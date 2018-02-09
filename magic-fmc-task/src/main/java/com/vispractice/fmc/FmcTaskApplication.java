package com.vispractice.fmc;

import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vispractice.fmc.job.TestJob;

@SpringBootApplication
public class FmcTaskApplication {
	public static void main(String[] args) throws Exception {
		TestJob testJob = new TestJob();
		
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String msg = scanner.next();
			if ("exit".equals(msg)) {
				break;
			} else if ("start".equals(msg)) {
				testJob.start();
			} else if ("stop".equals(msg)) {
				testJob.stop();
			} else {
				testJob.setCron("0/10 * * * * ?");
				testJob.restart();
			}
		}

		scanner.close();
	}
}
