import java.sql.Time;

public class Substring {

	public static void main(String[] args) {


		Time t = new Time(1610279351979L);
		
		System.out.println(t.toString().substring(0, 5));

	}

}
