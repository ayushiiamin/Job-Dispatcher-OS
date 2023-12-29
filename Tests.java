import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;



/**
 * @author mikec
 *
 */
class Tests {
	
	
	/*
	 * REFERENCES - 
	 * 
	 * 1) How to store elements in an array - 
	 * Sam (2015). How to store threads in arraylist. [online] Stack Overflow. 
	 * Available at: https://stackoverflow.com/questions/32470353/how-to-store-threads-in-arraylist [Accessed 12 Mar. 2021].
	 */
	
	

	@org.junit.jupiter.api.Test
	//Example Test
	void test() {
		JobDispatcher dispatcher = new JobDispatcher();
		
		//Specify job for 3 Compute threads and 0 Storage threads
		dispatcher.specifyJob(3,0);

		
		//But start only one Compute thread:
		Thread computeThread = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();

		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		
		//The single Compute thread should be blocked waiting: 
		assertEquals(Thread.State.WAITING, computeThread.getState());
		
	}
	
	@org.junit.jupiter.api.Test
	void test2UR1() {
		JobDispatcher dispatcher = new JobDispatcher();
		
		
		dispatcher.specifyJob(0,3);

		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		Thread computeThread = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start();
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start();
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		
		
		Thread storageThread = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread.start();
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start();
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start();
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		
		arrThreadStorage.add(storageThread);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(0, cComputeTerminated);
		assertEquals(5, cComputeWaiting);
		
		assertEquals(3, cStorageTerminated);
		assertEquals(2, cStorageWaiting);
		
	}
	
	
	@org.junit.jupiter.api.Test
	void test3UR1() {
		JobDispatcher dispatcher = new JobDispatcher();
		
		
		dispatcher.specifyJob(4,0);

		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		Thread computeThread = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start();
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start();
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		
		
		Thread storageThread = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread.start();
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start();
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start();
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		
		arrThreadStorage.add(storageThread);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(4, cComputeTerminated);
		assertEquals(1, cComputeWaiting);
		
		assertEquals(0, cStorageTerminated);
		assertEquals(5, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	void test4UR1() {
		JobDispatcher dispatcher = new JobDispatcher();
		
		
		dispatcher.specifyJob(2,4);

		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		Thread computeThread = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start();
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start();
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		
		
		Thread storageThread = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread.start();
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start();
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start();
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		
		arrThreadStorage.add(storageThread);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		

		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(2, cComputeTerminated);
		assertEquals(3, cComputeWaiting);
		
		assertEquals(4, cStorageTerminated);
		assertEquals(1, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	void test5UR1() {
		JobDispatcher dispatcher = new JobDispatcher();
		
		
		dispatcher.specifyJob(6,3);

		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		Thread computeThread = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start();
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start();
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		
		
		Thread storageThread = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread.start();
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start();
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start();
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		
		arrThreadStorage.add(storageThread);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		

		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(0, cComputeTerminated);
		assertEquals(5, cComputeWaiting);
		
		assertEquals(0, cStorageTerminated);
		assertEquals(5, cStorageWaiting);
		
	} 
	
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test1UR2() {
		
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,2);
		dispatcher.specifyJob(2,1);
		dispatcher.specifyJob(0,0);
		
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		//dispatcher.specifyJob(1,3);
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(3, cComputeTerminated);
		assertEquals(3, cComputeWaiting);
		
		assertEquals(3, cStorageTerminated);
		assertEquals(3, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test2UR2() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,5);
		dispatcher.specifyJob(5,0);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(6, cComputeTerminated);
		assertEquals(0, cComputeWaiting);
		
		assertEquals(5, cStorageTerminated);
		assertEquals(1, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test3UR2() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,8);
		dispatcher.specifyJob(3,4);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(3, cComputeTerminated);
		assertEquals(3, cComputeWaiting);
		
		assertEquals(4, cStorageTerminated);
		assertEquals(2, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test4UR2() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,1);
		dispatcher.specifyJob(1,2);
		dispatcher.specifyJob(2,0);
		dispatcher.specifyJob(0,3);
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(4, cComputeTerminated);
		assertEquals(2, cComputeWaiting);
		
		assertEquals(6, cStorageTerminated);
		assertEquals(0, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test5UR2() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(3,4);
		dispatcher.specifyJob(2,1);
		dispatcher.specifyJob(1,0);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
		

		assertEquals(6, cComputeTerminated);
		assertEquals(0, cComputeWaiting);
		
		assertEquals(5, cStorageTerminated);
		assertEquals(1, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test1UR3() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,2);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		dispatcher.specifyJob(2,1);
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		dispatcher.specifyJob(3,0);
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
	
		

		assertEquals(6, cComputeTerminated);
		assertEquals(0, cComputeWaiting);
		
		assertEquals(3, cStorageTerminated);
		assertEquals(3, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test2UR3() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,5);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		dispatcher.specifyJob(5,0);
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
	
		

		assertEquals(6, cComputeTerminated);
		assertEquals(0, cComputeWaiting);
		
		assertEquals(5, cStorageTerminated);
		assertEquals(1, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test3UR3() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,8);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		dispatcher.specifyJob(3,4);
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
	
		

		assertEquals(3, cComputeTerminated);
		assertEquals(3, cComputeWaiting);
		
		assertEquals(4, cStorageTerminated);
		assertEquals(2, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test4UR3() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(1,1);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		dispatcher.specifyJob(0,3);
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
	
		

		assertEquals(1, cComputeTerminated);
		assertEquals(5, cComputeWaiting);
		
		assertEquals(4, cStorageTerminated);
		assertEquals(2, cStorageWaiting);
		
	}
	
	@org.junit.jupiter.api.Test
	//Example Test
	void test5UR3() {
		
		JobDispatcher dispatcher = new JobDispatcher();
	
		dispatcher.specifyJob(2,1);
		
		
		ArrayList<Thread> arrThreadCompute = new ArrayList<Thread>();
		int cComputeTerminated = 0;
		int cComputeWaiting = 0;
		
		ArrayList<Thread> arrThreadStorage = new ArrayList<Thread>();
		int cStorageTerminated = 0;
		int cStorageWaiting = 0;
		
		
		Thread computeThread = new Thread() {			
			public void run () {
				String name = "Compute Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread.start();
		
		
		Thread computeThread2 = new Thread() {			
			public void run () {
				String name = "Compute Thread 2";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread2.start();
		
		
		Thread computeThread3 = new Thread() {			
			public void run () {
				String name = "Compute Thread 3";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread3.start();
		
		
		
		Thread computeThread4 = new Thread() {			
			public void run () {
				String name = "Compute Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread4.start(); 
	
		dispatcher.specifyJob(3,4);
		
		Thread computeThread5 = new Thread() {			
			public void run () {
				String name = "Compute Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread5.start(); 
		
		Thread computeThread6 = new Thread() {			
			public void run () {
				String name = "Compute Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueComputeThread();
			}
		};	
		computeThread6.start(); 
		
		
		
		arrThreadCompute.add(computeThread);
		arrThreadCompute.add(computeThread2);
		arrThreadCompute.add(computeThread3);
		arrThreadCompute.add(computeThread4);
		arrThreadCompute.add(computeThread5);
		arrThreadCompute.add(computeThread6);
		
		
		
		
		Thread storageThread1 = new Thread() {			
			public void run () {
				String name = "Storage Thread 1";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread1.start(); 
		
		
		Thread storageThread2 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 2";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread2.start(); 
		
		dispatcher.specifyJob(1,0);
		
		Thread storageThread3 = new Thread() {			
			public void run () {
				
				String name = "Storage Thread 3";
				Thread.currentThread().setName(name);
				
				dispatcher.queueStorageThread();
			}
		};	
		storageThread3.start();
		
		
		
		Thread storageThread4 = new Thread() {			
			public void run () {
				String name = "Storage Thread 4";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread4.start(); 
		
		
		
		Thread storageThread5 = new Thread() {			
			public void run () {
				String name = "Storage Thread 5";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread5.start();
		
		Thread storageThread6 = new Thread() {			
			public void run () {
				String name = "Storage Thread 6";
				Thread.currentThread().setName(name);
				dispatcher.queueStorageThread();
			}
		};	
		storageThread6.start();
		
		
		
		arrThreadStorage.add(storageThread1);
		arrThreadStorage.add(storageThread2);
		arrThreadStorage.add(storageThread3);
		arrThreadStorage.add(storageThread4);
		arrThreadStorage.add(storageThread5);
		arrThreadStorage.add(storageThread6);
		
		
		//Wait for set time and assume that execution has finished:
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace(); };
		
		Iterator<Thread> iterCompute = arrThreadCompute.iterator();
		
		while(iterCompute.hasNext()) {
			Thread i= iterCompute.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cComputeTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cComputeWaiting++;
			}
		}
		
		
		Iterator<Thread> iterStorage = arrThreadStorage.iterator();
		
		while(iterStorage.hasNext()) {
			Thread i= iterStorage.next();
			
			if(i.getState() == Thread.State.TERMINATED) {
				cStorageTerminated++;
			}
			else if(i.getState() == Thread.State.WAITING) {
				cStorageWaiting++;
			}
		}
		
		//System.out.println("THE VALUE OF C IN TEST IS " + cCompute);
	
		

		assertEquals(6, cComputeTerminated);
		assertEquals(0, cComputeWaiting);
		
		assertEquals(5, cStorageTerminated);
		assertEquals(1, cStorageWaiting);
		
	}
	
	

	
	
}
