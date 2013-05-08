public class Propiedades {
	public ListaProp LP;	
		
		/*
		 *Constructor
		 */
		 public Propiedades(){
		 	LP = new ListaProp();
		 }

		public Propiedades(Propiedades P){
			LP = P.LP.Clonar();
		}
		/**
		* Retorna el el valor de la propiedad especificada
		*/
 		public double ObtenerValor(/*Grafo, Vertice o lado*/String A){
 			return LP.Busq(A);
 		}
	
		/**
		 * Asigna Valor a una propiedad
		 */
		 public void AsigValor(/*Grafo,Vertice o lado */String S ,double A){
			LP.AsigV(S,A);
		 }
		
		/**
		* Crea una nueva propiedad
		*/ 
		public void CrearPropiedad(/*Grafo,Vertice o lado*/String S){
			LP.InsertP(S);
		}

		/**
		*  Verifica si una propiedad existe
		*/
		public boolean Exist(String S){
			return LP.Exist2(S);
		}

	public class ListaProp{
		protected NodoAr Cab;
		protected NodoAr Col;
		private int tam;
			
		protected ListaProp() {
			Cab = null;
			Col = null;
			tam=0;
		}


		public ListaProp Clonar(){
		//Pre:  true
		//Post: La lista en la salida de caminos en la salida debe ser igual a la de la entrada
			ListaProp T = new ListaProp();
			NodoAr P = Cab;
			while(P!=null){
				T.InsertP(P.Ind);
				T.AsigV(P.Ind,P.Obj);
				P=P.Sig;
			}
			return T;
		}


			
		/**
		*Devuelve el tamano actual de la lista
		*/
		protected int size(){
			return tam;
		}
		
		/**
		*Inserta una Elemento a la lista
		*/
		protected void InsertP(String I){
			if (tam==0){
				Cab = new NodoAr(null,I,0.0,null,0);
				Col = Cab;	
			} else{
				NodoAr Nw = new NodoAr(Col,I,0.0,null,tam);
				Col.Sig=Nw;
				Col = Nw;
			}
			tam++;
		}

		/**
		*Asignar Valor;
		*/
		protected void AsigV(String I, double E){
			NodoAr P = Cab;		
		  try {
			
			while(!(P.Ind.equals(I))){
				P = P.Sig;
			}
			
			P.Obj=E;
			
		  }catch (NullPointerException ER){
		  	System.out.println("La propiedad a la que se quiere asignar valor no ha sido inicializada");
		  	
		  }
		}

		/**
		* Existe la propiedad
		*/
		protected boolean Exist2(String S){
			NodoAr P = Cab;

			while(P!=null && P.Ind!=S){
				P = P.Sig;
			}
			boolean T=false;
			if(P!=null){
				T = true;
			}
	
			return T;
		}
	
		/**
		*   Busca un elemento en la poscicion especificada
		*/
		protected double Busq(String I){
			NodoAr P = Cab;
			double K;
		try {
			while(!(P.Ind.equals(I))){
				P = P.Sig;
			}
			
			K = P.Obj;
			
		} catch (NullPointerException E){
			System.out.println("La propiedad a la que se quiere buscar no ha sido inicializada,, se esta retornando el valor 0");
			K=0.0;
		}
			return K;
		}

		
				
		private class NodoAr{
			int index;
			NodoAr Sig;
			NodoAr Ant;
			String Ind;
			double Obj;
			
			NodoAr(NodoAr A,String T,double R,NodoAr S,int IND){
				Obj = R;
				Ind = T;
				Sig = S;
				Ant = A;
				index = IND;
			}
		}
		
	}


}