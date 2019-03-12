package model;

public interface Subject {
	
	public void registerObserver(Observer obv);
	
	public void removeObserver(Observer obv);
	
	public void notifyObserver();

}
