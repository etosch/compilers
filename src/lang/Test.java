package lang;

public class Test{

    public static Battery testCreateBattery(){
	Battery b = new Battery(9);
	System.out.println("voltage: "+b.outputCurrent);
	return b;
    }

    public static Not testSingleSource(Battery b){
	Not n = new Not();
	Battery b2 = new Battery(9);
	boolean connect = true;
	try{
	    connect=n.connect(b);
	}catch(BatteryException e){
	    connect=false;
	}finally{
	    System.out.println("connect?(true) "+connect);
	}
	try{
	    connect=n.connect(b2);
	}catch(BatteryException e){
	    connect=false;
	}finally{
	    System.out.println("connect?(false) "+connect);
	}
	System.out.println("Not Truth Value(false): "+n.semanticOutput());
	return n;
    }

    public static void main(String args[]){
	Battery b = testCreateBattery();
	Not n = testSingleSource(b);
    }
}