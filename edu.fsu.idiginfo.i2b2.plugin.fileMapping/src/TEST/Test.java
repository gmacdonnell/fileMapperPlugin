package TEST;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String test ="rash on facex one week; Followup: UNSPECIFIED ACQUIRED HYPOTHYROIDISM (244.9); Followup: OTHER MALAISE AND FATIGUE (780.79)";
		String pattern = "[\\D]+";
		int index = test.indexOf(",");
		boolean match = test.matches(pattern);
		System.out.println(match);

	}

}
