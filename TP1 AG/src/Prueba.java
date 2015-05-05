import java.util.ArrayList;
import java.util.Random;

public class Prueba {
	
	static String[][] cromosomas = new String[10][30];
	static int[] cromosomasDecimal = new int[10];
	static float[] valoresEnFuncion = new float[10];
    static float[] valoresFitness = new float[10];
    static float sumatoriaObj=0;
    static float sumatoriaFit=0;
    static float maxObj=0;
    static float maxFit=0;
	static float pc = (float) 0.75;
	static float pm = (float) 0.05;
	static   float promeObj,promeFit=0;
	static int[] ruleta = new int[100];
	static ArrayList<String> cro = new ArrayList<String>();


	public static void main(String[] args)
	{
		Random rnd = new Random();
		
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
		}
		
        for(int i=0;i<10;i++)
        {
        	sumatoriaObj = sumatoriaObj + valoresEnFuncion[i];
        }
        
        /*Guardo el arreglo de fittnes*/
        for(int i=0;i<10;i++)
		{
        	valoresFitness[i] = valoresEnFuncion[i]/sumatoriaObj;
        	sumatoriaFit += valoresFitness[i];
        }
        
        for(int i=0;i<10;i++)
        {
        	if(maxFit<valoresFitness[i])
        	{
        		maxFit=valoresFitness[i];
        	};
        	if(maxObj<valoresEnFuncion[i])
        	{
        		maxObj=valoresEnFuncion[i];
        	};
        }
        
        promeObj=sumatoriaObj/10;
        promeFit=sumatoriaFit/10;
                
        /*muestra*/
		System.out.println("POBLACION (BINARIO)\t\tDECIMAL\tFUNCION OBJ\tFITNESS\tPORCENTAJE");
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<30;j++)
			{
				System.out.print(cromosomas[i][j]);
			}
			System.out.println(" "+cromosomasDecimal[i]+" "+valoresEnFuncion[i]+" "+(valoresFitness[i]/*fitness*/)+" "+(valoresFitness[i]/*fitness*/)*100);
		
		}
		System.out.println(" ");
		System.out.println("Sumatoria del Fittnes: " + Math.round(sumatoriaFit));
        System.out.println("Sumatoria de la Objetiva: " + (sumatoriaObj));
        System.out.println("Promedio del Fitness: " + (promeFit));
        System.out.println("Promedio de la Objetiva: " + (promeObj));
        System.out.println("Maximo del Fitness: " + (maxFit));
        System.out.println("Sumatoria de la Objetiva: " + (maxObj));
        
	}

	private static int dameDecimal(String cromos2) 
	{
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