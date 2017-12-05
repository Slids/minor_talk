import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

class WrappedInt {
    private AtomicInteger val;

    WrappedInt() {
	val = new AtomicInteger(0);
    }

    public int value() {
	return val.get();
    }
    public void addOne() {
	int curr = val.get();
	if(!val.compareAndSet(curr, curr + 1)) {
	    curr = val.get();
	}
	System.out.println("Out from addOne is: " + val);
    }
    
    public void addTwo() {
	int curr = val.get();
	if(!val.compareAndSet(curr, curr + 2)) {
	    curr = val.get();
	}
	System.out.println("Out from addTwo is: " + val);
    }
}

class MultiThreadCas {
    public static void main(String args[]) {
	int numRuns = 0;
	try {
	    numRuns = new Integer(args[0]);
	}
	catch (Exception ex) { }

	int numTragicErrors = 0;
	for(int i = 0; i < numRuns; i++) {
	    WrappedInt myInt = new WrappedInt();

	    Thread t1 = new Thread( () -> {myInt.addOne();} );
	    Thread t2 = new Thread( () -> {myInt.addTwo();} );
	    t1.start();
	    t2.start();
	    try {
		t1.join();
		t2.join();
	    }
	    catch (Exception ex) {}
	    
	    if(myInt.value() != 3){
		numTragicErrors++;
	    }
	}
	
	System.out.println("Total times wrong final value: " + numTragicErrors);

	if(numRuns != 0) {
	    System.out.println("Error rate: " +(numTragicErrors*100/numRuns));
	}
    }
}
