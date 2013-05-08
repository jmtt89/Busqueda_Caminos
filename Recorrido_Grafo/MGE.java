
import java.util.LinkedList;

abstract class MGE {

    protected LinkedList Ab;
    protected LinkedList Cr;
    //Agrego Dos LinkedList Mas que se encargan de almacenar
    //Cuales son los vertices prohibidos y cuales son los de "Partida"
    protected LinkedList Partida;
    protected LinkedList Prohibidos;

    protected abstract Camino Atributs(Camino C);

    protected abstract void Eliminar(Camino[] A);

    protected abstract Camino SeleccionarCam(LinkedList S);

    public LinkedList Ejecutar(DIRGRAFO G, VERTICE V) {
        VERTICE[] T;
        VERTICE A;
        Camino R;
        Camino RT;
        Camino[] Sus;
        int i;

        //Las listas de abiertos y cerrados estan vacias
        Ab = new LinkedList();
        Cr = new LinkedList();

        //Inicia el camino cuyo unico elemento es V
        R = new Camino();
        R.append(null, V);
        // Calcula los atributos del camino cuyo unico elemento es V
        R = Atributs(R);
        // Lo agraga el camino a la lista de abiertos
        Ab.addFirst(R);

        //Mientras la lista de abiertos no este vacia hacer:
        while (!Ab.isEmpty()) {

            //Seleccionar Camino Abierto(Abstracto)
            // Entra:  La lista de Caminos Abiertos
            // Salida: El camino Elegido, NO borra el Camino de la lista de Abiertos
            R = SeleccionarCam(Ab);

            //Elimina el Camino elegido de la lista de abiertos
            Ab.remove(R);

            //Agrega el camino Elegido a la lista de Cerrados
            Cr.addFirst(R);

            //Obtiene los Sucesores del ultimo vertice del camino elegido
            A = R.VertFin();
            T = G.Sucesores(A.toString());
            Sus = new Camino[T.length];
            i = 0;
            //Construye los caminos extendidos de los sucesores
            while (i < T.length) {
                RT = new Camino(R);
                RT.append(G.LAD(A, T[i]), T[i]);
                Sus[i] = RT;
                i++;
            }

            //Calcula los atributos de cada camino(Abstracto);
            //Entrada: Un Camino
            //Salida:  Un camino con el atributo que se quiere
            i = 0;
            while (i < Sus.length) {
                Sus[i] = Atributs(Sus[i]);
                i++;
            }

            //Para cada camino de los caminos creados hacer rutina de eliminacion(Abstracto)
            //Entrada: El camino al que se le va a aplicar la rutina de eliminacion
            //Salida:  La lista de Abiertos
            Eliminar(Sus);
        }
        return Cr;
    }

    

    /**
     * @param G, Es el Grafo Dirigido de entrada
     * @param V, Es el identificador del Vertice de la ciudad Contaminada
     * @param C, Posee todas las ciudades pertenecientes a CityWithAir (String)
     * @param D, Posee todas las ciudades por las que no se desea pasar (String)
     * @return , se regresa El camino resultante
     */

    public Camino Ejecutar(DIRGRAFO G, String V, LinkedList C,LinkedList D) {

        //Pre :  Los parametros de entrada son correctos
        //Post:  El camino que regresa es el camino de Mayor/Menor costo en el
        //       grafo desde un aeropuerto WhitAir hasta la ciudad Contaminada


        /*
         * El Agoritmo parte de la idea de que la ciudad Contaminda es Unica
         * Entonces envez de partir de las ciudades Con aeropuerto hasta la ciudad
         * contaminda se parte de la ciudad contaminda que es unica viajando
         * por los predecesores hasta las ciudades con aeropuerto WhitAir,
         * de esta manera se ahorra bastante en caso de Grafos no conexos
         */


        VERTICE[] T;
        VERTICE A;
        Camino R;
        Camino RT;
        Camino[] Pred;
        int i;

        //Las listas de abiertos y cerrados estan vacias
        Ab = new LinkedList();
        Cr = new LinkedList();

        //Vertices con aeropuertos
        Partida = C;
        //Vertices Prohibidos
        Prohibidos = D;
        
        //Arreglo con todos los vertices del Grafo
        T = G.Vertices2();

        // Busca el vertices de partida (Ciudad Contaminada)
        R = new Camino();
        int k=0;
        for(k =0;k<T.length && !(T[k].toString().equals(V)) ;k++){
        }
        R.append(null, T[k]);
        R = Atributs(R);
        Ab.add(R);

        //Mientras la lista de abiertos no este vacia hacer:
        while (!Ab.isEmpty()) {

            //Seleccionar Camino Abierto(Abstracto)
            // Entra:  La lista de Caminos Abiertos
            // Salida: El camino Elegido, NO borra el Camino de la lista de Abiertos
            R = SeleccionarCam(Ab);
            //Elimina el Camino elegido de la lista de abiertos
            Ab.remove(R);

            //Agrega el camino Elegido a la lista de Cerrados
            Cr.add(R);

            //Obtiene los Predecesores del ultimo vertice del camino elegido
            A = R.VertFin();
            T = G.Predecesores(A.toString());

            // Elimina los Vertices prohibidos
            T=DELVP(T);


            Pred = new Camino[T.length];
            i = 0;
            //Construye los caminos extendidos de los predecesores
            while (i < T.length) {
                RT = new Camino(R);
                RT.append(G.LAD(T[i], A), T[i]);
                Pred[i] = RT;
                i++;
            }




            //Calcula los atributos de cada camino(Abstracto);
            //Entrada: Un Camino
            //Salida:  Un camino con el atributo que se quiere
            i = 0;

            while (i < Pred.length) {
                Pred[i] = Atributs(Pred[i]);
                i++;
            }

            //Para cada camino de los caminos creados hacer rutina de eliminacion(Abstracto)
            //Entrada: El camino al que se le va a aplicar la rutina de eliminacion
            //Salida:  La lista de Abiertos
            
            Eliminar(Pred);

        }

        //Regresa solamente el camino de menor o mayor costo segun se pida
        return Valid();
    }


    /**
     * Esta funcion busca en la lista de cerrados
     *
     * @return el camino de menor o mayor costo desde la ciudad contaminada a
     * la ciudad con aeropuerto Whitair e invierte el sentido del camino
     * ya que el ejecutar funciona con predecesores y el camino queda invertido
     *
     */
    private Camino Valid(){
        //Pre:
        //Post:
        boolean R=false;
        LinkedList Aux = new LinkedList();
        Camino A = new Camino();
        Camino C = new Camino();
        String B;

        /**
         * Busca en la lista de cerrados los caminos que tengan vertice terminal
         * igual a alguna de las ciudades Whitair
         */
        for(int i=0;i<Cr.size()&&!R;i++){
            A =(Camino)Cr.get(i);
            R = false;
            for(int j=0;j<Partida.size() && !R;j++){
                B = (String)Partida.get(j);
                R=(A.VertFin().toString()).equals(B);
                if(R){
                    /**
                     * Cuando los encuentra los va almacenando en unal ista Aux
                     */
                    Aux.add(A);
                }
            }
        }

        //Luego selecciona el de mayor o menor costo segun convenga
        A = SeleccionarCam(Aux);

        //Invierte el Camino
        C.append(null, A.VertFin());
        for(int i = (A.length()/2);i>0;i--){
            C.append(A.nodeLAD(i), A.nodeAt(i-1));
        }
        //Le Agrega el costo
        C.CrearPropiedad("Costo");
        C.AsignarValor("Costo", A.ObtenerValor("Costo"));

        //Lo retorna
        return C;
    }


    /**
     * @param C, Arreglo de Vertices pertenecientes a los Predecesores de otro Vertice
     * @return, Arreglo de Vertices sin los Vertices Prohibidos;
     */
    protected VERTICE[] DELVP(VERTICE[] C){
        //Pre:  true
        //Post: Los vertices en el arreglo de salida no son iguales a los
        //      Vertices prohibidos


        boolean R=false;
        String V;
        VERTICE A;
        int i=0;
        int j;

        /**
         * Compara todos los Vertices de el Arreglo C con los de el LinkedList
         * de prohibidos
         */

        while(i<C.length){
            A =C[i];
            j=0;
            R=false;
            while(j<Prohibidos.size() && !R){
                V = (String)Prohibidos.get(j);
                R = A.toString().equals(V);
                j++;
            }

            //Si encuentra alguno que sea igual en esa posicion agrega null
            if(R){
                C[i]=null;
            }
            i++;
        }

        //Elimina las posiciones null del Arreglo dejando solo los vertices
        //Que no son Prohibidos
        int N=0;
        for( i=0;i<C.length;i++){

            if(C[i]!=null){
                N++;
            }
        }
        VERTICE[] Cn = new VERTICE[N];
        j=0;
        for( i=0;i<C.length;i++){
            if(C[i]!=null){
                Cn[j]=C[i];
                j++;
            }
        }
        return Cn;
    }
}
