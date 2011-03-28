package user.target;

public abstract class LegalCircuitPiece{
    public LegalCircuitPiece[] inputs;
    public LegalCircuitPiece[] outputs;

    /**
       Called whenever a new Source is added.
    */
    public boolean ensureSingleSource(){
	LinkedList<LegalCircuitPiece> components = order(new List(), this);
	List sources = new List<Source>();
	for (LegalCircuitPiece lcp : components)
	    if (lcp.getSuperclass()==Source.class)
		sources.add(l);
	return sources.length==1;
    }
    
    public LinkedList<LegalCircuitPiece> order(LinkedList<LegalCircuitPiece> ll, LegalCircuitPiece currentPiece){
	if (currentPiece.inputs!=null)
	    for (LegalCircuitPiece lcp : currentPiece.inputs)
		if (!ll.contains(lcp)){
		    ll.add(lcp);
		    order(ll, lcp);
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
    public boolean connect(LegalCircuitPiece from, LegalCircuitPiece to);

}