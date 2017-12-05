class WrappedInt {
    private int val;

    WrappedInt() {
	val = 0;
    }

    public int value() {
	return val;
    }
    public void addOne() {
	val += 1;
	System.out.println("Out from addOne is: " + val);
    }
    
    public void addTwo() {
	val += 2;
	System.out.println("Out from addTwo is: " + val);
    }
}

class BasicAdd {
    public static void main(String args[]) {
	int numRuns = 0;
	try {
	    numRuns = new Integer(args[0]);
	}
	catch (Exception ex) { }

	int numTragicErrors = 0;
	for(int i = 0; i < numRuns; i++) {
	    WrappedInt myInt = new WrappedInt();

	    myInt.addOne();
	    myInt.addTwo();

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
