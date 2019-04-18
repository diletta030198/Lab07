package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

	private Model model; 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button buttonCalcola;

    @FXML
    private ChoiceBox<Nerc> choiseButton;

    @FXML
    private TextField inserisciAnni;

    @FXML
    private TextField inserisciOre;

    @FXML
    private TextArea restituisciRisultato;
    
    @FXML
    void doCalcola(ActionEvent event) {
    	this.restituisciRisultato.clear();
    	Nerc n= new Nerc(this.choiseButton.getValue().getId(),this.choiseButton.getValue().getValue()); 
    	
    	int anno; 
    	int ora; 
    	String anni= this.inserisciAnni.getText(); 
    	this.inserisciAnni.clear();
    	String ore= this.inserisciOre.getText();
    	this.inserisciOre.clear();
    
    	try {
    		 anno= Integer.parseInt(anni);
    		 ora= Integer.parseInt(ore);
    		 this.restituisciRisultato.setText(model.calcolaSequenzaEventi(n, anno, ora));
    	}
    	catch(NumberFormatException e) {
    		this.restituisciRisultato.setText("Devi inserire un numero per l'anno e per l'ora!");
    	}
    	
    	
    	

    }
    
    public void setModel(Model model) {
		this.model=model; 
		List<Nerc> listaNerc= new LinkedList<Nerc>(); 
		for(Nerc n: model.getListaNerc()) {
			listaNerc.add(n);
		}
		
		choiseButton.getItems().addAll(listaNerc);
	}
    
    

    @FXML
    void initialize() {
        assert choiseButton != null : "fx:id=\"choiseButton\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert inserisciAnni != null : "fx:id=\"inserisciAnni\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert inserisciOre != null : "fx:id=\"inserisciOre\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert restituisciRisultato != null : "fx:id=\"restituisciRisultato\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert buttonCalcola != null : "fx:id=\"buttonCalcola\" was not injected: check your FXML file 'PowerOutages.fxml'.";
    }

	
}

	

