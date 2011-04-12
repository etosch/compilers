package lang;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Gate extends Component{

    //changed to ArrayLists so that I could have indirect references
    protected static final int lowerThreshhold = 3;
    protected static final int upperThreshhold = 6;

    public Gate(){
	inputs=new LegalCircuitPiece[2];
	outputs=new LegalCircuitPiece[1];
    }

    public boolean addInput(LegalCircuitPiece lcp){
	if (inputs[0]==null)
	    this.inputs[0]=lcp;
	else if (inputs[1]==null)
	    this.inputs[1]=lcp;
	else return false;
	outputCurrent=calculateOutputValue();
	return true;
    }

    public boolean addOutput(LegalCircuitPiece lcp){
	if (outputs[0]==null){
	    outputs[0]=lcp;
	    return true;
	}else return false;
    }

    public boolean semanticOutput(){
	return (outputCurrent>=Gate.upperThreshhold)?true:false;
    }

    public abstract int calculateOutputValue();

    public void setIcon(String filelocation){
	try{
	    icon = ImageIO.read(new File(filelocation));
	}catch(IOException io){
	    System.err.println("Cannot find "+filelocation+" in "
			       +new File(".").getAbsolutePath());
	}
    }
}