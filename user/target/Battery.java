package user.target;

public class Battery extends Source{

    public Component connection1;
    public Component connection2;

    public Battery(int voltage){
	this.setVoltage(v);
    }

    public int getVoltage(){
	super().getVoltage();
    }

    public boolean connect(Component c){
	//throw BatteryConnectedException?
	if (connection1==null) {
	    connection1=c;
	    return true;
	}else if (connection2=null){
	    connection2=c;
	    return true;
	}else return false;
    }
}