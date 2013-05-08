import java.util.Random;


public class Main {

	/**
	 *  La llamada deberia ser java Main <Numero_de_Vertices> [<Uniforme>]
	 *  Numero_de_Vertices = Cuantos Vertices tendra el Grafo
	 *  Uniforme = 1 si se quiere que todos los costos y distancias sean 1
	 */
    public static void main(String[] args) {

		int NV = Integer.parseInt(args[0]); //<< Numero de Vertices
		int N1 = NV*NV; //<< Numero Maximo de Aristas
		int N2 = 4; //<< Cantidad de Columnas Necesarias
		
		boolean U = false; //<< Uniforme, si Es 1 Todos los Costos y Ganancias son 1, si es false son al azar
		
		try {
			if(Integer.parseInt(args[1])==1)
				U = true;
		}catch (Exception E1){
			U = false;
		}
		
		String[] V = new String[4];
        String[] L = new String[4];		
		
        double[][] PL=new double[N1][N2]; //<< Costo y Ganancia de Cada Nodo
        Random R = new Random();

        for(int i=0;i<N1;i++){
            for(int j=0;j<N2;j++){
				if(U)
					PL[i][j]=1.0;
				else
					PL[i][j]= R.nextDouble()*10;
            }
        }
		
        L[0]="CostoCoodNess";
        L[1]="TiempoGoodNess";
        L[2]="TiempoEvil";
        L[3]="GananciaEvil";

        CrearGrafo C = new CrearGrafo(NV,"DIR",L,PL,L,PL);
        
    }

}
