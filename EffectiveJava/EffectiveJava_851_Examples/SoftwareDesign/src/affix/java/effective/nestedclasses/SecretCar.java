package affix.java.effective.nestedclasses;

public class SecretCar {

	private final String type;
	private Plate thePlate;
	
	private static Plate[] plates = new Plate[4];
	
	static{
		for(int i=0; i<plates.length; i++){
			plates[i] = new Plate("XYZ 12"+i);
		}
	}
	
	public SecretCar(String type){
		this.type = type;
		
		for(int i=0; i<plates.length; i++){			
			if(!plates[i].isBusy()){
				thePlate = plates[i];
				plates[i].busy = true;
				break;
			}
		}
		if(thePlate == null){
			thePlate = new Plate("XYZ 999");
			thePlate.busy = true;
		}
	}
	
	public static class Plate{
		private final String text;
		private boolean busy;
		
		public Plate(String input){
			text = input;
		}
		
		public String getText(){
			return text;
		}
		public boolean isBusy(){
			return busy;
		}
	}
	
	public static Plate[] getPlates(){
		return plates;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("SecretCar: ");
		sb.append(type);
		sb.append(" ");
		sb.append(thePlate.getText());
		
		return sb.toString();
	}
}
