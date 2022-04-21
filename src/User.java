import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class User implements DesignService{
	
	//HashMap<String, HashMap<String, String>> user_hash = new HashMap<String, HashMap<String,String>>();
	
	@SuppressWarnings("unchecked")
    public static HashMap<String, HashMap<String, String>> read() {
		HashMap<String, HashMap<String, String>> hmap = new HashMap<String, HashMap<String,String>>();

        try (FileInputStream fis = new FileInputStream("./hashmap.ser")) {

            try (ObjectInputStream ois = new ObjectInputStream(fis)) {

                hmap=(HashMap<String, HashMap<String, String>>)ois.readObject();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hmap;
    }
	
	@Override
	public String createDesign(AuthContext ctx, String designContent) {
		
		HashMap<String, HashMap<String, String>> user_hash = read();
		
		String userid = ctx.userId;
		String designId="";
		
		
		HashMap<String, String> user_designs= user_hash.get(userid);
		
		if (user_designs == null) {
			user_designs = new HashMap<String, String>();
			designId =userid + "-1";
		}
		else {
			
			designId= userid +"-"+ (user_designs.size()+1);
		}
		
		
		user_designs.put(designId, designContent);
		user_hash.put(userid, user_designs);
		
		try (FileOutputStream fos = new FileOutputStream("./hashmap.ser")) {

            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(user_hash);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return designId;
	}
	

	@Override
	public String getDesign(AuthContext ctx, String designId) {
		// TODO Auto-generated method stub
		
		return "";
	}

}
