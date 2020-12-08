<h1 align="center">MegaChess - <a href="https://www.linkedin.com/in/agustin-fiordelisi/" target="_blank">Fiordelisi Agustín Noé</a></h1>
<p align="left"> Si estás viendo esto, bienvenido. Este Megachess paso por mucho cambios a lo largo de su creación, esto fue por las estrategias implementadas por mis distintos compañeros. A continuación se explica la versión final</p>

<h3 align="center">Estrategia Planteada</h3>
<p align="left"> 
-Primero que nada evaluó si alguna de mis piezas puede comer alguna pieza del enemigo que no sea un peón (**ya que si como un peón expongo la pieza que fue a comerlo y no se justifica en cuanto a puntajes).
</p>
<p align="left"> 
-En caso que nadie pueda defender busco reinas para que hagan su misión de avanzar una casilla para adelante, con el objetivo de esperar que otros peones coronen o que se arrinconen a la izquierda (**va a ser el sector designado para esconder las reinas y evitar que queden expuestas ante peones enemigos).
</p>
<p align="left"> 
-En caso que ninguna de mis piezas pueda comer y las reinas ya hayan cumplido su misión o no existan reinas en el medio del tablero, procedo con la estrategia de hacer avanzar los peones de a grupos de 4 para que se protejan entre si hasta que lleguen a ser reinas. El avance de los grupos tiene un orden establecido para evitar exponer las piezas más importantes del fondo de mi tablero. Primero avanza uno de los grupos de peones de los caballos, luego el otro, luego un grupo de los alfiles, luego el otro, luego las torres, luego los de las reinas y finalmente el grupo de los reyes. Por lo general las partidas no duran tanto para que se llegue a esta instancia.
</p>
<p align="left"> 
-En caso que ya no haya más opciones, procedo a mover o los reyes o los alfiles o los caballos. Si estamos en este punto asumo que no hay más reinas ni torres ni peones
</p>
<a href="https://github.com/agustinfiorde/MegaChess/blob/master/src/main/java/app/megachess/AI/Intelligence.java" target="_blank"><p align="left"> 
**Para mayor entendimiento de lo mencionado anteriormente, recomiendo ver la siguiente clase, donde esta documentado con mayor nivel de detalle.
</p></a>

<h3 align="center">Desarrollo Logrado</h3>
<p align="left"> 
-Todas las piezas tiene la capacidad de hacer algo sin romper el reglamento establecido
</p>
<p align="left"> 
-Todos los mensajes desde el Web Socket pueden ser procesados, pero solo se le da importancia a “ask_challenge” y “your_turn”
</p>
<p align="left"> 
-Test unitarios en el 100% de todos los métodos y funciones donde hubo necesidad de aplicarlos
</p>
<a href="https://github.com/agustinfiorde/MegaChess/blob/master/img/UML.jpg" target="_blank"><img src="https://github.com/agustinfiorde/MegaChess/blob/master/img/UML.jpg?raw=true" alt="spring" width="100%" height="100%"/></a>

<h3 align="center">Tests</h3>
<h5 align="center">La cobertura de test unitarios es la siguiente según clases y paquetes:</h5>

<p align="left"> 
Connection: 100%
</p>
<p align="left"> 
WebSocket : 0% (**métodos y funciones extraidos de un ejemplo de @ClientEndPoint )
</p>
<p align="left"> 
ChessUtil : 100%
</p>
<p align="left"> 
Util : 100%
</p>
<p align="left"> 
Intelligence: 100%
</p>
<p align="left"> 
Package.AI.pieces : 100% en todas las clases
</p>

<a href="https://github.com/agustinfiorde/MegaChess/blob/master/img/UnitTests.png" target="_blank"><img src="https://github.com/agustinfiorde/MegaChess/blob/master/img/UnitTests.png?raw=true" alt="spring" width="100%" height="100%"/></a>

<h3 align="center">Muchas gracias por visitar el proyecto <img src="https://raw.githubusercontent.com/verma-anushka/verma-anushka/master/gifs/wave.gif" width="30px" style="max-width:100%;"></h3>
