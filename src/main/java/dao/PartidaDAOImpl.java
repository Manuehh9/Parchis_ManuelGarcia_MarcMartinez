package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
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
	
	private List<Jugador> jugadores;
	private EntityTransaction transaction;
	private EntityManager entityManager;
	private static String NomGanador;
	private HibernateUtil hu = new HibernateUtil();
	private final int FINAL_CASILLA = 68;

	@Override
	public void iniciarPartida() {
	    entityManager = hu.getEntityManagerFactory().createEntityManager();
	    transaction = null;

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
	    
	    do {
	    	for (Jugador jugador : jugadores) {
	            for (Fitxa fitxa : jugador.getFitxes()) {
	                finalitzarRecorregut(fitxa, FINAL_CASILLA);
	            }
	        }
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i < jugadores.size(); i++) {
				boolean turnos = true;
				Fitxa f;
				do {
					int numFitxa = Turno(jugadores.get(i));
					f = jugadores.get(i).getFitxes().get(numFitxa);
					if(f.isMovible()) {
						turnos = false;
					}
				} while (turnos);
				if(!entradaAlTaulell(f) && f.isActiva() && f.isMovible()) {
						moureFitxa(f, llancarDaus());
						boolean comprobar = true;
						do {
							for (Jugador jugador : jugadores) {
					            for (Fitxa fitxa : jugador.getFitxes()) {
					                if(fitxa.getPosicion() == f.getPosicion() && !fitxa.getJugador().getColor().equals(f.getJugador().getColor())) {
					                	if(fitxa.isActiva() && f.isActiva()) {
					                		fitxa.setPosicion(0);
					                		fitxa.setActiva(false);
					                		fitxa.setMovible(false);
					                		moureFitxa(fitxa, 20);
					                	}
					                } else {
					                	comprobar = false;
					                }
					            }
					        }
						} while (comprobar);	
				}
				for (Jugador jugador : jugadores) {
		            for (Fitxa fitxa : jugador.getFitxes()) {
		                System.out.println(fitxa);
		            }
		        }
				System.out.println("------------------------------------------------------------------------------------------------------------------");
			}
		} while (!verificarVictoria());
	    
	    System.out.println("Guanyador: " + NomGanador);	    
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
	    jugadores = new ArrayList<>();
	    jugadores.add(new Jugador("Jugador1", "Rojo", partida));
	    jugadores.add(new Jugador("Jugador2", "Verde", partida));
	    jugadores.add(new Jugador("Jugador3", "Azul", partida));
	    jugadores.add(new Jugador("Jugador4", "Amarillo", partida));
	    
	    // Asignar los jugadores a la partida
	    partida.setJugadores(jugadores);
	    
	    // Crear y asignar 4 fichas a cada jugador
	    for (Jugador jugador : jugadores) {
	        List<Fitxa> fichas = new ArrayList<>();
	        for (int i = 0; i < 4; i++) {
	            Fitxa fitxa = new Fitxa();
	            fitxa.setPosicion(0); // Posición inicial de la ficha
	            fitxa.setActiva(false); // La ficha está inactiva al inicio del juego
	            fitxa.setJugador(jugador); // Asignar el jugador a la ficha
	            fitxa.setPartida(partida); // Asignar la partida a la ficha
	            fitxa.setMovible(true);
	            fichas.add(fitxa);
	        }
	        jugador.setFitxes(fichas); // Asignar las fichas al jugador
	    }
	}


	@Override
	public int llancarDaus() {
		//Creem un random de rang 1-6 i ho guardem en la variable dado
		Random rand = new Random();
        int dado1 = rand.nextInt(6) + 1;
        int dado2 = rand.nextInt(6) + 1;
        int suma;
        if(verificarDausIguals(dado1, dado2)) {
        	suma = 13;
        } else {
            suma = dado1 + dado2;
        }
        return suma;
	}
	
	public boolean verificarDausIguals(int dado1, int dado2) {
		//Verifiquem si els dos daus son iguals si ho son retorna true i si no false
        if (dado1 == dado2) return true;
        else return false;
	}

	@Override
	public void moureFitxa(Fitxa fitxa, int quantitat) {
		if(fitxa.isActiva() && fitxa.getPosicion() + quantitat <= FINAL_CASILLA) {
			fitxa.setPosicion(fitxa.getPosicion() + quantitat);
		} else {
			fitxa.setPosicion(FINAL_CASILLA);
		}
		
	}

	@Override
	public boolean capturarFitxa(Fitxa fitxa) {
		boolean comprobar = true;
		if(verificarCasaSegura(fitxa.getPosicion())) {
			comprobar = false;
		}
		return comprobar;
	}

	@Override
	public boolean entradaAlTaulell(Fitxa fitxa) {
		boolean comprobar = false;
		if(!fitxa.isActiva() && llancarDaus() == 13) {
			fitxa.setActiva(true);
			comprobar = true;
		}
		return comprobar;
	}

	@Override
	public boolean verificarCasaSegura(int posicio) {
		boolean comprobar = false;
		if(obtenerTipoCasilla(posicio).equals("Segura")) {
			comprobar = true;
		}
		return comprobar;
	}

	@Override
	public boolean finalitzarRecorregut(Fitxa fitxa, int quantitat) {
		boolean comprobar = false;
		if(fitxa.getPosicion() == quantitat) {
			fitxa.setMovible(false);
			comprobar = true;
		}
		return comprobar;
	}

	@Override
	public boolean verificarVictoria() {
	    
	    for (Jugador j : jugadores) {
	        if (finalitzarRecorregut(j.getFitxes().get(0), FINAL_CASILLA) &&
	            finalitzarRecorregut(j.getFitxes().get(1), FINAL_CASILLA) &&
	            finalitzarRecorregut(j.getFitxes().get(2), FINAL_CASILLA) &&
	            finalitzarRecorregut(j.getFitxes().get(3), FINAL_CASILLA)) {
	            NomGanador = j.getNom();
	            return true;
	        }
	    }
	    return false;
	}
	public int Turno(Jugador jugador) {
		Random rand = new Random();
		int fitxa = rand.nextInt(4);
		return fitxa;
	}


	public String getNomGanador() {
		return NomGanador;
	}
	
	
	
}