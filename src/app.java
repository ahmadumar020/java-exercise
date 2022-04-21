
public class app {

	public static void main(String[] args) {
		
		User user1 = new User();
		AuthContext ctx = new AuthContext("rando");
		String designId = user1.createDesign(ctx, "first-design-of-art");

		System.out.println(designId);
	}

}
