import java.util.ArrayList;
import java.util.Random;

public class Prueba {
	
	static String[][] cromosomas = new String[10][30];
	static int[] cromosomasDecimal = new int[10];
	static float[] valoresEnFuncion = new float[10];
	static float pc = (float) 0.75;
	static float pm = (float) 0.05;
		
	public static void main(String[] args)
	{
		Random rnd = new Random();
		ArrayList<String> cro = new ArrayList<String>();
		float sumatoria = 0;
		float suma = 0;
		
		for(int i=0;i<10;i++)
		{
			String c = "";
			for(int j=0;j<30;j++)
			{
				cromosomas[i][j] = String.valueOf((int)(rnd.nextDouble()*2+0));
				c = c+cromosomas[i][j];
			}
			cro.add(c);
			cromosomasDecimal[i] = dameDecimal(c);
			valoresEnFuncion[i] = funcion(dameDecimal(cro.get(i).toString()));
			sumatoria += valoresEnFuncion[i];
		}
		
		System.out.println("Sumatoria: "+sumatoria);
		System.out.println("POBLACION (BINARIO)\t\tDECIMAL\tFUNCION OBJ\tFITNESS\tPORCENTAJE");
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<30;j++)
			{
				System.out.print(cromosomas[i][j]);
			}
			System.out.println(" "+cromosomasDecimal[i]+" "+valoresEnFuncion[i]+" "+(valoresEnFuncion[i]/sumatoria/*fitness*/)+" "+(valoresEnFuncion[i]/sumatoria/*fitness*/)*100);
			suma += valoresEnFuncion[i]/sumatoria;
		}
		
		System.out.println(Math.round(suma));
			
	}


	private static int dameDecimal(String cromos2) {
		
		String nroBinario = cromos2.toString();
		int num = Integer.parseInt(nroBinario,2);
		return num;
	}
	
	private static float funcion(int nro)
	{
		int coef = (int)Math.pow(2,30) - 1;
		float rdo = 0;
		float div = (float)nro/(float)coef;
		rdo = (float)Math.pow(div, 2);		
		return rdo;
		
	}
}	
