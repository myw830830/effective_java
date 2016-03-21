package affix.java.effective.nestedclasses;

public class TestSecretCar {

	public static void main(String[] args) {
		SecretCar.Plate[] availablePlates = SecretCar.getPlates();
		for(int i=0; i<availablePlates.length; i++){
			System.out.println(availablePlates[i].getText()
					+ " busy: "+availablePlates[i].isBusy());
		}
		
		SecretCar[] scs = new SecretCar[3];
		scs[0] = new SecretCar("Volvo V70");
		scs[1] = new SecretCar("Toyota Prius");
		scs[2] = new SecretCar("SAAB 9-3");
		
		for(int i=0; i<scs.length; i++){
			System.out.println(scs[i]);
		}
		
		for(int i=0; i<availablePlates.length; i++){
			System.out.println(availablePlates[i].getText()
					+ " busy: "+availablePlates[i].isBusy());
		}
	}

}
