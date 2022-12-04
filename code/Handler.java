import java.util.Observable;

public abstract class Handler extends Observable {
	public static final int TSP = 1;
	public static final int TSPPRO = 2;
	public static final int KCLUSTER = 3;
	public static final int USERCONNECT = 4;
	
	//next element in chain or responsibility
	protected Handler nextHandler;
	
	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public abstract void handleRequest(int type);
	
}
