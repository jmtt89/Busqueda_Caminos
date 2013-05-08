import java.io.*;

public abstract class GRAFO{
	protected Lista Lv;
		
	//Constructor de la clase Grafo
	public GRAFO(){
	//Pre:  true
	//Post: El Grafo esta inicializado en Vacio
		Lv = new Lista();
	}
	
	//No pudimos hacer que funcionara el constructor que pretende construir un grafo a partir de un documento de texto
	//
	//Constructor de la clase Grafo a partir de un Archivo
	public GRAFO (String nameFile) throws IOException,NullPointerException {
		this();
		
		File A = new File(nameFile);
		
		if ((A.exists())){
		
			BufferedReader in = new BufferedReader(new FileReader(nameFile));
			String s;
			String[] tok;
			int numVer = 0;
			int numProp= 0;
			String[] NombreProp = new String[1];
			
			//Leer el tipo del grafo
			s = in.readLine();
			
			//Leer el numero de Vertices
			if((s = in.readLine()) != null) {
				tok = s.split("\\b\\s");
				numVer = Integer.parseInt(tok[0].trim());
				if (numVer < 1) {
					System.out.println("El numero de Vertices es menor a 1");
					throw new NullPointerException();
				}
			}
			
			//Leer el numero de propiedades que tendra cada vertice y los nombres de estas propiedades
			if((s = in.readLine()) != null) {
				tok = s.split("\\s+");  
				numProp = Integer.parseInt(tok[0]);
				if (numProp < 0) {
					System.out.println("El numero de propiedades de los vertices es negativo");
					throw new NullPointerException();
				}
				NombreProp = new String[tok.length-1];
				for (int i = 1; i < tok.length; i++) {
					NombreProp[i-1]=tok[i].trim();
				}
			}
			
			//Leer los nodos y sus propiedades del grafo y se anexan al mismo
			for (int i = 0; i < numVer; i++) {
				if((s = in.readLine()) != null ) {
					tok = s.split("\\s+");
					VERTICE v = new VERTICE(tok[0].trim());
					
					for(int j=0;j<numProp;j++){
						v.CrearPropiedad(NombreProp[j]);
					}
					
					for (int j = 1; j < tok.length; j++) {
						v.AsignarValor(NombreProp[j-1],Double.valueOf(tok[j].trim()).doubleValue());
					}
					AgregarVertice(v);
				}
			}
		
		}else {
			System.out.println("Archivo Invalido o Directorio no Valido");
			throw new NullPointerException();
		}
	}
	
	
	//Regresa la cantidad de Vertices
	public int TAMV(){
	//Pre:  El grafo esta inicializado
	//Post: TamV es la cantidad exacta de vertices en el grafo
		return Lv.NumVer();
	}
	
	public int TAML(){
	//Pre:  El grafo esta inicializado
	//Post: TamL es la cantidad exacta de vertices en el grafo		
		return Lv.NumLad();
	}
	
	//Agrega un vertice al Grafo
	public void  AgregarVertice(/*GRAFO G,*/ VERTICE V){
		Lv.InsertV(V);
	}
	
	public void EliminarVertice(/*GRAFO G,*/ String V){
		try{
			Lv.EliminarV(V);
		} catch(NullPointerException E){
			System.out.println("Error, se quiere eliminar un vertice que no pertenece al Grafo");
		}
	}
	
	public String[] Vertices(/*GRAFO G*/){
	//Pre:  true
	//Post: los elementos en el arreglo de salida corresponden a todos los vertices en el grafo
		String[] T;
		try{
			T=Lv.ElemtV();
		}catch(NullPointerException E){
			System.out.println("El Grafo no posee Vertices");
			T=new String[0];
		}
		return T;		
	}

	public VERTICE[] Vertices2(/*GRAFO G*/){
	//Pre:  true
	//Post: los elementos en el arreglo de salida corresponden a todos los vertices en el grafo
		VERTICE[] T;
		try{
			T=Lv.ElemtV2();
		}catch(NullPointerException E){
			System.out.println("El Grafo no posee Vertices");
			T=new VERTICE[0];
		}
		return T;		
	}

	public LADO[] Lados2(/*GRAFO G*/){
	//Pre:  true
	//Post: los elementos en el arreglo de salida corresponden a todos los vertices en el grafo
		LADO[] T;
		try{
			T=Lv.ElemtL2();
		}catch(NullPointerException E){
			System.out.println("El Grafo no posee Lados");
			T=new LADO[0];
		}
		return T;
	}


	public String[] Lados(/*GRAFO G*/){
	//Pre:  true
	//Post: los elementos en el arreglo de salida corresponden a todos los Lados en el grafo
		String[] T;
		try{
			T=Lv.ElemtL();
		}catch(NullPointerException E){
			System.out.println("El Grafo no posee Lados");
			T=new String[0];
		}
		return T;			
	}
	
	public int Grado(/*GRAFO G,*/ String V){
	//Pre:  El grafo debe contener al vertice V
	//Post: El Grado de Salida sera el numero de Arcos que tienen como vertice inicial a V
		int T;
		try{
			T=Lv.GradoT(V);
		}catch(NullPointerException E){
			System.out.println("El Vertice al que desea calcular el Grado no esta en el grafo");
			T=-1;
		}
		return T;		
	}
	
	public VERTICE[] Ady(/*GRAFO G,*/ String V){
	//Pre:  El Grafo debe estar inicializado
	//Post: Todos los Lados que devuelve tienen como extremo a V
		VERTICE[] T;
		try{
			T = Lv.Adya(V);
		} catch(NullPointerException E) {
			System.out.println("El Vertice del que desea saber sus adyacentes no se encuentra en el Grafo");
			T=new VERTICE[0];
		}
		return T;
	}
	
	//Retorna el lado asociado a los vertices V,W 
	public LADO LAD(/*GRAFO G, */VERTICE V,VERTICE W){
	//Pre:  El Grafo debe estar inicializado
	//Post: Todos los Lados que devuelve tienen como extremo a V
		LADO T;
		try{
			T = Lv.LAD(V,W);
		} catch(NullPointerException E) {
			T=null;
		}
		return T;
	}	
	
	
	public abstract LADO[] Incidentes(/*GRAFO G,*/String V);
	
	public abstract boolean[][] MatrizAdy ();
	
	public abstract boolean[][]  MatrizDeAlcace();


	protected class Lista{
		private NodoV CabV; // la cabeza de la lista de Vertices
		private NodoL CabL; // La cabeza de la lista de Lados
		private int tamV; //  tamano de la lista de vertices
		private int tamL; // tamano de la lista de lados
		
		/*
		 *Constructor de la clase  lista
		 *Crea una lista Vacia
		 */
		private Lista(){
			//Pre:  true
			//Post: devuelve una lista inicializada en vacio
			CabV=null;
			CabL=null;
			tamV=0;
			tamL=0;
		}
		

		/** 
		 *Devuelve un arreglo con todos los elementos
		 *de la lista de lados
		 **/
		public LADO[] ElemtL2()throws NullPointerException{
			//Pre:  true
			//Post: el arreglo de string contiene todos los elementos de la lista lados
			NodoL P = CabL;
			LADO[] Str = new LADO [tamL];
			int i=0;
			while (P!=null){
				Str[i]=P.E;
				P=P.Sig;
				i=i+1;
			}
			return Str;
		}


		/**
		*  Regresa la matriz de Adyacencias
		* Entrada:
		*  0 si es no dirigido
		*  1 si es dirigido
		*/
		protected boolean[][] MatAd(int d){
			boolean [][] Mad = new boolean [tamV][tamV];
			
			NodoL P = CabL;
			while (P!=null){
				Mad[P.Vi.index][P.Vf.index]=true;
				if ( d==0 ){
					Mad[P.Vf.index][P.Vi.index]=true;	
				}
				P = P.Sig;
			}
			return Mad;
		}
		
		/**
		 *Procedimiento que calcula si existe camino de un vertice a otro
		 */
		private boolean AlcanzableAUX(int w, int v, boolean[][] M){
			boolean hola = false;
			for (int k=0 ; k<M.length && !hola ; k++) {
				hola = M[w][v] || (M[w][k] && M[k][v]);
			}
			return hola;
		}

		/**
		 *Devuelve la matriz de alcanse de un grafo
		 */
		protected boolean[][] Alcanzable(/*Graph G ,*/ int d){
			// Matriz M es la matriz que dice si un vertice va a otro vertice es 1 sino 0
			boolean M [][] =  MatAd(d);
			// Matriz Auxiliar dond se cacula los caminos
			boolean N [][] = new boolean [tamV][tamV];
			
			// Agregamos la matriz identidad a la matriz M
			for(int i=0;i<tamV;i++){
					M[i][i]=true;	
			}
			
			for(int i=0; i<tamV;i++){
				for (int j=0;j<tamV;j++){
					for (int k=0;k<tamV;k++){
						N[j][k] = AlcanzableAUX(j,k,M) ;
					}
				}
				for (int j=0;j<tamV;j++){
					for (int k=0;k<tamV;k++){
						if(M[j][k]==false) {M[j][k] = N[j][k];}
					}
				}
			}
			return M;
		}
		

		/**
		 *Regresa el tamano de la lista de vertices
		 */
		public int NumVer(){
			//Pre:  true
			//Post: devuelve el tamano de la lista de vertices
			return tamV;
		}
		
		public int NumLad(){
			//Pre:  true
			//Post: devuelve el tamano de la lista de lados			
			return tamL;
		}
		
		/**
		 *Inserta un vertice en la lista
		 */
		public void InsertV(VERTICE V){
			//Pre:  La lista principal posee el elemento E
			//Post: La lista principal es identica a la anterior pero posee el elemento V
			NodoV P;
			if (tamV==0){
				 P= new NodoV(null,V,null);
			} else {
				 P = new NodoV(null,V,CabV);
				CabV.Ant = P;

			}
			P.index = tamV;
			CabV=P;
			tamV++;
		}

		/**
		*  Funcion que devuelve	los dos nodos de vertices que se une una arista
		*/
		private NodoV[] BusQ(LADO E){
		   NodoV P = CabV;
		   NodoV[] A = new NodoV[2];
		   int i = 0;
		   while(i!=2){
		   	try{
				if(P.V.equals(E.V())){
					A[0]=P;
					i++;
				}
				
				if(P.V.equals(E.W())){
					A[1]=P;
					i++;
				}
		   	} catch(NullPointerException EP){
		   		System.out.println("La lista de Vertices no tiene el vertice inicial o terminal de este Lado");
		   		i = 2;
		   		A =null;
		   	}
			// Nunca se sale xq los vertices son los pertenecientes a una arista osea tienen q estar en la lista de vertices
				P = P.Sig;
		   }
		   return A;
		}

		/**
		* Inserta un lado en la lista de lados (Tanto para Dirigido como para no dirigido)
		*/
		public void InsertL(LADO E){
			//Pre:  La lista principal posee el elemento E
			//Post: La lista principal es identica a la anterior pero posee el elemento V
			NodoV[] A = BusQ(E);
			NodoL P;
			if (tamL==0){
				 P= new NodoL(null,E,null);
				 CabL = P;
			} else {
				P = new NodoL(null,E,CabL);
				CabL.Ant = P;
				CabL = P;

			}
			P.Vi=A[0];
			P.Vf=A[1];
			tamL++;
		}

		/**
		 *Elimina de la lista de vertices el elemento buscado
		 *Si no lo encuentra entonces no hace nada
		 *y despues elimina de la lista de lados todos los lados relacionados a ese vertice
		 */		
	 	 protected void EliminarV(String E)throws NullPointerException{
	 	 	//Pre:  Se encuentra en la lista principal el elemento a eliminar
	 	 	//Post: La lista principal es identica pero sin el elemento E
			NodoV P = CabV;
			while (!E.equals(P.V.toString())){
				P = P.Sig;
			}
		
			NodoV R = P.Sig;
			NodoV Q = P.Ant;
				
			if (R!=null){
				R.Ant = Q;
			}
				
			if (Q!=null){
				Q.Sig = R;
			}
				
			P=null;
			tamV=tamV-1;
		
			
			NodoL S = CabL;
			//Elimina los Lados esociados al vertice que se desea eliminar
			while(S!=null){
			   if(S.E.V().toString().equals(E) || S.E.W().toString().equals(E)){
				NodoL Aux= S.Ant;
				EliminarL(S.E.toString());	
				S = Aux;
			   }
			   S=S.Sig;
			}
		 }
	
		
		/**
		 * Elimina de la lista de Lados El lado que se desee (Tanto para Dirigido como para NoDirigido)
		 */
		public void EliminarL(String E)throws NullPointerException{
			//Pre: El elemento V1 pertenece a la lista principal y el elemento V2 a la segundaria
			//Post: La lista de adyacencia es igual a la anterior excluyendo el elemento V2
			NodoL P = CabL;

			while (!E.equals(P.E.toString())){	
				P = P.Sig;
			}
			
				NodoL R = P.Sig;
				NodoL Q = P.Ant;
				
				if (R!=null){
					R.Ant = Q;
				}
				
				if (Q!=null){
					Q.Sig = R;
				}
				
				P=null;
				tamL=tamL-1;
		}


		/** 
		 *Devuelve un arreglo con todos los elementos
		 *de la lista de vertices
		 **/
		public String[] ElemtV()throws NullPointerException{
			//Pre:  true
			//Post: el arreglo de string contiene todos los elementos de la lista principal
			NodoV P = CabV;
			String[] Str = new String [tamV];
			int i=0;
			while (P!=null){
				Str[i]=P.V.toString();
				P=P.Sig;
				i=i+1;
			}
			return Str;
		}

		/** 
		 *Devuelve un arreglo con todos los elementos
		 *de la lista de vertices
		 **/
		public VERTICE[] ElemtV2()throws NullPointerException{
			//Pre:  true
			//Post: el arreglo de string contiene todos los elementos de la lista principal
			NodoV P = CabV;
			VERTICE[] Str = new VERTICE [tamV];
			int i=0;
			while (P!=null){
				Str[i]=P.V;
				P=P.Sig;
				i=i+1;
			}
			return Str;
		}

		/** 
		 *Devuelve un arreglo con todos los elementos
		 *de la lista de Lados
		 **/
		public String[] ElemtL(){
			//Pre:  true
			//Post: el arreglo de string contiene todos los elementos de la lista principal
			NodoL P = CabL;
			String[] Str = new String [tamL];
			int i=0;
			while (P!=null){
				Str[i]=P.E.toString();
				P=P.Sig;
				i=i+1;
			}
			return Str;
		}

		public void ImpElmLisV(){
			//Pre:  true
			//Post: true
			NodoV P = CabV;
			while(P!=null){
				System.out.println(P.V.toString()+":");
				P = P.Sig;
			}
		}

		public void ImpElmLisL(){
			//Pre:  true
			//Post: true
			NodoL P = CabL;
			while(P!=null){
				System.out.println(P.E.toString()+":");
				P = P.Sig;
			}
		}

		/**
		 * Calcula el Grado (Total) de un vertice
		 */
		 public int GradoT(String V)throws NullPointerException{
		 	int Gra = 0;
			NodoL P = CabL;
			
			while(P!=null){
				if(P.E.V().toString().equals(V)){
					Gra = Gra+1;
				}
				if	(P.E.W().toString().equals(V)){
					Gra = Gra+1;
				}
				
				P = P.Sig;
			}
			return Gra;
		}
		
		/**
		 * Calcula el Grado Entrante de un vertice
		 */
		 public int GradoE(String V)throws NullPointerException{
		 	int Gra = 0;
			NodoL P = CabL;
			
			while(P!=null){
				if	(P.E.W().toString().equals(V)){
					Gra = Gra+1;
				}
				P = P.Sig;
			}
			return Gra;
		}

		/**
		 * Calcula el Grado Saliente de un vertice
		 */
		 public int GradoS(String V)throws NullPointerException{
		 	int Gra = 0;
			NodoL P = CabL;
			
			while(P!=null){
				if(P.E.V().toString().equals(V) ){
					Gra = Gra+1;
				}
				P = P.Sig;
			}
			return Gra;
		}
		
		/**
		*	Calcula los lados incidentes en un vertice determinado (Grafo NoDirigido)
		*/
		 public LADO[] IncdnN(String V)throws NullPointerException{
		 	LADO[] Str = new LADO[tamL];
			NodoL P = CabL;
			int i = 0;
			while(P!=null){
				if(P.E.V().toString().equals(V) || P.E.W().toString().equals(V) ){
					Str[i]=P.E;
					i = i+1;
				}
				P = P.Sig;
			}
			int tam = 0;
			try {
				while(Str[tam]!=null){
					tam++;
				}
			} catch(NullPointerException E) {
				tam = tamV;
			}
			
			LADO[] Aux = new LADO[tam];
			i=0;
			while(i<tam){
				Aux[i]=Str[i];
				i++;
			}
			
			return Aux;	
		}

		/**
		*	Calcula los lados incidentes en un vertice determinado (Grafo Dirigido)
		*/
		 public LADO[] IncdnD(String V)throws NullPointerException{
		 	LADO[] Str = new LADO[tamL];
			NodoL P = CabL;
			int i = 0;
			while(P!=null){
				if(P.E.W().toString().equals(V)){
					Str[i]=P.E;
					i = i+1;
				}
				P = P.Sig;
			}
			int tam = 0;
			try {
				while(Str[tam]!=null){
					tam++;
				}
			} catch(NullPointerException E) {
				tam = tamV;
			}
			
			LADO[] Aux = new LADO[tam];
			i=0;
			while(i<tam){
				Aux[i]=Str[i];
				i++;
			}
			
			return Aux;	
		}
		
		/**
		*	Calcula los lados Adyacentes en un vertice determinado (Podria devoler Un vertice repetido)
		*/
		 public VERTICE[] Adya(String V)throws NullPointerException{
		 	VERTICE[] Str = new VERTICE[tamL];
			NodoL P = CabL;
			int i = 0;
			while(P!=null){
				if(P.E.V().toString().equals(V) ){
					Str[i]=P.E.W();
					i++;
				} else {
					if(P.E.W().toString().equals(V) ){
						Str[i]=P.E.V();
						i++;
					}
				}
				P = P.Sig;
			}
			int tam = 0;
			try {
				while(Str[tam]!=null){
					tam++;
				}
			} catch(NullPointerException E) {
				tam = tamV;
			}
			
			VERTICE[] Aux = new VERTICE[tam];
			i=0;
			while(i<tam){
				Aux[i]=Str[i];
				i++;
			}
			
			return Aux;			
		}
		
		/**
		*	Calcula los Sucesores de un vertice determinado (Podria devoler uno o varios vertice repetidos)
		*/
		 public VERTICE[] Susc(String V){
		 	VERTICE[] Str = new VERTICE[tamL];
			NodoL P = CabL;
			int i = 0;
			while(P!=null){
				if(P.E.V().toString().equals(V)){
					Str[i]=P.E.W();
					i = i+1;
				}
				P = P.Sig;
			}
			int tam = 0;
			try {
				while(Str[tam]!=null){
					tam++;
				}
			} catch(NullPointerException E) {
				tam = tamL;
			}
			
			VERTICE[] Aux = new VERTICE[tam];
			i=0;
			while(i<tam){
				Aux[i]=Str[i];
				i++;
			}
			
			return Aux;
		 }

		/**
		*	Calcula los Predecesores de un vertice determinado (Podria devoler uno o varios vertice repetidos)
		*/
		 public VERTICE[] Pred(String V)throws NullPointerException{
		 	VERTICE[] Str = new VERTICE[tamL];
			NodoL P = CabL;
			int i = 0;
			while(P!=null){
				if(P.E.W().toString().equals(V)){
					Str[i]=P.E.V();
					i = i+1;
				}
				P = P.Sig;
			}
			int tam = 0;
			try {
				while(Str[tam]!=null){
					tam++;
				}
			} catch(NullPointerException E) {
				tam = tamL;
			}
			
			VERTICE[] Aux = new VERTICE[tam];
			i=0;
			while(i<tam){
				Aux[i]=Str[i];
				i++;
			}
			
			return Aux;
		}
		
		/**
		 **Calcula cual es el lado que tiene asociado dos vertices V y W
		 */
		private LADO LAD(VERTICE V,VERTICE W)throws NullPointerException{
			NodoL P = CabL;
			while(!(P.E.V().equals(V) && P.E.W().equals(W))){
				P=P.Sig;
			}
			return P.E;
		}
		
		protected class NodoV{
			int index;
			NodoV Ant;
			VERTICE V;
			NodoV Sig;
			
			NodoV(NodoV S,VERTICE E,NodoV P){
				Ant = S;
				V = E;
				Sig = P;
			}
		}
		
		protected class NodoL{
			NodoL Ant;
			LADO E;
			NodoL Sig;
			NodoV Vi;
			NodoV Vf;
			
			NodoL(NodoL A,LADO El,NodoL P){
				Ant = A;
				E   = El;
				Sig = P;
			}
		}
	}
}
