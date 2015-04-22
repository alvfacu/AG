import java.util.ArrayList;
import java.util.Random;

public class Prueba {
	
	static String[][] cromosomas = new String[10][30];
	

	public static void main(String[] args)
	{
		Random rnd = new Random();
		ArrayList<String> cro = new ArrayList<String>();
		for(int i=0;i<10;i++)
		{
			String c = "";
			for(int j=0;j<30;j++)
			{
				cromosomas[i][j] = String.valueOf((int)(rnd.nextDouble()*2+0));
				c = c+cromosomas[i][j];
			}
			cro.add(c);
		}
		
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<30;j++)
			{
				System.out.print(cromosomas[i][j]);
			}
			System.out.println(" "+dameDecimal(cro.get(i).toString()));
		}
			
	}


	private static int dameDecimal(String cromos2) {
		
		String nroBinario = cromos2.toString();
		int num = Integer.parseInt(nroBinario,2);
		return num;
	}
}	
