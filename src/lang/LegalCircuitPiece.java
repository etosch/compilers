package lang;
import java.util.List;
import java.util.LinkedList;
import java.awt.image.BufferedImage;

public abstract class LegalCircuitPiece{
    protected BufferedImage icon;
    protected LegalCircuitPiece[] inputs;
    protected LegalCircuitPiece[] outputs;
    protected int outputCurrent;
    /**
       @return boolean indicating whether there is only one source
       Called whenever a new Source is added.
    */
    public int  ensureSingleSource(){
	LinkedList<LegalCircuitPiece> components = order(new LinkedList(), this);
	//comment this out later
	System.out.println("****************");
	for (Object o: components)
	    System.out.println(o.getClass().getName());
	System.out.println("****************");
	//comment above out later
	List sources = new LinkedList<Source>();
	for (LegalCircuitPiece lcp : components)
	    if (lcp.getClass().getSuperclass()==Source.class)
		sources.add(lcp);
	if (sources.size()==0)
	    return 0;
	else if (sources.size()==1)
	    return 1;
	else return 2;
    }
    
    public LinkedList<LegalCircuitPiece> order(LinkedList<LegalCircuitPiece> ll
					       , LegalCircuitPiece currentPiece){
	if (!ll.contains(currentPiece)){
	    ll.add(currentPiece);
	    if (currentPiece.inputs!=null)
		for (LegalCircuitPiece lcp : currentPiece.inputs)
		    if (lcp!=null)
			ll=order(ll, lcp);
	    if (currentPiece.outputs!=null)
		for (LegalCircuitPiece lcp : currentPiece.outputs)
		    if (lcp!=null)
			ll=order(ll, lcp);
	}
	return ll;
    }

    /**
       @param from
       @param to
       Components connect from other components or from batteries to
       other components.
       Components can be Gates, Resistors, Capacitors, Output, etc.
       Some components may be connected to more than one thing.
       @return boolean indicating whether the component has successfully connected.
     */
    public abstract boolean connect(LegalCircuitPiece from, LegalCircuitPiece to);


    public abstract boolean addInput(LegalCircuitPiece lcp);

    public abstract boolean addOutput(LegalCircuitPiece lcp);
}