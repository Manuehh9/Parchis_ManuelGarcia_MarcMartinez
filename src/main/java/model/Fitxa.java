package model;

import javax.persistence.*;

@Entity
@Table(name = "Fitxa")  // Cambiado de "Fichas" a "Fichas"
public class Fitxa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFitxa")
    private Long IdFitxa;


    @Column(name = "posicio")
    private int posicio;

    @Column(name = "activa")
    private boolean activa;

    @ManyToOne
    @JoinColumn(name = "IdJugador")
    private Jugador IdJugador;

    @ManyToOne
    @JoinColumn(name = "IdPartida")
    private Partida IdPartida;

	public Fitxa(Long idFitxa, int posicion, boolean activa, Jugador jugador, Partida partida) {
		super();
		this.IdFitxa = idFitxa;
		this.posicio = posicion;
		this.activa = activa;
		this.IdJugador = jugador;
		this.IdPartida = partida;
	}

	public Long getIdFitxa() {
		return IdFitxa;
	}

	public void setIdFitxa(Long idFitxa) {
		this.IdFitxa = idFitxa;
	}

	public int getPosicion() {
		return posicio;
	}

	public void setPosicion(int posicion) {
		this.posicio = posicion;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Jugador getJugador() {
		return IdJugador;
	}

	public void setJugador(Jugador jugador) {
		this.IdJugador = jugador;
	}

	public Partida getPartida() {
		return IdPartida;
	}

	public void setPartida(Partida partida) {
		this.IdPartida = partida;
	}

	@Override
	public String toString() {
		return "Fitxa [IdFitxa=" + IdFitxa + ", posicio=" + posicio + ", activa=" + activa + ", IdJugador=" + IdJugador
				+ ", IdPartida=" + IdPartida + "]";
	}
}
