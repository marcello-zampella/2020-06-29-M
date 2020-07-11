/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaAffini"
    private Button btnCercaAffini; // Value injected by FXMLLoader

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxRegista"
    private ComboBox<Director> boxRegista; // Value injected by FXMLLoader

    @FXML // fx:id="txtAttoriCondivisi"
    private TextField txtAttoriCondivisi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	if(this.boxAnno.getValue()==null) {
    		this.txtResult.setText("SCEGLIO QUALCOSA");
    		return;
    	}
    	int anno= this.boxAnno.getValue();
    	for(Director d:this.model.creaGrafo(anno)) {
    	this.boxRegista.getItems().add(d);
    	}
    	this.txtResult.setText("*** GRAFO CREATO*** \n numero vertici: "+model.getGrafo().vertexSet().size()+"\n numero archi: "+model.getGrafo().edgeSet().size()+"\n");
    	this.btnAdiacenti.setDisable(false);

    }
    
    public static boolean isNumeric(String str) { 
    	  try {  
    	    Integer.parseInt(str);  
    	    return true;
    	  } catch(NumberFormatException e){  
    	    return false;  
    	  }  
    	}
    
    private Director s;

    @FXML
    void doRegistiAdiacenti(ActionEvent event) {
    	s=this.boxRegista.getValue();
    	if(s==null) {
    		this.txtResult.setText("SCEGLI!");
    		return;
    	}
    	this.txtResult.appendText("***** REGISTI ADIACENTI ***** \n");
    	for(Director d:this.model.cercaRegistiAdiacenti(s)) {
    		this.txtResult.appendText(d.toString()+"\n");
    	}
    	this.btnCercaAffini.setDisable(false);

    }

    @FXML
    void doRicorsione(ActionEvent event) {
    	String n=this.txtAttoriCondivisi.getText();
    	if(!this.isNumeric(n)) {
    		this.txtResult.setText("INSERISCI UN NUMERO INTERO");
    		return;
    	}
    	int numero=Integer.parseInt(n);
    	model.cercaAttoriCondivisi(numero,s);
    	
    	this.txtResult.appendText("\n \n *** RISULTATO MIGLIORE PER IL REGISTA "+s+" *** \n \n");
    	ArrayList<Director>migliore=model.getMigliore();
    	if(migliore==null) {
    		return;
    	}
    	for(Director r: migliore) {
    		this.txtResult.appendText(r.toString()+"\n");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaAffini != null : "fx:id=\"btnCercaAffini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxRegista != null : "fx:id=\"boxRegista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAttoriCondivisi != null : "fx:id=\"txtAttoriCondivisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
   public void setModel(Model model) {
    	
    	this.model = model;
    	ArrayList<Integer> anni=new ArrayList<Integer>();
    	for(int i=2004;i<2007;i++) {
    		anni.add(i);
    	}
    	this.boxAnno.getItems().addAll(anni);
    	this.btnCercaAffini.setDisable(true);
    	this.btnAdiacenti.setDisable(true);
    	
    }
    
}
