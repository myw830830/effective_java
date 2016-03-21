package affix.java.effective.annotations;

@Fix("Add complete JavaDoc")
public class AnnotationFix {

    @SuppressWarnings("unused")
	private float val;
	
	@Fix(value = "Define a default value for attribute val")
	public AnnotationFix(){
	}
	
	@Fix(value = "Add JUnit test for this method!")
	public void setVal(float val){
		this.val = val;
	}	
	
	@Override
	public String toString() {
		return "Annotation Fix";
	}
		
}
