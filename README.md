# Job-Dispatcher-OS

This class will block Storage and Compute threads until there are enough of them to perform a specified job.
Once the right combination of threads is available for a job, JobDipatcher will stop blocking the threads needed for the job and allow them to proceed.
This class implements the Dispatcher interface. It contains three methods. Their behaviours are specified in the file Dispatcher.java.
