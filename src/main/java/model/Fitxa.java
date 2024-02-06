package model;

import javax.persistence.*;

@Entity
@Table(name = "Fichas")  // Cambiado de "Fichas" a "Fichas"
public class Fitxa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFitxa")
    private Long idFitxa;


    @Column(name = "Posicion")
    private int posicion;

    @Column(name = "Activa")
    private boolean activa;

    @ManyToOne
    @JoinColumn(name = "IdJugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "IdPartida")
    private Partida partida;

	public Fitxa(Long idFitxa, int posicion, boolean activa, Jugador jugador, Partida partida) {
		super();
		this.idFitxa = idFitxa;
		this.posicion = posicion;
		this.activa = activa;
		this.jugador = jugador;
		this.partida = partida;
	}

	public Long getIdFitxa() {
		return idFitxa;
	}

	public void setIdFitxa(Long idFitxa) {
		this.idFitxa = idFitxa;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "Fitxa [idFitxa=" + idFitxa + ", posicion=" + posicion + ", activa=" + activa + ", jugador=" + jugador
				+ ", partida=" + partida + "]";
	}
}
