// Camino de mayor costo
import java.util.LinkedList;

public class Act1 extends MGE {
    String propV;
    String propA;

	/**
	 *  Constructor
         *
         * los campos propV y propA se inicializan, estos campos son los que se
         * van a tomar como costos en el grafo para conseguir el camino de mayor
         * costo
	 */
	public Act1 (String propVert, String propArco){
        propV = propVert;
        propA = propArco;

	}

        /**
         * @param T, LinkedList que contiene caminos de los que se quiere Seleccionar el de mayor costo
         * @return, Camino de mayor costo del LinkedList de entrada
         */
	protected Camino SeleccionarCam(LinkedList T){
		Camino C;
		int i = 0;
		double p = -9999999;
		Camino S = null;
		double k;
                /**
                 * Solamente compara los costos de todos los caminos hasta que
                 * consigue el mayor, misma idea de burbuja en su primera
                 * iteracion
                 */
		while (i<T.size()){
			C = (Camino)T.get(i);
			k = C.ObtenerValor("Costo");
			if (p<k){
				p = C.ObtenerValor("Costo");
				S = C;
			}
			i++;
		}

		return S;
	}

	/**
         *
         * @param CC, Camino al que se quiere Calcular sus Atributos
         * @return, El camino que entro con los atributos ya calculados
         */
	protected  Camino Atributs(Camino CC){

            /**
             * En este caso el atributo simplemente sera el costodel camino
             */
		Camino C = CC;
		int i = 1;
		VERTICE V;
		LADO    L;
		double d=0.0;
		V = C.nodeAt(0);
		d = V.ObtenerValor(propV);
		int k = (C.length())/(2);

                // calcula el costo del camino sumando el valor de cada vertice
                // Con cada arco del camino
		while (i< k+1){
			V = C.nodeAt(i);
			L = C.nodeLAD(i);
			if (L!=null){
				d = d + (V.ObtenerValor(propV)+L.ObtenerValor(propA));
			}
			i++;
		}

		if(!(C.Exist34("Costo"))){
			C.CrearPropiedad("Costo");
		}

		C.AsignarValor("Costo",d);

		return C;
	}

        /**
         * @param AA, Arreglo de caminos en los que se va a correr la rutina de Eliminacion
         */
	protected void Eliminar(Camino[] AA){

            //La rutina de eliminacion es la misma que de Dikstra solo que
            //Considerando mayor en lugar de menor

		Camino[] A = VerifCE(AA);
		Camino Aux = new Camino();
                VERTICE Aux2;

		VERTICE[] T = new VERTICE[A.length];

                //Crea un Arreglo T con el vertice final de todos los caminos
                //validos de los predecesores
		int i = 0;
		while(i<A.length){
			T[i]=A[i].VertFin();
			i++;
		}

		int j = 0;
		boolean R;
                /**
                 * Compara cada uno de estos vertices finales con los vertices
                 * finales de la lista de abiertos y la de cerrados
                 */
		while(j<T.length){

			i=0;
			R=false;
			while(i<Cr.size()+Ab.size()&&!R){
				if(i<Cr.size()){
                                        Aux2 = ((Camino)Cr.get(i)).VertFin();
					R=T[j].toString().equals(Aux2.toString());
					if(R){
						Aux = (Camino)Cr.get(i);
					}
				} else {
                                        Aux2 = ((Camino)Ab.get(i-Cr.size())).VertFin();
					R=T[j].toString().equals(Aux2.toString());
					if(R){
						Aux = (Camino)Ab.get(i-Cr.size());
					}
				}
				i++;
			}

                        /**
                         * Si encuentra alguno que sea igual compara su costo
                         */
			if(R){
				if(Aux.LP.ObtenerValor("Costo")<A[j].LP.ObtenerValor("Costo")){
                                    /**
                                     * En caso de que sea mayor el costo si el
                                     * camino se encontraba cerrado lo elimina
                                     * y agrega el nuevo a la lista de abiertos
                                     */
					if(i>Cr.size()){
						Ab.remove(Aux);
						Ab.addFirst(A[j]);
					}
                                    /**
                                     * En el caso de que sea mayor el costo a un
                                     * camino que estuviera en la lista de
                                     * abiertos se elimina y se agrega en su
                                     * lugar el nuevo
                                     */
					if(i<Cr.size()){
						Cr.remove(Aux);
						Ab.addFirst(A[j]);
					}
				}
			}
                        /**
                         * En el caso en que no se encuentre ningun vertice
                         * terminal se agrega a la lista de abiertos este camino
                         */
			if(!R){
				Ab.addFirst(A[j]);
			}
			j++;
		}
	}


        //Verifica condicion de camino elemental
        /**
         * @param HJ, Lista de caminos que se va a verificar elemtalidad
         * @return, Solamente caminos que sean elementales
         */
       private Camino[] VerifCE(Camino[] HJ){
           VERTICE Aux;
           boolean R=false;

           /**
            * Esta funcion simplemente toma el ultimo vertice de cada camino
            * y lo compara con todos los anteriores
            */
           for(int i=0;i<HJ.length;i++){
               R = false;
               Aux = HJ[i].VertFin();
               for(int j=0;j<(HJ[i].length()/2) && !R ; j++){
                   R = (HJ[i].nodeAt(j).toString()).equals(Aux.toString());
               }
               //Si encuentra alguo que sea igual lo hace null
               if (R){
                   HJ[i]=null;
               }
           }

           //Elimina todos las posiciones que sean null
        int N=0;
        for(int i=0;i<HJ.length;i++){
            if(HJ[i]!=null){
                N++;
            }
        }

        Camino[] Cn = new Camino[N];
        int j=0;
        for(int i=0;i<HJ.length;i++){
            if(HJ[i]!=null){
                Cn[j]=HJ[i];
                j++;
            }
        }
        return Cn;

        }
}