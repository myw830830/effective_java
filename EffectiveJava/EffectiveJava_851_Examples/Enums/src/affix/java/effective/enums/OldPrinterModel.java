package affix.java.effective.enums;

public class OldPrinterModel {
	
	public final static int BLACK = 1;
	public final static int RED = 2;

	private final String modelName;
	private boolean printerOn;
	private int printingColor;
	
	public OldPrinterModel(String name){
		modelName = name;
		printerOn = false;
		printingColor = BLACK;
	}
	
	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param the new printer mode
	 */
	public void setPrinterOn(boolean status) {
		printerOn = status;
	}

	/**
	 * @return the current printer mode
	 */
	public boolean isPrinterOn() {
		return printerOn;
	}
	
	/**
	 * @param set new printing color
	 */
	public void setPrintingColor(int color){
		printingColor = color;
	}
	
	/**
	 * @return printing color
	 */
	public int getPrintingColor(){
		return printingColor;
	}
	
}
