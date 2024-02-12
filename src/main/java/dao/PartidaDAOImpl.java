package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernateConfiguration.HibernateUtil;
import model.Casella;
import model.Fitxa;
import model.Jugador;
import model.Partida;

public class PartidaDAOImpl implements PartidaDAO{

	@Override
	public void iniciarPartida() {
	    EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction transaction = null;

	    try {
	        // Iniciar una transacción
	        transaction = entityManager.getTransaction();
	        transaction.begin();

	        // Crear una nueva partida
	        Partida nuevaPartida = new Partida();

	        // Registrar la fecha de inicio de la partida
	        Date fechaInicio = new java.sql.Date(System.currentTimeMillis());
	        nuevaPartida.setFechaInicio(fechaInicio);

	        // Establecer la condición de "EnCurso"
	        nuevaPartida.setEnCurso(true);

	        // Asignar jugadores a la partida
	        asignarJugadores(nuevaPartida);

	        // Guardar la partida en la base de datos
	        entityManager.persist(nuevaPartida);

	        // Crear las casillas del tablero del Parchís
	        List<Casella> casillas = new ArrayList<>();
	        for (int i = 0; i < 68; i++) { // Parchís tradicional tiene 68 casillas
	            Casella casella = new Casella();
	            casella.setTipoCasilla(obtenerTipoCasilla(i)); // Utiliza el método para obtener el tipo de casilla específico del Parchís
	            casella.setPosicion(i);
	            casella.setPartida(nuevaPartida);
	            casillas.add(casella);
	        }

	        // Guardar las casillas en la base de datos
	        for (Casella casella : casillas) {
	            entityManager.persist(casella);
	        }

	        // Crear y guardar las fichas en la base de datos
	        for (Jugador jugador : nuevaPartida.getJugadores()) {
	            for (Fitxa fitxa : jugador.getFitxes()) {
	                entityManager.persist(fitxa);
	            }
	        }

	        // Confirmar la transacción
	        transaction.commit();

	        // Mostrar un mensaje indicando que la partida de Parchís ha sido iniciada
	        System.out.println("¡La partida de Parchís ha sido iniciada!");

	    } catch (Exception e) {
	        // Si ocurre algún error, hacer rollback de la transacción
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        // Cerrar el EntityManager
	        entityManager.close();
	    }
	}
 

	// Método para obtener el tipo de casilla específico del Parchís según su posición en el tablero
	private String obtenerTipoCasilla(int posicion) {
	    // Implementa la lógica para determinar el tipo de casilla en el juego de Parchís
	    // Aquí puedes definir los tipos de casillas del Parchís, por ejemplo, salida, segura, ocupada, etc.
	    // Puedes adaptar esta lógica según las reglas del Parchís
	    
	    // Las primeras casillas del tablero, después de la salida, suelen ser seguras
	    if (posicion >= 1 && posicion <= 8) {
	        return "Segura";
	    }
	    
	    // Las casillas seguras en el mapa
	    if (posicion % 17 == 0) {
	        return "Segura";
	    }
	    
	    // Las últimas 8 casillas antes de llegar al final de cada color también son seguras
	    if (posicion >= 60 && posicion <= 67) {
	        return "Segura";
	    }
	    
	    // Casillas normales
	    return "Normal";
	}


	private void asignarJugadores(Partida partida) {
	    // Aquí puedes añadir lógica para asignar jugadores a la partida
	    // Por ejemplo, podrías solicitar la información de los jugadores al usuario
	    // o bien, podrías tener una lista predefinida de jugadores
	    
	    // Supongamos que tenemos jugadores predefinidos para este ejemplo
	    List<Jugador> jugadores = new ArrayList<>();
	    jugadores.add(new Jugador("Jugador1", "Rojo"));
	    jugadores.add(new Jugador("Jugador2", "Verde"));
	    jugadores.add(new Jugador("Jugador3", "Azul"));
	    jugadores.add(new Jugador("Jugador4", "Amarillo"));
	    
	    // Asignar los jugadores a la partida
	    partida.setJugadores(jugadores);
	    
	    // Crear y asignar 4 fichas a cada jugador
	    for (Jugador jugador : jugadores) {
	        List<Fitxa> fichas = new ArrayList<>();
	        for (int i = 0; i < 4; i++) {
	            Fitxa fitxa = new Fitxa();
	            fitxa.setPosicion(0); // Posición inicial de la ficha
	            fitxa.setActiva(true); // La ficha está activa al inicio del juego
	            fitxa.setJugador(jugador); // Asignar el jugador a la ficha
	            fitxa.setPartida(partida); // Asignar la partida a la ficha
	            fichas.add(fitxa);
	        }
	        jugador.setFitxes(fichas); // Asignar las fichas al jugador
	    }
	}


	@Override
	public int llancarDaus() {
		//Creem un random de rang 1-6 i ho guardem en la variable dado
		Random rand = new Random();
        int dado = rand.nextInt(6) + 1;

        return dado;
	}
	
	public boolean verificarDausIguals(int dado1, int dado2) {
		//Verifiquem si els dos daus son iguals si ho son retorna true i si no false
        if (dado1 == dado2) return true;
        else return false;
	}

	@Override
	public void moureFitxa(Fitxa fitxa, int quantitat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean capturarFitxa(Fitxa fitxa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int entradaAlTaulell(Fitxa fitxa) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean verificarCasaSegura(int posicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finalitzarRecorregut(Fitxa fitxa, int quantitat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verificarVictoria() {
		// TODO Auto-generated method stub
		return false;
	}
}