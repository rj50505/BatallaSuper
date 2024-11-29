# BatallaSuper 
Este juego consiste en una simulacion de batalla entre superheroes y villanos.
En terminos de caracteristicas generales del juego podemos encontrar que:
- Existen 2 tipos de personaje (villano y superheroe) los cuales poseen diferentes habilidades tanto propias como compartidas. Entre las habilidades presentes en los personajes podemos encontrar:
* Atacar al oponente.
* Defenderse (esta habilidad reduce el ataque del oponente).
* Recuperar vida.
* Habilidades especiales: AUMENTO DE PODER y ATAQUE ESPECIAL en el caso del superheroe; y HACER TRAMPA en el caso del villano.
- La batalla se va realizando por medio de rondas, que funcionan hasta que alguno de los personajes pierda toda la vida.
- Las batallas se llevan a cabo segun el criterio del jugador y las habilidades que vaya seleccionando.
- Se muestra tambien un recuento de estadisticas antes de empezar la batalla donde se muestran todos los atributos de los personajes (vida, fuerza,etc); recuento que tambien es mostrado cuando finaliza la batalla para que el jugador pueda ver las estadisticas finales con las que terminaron cada uno de los personajes.

Si hablamos de la forma de jugar, es muy sencilla.
Primero se presenta una lista de superheroes y villanos (con un numero asignado) para que el jugador escoja cuales personajes entraran en batalla.
Luego de escoger los personajes, se muestran las estadisticas iniciales seguido de un menu donde se muestra al jugador las habilidades disponibles inicialmente con el superheroe quien es el que inicia la batalla.
Seguido se muestra nuevamente el menu de habilidades, pero esta vez con el villano; y esta accion se va a ir turnando sucesivamente hasta que alguno de los 2 personajes se quede sin vida.
Cuando esto finalmente suceda, se mostrara el personaje vencedor junto con las estadisticas finales de la batalla.

En terminos de estructura del codigo, tenemos 4 clases presentes:
- Personaje: La cual se podria decir que es la clase madre de las siguientes 2 clases, ya que les permite heredar diferentes atributos.
- Superheroe: Siendo una extension de la clase "Personaje", hereda sus atributos y tambien se definen atributos propios para esta clase.
- Villano: Basicamente viene siendo lo mismo que la clase "Superheroe", una extension de la clase "Personaje" que hereda atributos y a su vez posee atributos propios, algunos similares y otros diferentes a los de la clase "Superheroe"
- BatallaSuper: Esta es la clase principal del codigo y donde basicamente se ejecuta el juego, ademas de ser la que permite la interaccion con el jugador.












