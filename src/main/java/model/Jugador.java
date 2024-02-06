package model;

import java.util.List;

import javax.persistence.*;
@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJugador")
    private Long jugador_id;

    @Column(name = "Nom")
    private String nom;

    @Column(name = "Color")
    private String color;

    @Column(name = "Victories")
    private int victories;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;
    
    @OneToMany(mappedBy = "jugador")
    private List<Partida> partides;

    @OneToMany(mappedBy = "jugador")
    private List<Fitxa> fitxes;

	public Jugador(Long jugador_id, String nom, String color, int victories, Partida partida, List<Partida> partides,
			List<Fitxa> fitxes) {
		super();
		this.jugador_id = jugador_id;
		this.nom = nom;
		this.color = color;
		this.victories = victories;
		this.partida = partida;
		this.partides = partides;
		this.fitxes = fitxes;
	}

	public Long getIdJugador() {
		return jugador_id;
	}

	public void setIdJugador(Long jugador_id) {
		this.jugador_id = jugador_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public List<Partida> getPartides() {
		return partides;
	}

	public void setPartides(List<Partida> partides) {
		this.partides = partides;
	}

	public List<Fitxa> getFitxes() {
		return fitxes;
	}

	public void setFitxes(List<Fitxa> fitxes) {
		this.fitxes = fitxes;
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + jugador_id + ", nom=" + nom + ", color=" + color + ", victories=" + victories
				+ ", partida=" + partida + ", partides=" + partides + ", fitxes=" + fitxes + "]";
	}
}
