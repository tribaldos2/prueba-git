package prprpr;
import java.util.*;

public class holi {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		/*char ja;
		System.out.println("Pon");
		ja=sc.nextLine().charAt(0);
		if (Character.isLetter(ja)) {
			System.out.println("Si");
		} else {
			System.out.println("No");
		}*/
		
		String frase = "gabriel@francis@tribaldos";
		String[] partes = frase.split("@");
		
		for (int i = 0; i < partes.length; i++) {
			partes[i] = Character.toUpperCase(partes[i].charAt(0)) + partes[i].substring(1);
			System.out.println(partes[i]);
		}
	}
}
