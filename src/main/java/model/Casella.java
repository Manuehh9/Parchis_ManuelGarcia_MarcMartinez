package model;

import javax.persistence.*;
@Entity
@Table(name = "Casella")  // Cambiado de "Casillas" a "Casillas"
public class Casella {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCasella")
    private Long idCasella;


    @Column(name = "tipoCasella")
    private String tipoCasella;

    @Column(name = "posicio")
    private int posicio;

    @ManyToOne
    @JoinColumn(name = "IdPartida")
    private Partida IdPartida;

	public Casella(Long idCasilla, String tipoCasilla, int posicion, Partida partida) {
		super();
		this.idCasella = idCasilla;
		this.tipoCasella = tipoCasilla;
		this.posicio = posicion;
		this.IdPartida = partida;
	}
	
	public Long getIdCasilla() {
		return idCasella;
	}

	public void setIdCasilla(Long idCasilla) {
		this.idCasella = idCasilla;
	}

	public String getTipoCasilla() {
		return tipoCasella;
	}

	public void setTipoCasilla(String tipoCasilla) {
		this.tipoCasella = tipoCasilla;
	}

	public int getPosicion() {
		return posicio;
	}

	public void setPosicion(int posicion) {
		this.posicio = posicion;
	}

	public Partida getPartida() {
		return IdPartida;
	}

	public void setPartida(Partida partida) {
		this.IdPartida = partida;
	}

	@Override
	public String toString() {
		return "Casella [idCasella=" + idCasella + ", tipoCasella=" + tipoCasella + ", posicio=" + posicio
				+ ", IdPartida=" + IdPartida + "]";
	}    
}
