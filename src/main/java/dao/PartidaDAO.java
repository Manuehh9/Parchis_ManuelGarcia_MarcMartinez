package dao;

import model.Fitxa;

public interface PartidaDAO {

	public void iniciarPartida();
	public int llancarDaus();
	public void moureFitxa(Fitxa fitxa , int quantitat);
	public boolean capturarFitxa(Fitxa fitxa);
	public int entradaAlTaulell(Fitxa fitxa);
	public boolean verificarCasaSegura(int posicio);
	public boolean finalitzarRecorregut(Fitxa fitxa, int quantitat);
	public boolean verificarVictoria();
	
}