package model;

import java.util.List;

import javax.persistence.*;
@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJugador")
    private Long IdJugador;

    @Column(name = "nom")
    private String nom;

    @Column(name = "color")
    private String color;

    @Column(name = "victories")
    private int victories;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida_id;
    
    @OneToMany(mappedBy = "IdPartida")
    private List<Partida> partides;

    @OneToMany(mappedBy = "IdFitxa")
    private List<Fitxa> fitxes;

	public Jugador(Long jugador_id, String nom, String color, int victories, Partida partida, List<Partida> partides,
			List<Fitxa> fitxes) {
		super();
		this.IdJugador = jugador_id;
		this.nom = nom;
		this.color = color;
		this.victories = victories;
		this.partida_id = partida;
		this.partides = partides;
		this.fitxes = fitxes;
	}

	public Long getIdJugador() {
		return IdJugador;
	}

	public void setIdJugador(Long jugador_id) {
		this.IdJugador = jugador_id;
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
		return partida_id;
	}

	public void setPartida(Partida partida) {
		this.partida_id = partida;
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
		return "Jugador [IdJugador=" + IdJugador + ", nom=" + nom + ", color=" + color + ", victories=" + victories
				+ ", partida_id=" + partida_id + ", partides=" + partides + ", fitxes=" + fitxes + "]";
	}
}
