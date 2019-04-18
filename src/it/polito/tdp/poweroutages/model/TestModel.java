package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getListaNerc());
		
		Nerc n = new  Nerc(1,"ERCOT"); 
		
		System.out.println(model.calcolaSequenzaEventi(n, 3, 50));
	}
}
