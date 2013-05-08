public class ARCO extends ARISTA{
	private VERTICE Ini;
	private VERTICE Fin;	
	
	public ARCO (String E, VERTICE V,VERTICE W){
		super(E,V,W);
		Ini = V;
		Fin = W;
	}
	
	public VERTICE Vini() { return Ini; }
    
    public VERTICE Fnl() { return Fin; }
    
	
}