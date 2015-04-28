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
			System.out.print(" "+dameDecimal(cro.get(i).toString()));
			System.out.println(" "+funcion(dameDecimal(cro.get(i).toString())));
		}
			
	}


	private static int dameDecimal(String cromos2) {
		
		String nroBinario = cromos2.toString();
		int num = Integer.parseInt(nroBinario,2);
		return num;
	}
	
	private static double funcion(int nro)
	{
		int coef = (int)Math.pow(2,30) - 1;
		double rdo = 0;
		double div = (double)nro/(double)coef;
		rdo = (double)Math.pow(div, 2);		
		return rdo;
		
	}
}	
