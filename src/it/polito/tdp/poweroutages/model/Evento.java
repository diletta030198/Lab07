package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Evento {
	 private int id; 
	private Nerc nerc;
	private long numeroDiClienti;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	
	public Evento(int id, Nerc nerc, long numeroDiClienti, LocalDateTime dataInizio, LocalDateTime dataFine) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.numeroDiClienti = numeroDiClienti;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public long getNumeroDiClienti() {
		return numeroDiClienti;
	}
	public void setNumeroDiClienti(long numeroDiClienti) {
		this.numeroDiClienti = numeroDiClienti;
	}
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	public long getDurata() {

Duration d= Duration.between(dataInizio, dataFine); 


return d.toHours(); 
	}
	@Override
	public String toString() {
		return this.nerc+" "+this.numeroDiClienti+" "+this.dataInizio+" "+this.dataFine+" "+this.getDurata(); 
	}
	
	

}
