package mochila;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class programaPrincipal {

	static ArrayList<Elemento> elementos = new ArrayList<Elemento>();
	static ArrayList<String> posibilidades = new ArrayList<String>();
	static ArrayList<Integer> valoresPosibilidades = new ArrayList<Integer>();
	static ArrayList<Integer> volumenesPosibilidades = new ArrayList<Integer>();
	static ArrayList<Posibilidad> conjuntoPosibilidades = new ArrayList<Posibilidad>();
	static int mochila = 4200;
	
	public static void main(String[] args) {
		
		inicializarLista();
		
		System.out.println("Los elementos disponibles para robar son: ");
		System.out.println("ID\tVolumen\tValor");
		for(Elemento el : elementos)
		{
			System.out.println(el.id+"\t "+el.volumen+"\t "+el.valor);
		}
		
		crearPosibilidades();
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nCombinación:\tNro de Posibilidad:");
		for(String p : posibilidades)
		{
			System.out.print(p);
			System.out.println("\t\t"+binarioADecimal(p));
		}
		
		valorXPosibilidad(posibilidades);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nCombinacion:\tValor de Posibilidad:");
		for(int i=0;i<posibilidades.size();i++)
		{
			System.out.print(posibilidades.get(i).toString());
			System.out.println("\t\t"+valoresPosibilidades.get(i));
		}
		
		volumenXPosibilidad(posibilidades);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nCombinacion:\tVolumen de Posibilidad:");
		for(int i=0;i<posibilidades.size();i++)
		{
			System.out.print(posibilidades.get(i).toString());
			System.out.println("\t\t"+volumenesPosibilidades.get(i));
		}
		
		crearConjuntoPosibilidades();
		
/*		System.out.println("\nCombinacion:\tVolumen:\tValor:");
		for(Posibilidad p : conjuntoPosibilidades)
		{
			System.out.println(p.getPosib()+"\t"+p.getVolumen()+"\t\t"+p.getValor());
		}*/
		
		Collections.sort(conjuntoPosibilidades, new Comparator<Posibilidad>() {
			@Override
			public int compare(Posibilidad p1, Posibilidad p2) {
				return new Integer(p2.getValor()).compareTo(new Integer(p1.getValor()));
			}
		});
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nCombinacion:\tVolumen:\tValor:");
		for(Posibilidad p : conjuntoPosibilidades)
		{
			System.out.println(p.getPosib()+"\t"+p.getVolumen()+"\t\t"+p.getValor());
		}
		
		int indice = 0;
		int maxValor = 0;
		for(int i=0;i<conjuntoPosibilidades.size();i++)
		{
			if(conjuntoPosibilidades.get(i).getVolumen()<=mochila)
			{
				if(maxValor<conjuntoPosibilidades.get(i).getValor())
				{
					maxValor = conjuntoPosibilidades.get(i).getValor();
					indice = i;
				}
			}
		}
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.println("La mejor posibilidad es: ");
		System.out.println(conjuntoPosibilidades.get(indice).getPosib());
		System.out.println("Compuesta por los siguientes elementos: ");
		System.out.println("ID\tVolumen\tValor");
		for(int i=0;i<elementos.size();i++)
		{
			if(conjuntoPosibilidades.get(indice).getPosib().charAt(i)!='0')
			{
				Elemento el = elementos.get(i);
				System.out.println(el.getId()+"\t"+el.getVolumen()+"\t"+el.getValor());
			}
		}
		System.out.println("Total en volumen: "+conjuntoPosibilidades.get(indice).getVolumen());
		System.out.println("Total en valor: "+conjuntoPosibilidades.get(indice).getValor());
		
	}	
	
	private static void crearConjuntoPosibilidades() {
		
		for(int i=0;i<posibilidades.size();i++)
		{
			conjuntoPosibilidades.add(new Posibilidad(posibilidades.get(i),volumenesPosibilidades.get(i),valoresPosibilidades.get(i)));
		}
		
	}

	private static void volumenXPosibilidad(ArrayList<String> posibis) {

		for(String p : posibis)
		{
			int cont = 0;
			for(int i=0;i<p.length();i++)
			{
				if(p.charAt(i)!='0')
				{
					Elemento el = elementos.get(i);
					cont+=el.getVolumen();
				}
			}
			volumenesPosibilidades.add(cont);
		}
		
	}

	private static void valorXPosibilidad(ArrayList<String> posibis) {
		
		for(String p : posibis)
		{
			int cont = 0;
			for(int i=0;i<p.length();i++)
			{
				if(p.charAt(i)!='0')
				{
					Elemento el = elementos.get(i);
					cont+=el.getValor();
				}
			}
			valoresPosibilidades.add(cont);
		}		
	}

	private static void crearPosibilidades() {
		
		for(int i=0;i<Math.pow(2,elementos.size());i++)
		{
			String posi = decimalABinario(i);
			switch(posi.length()){
			case 1:
				posi = "000000000"+posi;
				break;
			case 2:
				posi = "00000000"+posi;
				break;
			case 3:
				posi = "0000000"+posi;
				break;
			case 4:
				posi = "000000"+posi;
				break;
			case 5:
				posi = "00000"+posi;
				break;
			case 6:
				posi = "0000"+posi;
				break;
			case 7:
				posi = "000"+posi;
				break;
			case 8:
				posi = "00"+posi;
				break;
			case 9:
				posi = "0"+posi;
				break;
			case 10:
				break;
			}
			
			posibilidades.add(posi);
			
		}
		
	}

	private static void inicializarLista()
	{
		elementos.add(new Elemento(1,150,20));
		elementos.add(new Elemento(2,325,40));
		elementos.add(new Elemento(3,600,50));
		elementos.add(new Elemento(4,805,36));
		elementos.add(new Elemento(5,430,25));
		elementos.add(new Elemento(6,1200,64));
		elementos.add(new Elemento(7,770,54));
		elementos.add(new Elemento(8,60,18));
		elementos.add(new Elemento(9,930,46));
		elementos.add(new Elemento(10,353,28));
	}
	
	public static String decimalABinario(int num){
		
		return Integer.toBinaryString(num);
	}
	
	public static String binarioADecimal(String num){
	
		int i = Integer.parseInt(num,2);
		String dec = Integer.toString(i);
		return dec;
	}

}
