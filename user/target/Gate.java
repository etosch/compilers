package user.target;

public abstract class Gate extends Component{

    public LegalCircuitPiece output;


    public Gate(){
	super.inputs=new LegalCircuitPiece[2];
	super.outputs=new LegalCircuitPiece[1];
	outputs[0]=this.output;
    }

    public boolean addInput(LegalCircuitPiece lcp){
	if (inputs[0]==null)
	    this.inputs[0]=lcp;
	else if (inputs[1]==null)
	    this.inputs[1]=lcp;
	else return false;
	return true;
    }

    public boolean addOutput(LegalCircuitPiece lcp){
	this.output=lcp;
    }

    public boolean connect(LegalCircuitPiece from, LegalCircuitPiece to){
	
    }


}