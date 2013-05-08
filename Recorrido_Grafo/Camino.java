public class Camino {
	public ListaCam LC;	
	public Propiedades LP;
	
	/**
	 **Constructor
	 */
	public Camino(){
		LC = new ListaCam();
		LP = new Propiedades();
	}
	
	public Camino(Camino P){
		LC = P.LC.Clonar();
		LP = new Propiedades();
		LP.CrearPropiedad("Costo");
		double a = 0.0;
		if((P.Exist34("Costo"))){
			a = P.ObtenerValor("Costo");
		}
		
		LP.AsigValor("Costo",a);
	}
	
	/**
	 **  Regresa la longitud del camino (Tanto Vertices como Lados se cuentan)
	 */		
	public int length(){
	// Pre:  La lista debe estar inicializada
	// Post: el numero de lados del camino
		return LC.size();
	}

	
	/**
	 ** Retorna el elemento en la posicion especificada
	 */
	public VERTICE nodeAt(int A){
	//Pre:  El elemnto buscado debe estar en la lista
	//Post: El Elemento buscado corresponde con el vertice en posicion A
		VERTICE T ;
		try {
			T = (VERTICE)LC.Busq(A*2);
		} catch (NullPointerException E){
			//Mensaje de error para usuario
			System.out.println("El indice buscado no pertenece al camino, se regresa Null");
			T=null;
		}
		return T;
	}

	/**
	 ** Retorna el elemento en la posicion especificada
	 */
	public LADO nodeLAD(int A){
	//Pre:  El elemnto buscado debe estar en la lista
	//Post: El Elemento buscado corresponde con el vertice en posicion A
		LADO T ;
		try {
			T = (LADO)LC.Busq((A*2)-1);
		} catch (NullPointerException E){
			//Mensaje de error para usuario
			System.out.println("El indice buscado no pertenece al camino, se regresa Null");
			T=null;
		}
		return T;
	}

	/**
	* Crea una nueva propiedad
	*/ 
	public void CrearPropiedad(/*Grafo,Vertice*/String S){
		LP.CrearPropiedad(S);
	}

	/**
	 * Asigna Valor a una propiedad
	 */
	 public void AsignarValor(/*Grafo,Vertice*/String S ,double A){
		LP.AsigValor(S,A);
	 }

	/**
	* Retorna el el valor de la propiedad especificada
	*/
 	public double ObtenerValor(/*Grafo, Vertice*/String A){
 		return LP.ObtenerValor(A);
 	}

	/**
	*  Verifica si una propiedad existe
	*/
	public boolean Exist34(String S){
		return LP.Exist(S);
	}	

	
	public VERTICE VertFin(){
	//Pre:  El camino debe tener almenos un elemento
	//Post: es el ultimo vertice del camino
		VERTICE T ;
		try {
			T = (VERTICE)LC.VerFin();
		} catch (NullPointerException E){
			//Mensaje de error para usuario
			System.out.println("El camino esta Vacio, regresa Null");
			T=null;
		}
		return T;
	}
	
	/**
	 ** Insertar en lista que referencia a un camino
	 ** no verifica el insertar simplemente inserta Lado y vertice
	 */
	public void append(/*Camino,*/LADO A, VERTICE V){
	//Pre:  La lista esta iniciada
	//Post: Todos los elementos de la lista estan en la misma posicion que cuando entro pero
	//		el ultimo elemento es el vertice y el penultimo es El Lado recien insertada
	
		//Si se quiere insertar en una lista vacia se debe insertar solamente un vertice que representa el inicio
		if (LC.size()==0) {
			LC.Insert(V);
		} else {
			LC.Insert(A);
			LC.Insert(V);
		}
	}
	
	
	/**
	 **Elimina del camino el vertice de la lista y su lado asociado
	 */
	public void delete(){
	//Pre:  La lista tiene almenos 3 elementos
	//Post: La lista es igual a como entro pero con 2 elementos menos, un lado y un vertice
		try{
			LC.Eliminar();
			LC.Eliminar();
		}catch (NullPointerException E){
			// Mensaje de error al usuario
			System.out.println("ERROR: Se desea Eliminar mas de los elementos que posee el camino");
		}
	}
	
	/**
	 ** Muestra por pantalla el camino
	 */
	public void Print(){
	//Pre:  La lista este inicializada
	//Post: true
		String[] A = LC.ElemtCAM();
		for(int i=0;i<LC.size();i++){
			System.out.print(A[i]+" ");
		}
		System.out.println();
	}
		
		/**
		 ** Lista doble enlazada que representa un camino
		 */
		public class ListaCam{
			public NodoCam Cab;
			public NodoCam Col;
			public int tam;
		
			public ListaCam() {
				Cab = null;
				Col = null;
				tam=0;
			}
			
			
			public ListaCam Clonar(){
			//Pre:  true
			//Post: La lista en la salida de caminos en la salida debe ser igual a la de la entrada
				ListaCam T = new ListaCam();
				NodoCam P = Cab;
				while(P!=null){
					T.Insert(P.Obj);
					P=P.Sig;
				}
				return T;
			}
			
			/**
			 **Devuelve el tamano actual de la lista
			 */
			public int size(){
				//Pre:  La lista debe estar inicializada
				//Post: no hay ningun index mayor o igual a tam
				return tam;
			}
			
			/**
			 **Inserta una Elemento a la lista
			 */
			public void Insert(Object E){
			//Pre:  La Lista esta iniciada
			//Post: La lista tiene un elemento mas que antes y todos los anteriores estan en la misma posicion que antes
				if (tam==0){
					Cab = new NodoCam(null,E,null,0);
					Col = Cab;	
				} else{
					NodoCam Nw = new NodoCam(Col,E,null,tam);
					Col.Sig=Nw;
					Col = Nw;
				}
				tam++;
			}
			
			/**
			 **Busca un elemento en la poscicion especificada
			 */
			public Object Busq(int I)throws NullPointerException{
			//Pre:  El indice buscado es menor a tam
			//Post: El elemnto que devuelve posee el indice I en el camino 
				NodoCam P = null;
				//si tam es mas pequeno o igual al indice buscado no vale la pena buscarlo porque se va a salir
				if(tam > I ){
					P = Cab;
					while(!(P.index==I)){
					P = P.Sig;
					}
				}
				return P.Obj;
			}
			
			/**
			 **Elimina El ultimo de la lista
			 */
			public void Eliminar()throws NullPointerException{
			//Pre:  La lista tiene almenos un elemento;
			//Post: la lista es igual que al principio pero sin el ultimo elemento y el tam es uno menos
				Col = Col.Ant;
				Col.Sig.Ant=null;
				Col.Sig = null;
				tam--;
			}
			
			/** 
			 **Devuelve un arreglo con el identificador de todos los elementos de la lista
			 */
			public String[] ElemtCAM(){
			//Pre:  La lista debe estar inicializada
			//Post: Todos los identificadores de lal ista estan en el arreglo de salida
				NodoCam P = Cab;
				String[] Str = new String [tam];
				int i=0;
				while (P!=null){
					Str[i]=String.valueOf(P.Obj.toString());
					P=P.Sig;
					i=i+1;
				}
				return Str;
			}
			
			/**
			 **Devuelve el ultimo Vertice del Camino
			 */
			 public Object VerFin(){
			 //Pre:  El camino tiene almenos un Vertice
			 //Post: El Vertice que devuelve es el ultimo del camino
			 	return Col.Obj;
			 }
			
			/**
			 **Clase Nodo que representa cada cajita donde se guarda un elemento del camino
			 */
			public class NodoCam{
				public int index;
				public NodoCam Sig;
				public NodoCam Ant;
				public Object Obj;
			
				public NodoCam(NodoCam A,Object R,NodoCam S,int IND){
					Obj = R;
					Sig = S;
					Ant = A;
					index = IND;
				}
			}
			
		}	
}