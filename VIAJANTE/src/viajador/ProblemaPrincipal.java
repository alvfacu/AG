package viajador;

import java.util.ArrayList;


public class ProblemaPrincipal {

	static ArrayList<String> recorrido;
	static int trayectoria;

	static int[][] distancias =
		{
			{0,1543,1510,1203,1043,1191,1023,478,940,1040,480,715,1150,1110,790,1155,1050,620,1158,960,1455,2635,3228},
			{1543,0,99,340,500,960,860,1107,883,1198,1138,930,770,1220,1320,572,1345,1530,2200,2124,2385,3565,4158},
			{1510,99,0,307,467,948,780,1074,803,1118,1105,897,695,1145,1245,539,1227,1497,2082,2091,2352,3532,4125},
			{1203,340,307,0,160,936,768,767,791,1106,798,590,338,838,938,232,1005,1190,1860,1784,2045,3225,3818},
			{1043,500,467,160,0,776,610,607,633,948,638,430,360,810,850,212,977,1030,1567,1624,1885,3065,3658},
			{1191,960,948,936,776,0,168,713,191,506,744,1043,1136,1543,1463,988,1710,1523,2060,2117,2378,3558,4151},
			{1023,860,780,765,610,168,0,545,23,338,576,875,970,1420,1295,822,1587,1475,2012,2069,2210,3390,3983},
			{478,1107,1074,767,607,713,545,0,568,883,31,330,765,830,625,770,885,810,1347,1404,1665,2845,3438},
			{940,883,803,791,633,191,23,568,0,315,590,898,993,1398,1318,845,1565,1378,1989,2046,2187,3367,3960},
			{1040,1198,1118,1106,948,506,338,883,315,0,820,1213,1308,1758,1633,1160,1925,1660,2198,2000,2495,3675,4268},
			{480,1138,1105,798,638,744,576,31,590,820,0,361,796,861,656,801,916,841,1378,1435,1696,2876,3469},
			{715,930,897,590,430,1043,875,330,898,1213,361,0,435,500,420,440,670,600,1137,1194,1455,3635,3228},
			{1150,770,695,388,360,1136,970,765,993,1308,796,435,0,450,550,156,617,1035,1472,1629,1890,3070,3663},
			{1110,1220,1145,838,810,1543,1420,830,1398,1758,861,500,450,0,320,606,167,825,1022,1419,1680,2860,3453},
			{790,1320,1245,938,850,1463,1295,625,1318,1633,656,420,550,320,0,705,260,505,883,1099,1360,2540,3133},
			{1145,572,539,232,212,988,822,770,845,1160,801,440,156,606,705,0,773,1040,1588,1634,1895,3075,3668},
			{1050,1345,1227,1005,977,1710,1587,885,1565,1925,916,670,617,167,260,773,0,765,855,1359,1620,2800,3393},
			{620,1530,1497,1190,1030,1523,1475,810,1378,1660,841,600,1035,825,505,1040,765,0,537,594,855,2035,2628},
			{1158,2200,2082,1860,1567,2060,2012,1347,1989,2198,1378,1137,1472,1022,883,1588,855,537,0,660,750,1930,2523},
			{960,2124,2091,1784,1624,2117,2069,1404,2046,2000,1435,1194,1629,1419,1099,1634,1359,594,660,0,495,1675,2268},
			{1455,2385,2352,2045,1885,2378,2210,1665,2187,2495,1696,1455,1890,1680,1360,1895,1620,855,750,495,0,1180,1773},
			{2635,3565,3532,3225,3065,3558,3390,2845,3367,3675,2876,2635,3070,2860,2540,3075,2800,2035,1930,1675,1180,0,593},
			{3228,4158,4125,3818,3658,4151,3983,3438,3960,4268,3469,3228,3660,3453,3133,3668,3393,2628,2523,2268,1773,593,0}
		};
	
	static String[][] ciudades =
		{
			{"0","Buenos Aires"},
			{"1","San Salvador de Jujuy"},
			{"2","Salta"},
			{"3","San Miguel de Tucuman"},
			{"4","Santiago del Estero"},
			{"5","Formosa"},
			{"6","Resistencia"},
			{"7","Santa Fe"},
			{"8","Corrientes"},
			{"9","Posadas"},
			{"10","Parana"},
			{"11","Cordoba"},
			{"12","La Rioja"},
			{"13","San Juan"},
			{"14","San Luis"},
			{"15","Catamarca"},
			{"16","Mendonza"},
			{"17","Santa Rosa"},
			{"18","Neuquen"},
			{"19","Viedma"},
			{"20","Rawson"},
			{"21","Rio Gallegos"},
			{"22","Ushuaia"},
		};

	public static void main(String[] args) {
		
		int trayectoria = viaje("22");
		
		for(String ciu : recorrido)
		{
			System.out.println(/*(Integer.parseInt(*/ciu.toString()/*)+1)*/+" "+ciudades[Integer.parseInt(ciu.toString())][1]);
		}
		
		System.out.println("Distancia recorrida total: "+trayectoria);
	}

	private static int viaje(String inicio) {

		int indice = Integer.valueOf(inicio);		
		recorrido.add(ciudades[indice][0]);
		int cont = 0;
		
		for(int i=0;i<22;i++)
		{
			int menor = 10000;
			int indMenor = 0;
			for(int j=0;j<23;j++)
			{
				int dis = distancias[indice][j];
				if(menor>dis && dis!=0)
				{
					if(!(yaPaso(j)))
					{
						menor = distancias[indice][j];
						indMenor = j;
					}					
				}
			}
			cont = cont + menor;
			recorrido.add(ciudades[indMenor][0]);
			indice = indMenor;
		}
		
		cont = cont + distancias[indice][Integer.valueOf(inicio)];
		recorrido.add(ciudades[Integer.valueOf(inicio)][0]);
		return cont;
		
	}

	private static boolean yaPaso(int c) {
		boolean paso = false;
		for(String ciu : recorrido)
		{
			if(ciu.equalsIgnoreCase(Integer.toString(c)))
			{
				paso = true;
			}
		}
		return paso;
	}

	public void iniciarViaje(int inicio) {
	
		recorrido = new ArrayList<String>();
		trayectoria = viaje(String.valueOf(inicio));		
	}

}