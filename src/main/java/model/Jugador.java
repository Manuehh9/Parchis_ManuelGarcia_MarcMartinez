package model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Jugador {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJugador")
    private Long idJugador;
	
	@Column(name = "Nom")
    private String nom;

    @Column(name = "Color")
    private String color;

    @Column(name = "Victories")
    private int victories;
    
    @OneToMany(mappedBy = "jugador")
    private List<Partida> partides;
    
    @OneToMany(mappedBy = "jugador")
    private List<Fitxa> fitxes;

	public Jugador(String nom, String color, int victories, List<Partida> partides,
			List<Fitxa> fitxes) {
		super();
		this.nom = nom;
		this.color = color;
		this.victories = victories;
		this.partides = partides;
		this.fitxes = fitxes;
	}

	public Long getIdJugador() {
		return idJugador;
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
		return "Jugadors [idJugador=" + idJugador + ", nom=" + nom + ", color=" + color + ", victories=" + victories
				+ ", partides=" + partides + ", fitxes=" + fitxes + "]";
	}
}
