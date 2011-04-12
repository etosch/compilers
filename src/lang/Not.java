package lang;

public class Not extends Gate{

    public Not(){
	super();
	inputs=new LegalCircuitPiece[1];
	setIcon("../Images/NOT_ANSI.svg");
    }

    public int calculateOutputValue() 
	throws AmbiguousCurrentException,NotFullyConnectedException{
	if (inputs[0]==null) throw new NotFullyConnectedException("Gate is not fully connected to the circuit.");
	if (inputs[0].outputCurrent <= Gate.lowerThreshhold)
	    return Gate.upperThreshhold;
	else if (inputs[0].outputCurrent >= Gate.upperThreshhold)
	    return Gate.lowerThreshhold;
	else throw new AmbiguousCurrentException("Current is between upper and lower threshholds of "
						 +Gate.upperThreshhold+" and "+Gate.lowerThreshhold);
    }

    public boolean addInput(LegalCircuitPiece lcp){
	if (inputs[0]==null){
	    inputs[0]=lcp;
	    outputCurrent=calculateOutputValue();
	    return true;
	}else return false;
    }

    public boolean connect(LegalCircuitPiece from, LegalCircuitPiece to) 
	throws BatteryException{
	boolean success = false;
	System.out.println(ensureSingleSource());
	switch(ensureSingleSource()){
	case 0: {
	    to.addInput(from);
	    from.addOutput(to);
	    success=true;
	    break;
	}
	case 1: break;
	case 2: throw new BatteryException("More than one battery connected");
	}
	return success;
    }

    public boolean connect(LegalCircuitPiece from){
	return this.connect(from, this);
    }
}
		     
	