package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Partides")
public class Partida {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPartida")
    private Long idPartida;

    @Column(name = "FechaInicio")
    private Date fechaInicio;

    @Column(name = "FechaFin")
    private Date fechaFin;

    @Column(name = "EnCurso")
    private boolean enCurso;

    @ManyToOne
    @JoinColumn(name = "IdGanador")
    private Jugador ganador;

    @OneToMany(mappedBy = "partida")
    private List<Casilla> casillas;

    @OneToMany(mappedBy = "partida")
    private List<Fitxa> fitxes;

	public Partida(Date fechaInicio, Date fechaFin, boolean enCurso, Jugador ganador,
			List<Casilla> casillas, List<Fitxa> fitxes) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.enCurso = enCurso;
		this.ganador = ganador;
		this.casillas = casillas;
		this.fitxes = fitxes;
	}

	public Long getIdPartida() {
		return idPartida;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isEnCurso() {
		return enCurso;
	}

	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public List<Fitxa> getFitxes() {
		return fitxes;
	}

	public void setFitxes(List<Fitxa> fitxes) {
		this.fitxes = fitxes;
	}

	@Override
	public String toString() {
		return "Partida [idPartida=" + idPartida + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", enCurso=" + enCurso + ", ganador=" + ganador + ", casillas=" + casillas + ", fitxes=" + fitxes
				+ "]";
	}
}
