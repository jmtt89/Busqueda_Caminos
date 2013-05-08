import java.io.*;

class DIRGRAFO extends GRAFO{
	//arco

	DIRGRAFO(){
		super();
	}	

	public DIRGRAFO (String nameFile) throws IOException,NullPointerException,ClassCastException {
		super(nameFile);
		DIRGRAFO G = new DIRGRAFO();
		G.Lv = this.Lv;
		BufferedReader in = new BufferedReader(new FileReader(nameFile));
		String s;
		String[] tok;
		int numVer = 0;
		int numProp = 0;
		int numLad = 0;
		String[] NombreProp = new String[1];
		
		//Leer el tipo del grafo
		if((s = in.readLine()) != null) {
			tok = s.split("\\b\\s");
			if (!tok[0].trim().equals("DIR")) {
				System.out.println("El Grafo ingresado no es un Grafo No Dirigido");
			throw new ClassCastException();
			}
		}
		
		//Leer el numero de nodos
		if((s=in.readLine()) != null) {
			tok = s.split("\\b\\s");
			numVer = Integer.parseInt(tok[0].trim());
		}
		
		//Leer el numero de propiedades que tendra cada vertice y los nombres de estas propiedades
		s = in.readLine();
		
		//Leer los nodos del grafo
		for (int i = 0; i < numVer; i++){
			s = in.readLine();
		}
		
		//Leer el numero de lados
		if((s=in.readLine()) != null) {
			tok = s.split("\\b\\s");
			numLad = Integer.parseInt(tok[0].trim());
		}
		
		//Leer el numero de propiedades que tendra cada lado y los nombres de estas propiedades
		if((s = in.readLine()) != null) {
			tok = s.split("\\s+");  
			numProp = Integer.parseInt(tok[0]);
			NombreProp = new String[numProp];
			if (numProp < 0) {
				System.out.println("El numero de propiedades de los lados es negativo");
				throw new NullPointerException();
			}
			for (int i = 1; i < tok.length; i++) {
				NombreProp[i-1]=tok[i].trim();
			}
		}
		VERTICE[] A = G.Vertices2();	
		int w = 0;
		VERTICE Y=null;
		VERTICE Y1=null;
		ARCO a = null;
		//Leer las Aristas y sus propiedades
		for (int i = 0; i < numLad; i++) {
			if ((s = in.readLine()) != null) {
				tok = s.split("\\s+");
	
				w=0;
				while(w<A.length){
					if(A[w].toString().equals(tok[1].trim())){
						Y = A[w];
					}
					if(A[w].toString().equals(tok[2].trim())){
						Y1 = A[w];
					}
					w++;
				}
				a = new ARCO(tok[0].trim(), Y, Y1);
				
				for(int j=0;j<numProp;j++){
					a.CrearPropiedad(NombreProp[j]);
				}
				
				for (int j = 3; j < tok.length; j++) {
					a.AsignarValor(NombreProp[j-3],Double.valueOf(tok[j].trim()).doubleValue());
				}
				G.AgregarArco(a);
			}
		}
		this.Lv = G.Lv;
	}	
	
	
	/**
	 **Agrega una arco al Grafo
	 */	
	public void AgregarArco (/*GRAFO G,*/ ARCO A){
	//Pre:  El Grafo debe estar inicializado
	//Post: El grafo posee los mismos elementos que antes sumandole la Arista		
		Lv.InsertL(A);
	}
	
	/**
	 **Elimina una arco del Grafo
	 */	
	public void EliminarArco (/*GRAFO,*/ String A){
	//Pre:  El grafo debe tener almenos una Arista a eliminar
	//Post: El grafo tiene todo igual que antes de la eliminicion a excepcion de el elemento eliminado
		try{
			Lv.EliminarL(A);
		}catch (NullPointerException E){
			System.out.println("La Arista que se pide eliminar no se encuentra en el Grafo");
		}
	}
	
	/**
	 **Caulcula el Grado de entrada de un Vertice en particular
	 */
	public int GradoInt(/*GRAFO G,*/ String V){
	//Pre:  El grafo debe contener al vertice V
	//Post: El Grado de entrada sera el numero de Arcos que tienen como vertice inicial a V
		int T;
		try{
			T=Lv.GradoE(V);
		}catch(NullPointerException E){
			System.out.println("El Vertice al que desea calcular el Grado de entrada no esta en el grafo");
			T=-1;
		}
		return T;
	}
	
	/**
	 **Caulcula el Grado de Salida de un Vertice en particular
	 */
	public int GradoExt(/*GRAFO G,*/ String V){
	//Pre:  El grafo debe contener al vertice V
	//Post: El Grado de Salida sera el numero de Arcos que tienen como vertice inicial a V
		int T;
		try{
			T=Lv.GradoS(V);
		}catch(NullPointerException E){
			System.out.println("El Vertice al que desea calcular el Grado de salida no esta en el grafo");
			T=-1;
		}
		return T;
	}
	
			
	/**
	 **Calcula los lados que inciden en un Vertice
	 */
	public LADO[] Incidentes (/*GRAFO G,*/ String V){
	//Pre:  El Grafo debe estar inicializado
	//Post: Todos los Lados que devuelve tienen como extremo a V
		LADO[] T;
		try{
			T = Lv.IncdnD(V);
		} catch(NullPointerException E) {
			System.out.println("El Vertice del que desea saber sus incidentes no se encuentra en el Grafo");
			T=new LADO[0];
		}
		return T;
	}
	
	
	/**
	 ** Calcula los sucesores de un vertice determinado
	 */
	public VERTICE[] Sucesores (/*GRAFO G, */ String V){
	//Pre:  El Grafo debe estar inicialaizado
	//Post: Los vertices que develve comparten lado con V
		VERTICE[] T;
		try{
			T = Lv.Susc(V);
		} catch(NullPointerException E) {
			System.out.println("El Vertice del que desea saber sus Suscesores no se encuentra en el Grafo");
			T=new VERTICE[0];
		}
		return T;
	}
	
	/**
	 ** Calcula los predecesores de un vertice determinado
	 */	
	public VERTICE[] Predecesores (/*GRAFO G, */ String V){
	//Pre:  El Grafo debe estar inicialaizado
	//Post: Los vertices que develve comparten lado con V
		VERTICE[] T;
		try{
			T = Lv.Pred(V);
		} catch(NullPointerException E) {
			System.out.println("El Vertice del que desea saber sus predecesores no se encuentra en el Grafo");
			T=new VERTICE[0];
		}
		return T;
	}
		
	/**
	 **Calcula la matriz de Adyacencia de un Grafo
	 */		
	public boolean[][] MatrizAdy (){
	//Pre:  El grafo debe estar inicializado
	//Post: si el vertice v es adyacente al w entonces en la matriz de adyacensia hay un true en la casilla[v,w]		
		return Lv.MatAd(1);
	}

	/**
	 **Calculo de la matriz de Alcance
	 */			
	public boolean[][] MatrizDeAlcace(){
	//Pre:  El grafo debe estar inicializado
	//Post: si el vertice v se comunica con w entonces en la matriz de Alcance hay un true en la casilla[v,w]		
		return Lv.Alcanzable(1);
	}
	
	//Muestra la matriz de alcance correctamente indexada	
	public void ShowMatrizAlcance(GRAFO G){
		boolean[][] T =	G.MatrizDeAlcace();
		byte b = 9;
		char c = (char) b;
		String[] A = G.Vertices();
		for(int i=(G.TAMV()-1);i>=0;i--){
			System.out.print(c+A[i]);
		}
		System.out.println();
		
		for(int i=0;i<G.TAMV();i++){
			System.out.print(A[(G.TAMV()-1)-i]);
			for(int j=0;j<G.TAMV();j++){
				if (T[i][j]==true) {
					System.out.print(c+"1");
				} else {
					System.out.print(c+"0");
				}
				
			}
			System.out.println();
		}
	}
	
}