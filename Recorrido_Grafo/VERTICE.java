class VERTICE{
	private String V;
	private Propiedades Prop;	
	
	public VERTICE(String T){
		V = T;
		Prop = new Propiedades();
	}
	
	public String toString() {
		return V;
	}

	/**
	* Crea una nueva propiedad
	*/ 
	public void CrearPropiedad(/*Grafo,Vertice*/String S){
		Prop.CrearPropiedad(S);
	}

	/**
	 * Asigna Valor a una propiedad
	 */
	 public void AsignarValor(/*Grafo,Vertice*/String S ,double A){
		Prop.AsigValor(S,A);
	 }

	/**
	* Retorna el el valor de la propiedad especificada
	*/
 	public double ObtenerValor(/*Grafo, Vertice*/String A){
 		return Prop.ObtenerValor(A);
 	}
	
	
	
}