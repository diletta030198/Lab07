package it.polito.tdp.poweroutages.model;



import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	Set<Nerc> listaNerc;
	private Set<Evento>sequenzaEventi; 
	private LinkedList<Evento> best; 
	private double costo_best; 
	private int ore_best; 
	List<Evento> eventi= new LinkedList<Evento>(); //set di eventi relativi a quel preciso nerc
	
	public Model() {
		podao = new PowerOutageDAO();
	 listaNerc= new HashSet<Nerc>(); 
		listaNerc.addAll(podao.getNercList());
	}

	public Set<Nerc> getListaNerc() {
		return listaNerc;
	}
	
	
	public String calcolaSequenzaEventi(Nerc n,int anni, int ore) {
	String s= ""; 
	eventi.addAll(this.podao.getEventList(n)); 
	best= new LinkedList<Evento>(); 
	costo_best=0.0; 
	ore_best=0; 
	List<Evento> parziale= new LinkedList<>(); 
	cerca(parziale, 0, anni,ore ); 
	for(Evento e: best) {
		s+=e.toString()+"\n"; 
	}
	s+=costo_best+" "+ore_best; 
	return s; 
	}

	private void cerca(List<Evento> parziale, int L, int anni, int ore) {
	 long costo=0; 
	
		for(int i=0; i<parziale.size();i++) {
			costo+=parziale.get(i).getNumeroDiClienti();
		}  
	 
	 
	 if(costo>costo_best) {
		 costo_best=costo; 
		 best=new LinkedList<Evento>(parziale);
		ore_best=this.contaOre(parziale); 
	 }
	 
		//Casi terminali
		if(L==eventi.size()) {
			return; 
		}
		
		
		
		//Inizia la ricorsione
		
		//aggiungo
		if(possoAggiungere(parziale, L,anni)==false) {
			
			return; 
		}
		
		
		//non hai sforato gli anni
			
			//test delle ore 
			if(this.contaOre(parziale)+eventi.get(L).getDurata()>ore) {
				//non lo posso aggiungere 
				cerca(parziale,L+1, anni,ore); 
			}
			
			
			else {
			//lo aggiungo
			parziale.add(eventi.get(L)); 
			Evento e = eventi.get(L);
			cerca(parziale,L+1, anni,ore); 
			parziale.remove(e); 
			
			
			//CONTROLLA
			//non aggiungo
			cerca(parziale,L+1,anni,ore); 
		}
		
		
		
	}

	private boolean possoAggiungere(List<Evento> parziale, int l,int anno) {
		if(parziale.size()==0) {
			return true; 
		}
		//aggiungo solo se la differenza degli anni è minore di quella passata dal client
		
		int durata= eventi.get(l).getDataFine().getYear()-parziale.get(0).getDataInizio().getYear(); 
		//int durata= Period.between(dataInizio.toLocalDate(), dataIni.toLocalDate()).getYears(); 
		if(durata>=anno) 
			return false;
		
		return true;
	}

	private int contaOre(List<Evento> parziale) {
		long durata=0; 
		for(Evento e: parziale) {
			durata= durata+e.getDurata();
		}
		return (int)durata;
	}
	
	
	

}
