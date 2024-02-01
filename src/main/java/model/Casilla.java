package model;

import javax.persistence.*;

@Entity
@Table(name = "Casillas")
public class Casilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCasilla")
    private Long idCasilla;

    @Column(name = "TipoCasilla")
    private String tipoCasilla;

    @Column(name = "Posicion")
    private int posicion;

    @ManyToOne
    @JoinColumn(name = "IdPartida")
    private Partida partida;

	public Casilla(Long idCasilla, String tipoCasilla, int posicion, Partida partida) {
		super();
		this.idCasilla = idCasilla;
		this.tipoCasilla = tipoCasilla;
		this.posicion = posicion;
		this.partida = partida;
	}
	
	public Long getIdCasilla() {
		return idCasilla;
	}

	public void setIdCasilla(Long idCasilla) {
		this.idCasilla = idCasilla;
	}

	public String getTipoCasilla() {
		return tipoCasilla;
	}

	public void setTipoCasilla(String tipoCasilla) {
		this.tipoCasilla = tipoCasilla;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "Casilla [idCasilla=" + idCasilla + ", tipoCasilla=" + tipoCasilla + ", posicion=" + posicion
				+ ", partida=" + partida + "]";
	}    
}
