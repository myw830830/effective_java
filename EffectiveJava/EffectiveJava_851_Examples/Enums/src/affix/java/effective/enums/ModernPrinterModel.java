package affix.java.effective.enums;

public class ModernPrinterModel {
	
	public enum Mode {ON, OFF, STANDBY}
	
	public enum ColorChoice {BLACK, RED, GREEN, BLUE} // enum evolution, YELLOW, BROWN, PURPLE	
	
	private final String modelName;
	private Mode printerMode;
	private ColorChoice printingColor;
	
	public ModernPrinterModel(String name, int year){
		modelName = name;
		printerMode = Mode.OFF;
		printingColor = ColorChoice.BLACK;
	}
	
	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * @param set new printer mode choice must be within enum range
	 */
	public void setPrinterMode(Mode choice){
		printerMode = choice;
	}
	
	/**
	 * @return printer mode
	 */
	public Mode getPrintingMode(){
		return printerMode;
	}
	
	/**
	 * @param set new printing color choice must be within enum range
	 */
	public void setPrintingColor(ColorChoice choice){
		printingColor = choice;
	}
	
	/**
	 * @return printing color
	 */
	public ColorChoice getPrintingColor(){
		return printingColor;
	}
	
}
