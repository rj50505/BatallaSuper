import java.util.Scanner;

abstract class Personaje {
    protected String nombre;
    protected int fuerza;
    protected int velocidad;
    protected int vida_hp;
    protected boolean estaDefendiendo;

    public Personaje(String nombre, int fuerza, int velocidad, int vida_hp) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.vida_hp = vida_hp;
        this.estaDefendiendo = false;
    }

    public void atacar(Personaje objetivo) {
        int daño = this.fuerza;
        if (objetivo.estaDefendiendo) {
            daño /= 2;
            objetivo.estaDefendiendo = false;
        }
        objetivo.vida_hp -= daño;
        System.out.println(this.nombre + " ataca a " + objetivo.nombre + " causando " + daño + " de daño!");
    }

    public void defender() {
        this.estaDefendiendo = true;
        System.out.println(this.nombre + " se prepara para defender!");
    }

    public void recuperar() {
        int recuperacion = 20;
        this.vida_hp += recuperacion;
        System.out.println(this.nombre + " se recupera " + recuperacion + " puntos de vida!");
    }

    public void mostrarEstadisticas() {
        System.out.println("\nEstadísticas de " + this.nombre);
        System.out.println("Vida: " + this.vida_hp);
        System.out.println("Fuerza: " + this.fuerza);
        System.out.println("Velocidad: " + this.velocidad);
    }

    public boolean estaVivo() {
        return vida_hp > 0;
    }

    public String getNombre() {
        return nombre;
    }
}

class SuperHero extends Personaje {
    private int poderEspecial;

    public SuperHero(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp);
        this.poderEspecial = 50;
    }

    public void ataqueEspecial(Personaje objetivo) {
        int dañoEspecial = this.fuerza + this.poderEspecial;
        if (objetivo.estaDefendiendo) {
            dañoEspecial /= 2;
            objetivo.estaDefendiendo = false;
        }
        objetivo.vida_hp -= dañoEspecial;
        System.out.println(this.nombre + " usa su ataque especial contra " + objetivo.nombre + 
                         " causando " + dañoEspecial + " de daño!");
    }

    public void aumentarPoderes() {
        this.fuerza *= 1.5;
        this.poderEspecial *= 1.3;
        System.out.println(this.nombre + " aumenta sus poderes!");
    }
}

class Villano extends Personaje {
    private boolean haTrampeado;

    public Villano(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp);
        this.haTrampeado = false;
    }

    public void hacerTrampa() {
        if (!haTrampeado) {
            this.fuerza *= 2;
            haTrampeado = true;
            System.out.println(this.nombre + " hace trampa y duplica su fuerza!");
        } else {
            System.out.println(this.nombre + " ya ha hecho trampa!");
        }
    }
}

public class BatallaSuper {
    private static SuperHero[] heroes;
    private static Villano[] villanos;
    private static Scanner scanner;

    public static void main(String[] args) {
        inicializarPersonajes();
        scanner = new Scanner(System.in);
        
        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            
            if (opcion == 0) {
                System.out.println("¡Gracias por jugar!");
                break;
            }
            
            iniciarBatalla();
        }
        
        scanner.close();
    }

    private static void inicializarPersonajes() {
        heroes = new SuperHero[]{
            new SuperHero("Superman", 100, 90, 500),
            new SuperHero("Batman", 80, 100, 400),
            new SuperHero("Wonder Woman", 95, 85, 450)
        };

        villanos = new Villano[]{
            new Villano("Lex Luthor", 70, 40, 300),
            new Villano("Cat Woman", 75, 80, 350),
            new Villano("Joker", 65, 70, 250)
        };
    }

    private static void mostrarMenu() {
        System.out.println("\n=== BATALLA DE SUPERHÉROES ===");
        System.out.println("1. Iniciar nueva batalla");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void mostrarPersonajes(Personaje[] personajes) {
        for (int i = 0; i < personajes.length; i++) {
            System.out.println((i + 1) + ". " + personajes[i].getNombre());
        }
    }

    private static void iniciarBatalla() {
        System.out.println("\nElige un superhéroe:");
        mostrarPersonajes(heroes);
        int heroIndex = scanner.nextInt() - 1;

        System.out.println("\nElige un villano:");
        mostrarPersonajes(villanos);
        int villanoIndex = scanner.nextInt() - 1;

        if (heroIndex >= 0 && heroIndex < heroes.length && 
            villanoIndex >= 0 && villanoIndex < villanos.length) {
            
            realizarBatalla(heroes[heroIndex], villanos[villanoIndex]);
        } else {
            System.out.println("Selección inválida");
        }
    }

    private static void realizarBatalla(SuperHero heroe, Villano villano) {
        System.out.println("\n¡Comienza la batalla entre " + heroe.getNombre() + 
                          " y " + villano.getNombre() + "!");
        
        heroe.mostrarEstadisticas();
        villano.mostrarEstadisticas();

        // Simulación de batalla
        int ronda = 1;
        while (heroe.estaVivo() && villano.estaVivo()) {
            System.out.println("\n=== Ronda " + ronda + " ===");
            
            // Turno del héroe
            mostrarMenuAcciones("héroe");
            int accionHeroe = scanner.nextInt();
            realizarAccionHeroe(heroe, villano, accionHeroe);

            if (!villano.estaVivo()) {
                System.out.println("\n¡" + heroe.getNombre() + " ha vencido!");
                break;
            }

            // Turno del villano
            mostrarMenuAcciones("villano");
            int accionVillano = scanner.nextInt();
            realizarAccionVillano(villano, heroe, accionVillano);

            if (!heroe.estaVivo()) {
                System.out.println("\n¡" + villano.getNombre() + " ha vencido!");
                break;
            }

            ronda++;
        }

        // Mostrar estadísticas finales
        System.out.println("\nEstadísticas finales:");
        heroe.mostrarEstadisticas();
        villano.mostrarEstadisticas();
    }

    private static void mostrarMenuAcciones(String tipo) {
        System.out.println("\nAcciones disponibles para el " + tipo + ":");
        System.out.println("1. Atacar");
        System.out.println("2. Defender");
        System.out.println("3. Recuperar");
        if (tipo.equals("héroe")) {
            System.out.println("4. Ataque Especial");
            System.out.println("5. Aumentar Poderes");
        } else {
            System.out.println("4. Hacer Trampa");
        }
        System.out.print("Elige una acción: ");
    }

    private static void realizarAccionHeroe(SuperHero heroe, Villano villano, int accion) {
        switch (accion) {
            case 1: heroe.atacar(villano); break;
            case 2: heroe.defender(); break;
            case 3: heroe.recuperar(); break;
            case 4: heroe.ataqueEspecial(villano); break;
            case 5: heroe.aumentarPoderes(); break;
            default: System.out.println("Acción inválida");
        }
    }

    private static void realizarAccionVillano(Villano villano, SuperHero heroe, int accion) {
        switch (accion) {
            case 1: villano.atacar(heroe); break;
            case 2: villano.defender(); break;
            case 3: villano.recuperar(); break;
            case 4: villano.hacerTrampa(); break;
            default: System.out.println("Acción inválida");
        }
    }
}