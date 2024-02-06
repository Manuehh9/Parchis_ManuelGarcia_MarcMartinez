package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Partida")
public class Partida {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPartida")
    private Long IdPartida;

    @Column(name = "DataInici")
    private Date DataInici;

    @Column(name = "DataFi")
    private Date DataFi;

    @Column(name = "EnCurs")
    private boolean EnCurs;

    @ManyToOne
    @JoinColumn(name = "IdGuanyador")
    private Jugador IdGuanyador;
    
    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador_id;

    @OneToMany(mappedBy = "idCasella", cascade = CascadeType.ALL)
    private List<Casella> casellas;

    @OneToMany(mappedBy = "IdFitxa", cascade = CascadeType.ALL)
    private List<Fitxa> fitxes;
    
    @OneToMany(mappedBy = "IdJugador", cascade = CascadeType.ALL)
    private List<Jugador> jugadors;

	public Partida(Long idPartida, Date fechaInicio, Date fechaFin, boolean enCurso, Jugador ganador,
			List<Casella> casillas, List<Fitxa> fitxes, List<Jugador> jugadores) {
		super();
		this.IdPartida = idPartida;
		this.DataInici = fechaInicio;
		this.DataFi = fechaFin;
		this.EnCurs = enCurso;
		this.IdGuanyador = ganador;
		this.casellas = casillas;
		this.fitxes = fitxes;
		this.jugadors = jugadores;
	}

	public Long getIdPartida() {
		return IdPartida;
	}

	public void setIdPartida(Long idPartida) {
		this.IdPartida = idPartida;
	}

	public Date getFechaInicio() {
		return DataInici;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.DataInici = fechaInicio;
	}

	public Date getFechaFin() {
		return DataFi;
	}

	public void setFechaFin(Date fechaFin) {
		this.DataFi = fechaFin;
	}

	public boolean isEnCurso() {
		return EnCurs;
	}

	public void setEnCurso(boolean enCurso) {
		this.EnCurs = enCurso;
	}

	public Jugador getGanador() {
		return IdGuanyador;
	}

	public void setGanador(Jugador ganador) {
		this.IdGuanyador = ganador;
	}

	public List<Casella> getCasillas() {
		return casellas;
	}

	public void setCasillas(List<Casella> casillas) {
		this.casellas = casillas;
	}

	public List<Fitxa> getFitxes() {
		return fitxes;
	}

	public void setFitxes(List<Fitxa> fitxes) {
		this.fitxes = fitxes;
	}

	public List<Jugador> getJugadores() {
		return jugadors;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadors = jugadores;
	}

	@Override
	public String toString() {
		return "Partida [IdPartida=" + IdPartida + ", DataInici=" + DataInici + ", DataFi=" + DataFi
				+ ", EnCurs=" + EnCurs + ", IdGuanyador=" + IdGuanyador + ", casellas=" + casellas + ", fitxes=" + fitxes
				+ ", jugadors=" + jugadors + "]";
	}
}
