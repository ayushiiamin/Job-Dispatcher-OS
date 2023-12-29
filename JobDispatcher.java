import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
// Note that you MUST not use any other `thread safe` classes than the two imported above

public class JobDispatcher implements Dispatcher {
	

	
	
	final ReentrantLock jobDispatcherLock = new ReentrantLock();
	final Condition computeLock = jobDispatcherLock.newCondition();               //Condition variable for the compute thread
	final Condition storageLock = jobDispatcherLock.newCondition();              //Condition variable for the storage thread
	
	

	final ReentrantLock boundedComputeLock = new ReentrantLock();
	final Condition notFullCompute  = boundedComputeLock.newCondition(); 
	final Condition notEmptyCompute = boundedComputeLock.newCondition();
	
	private int capacityComputeBuffer = 13;
	private Thread[] bufferCompute = new Thread[capacityComputeBuffer];
	
	private int outCompute = 0, inCompute = 0; //In and out pointers for buffer
	private int countCompute = 0;




	final ReentrantLock boundedStorageLock = new ReentrantLock();
	final Condition notFullStorage  = boundedStorageLock.newCondition(); 
	final Condition notEmptyStorage = boundedStorageLock.newCondition();
	
	private int capacityStorageBuffer = 13;
	private Thread[] bufferStorage = new Thread[capacityStorageBuffer];
	
	private int outStorage = 0, inStorage = 0; //In and out pointers for buffer
	private int countStorage = 0;
	


	 private boolean checker = false;                                  //Variable to check if the condition is satisfied in order to signal the right set of worker threads

	 //Arraylists to store the number compute and storage threads
	 private ArrayList<Integer> computeArray = new ArrayList<Integer>();                   
	 private ArrayList<Integer> storageArray = new ArrayList<Integer>();
	 
	 
	 /*
	  * In this method, we are taking the number of compute threads and number of storage threads (which were passed
	  * as arguments in the test file) and storing these values in an arraylist.
	  * 
	  * This is done in order to store the different values given for number of compute and number of storage threads by
	  * multiple jobs.
	  */

	@Override
	public void specifyJob(int nComputeThreads, int nStorageThreads) {
		
		computeArray.add(nComputeThreads);       //Adds the number of compute threads into the compute array
		storageArray.add(nStorageThreads);		 //Adds the number of storage threads into the storage array		
		
	}
	
	/*
	 * The below method is used to add the compute threads into the bounded buffer
	 * and increase the count. The count is the number of items in the buffer.
	 * 
	 */
	
	/*
	 * REFERENCES - 
	 * 
	 * 1) The below method has been used from LAB 4.5 ExtrinsicBoundedBuffer.java file
	 * 
	 *  2) Understanding how the bounded buffer works - 
	 * Dartmouth.edu. (2016). [online] 
	 * Available at: https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html [Accessed 8 Mar. 2021]. 
	 *  
	 */
	
	private void putComputeThread(Thread thread) throws InterruptedException { 
		boundedComputeLock.lock();
		try {
			while (countCompute == capacityComputeBuffer) {			//If the buffer is full, then make the threads (calling this method) wait
				notFullCompute.await();  
			}		
			bufferCompute[inCompute] = thread; 						//Insert the thread into the buffer if buffer has space
				inCompute = (inCompute + 1) % capacityComputeBuffer; 
				countCompute++;											//Increment the counter in order to signify that 1 thread has been added to the buffer
				notEmptyCompute.signal();								//Signal that there is space in the buffer
			}
			finally {
				boundedComputeLock.unlock();
			}
	}
	
	/*
	 * The below method is used to fetch the compute threads from the bounded buffer
	 * and decrease the count. The count is the number of items in the buffer.
	 * 
	 */
	
	/*
	 * REFERENCES - 
	 * 
	 * 1) The below method has been used from LAB 4.5 ExtrinsicBoundedBuffer.java file
	 * 
	 * 2) Understanding how the bounded buffer works - 
	 * Dartmouth.edu. (2016). [online] 
	 * Available at: https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html [Accessed 8 Mar. 2021]. 
	 *  
	 */

	private Thread takeComputeThread() throws InterruptedException { 
		Thread thread = new Thread(); 
		boundedComputeLock.lock();
		try {		
			while (countCompute == 0) {				//If the buffer is empty, then wait since no threads are present in the buffer
				notEmptyCompute.await();  
			}
			thread = bufferCompute[outCompute]; 			//Fetch the thread from the buffer if buffer is not empty
			outCompute = (outCompute + 1) % capacityComputeBuffer; 
			countCompute--;									//Decrement the counter in order to signify that 1 thread has been fetched from the buffer
			notFullCompute.signal();						
		}
		finally {
			boundedComputeLock.unlock();
		}
		return thread; 
	}
	


	@Override
	public void queueComputeThread() {
		//Your code here
		jobDispatcherLock.lock();
		try {
			//System.out.println("The current thread - " + Thread.currentThread().getName() + " their state is " + Thread.currentThread().getState());
			
			
			try {
				putComputeThread(Thread.currentThread());               //Inserting the current thread that called queueComputeThread() into the buffer and incrementing the counter by 1. The counter also 
																		//keeps track of how many times queueComputeThread() has been called
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			//System.out.println(Arrays.toString(bufferCompute));
			
			//System.out.println();
			
			
			//System.out.println("The current thread - " + Thread.currentThread().getName() + " their state now is " + Thread.currentThread().getState());
			//System.out.println();
			
			//System.out.println("NOW EXECUTING COMPARE() FOR COMPUTE");
						//System.out.println();
			
			
			
			
			
			

					/*
					 * We now call the private method compare(), in order to check if we got the right set of compute and storage threads
					 * so we can signal.
					 * 
					 * If the checker is false after the execution of compare() is completed, then we put the thread in WAITING state
					 * Else, we reset the checker variable to false.
					 */
							compare();
							if(checker == false) {
								//System.out.println("compute going into waiting");
								try {
									computeLock.await();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
				
						else {
							checker=false;
						}
						
	
							
//							System.out.println();
//							System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//							System.out.println("THE NUMBER OF COMPUTE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(computeLock));
//							//System.out.println("THE NUMBER OF STORAGE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(storageLock));
//							System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//							System.out.println();
				
				
			} 
		finally {
				// TODO Auto-generated catch block
				jobDispatcherLock.unlock();
			}
			
			
		
		
		
//		System.out.println("The current thread - " + Thread.currentThread().getName() + " their state now now is " + Thread.currentThread().getState());
//		System.out.println();
		
	}
	
	
	
	
	/*
	 * The below method is used to insert the storage threads into the bounded buffer
	 * and increment the count. The count is the number of items in the buffer.
	 * 
	 */
	
	/*
	 * REFERENCES - 
	 * 
	 * 1) The below method has been used from LAB 4.5 ExtrinsicBoundedBuffer.java file
	 * 
	 * 2) Understanding how the bounded buffer works - 
	 * Dartmouth.edu. (2016). [online] 
	 * Available at: https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html [Accessed 8 Mar. 2021]. 
	 *  
	 */

	private void putStorageThread(Thread thread) throws InterruptedException { 
		boundedStorageLock.lock();
		try {
			while (countStorage == capacityStorageBuffer) {           //If the buffer is full, then make the threads (calling this method) wait
				notFullStorage.await(); 	
			}
			bufferStorage[inStorage] = thread;                        //Insert the thread into the buffer if buffer has space
				inStorage = (inStorage + 1) % capacityStorageBuffer; 
				countStorage++;											//Increment the counter in order to signify that 1 thread has been added to the buffer			
				notEmptyStorage.signal();								//Signal that there is space in the buffer
			}
			finally {
				boundedStorageLock.unlock();
			}
	}
	
	
	/*
	 * The below method is used to fetch the storage threads from the bounded buffer
	 * and decrease the count. The count is the number of items in the buffer.
	 * 
	 */
	
	/*
	 * REFERENCES - 
	 * 
	 * 1) The below method has been used from LAB 4.5 ExtrinsicBoundedBuffer.java file
	 * 
	 * 2) Understanding how the bounded buffer works - 
	 * Dartmouth.edu. (2016). [online] 
	 * Available at: https://www.cs.dartmouth.edu/~scot/cs10/lectures/27/27.html [Accessed 8 Mar. 2021]. 
	 *  
	 */
	
	private Thread takeStorageThread() throws InterruptedException { 
		Thread thread = new Thread(); 
		boundedStorageLock.lock();
		try {		
			while (countStorage == 0) {								//If the buffer is empty, then wait since no threads are present in the buffer
				notEmptyStorage.await();  
			}
			thread = bufferStorage[outStorage]; 					//Fetch the thread from the buffer if buffer is not empty
			outStorage = (outStorage + 1) % capacityStorageBuffer; 
			countStorage--;
			notFullStorage.signal();									//Decrement the counter in order to signify that 1 thread has been fetched from the buffer
		}
		finally {
			boundedStorageLock.unlock();
		}
		return thread; 
	}
	
	

	@Override
	public void queueStorageThread() {
		//Your code here

		jobDispatcherLock.lock();
		try {
		//System.out.println("The current thread - " + Thread.currentThread().getName() + " their state is " + Thread.currentThread().getState());
		
		//System.out.println("THE VALUE OF numStorage IS " + numStorage);
		
		try {
			putStorageThread(Thread.currentThread());                                      //Inserting the current thread that called queueStorageThread() into the buffer and incrementing the counter by 1. The counter also
																						   //keeps track of how many times queueStorageThread() has been called
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(Arrays.toString(bufferStorage));
//		
//		System.out.println();
//		
//		
//		System.out.println("NOW EXECUTING COMPARE() FOR STORAGE");
//			System.out.println();
			
			//if(checker == false) {
		
		
		
		
		/*
		 * We now call the private method compare(), in order to check if we got the right set of compute and storage threads
		 * so we can signal.
		 * 
		 * If the checker is false after the execution of compare() is completed, then we put the storage thread in WAITING state
		 * Else, we reset the checker variable to false.
		 */
		
				compare();
				if(checker == false) {
					//System.out.println("storagee going into waiting");
					try {
						storageLock.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			else {
				checker=false;
			}
				
				
 
//				System.out.println();
//				System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{");
//				//System.out.println("THE NUMBER OF COMPUTE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(computeLock));
//				System.out.println("THE NUMBER OF STORAGE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(storageLock));
//				System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{");
//				System.out.println();
//				
			
			} 
		finally {
				// TODO Auto-generated catch block
				jobDispatcherLock.unlock();
			}
			
			
		
		
		
//		System.out.println("The current thread - " + Thread.currentThread().getName() + " their state now now is " + Thread.currentThread().getState());
//		System.out.println();
	}
	

	private void compare() {
		
		jobDispatcherLock.lock();
		try {
			
			
//		System.out.println("LETS COMPARE COMPUTE N STORAGE THREADS");
		
		//System.out.println("THE VALUE OF computeArray.get(computeArrayCounter) is " + computeArray.get(computeArrayCounter));
		//System.out.println("THE VALUE OF storageArray.get(storageArrayCounter) is " + storageArray.get(storageArrayCounter));
//		System.out.println();
//		
//		System.out.println("THE COMPUTE ARRAY IS = " + computeArray);
//		System.out.println("THE STORAGE ARRAY IS = " + storageArray);
		
		
//		System.out.println();
//		System.out.println("+++++++++++++++++++++++++++++++++++++++");
//		System.out.println("NO OF COMPUTE THREADS = " + countCompute);
//		System.out.println("TNO OF STORAGE THREADS = " + countStorage);
//		System.out.println("+++++++++++++++++++++++++++++++++++++++");
//		System.out.println();
//		
//		
//		System.out.println();
//		System.out.println("*********************************************");
//		System.out.println("THE NUMBER OF COMPUTE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(computeLock));
//		System.out.println("THE NUMBER OF STORAGE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(storageLock));
//		System.out.println("*********************************************");
//		System.out.println();
		
//		if(storageArray.get(storageArrayCounter) == 0) {
//			System.out.println();
//			
//			try {
//				storageLock.await();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		

		/*
		 * We first create 2 copies, 1 for the array that holds the number of compute threads required for a job and 1 for
		 * the array that holds the number of storage threads required for a job.
		 * 
		 * This is done so that we can remove elements from the original compute and storage arraylists without having to keep changing
		 * the indexes of certain elements
		 */
			
			
			
			
			/*
			 * REFERENCES - 
			 * 
			 * 1) To remove from an array by making a copy - 
			 * Removing elements on a List while iterating through it (2014). Removing elements on a List while iterating through it. [online] Code Review Stack Exchange. 
			 * Available at: https://codereview.stackexchange.com/questions/64011/removing-elements-on-a-list-while-iterating-through-it [Accessed 11 Mar. 2021].
			 */
			
			
			
			
		
		
		ArrayList<Integer> computeCopy = new ArrayList<Integer>(computeArray);
//		System.out.println("THE computeCopy VALUE IS " + computeCopy);
		
		ArrayList<Integer> storageCopy = new ArrayList<Integer>(storageArray);
//		System.out.println("THE storageCopy VALUE IS " + storageCopy);
		
		
		//This variable keeps track of the number of elements removed from the storage and compute arraylists
		int countCopies = 0;
		
		/*
		 * This loop is used to loop over the compute and storage arraylists
		 * 
		 * The reason k<computeCopy.size() and not k<computeCopy.size()+storageCopy.size() is because, the compute and storage lists
		 * are of same size so we can use the size of the compute arraylist copy
		 */
		for(int k = 0; k<computeCopy.size(); k++) {
			
			/*
			 * Checking if the current element is 0 (specifyJob(0,0))
			 * 
			 * If it is, then we remove 0 from the compute and storage arraylists, and increment
			 * the countCopies counter by 1. This is to signify that we have removed an element from
			 * the compute and storage arraylists
			 */
			
			if( (computeCopy.get(k) == 0) && (storageCopy.get(k) == 0)) {
				
//				System.out.println();
//				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//				System.out.println("THE 0");
//				System.out.println();
				
//				System.out.println("REMOVING THE 0 FROM computeArray ");
				
				computeArray.remove(k);              //Remove 0 from the arraylist of compute threads
				
//				System.out.println("REMOVING THE 0 FROM storageArray ");
				
				storageArray.remove(k);           //Remove 0 from the arraylist of storage threads
				
//				System.out.println();
				
				countCopies++;                   //Increment the countCopies counter by 1 to signify that an element has been removed from the arraylists
				checker = false;				//Set the checker variable to false, this is done so that we can put the threads in WAITING after executing the compare() method
			}
			
			
			
			/*
			 * Checking if the current element in the compute arraylist is smaller than or equal to the counter that keeps track of the number of
			 * compute threads in the bounded buffer. The same is checked for storage
			 * 
			 * Only if the current elements in the storage and compute arraylists copies are smaller than or equal to the countStorage and countCompute respectively,
			 * then only we can proceed.
			 * 
			 * This is because, we want to signal only when the right number of storage and compute threads have appeared for the job
			 * 
			 * If this condition is not satisfied, the the checker remains false and the threads are put in WAITING
			 */
			
			
			
			else if((countCompute>=computeCopy.get(k)) && (countStorage >= storageCopy.get(k))) {
				
				int index = k-countCopies;     		//This variable is just to get the index value of the element present at (k - the number of elements removed from the list)   
				checker=true;											//Setting the checker to true, so that the threads can be signalled and not be put in WAITING state
				
				for(int i = 0; i<computeCopy.get(k); i++) {							//For loop to loop till we reach a point where the number of threads are not enough to signal for the job. We loop based on the number of compute threads present in that index of the arraylist
					
//					System.out.println("ENTERING FOR LOOP OF COMPUTE");
					try {
						takeComputeThread();											//Fetch the thread from the bounded buffer and decrement the counter by 1
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					System.out.println("SIGNALLING THE COMPUTE THREADS");
					
					computeLock.signal();                                                 //Signal the compute threads to signify that it is available for the job
				}  
				

//				System.out.println();
//				System.out.println("COMPUTE BEFORE REMOVING" + computeArray);
				
				
				
				
				
				/*
				 * REFERENCES - 
				 * 
				 * 1) To remove from an array by making a copy - 
				 * Removing elements on a List while iterating through it (2014). Removing elements on a List while iterating through it. [online] Code Review Stack Exchange. 
				 * Available at: https://codereview.stackexchange.com/questions/64011/removing-elements-on-a-list-while-iterating-through-it [Accessed 11 Mar. 2021].
				 */
				
				
				
				
						computeArray.remove(index);                                             //As soon as we signal the number of compute threads required for the job, we remove current element from the original compute arraylist
						
//						System.out.println("COMPUTE AFTER REMOVING" + computeArray);
//						System.out.println();

				
				for(int j = 0; j<storageCopy.get(k); j++) {                             //For loop to loop till we reach a point where the number of threads are not enough to signal for the job. We loop based on the number of storage threads present in that index of the arraylist
					
					//System.out.println("ENTERING FOR LOOP OF STORAGE");
					
					try {
						takeStorageThread();                                             //Fetch the thread from the bounded buffer and decrement the counter by 1
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					System.out.println("SIGNALLING THE STORAGE THREADS");
					
					storageLock.signal();                                                //Signal the storage threads to signify that it is available for the job
				}
				
				
				
//				System.out.println();
//				System.out.println("STORAGE BEFORE REMOVING" + storageArray);
				
				storageArray.remove(index);                                                  //As soon as we signal the number of storage threads required for the job, we remove current element from the original storage arraylist
				
				countCopies++;                                                                //Increment the countCopies counter by 1
				
//				System.out.println("STORAGE AFTER REMOVING" + storageArray);
//				System.out.println();
				
				
//				System.out.println();
//				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//				System.out.println("THE NUMBER OF COMPUTE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(computeLock));
//				System.out.println("THE NUMBER OF STORAGE THREADS IN WAITING = " + jobDispatcherLock.getWaitQueueLength(storageLock));
//				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//				System.out.println();

		} 

		}
		}
			finally {
			jobDispatcherLock.unlock();
			}
	}
	

	
			
			
//	class Worker extends Thread{
//			
//	}
			
			
	

}
