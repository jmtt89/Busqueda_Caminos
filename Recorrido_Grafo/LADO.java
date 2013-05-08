abstract class LADO{
	protected String E;
	protected VERTICE V;
	protected VERTICE W;
	protected Propiedades Prop;
	
	protected LADO(String E, VERTICE V,VERTICE W){
		this.E = E; 
		this.V = V;
		this.W = W; 
		Prop = new Propiedades();
    }

    public VERTICE V() { return V; }
    
    public VERTICE W() { return W; }
    
    public String toString() { return E; }

	/**
	* Crea una nueva propiedad
	*/ 
	public void CrearPropiedad(/*Grafo,Lado*/String S){
		Prop.CrearPropiedad(S);
	}

	/**
	 * Asigna Valor a una propiedad
	 */
	 public void AsignarValor(/*Grafo,Lado*/String S ,double A){
		Prop.AsigValor(S,A);
	 }

	/**
	* Retorna el el valor de la propiedad especificada
	*/
 	public double ObtenerValor(/*Grafo, Lado*/String A){
 		return Prop.ObtenerValor(A);
 	}

}