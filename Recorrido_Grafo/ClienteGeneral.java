import java.util.LinkedList;
import java.io.*;


// Cliente principal que se encarga de llamar todo e imprimir los resultados
//
class ClienteGeneral{

        private static LinkedList Arch(String NameFile) throws IOException, NullPointerException{
            LinkedList R= new LinkedList();
            BufferedReader in = new BufferedReader(new FileReader(NameFile));
            String s;
            String[] tok;

            //Lee Las ciudades de ciudadesWithAir o ciudadesEvitar
            // Como las regresa simplemente en un linkedList no importa
            while((s = in.readLine()) != null ){
                tok = s.split("\\s+");
                R.add(tok[0].trim());
            }
            return R;
        }

        private static boolean Verify(String S,GRAFO G){
            boolean R = false;
            VERTICE[] A = G.Vertices2();
            for(int i=0;i<A.length&&!R;i++){
                R = A[i].toString().equals(S);
            }
            return R;
        }
	
	public static void main (String[] args) {

            //Variables para elñ tiempo de ejecucion:
            long TimeI;
            long TimeF;
            long TimeIF;

            //Variables para utilizacion interna del cliente
		int i=0;	
                Camino A;
                Camino A1;
                Camino B;

                boolean Rs = true;
                //Ciudades Con aeropuerto WithAir
                LinkedList LstN = new LinkedList();
                //Ciudades que se deben Evitar
                LinkedList LstP = new LinkedList();
                //Ciudad Contaminada
                String C;


            /*Se carga el Grafo, la ubicacion de los Aeropuertos, la ubicacion de
             * las ciudades a evitar y la ciudad Contaminada
             */
//////////*****//////Inicializacion del Grafo ******///////*******/////////////
                TimeI = System.currentTimeMillis();
                try {
                    // Lista de  ciudadesWithAir
                    LstN = Arch(args[1]);
                } catch (Exception E1){
                    System.out.println("El archivo "+ args[1] +" no existe");
                    System.out.println("Creelo asi lo valla a dejar Vacio");
                    Rs = false;
                }

                try {
                    // Lista de  ciudadesEvitar
                    LstP = Arch(args[2]);
                } catch (ArrayIndexOutOfBoundsException ERT){
                    LstP = new LinkedList();

                }catch (Exception E1){
                    System.out.println("El archivo "+ args[2] +" no existe");
                    System.out.println("Creelo asi lo valla a dejar Vacio");
                    Rs = false;
                }
                
                if(Rs){
                
                try{
                // Inicializa el Grafo dirigido
                DIRGRAFO G = new DIRGRAFO(args[0]);

                boolean Vrf = false;

                do {
                    C = Console.readString("Introduzca el nombre de la ciudad Contaminada");
                    Vrf = Verify(C,G);
                } while(Vrf==false);

                TimeF = System.currentTimeMillis();
                TimeIF=TimeF-TimeI;
                System.out.println("El tiempo utilizado para Cargar todo en milisegundos es: "+ (double)TimeIF/1000);
                System.out.println();
                Console.readWord();
                
 /////////////// **********Tiempo Minimo**************** ///////////////////
                TimeI = System.currentTimeMillis();
                System.out.println("Camino de tiempo minimo (Goodness)");
		D OK = new D("TiempoGoodNess","TiempoGoodNess");

                A = OK.Ejecutar(G, C ,LstN, LstP);

                System.out.println("Camino de menor Tiempo: ");
                A.Print();

                System.out.print("Tiempo: ");
                System.out.println(A.ObtenerValor("Costo"));

                double T=A.nodeAt(0).ObtenerValor("CostoCoodNess");
                double T1;
                double T2;
                System.out.print("Costo: ");
                for(int j=1;j<(A.length()/2)+1;j++){
                    T1 = A.nodeAt(j).ObtenerValor("CostoCoodNess");
                    T2 = A.nodeLAD(j).ObtenerValor("CostoCoodNess");
                    T = T + T1 + T2;
                }
                System.out.println(T);

                System.out.println();

                TimeF = System.currentTimeMillis();
                TimeIF=TimeF-TimeI;
                System.out.println("El tiempo utilizado para conseguir el camino de menor costo en milisegundos es: "+ (double)TimeIF/1000);
                System.out.println();
                Console.readWord();

/////////////// **********Costo Maximo**************** ///////////////////
                TimeI = System.currentTimeMillis();
                System.out.println("Camino de costo maximo (Evil)");
		Act1 DK = new Act1("GananciaEvil","GananciaEvil");

                B = DK.Ejecutar(G, C, LstN, LstP);

                System.out.println("Camino de Mayor Costo: ");
                B.Print();
                
                System.out.print("Costo: ");
                System.out.println(B.ObtenerValor("Costo"));

                /**
                 * Le pregunte al prof. eduardo blanco si el tiempo de Evil
                 * debe ser menor o igual al tiempo Goodness o si no hay
                 * restriccion y el cotesto que ´para evil solo se evaluara
                 * Camino de costo maximo, sin restricciones sobre el tiempo
                 */

                T=B.nodeAt(0).ObtenerValor("TiempoEvil");
                System.out.print("Tiempo: ");
                for(int j=1;j<(B.length()/2)+1;j++){
                    T1 = B.nodeAt(j).ObtenerValor("TiempoEvil");
                    T2 = B.nodeLAD(j).ObtenerValor("TiempoEvil");
                    T = T + T1 + T2;
                }
                System.out.println(T);

                TimeF = System.currentTimeMillis();
                TimeIF=TimeF-TimeI;
                System.out.println("El tiempo utilizado para conseguir el camino de mayor costo en segundos es: "+ (double)TimeIF/1000);

                System.out.println();
                Console.readWord();

/////////////// **********Sabotaje**************** ///////////////////
                i=1;
                while(i!=0){
                    TimeI = System.currentTimeMillis();
                    System.out.println("Sabotaje (Evil)");

                    System.out.println("Hay "+A.length()/2+" Maneras diferentes de sabotear");

                    System.out.println("si Desea eliminar alguna de las siguientes cuidades del camino de Goodness presione su numero");
                    System.out.println("En caso contrario Introduzca 0");
                    for(int j=0;j<(A.length()/2)+1;j++){
                        System.out.println((j+1)+" "+A.nodeAt(j).toString());
                    }

                    i = Console.readInt(" ");


                    if(i!=0){

                        //Agrega a la lista de prohibidos el nodo designado
                        LstP.addLast(A.nodeAt(i-1).toString());

                        try{
                        A1 = OK.Ejecutar(G, C ,LstN, LstP);

                        System.out.println("Nuevo Camino de menor Tiempo: ");
                            A1.Print();

                        System.out.print("Tiempo: ");
                        System.out.println(A1.ObtenerValor("Costo"));

                        T=A.nodeAt(0).ObtenerValor("CostoCoodNess");
                        System.out.print("Costo: ");
                        for(int j=1;j<(A1.length()/2)+1;j++){
                            T1 = A1.nodeAt(j).ObtenerValor("CostoCoodNess");
                            T2 = A1.nodeLAD(j).ObtenerValor("CostoCoodNess");
                            T = T + T1 + T2;
                        }
                        System.out.println(T);
                        System.out.println();
                        Console.readWord();

                        System.out.println("Si desea realizar un sabotaje seguido de este presione 1");
                        System.out.println("Si desea Regresar a un estado anterior y realizar el sabotaje presione 2");

                        i=Console.readInt(" ");

                        if(i==1) A = A1;

                        if(i==2){
                            LstP.removeLast();
                        }

                        TimeF = System.currentTimeMillis();
                        TimeIF=TimeF-TimeI;
                        System.out.println("El tiempo utilizado para realizarsabotaje en segundos es: "+ (double)TimeIF/1000);

                        System.out.println();
                        Console.readWord();


                      }catch(NullPointerException ER1){
                        System.out.println("La ciudad Contaminda es Inalcansable");
                        TimeF = System.currentTimeMillis();
                        TimeIF=TimeF-TimeI;
                        System.out.println("El tiempo utilizado para realizarsabotaje en segundos es: "+ (double)TimeIF/1000);
                        System.out.println();
                        Console.readWord();
                      }


                    }
                }



		} catch (IOException EE) {
			System.out.println("Error en ejecucion...1");
		} catch (NullPointerException RR){
			System.out.println("Error en ejecucion...2");
		} catch (ClassCastException YY){
			System.out.println("Error en ejecucion...3");
		}

                }

	}
}
