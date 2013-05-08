Busqueda De Caminos
===================

Integrantes:

	Jesus Torres
	Christian Socorro

*******************
Para Compilar el Proyecto:

javac ClienteGeneral.java

Para Ejecutar el Proyecto:

java ClienteGeneral <Grafo> <WithAir> <Evitar>

Donde

Grafo, es un Archivo de texto Plano, que contiene el Grafo

WithAir, es un Archivo de Texto plano, que contiene ciudades con Aeropuerto

Evitar, es un Archivo de Texto plano, que contiene caminos que se deben Evitar

*******************
El proyecto era basicamente un proyecto de busqueda en un grafo cualquiera.

Datos:

Existe un Conjunto de Ciudades conectadas entre si por Carreteras (Grafo), 
Existen determinadas Ciudades en esta Red con Aeropuertos (WithAir),
cuando una de las ciudades se le presenta algun brote de enfermedades,
existen dos Empresas que prestan sus servicios para solucionarlos enviando medicinas, GoodNess y Evil.
GoodNess tiene como objetivo ayudar al mundo por lo tanto, busca el camino mas corto (En tiempo y costo) desde alguna ciudad con Aeropuerto hacia el Brote de enfermedad para Solucionarlo,
Evil en cambio, tiene una politica mas interesada, por lo tanto buscara el camino mayor Costoso de llegar al Brote desde alguna ciudad con Aeropuerto.

Adicionalmente por condiciones fisicas, algunos caminos entre ciudades no estan habilitados (Evitar).

El programa debe calcular los caminos tanto de GoodNess como de Evil y 
adicionalmente permitirle a Evil Sabotear a Goodness Eliminando algun camino del grafo.
Debe mostrar una lista de opciones y recalcular caminos luego del saboteo.

********************

Detaller de Implementacion:

Como se nos indico que la ciudad contaminada seria única por corrida, 
entonces nosotros pensamos partir de la ciudad contaminada por los predecesores 
envés de las ciudades con aeropuerto CityWhitAir porque aunque en el peor de los casos, 
que para nosotros es una clausura transitiva, daría igual, 
para el caso promedio de un grafo la complejidad será un poco menor, 
ya que no seria necesario recorrer todo el grafo, solo el nivel del vértice contaminado. 
 
No utilizamos la clase date de java pues nos dimos cuenta que para los requerimientos 
bastaba el uso de System.currentTimeMillis(); que nos refleja el numero de milisegundos 
que han pasado entre la fecha actual y la media noche del 1 de enero de 1970 UTC. 
Como simplemente necesitamos la diferencia entre el principio y el final entonces 
basta preguntar el sistema al principio y al final y restar ambas cantidades 
y nos dará el tiempo utilizado en millisegundos, 
lo dividimos entre mil y ya esta el tiempo en segundos

********************

Extra: 

Para realizar las pruebas se desarrollo un Generador de Grafos, para mas informacion sobre este revisar el Readme en Creador_Grafo_Automatico

Algunos Archivos de Prueba se encuentran en la Carpeta Test