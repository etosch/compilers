package lang;

public class Battery extends Source{

    private Component connection1;
    private Component connection2;

    public Battery(int voltage){
	outputCurrent = voltage;
    }

    public int getVoltage(){
	return outputCurrent;
    }

    public boolean addOutput(Component c){
	if (connection1==null) {
	    connection1=c;
	    return true;
	}else if (connection2==null){
	    connection2=c;
	    return true;
	}else return false;
    }

    public boolean addOutput(LegalCircuitPiece lcp){
	return (lcp instanceof Component)?addOutput((Component)lcp):false;
    }
	
    public boolean connect(Component c){
	return addOutput(c);
    }

    public boolean connect(LegalCircuitPiece lcp){
	//throw BatteryConnectedException?
	return (lcp instanceof Component)?connect((Component)lcp):false;
    }

    public boolean connect(LegalCircuitPiece from, LegalCircuitPiece to){
	return (from.getClass()==Component.class)?
	    connect((Component)from): false;
    }

}