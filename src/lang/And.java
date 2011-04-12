package lang;

public class And extends Gate{

    public And(){
	super();
	setIcon("../images/AND_ANSI.svg");
    }

    public int calculateOutputValue() 
	throws NotFullyConnectedException, AmbiguousCurrentException{
	if (inputs[0]!=null && inputs[1]!=null){
	    if (inputs[0].outputCurrent>=Gate.upperThreshhold &&
		inputs[1].outputCurrent<=Gate.lowerThreshhold)
		return Gate.upperThreshhold;
	    else if ((inputs[0]<Gate.upperThreshhold && inputs[0]>Gate.lowerThreshhold) ||
		     (inputs[1]<Gate.upperThreshhold && inputs[1]>Gate.lowerThreshhold))
		throw new AmbiguousCurrentException("One of the input currents is between upper and lower threshholds of "
						 +Gate.upperThreshhold+" and "+Gate.lowerThreshhold);
	    else return Gate.lowerThreshhold;
	} else throw new NotFullyConnectedException("Gate is not fully connected to the circuit.");
    }

    public boolean connect(LegalCircuitPiece from, LegalCircuitPiece to){
       	boolean success = false;
	switch(ensureSingleSource()){
	case 0: {
	    to.addInput(from);
	    from.addOutput(to);
	    outputCurrent = calculateOutputValue();
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