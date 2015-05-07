package geneticos;
import java.util.ArrayList;
import java.util.Random;

public class JugandoASerDioses {

	//Declaración de variables
	static int pc = 75;
	static int pm = 5;
	static float[] sumaObjetivaXpoblacion = new float[22];
	static float[] maxCromoXpoblacion = new float[22];
	static float[] promXpoblacion = new float[22];

	
	//Comienzo del programa
	public static void main(String[] args)
	{	 

		String[][] cromosomas = new String[10][30];
		int[] cromosomasDecimal = new int[10];
		float[] valoresEnFuncion = new float[10];
		float[] porcentajeFit = new float[10];
		float[] valoresFitness = new float[10];
		ArrayList<String> cro = new ArrayList<String>();
		float sumatoriaObj;
		float sumatoriaFit;
		float maxObj;
		float maxFit;
		float promeObj,promeFit;
		Random rnd = new Random();

		//Cargo la matriz cromosomas con numeros binarios
		//Lo guardo en un ArrayList de cromosomas
		//Pido el binario -> decimal y guardo 
		//Obtengo valores de la Funcion Objetiva

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
		}

		asignarValoresFuncion(cro,valoresEnFuncion);

		//Obtengo sumatoria de la Funcion Objetiva

		sumatoriaObj = sumatoriaObj(valoresEnFuncion);

		//Guardo el arreglo de fittnes y su Sumatoria

		sumatoriaFit = guardarFit(valoresEnFuncion, valoresFitness,sumatoriaObj);

		//Obtengo maximo y minimo de la objetiva y fittnes

		maxFit = obtenerMaxFit(valoresFitness);
		maxObj = obtenerMaxObj(valoresEnFuncion);

		obtenerPorcentajes(porcentajeFit, valoresFitness);

		promeObj=sumatoriaObj/10;
		promeFit=sumatoriaFit/10;

		//Muestro todo
		System.out.println("\t\tPoblación inicial\n");
		System.out.println("POBLACION (BINARIO)\t\tDECIMAL\tFUNCION OBJ\tFITNESS\tPORCENTAJE");
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<30;j++)
			{
				System.out.print(cromosomas[i][j]);
			}
			System.out.println(" "+cromosomasDecimal[i]+" "+valoresEnFuncion[i]+" "+valoresFitness[i]+" "+porcentajeFit[i]);

		}
		System.out.println("\n\tSumatoria del Fittnes:    " + Math.round(sumatoriaFit));
		System.out.println("\tSumatoria de la Objetiva: " + (sumatoriaObj));
		System.out.println("\tPromedio del Fitness:     " + (promeFit));
		System.out.println("\tPromedio de la Objetiva:  " + (promeObj));
		System.out.println("\tMáximo del Fitness:       " + (maxFit));
		System.out.println("\tMáximo de la Objetiva:    " + (maxObj));
		
		sumaObjetivaXpoblacion[0] = sumatoriaObj;
		maxCromoXpoblacion[0] = maxObj;
		promXpoblacion[0] = promeObj;
		
		for(int n=0, l=1;n<20;n++,l++)
		{
			if(n!=0)
			{
				System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("\nVamos a crear a partir de esta población de los mejores hijos, una nueva: ");
			}

			// asignarValoresFuncion(cro);
			ArrayList<String> nuevaPobla = new ArrayList<String>();
			ArrayList<String> cromoMutados = new ArrayList<String>(); 

			//Comenzamos ruleta
			int min=0,max=0;
			ArrayList<Integer> ruleta = new ArrayList<Integer>();
			for(int i=0;i<10;i++)
			{
				int trunca = Math.round(porcentajeFit[i]);
				if (trunca==0)
				{
					max=max+1;
				}
				else
				{
					max=max+trunca;
				}

				for (int j=min;j<(max);j++)
				{                
					ruleta.add(i);                           
				}
				min=max;
			}


			System.out.println("\nRuleta:");
			for(Integer nro : ruleta)
			{
				System.out.print(nro);
			}
			System.out.println("\nTamaño ruleta:"+ruleta.size());
			System.out.println(" ");

			String[] tiradas = new String[10];
			String[] probCross = new String[5];


			System.out.println("Tiradas:");
			for(int i=0;i<10;i++)
			{
				tiradas[i] = String.valueOf((int)(rnd.nextDouble()*ruleta.size()+0));
				System.out.print(tiradas[i]+" ");
			}

			System.out.println("\n\nPorcentaje de CrossOver de: ");
			for(int j=0;j<5;j++)
			{
				probCross[j] = String.valueOf((int)(rnd.nextDouble()*100+1));
				System.out.print(probCross[j]+" ");
			}
			System.out.println("\n");
			
			
			System.out.println("Se van a cruzar los pares de cromosomas que no superaron el Porcentaje de CrossOver: "+pc+".\n");
			int a=0,b=1;
			for(int j=0;j<5;j++)
			{  
				int numeroTirada1 = Integer.valueOf(tiradas[a]);
				int numeroTirada2 = Integer.valueOf(tiradas[b]);
				int nro1 = ruleta.get(numeroTirada1);
				int nro2 = ruleta.get(numeroTirada2); 

				String cromo1 = cro.get((nro1));
				String cromo2 = cro.get((nro2));


				if(Integer.valueOf(probCross[j])<=pc)
				{

					System.out.println("Con un porcentaje de CrossOver de: "+probCross[j]);
					System.out.println("En la ubicacion del arreglo de cromosomas: " + nro1 + " y " + nro2);
					System.out.println("Los cromosomas: "+cromo1+" y "+cromo2);

					int ubicacion = (int)(rnd.nextDouble()*30+0);


					String cromoParte11 = cromo1.substring(0,ubicacion);
					String cromoParte12 =cromo2.substring(0, ubicacion);

					String cromoParte21 = cromo1.substring(ubicacion, 30);
					String cromoParte22 = cromo2.substring(ubicacion,30);

					String cromoFinal1 = cromoParte11+cromoParte22 ;
					String cromoFinal2 = cromoParte12+cromoParte21 ;

					System.out.println("Los dos cromosomas se cortan en el punto "+ubicacion);
					System.out.println("Quedando finalmente "+cromoFinal1+" y "+cromoFinal2+"\n");
		
					nuevaPobla.add(cromoFinal1);
					nuevaPobla.add(cromoFinal2);
				}
				else
				{
					nuevaPobla.add(cromo1);
					nuevaPobla.add(cromo2);
				}
				a=a+2;
				b=b+2;
			}

			System.out.println("Entonces resultan estos hijos: ");
			for(String c: nuevaPobla)
			{
				System.out.println(c);
			}

			System.out.println("\nAhora vamos a ver cuáles de estos hijos puede llegar a mutar (Porcentaje de mutación: "+pm+")...");
			// Now, i´m going to create the mutation
			for(int i=0;i<10;i++)
			{
				int nroRandom = (int)(rnd.nextDouble()*100+1);
				System.out.println("Porcentaje de mutar del cromosoma hijo "+i+" : "+nroRandom );

				if(nroRandom<=pm)
				{
					int ubicacion = (int)(rnd.nextDouble()*30+0);
					String cromoMuta=nuevaPobla.get(i);
					char[] cromoMu = cromoMuta.toCharArray();
					
					System.out.println("\nEl cromosoma hijo "+i+" va a mutar en el gen ubicado en la posición: "+ubicacion);
					System.out.println("Antes:\n"+cromoMuta);
					
					if(cromoMuta.charAt(ubicacion) == '0')
					{
						cromoMu[ubicacion] = '1' ;
					}
					else
					{
						cromoMu[ubicacion]='0';
					}
					
					cromoMuta= new String(cromoMu);
					System.out.println("Después:\n"+cromoMuta+"\n");

					cromoMutados.add(cromoMuta);
				}

				else {cromoMutados.add(nuevaPobla.get(i));}
			}
			cro.clear();
			for(int i=0;i<10;i++)
			{
				cro.add(cromoMutados.get(i));
			}

			float[] valEnFunc = new float[10];
			float[] porcFit = new float[10];
			float[] valFit = new float[10];
			float sumaObj;
			float sumaFit;
			float maxO;
			float maxF;
			float promObj,promFit;

			asignarValoresFuncion(cro,valEnFunc);
			sumaObj = sumatoriaObj(valEnFunc);
			sumaFit = guardarFit(valEnFunc, valFit,sumaObj);
			maxO = obtenerMaxObj(valEnFunc);
			maxF = obtenerMaxFit(valFit);
			obtenerPorcentajes(porcFit, valFit);
			porcentajeFit = porcFit;
			promObj=sumaObj/10;
			promFit=sumaFit/10;

			System.out.println("\nFinalmente, la POBLACIÓN "+(n+1)+" RESULTANTE es: \n");

			System.out.println("POBLACION (BINARIO)\t\tDECIMAL\tFUNCION OBJ\tFITNESS\tPORCENTAJE");
			for(int i=0;i<10;i++)
			{
				System.out.println(cro.get(i)+" "+dameDecimal(cro.get(i))+" "+valEnFunc[i]+" "+valFit[i]+" "+porcentajeFit[i]);
			}

			System.out.println("\n\tSumatoria del Fittnes:    " + Math.round(sumaFit));
			System.out.println("\tSumatoria de la Objetiva: " + (sumaObj));
			System.out.println("\tPromedio del Fitness:     " + (promFit));
			System.out.println("\tPromedio de la Objetiva:  " + (promObj));
			System.out.println("\tMáximo del Fitness:       " + (maxF));
			System.out.println("\tMáximo de la Objetiva:    " + (maxO));

			sumaObjetivaXpoblacion[l] = sumaObj;
			maxCromoXpoblacion[l] = maxO;
			promXpoblacion[l] = promObj;

		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nPoblación"+"\t"+"Sumatoria F. Objetiva"+"\t"+"Max F.Objetiva"+"\t"+"Promedio F. Objetivo");
		for(int i=0;i<21;i++)
		{
			System.out.println(i+"\t\t"+sumaObjetivaXpoblacion[i]+"\t\t"+maxCromoXpoblacion[i]+"\t\t"+promXpoblacion[i]);
		}

	}

	private static void obtenerPorcentajes(float[] porcentajeFit,
			float[] valoresFitness) {
		for(int i=0;i<10;i++){

			porcentajeFit[i]=(valoresFitness[i])*100;
		}
	}

	private static float obtenerMaxFit(float[] valoresFitness) {
		float maxFit=0;		
		for(int i=0;i<10;i++)
		{
			if(maxFit<valoresFitness[i]){maxFit=valoresFitness[i];};
		}
		return maxFit;
	}

	private static float obtenerMaxObj(float[] valoresEnFuncion)
	{
		float maxObj = 0;
		for(int i=0;i<10;i++)
		{
			if(maxObj<valoresEnFuncion[i])
			{
				maxObj=valoresEnFuncion[i];
			}			

		}

		return maxObj;
	}

	private static float guardarFit(float[] valoresEnFuncion,
			float[] valoresFitness, float sumatoriaObj) {
		float sumatoriaFit = 0;
		for(int i=0;i<10;i++)
		{ 
			valoresFitness[i] = valoresEnFuncion[i]/sumatoriaObj;
			sumatoriaFit += valoresFitness[i];
		}

		return sumatoriaFit;
	}

	private static float sumatoriaObj(float[] valoresEnFuncion) {
		float sumatoriaObj = 0;
		for(int i=0;i<10;i++)
		{ 
			sumatoriaObj = sumatoriaObj + valoresEnFuncion[i];
		}
		return sumatoriaObj;
	}

	private static void asignarValoresFuncion(ArrayList<String> c,float[] valoresEnFuncion) {
		for(int i=0;i<10;i++)
		{
			valoresEnFuncion[i] = funcion(dameDecimal(c.get(i).toString()));
		}
	}

	//Funcion que recibe un objeto cromosoma devuelve un decimal

	private static int dameDecimal(String cromos2) {
		String nroBinario = cromos2.toString();
		int num = Integer.parseInt(nroBinario,2);
		return num;
	}

	//Funcion que recibe el numero decimal corresp al cromosoma 
	//y devuelve el valor dado por la Fumcion Objetiva

	private static float funcion(int nro){
		int coef = (int)Math.pow(2,30) - 1;
		float rdo = 0;
		float div = (float)nro/(float)coef;
		rdo = (float)Math.pow(div, 2);		
		return rdo;	
	}
}