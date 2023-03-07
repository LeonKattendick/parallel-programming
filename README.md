# parallel-programming

What are the necessary conditions for deadlocks?

A: There are four conditions which are needed to be met in order to create a deadlock. (e.g blocked threads)
    1.) Mutual Exclusion: n2...n threads might want to use a resource simultaneously but the resource is held in non-shareable mode by a thread
    2.) Hold and Wait: A process is holding a resource and is waiting to acquire another resource which is currently held by another process
    3.) No preemption: resources can not be forcefully taken away from a process while using it
    4.) Circular wait: A circular chain of at least two processes has to exist where each process is waiting for a resource held by the next process in the chain.

Why does the initial solution lead to a deadlock?

A: Resources are exclusively used by a thread e.g forks and the threads are waiting to acquire another fork which might 
be held by another thread. (leftfork - rightfork). Forks are not forcefully taken away to prevent deadlocks and the
requirement for the Circular wait is also fulfilled. All these four condition met lead to a deadlock.

Does this strategy resolve the deadlock and why?

A:Ultimately n0 and n1 philosopher try to pick up the same fork at the start. Because of the synchronized statement the fork is held in a
non-shareable mode (locked) by one of the threads. The other one which tries to access the same resource waits till the fork is released again. 
There is always a free fork available e.g sample picture. This strategy resolved the circular wait condition. 

Measure the total time spent in waiting for forks and compare it to the total runtime. Interpret
the measurement - Was the result expected?

A: With 8 threads running the totalWaitedTime is double the runTime. The dependency of the threads combined with the thinking and eatingTime was expected.

Can you think of other techniques for deadlock prevention?

A: Another technique would be just to force just one philosopher to take a fork from a different side. 