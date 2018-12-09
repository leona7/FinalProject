package pkgApp.controller;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;
	
	@FXML
	private TextField txtYearsToWork;
	
	@FXML
	private TextField txtAnnualReturnWork;
	
	@FXML
	private TextField txtYearsRetired;
	
	@FXML
	private TextField txtAnnualReturnRetired;
	
	@FXML
	private TextField txtRequiredIncome;
	
	@FXML
	private TextField txtMonthlySSI;
	
	@FXML
	private Label lblSaveEachMonth;
	
	@FXML
	private Label lblNeedSaved;

	
	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	

	@FXML
		public void btnClear(ActionEvent event) {
			//Clear inputs
			System.out.println("Clear pressed");
			lblSaveEachMonth.setText("");
			lblNeedSaved.setText("");
			txtYearsToWork.setText("");
			txtAnnualReturnWork.setText("");
			txtAnnualReturnRetired.setText("");
			txtYearsRetired.setText("");
			txtRequiredIncome.setText("");
			txtMonthlySSI.setText("");
			
			
	}
	//ensure that type is valid 
	public Boolean validType(String testString, String type) {
		try {
			 if (type.equalsIgnoreCase("int")) {
				Integer.parseInt(testString);
			} else if (type.equalsIgnoreCase("double")) {
				Double.parseDouble(testString);
			} return true;
		} catch(Exception e) {
			return false;
		}
 
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		boolean breakable = false;

		if (!validType(txtAnnualReturnRetired.getText(), "double")) {
			txtAnnualReturnRetired.setText("Invalid Input (not double)");
			breakable = true;
		}
		if (!validType(txtAnnualReturnWork.getText(), "double")) {
			txtAnnualReturnWork.setText("Invalid Input (not double)");
			breakable = true;
		}
		if (!validType(txtMonthlySSI.getText(), "double")) {
			txtMonthlySSI.setText("Invalid Input (not double)");
			breakable = true;
		}
		if (!validType(txtRequiredIncome.getText(), "double")) {
			txtRequiredIncome.setText("Invalid Input (not double)");
			breakable = true;
		}
		if (!validType(txtYearsRetired.getText(), "int")) {
			txtYearsRetired.setText("Invalid Input (not int)");
			breakable = true;
		}
		if (!validType(txtYearsToWork.getText(), "int")) {
			txtYearsToWork.setText("Invalid Input (not int)");
			breakable = true;
		}
		
		
		// Given restrictions for inputs
		
		
		if (Double.parseDouble(txtAnnualReturnRetired.getText()) > .03) {
			txtAnnualReturnRetired.setText("Invalid Input (must be at or below .03 (3%))");
			breakable = true;
		}
		if (Double.parseDouble(txtAnnualReturnRetired.getText()) < 0) {
			txtAnnualReturnRetired.setText("Invalid Input (must be at or above 0)");
			breakable = true;
		}
		if (Double.parseDouble(txtAnnualReturnWork.getText()) > .2) {
			txtAnnualReturnWork.setText("Invalid Input (must be at or below .2 (20%))");
			breakable = true;
		}
		if (Double.parseDouble(txtAnnualReturnWork.getText()) < 0) {
			txtAnnualReturnWork.setText("Invalid Input (must be at or above 0)");
			breakable = true;
		}
		if (breakable) {
			return;
		}
		
		//Calculate:
		int iYearsToWork = Integer.parseInt(txtYearsToWork.getText());
		double dAnnualReturnWorking = Double.parseDouble(txtAnnualReturnWork.getText());
		int iYearsRetired = Integer.parseInt(txtYearsRetired.getText());
		double dAnnualReturnRetired = Double.parseDouble(txtAnnualReturnRetired.getText());
		double dRequiredIncome = Double.parseDouble(txtRequiredIncome.getText());
		double dMonthlySSI = Double.parseDouble(txtMonthlySSI.getText());

		Retirement Ret = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired, dAnnualReturnRetired,
				dRequiredIncome, dMonthlySSI);

		lblSaveEachMonth.setText(String.format("%.2f", Math.abs(Ret.amountToSave())));
		lblNeedSaved.setText(String.format("%.2f", Math.abs(Ret.totalAmountSaved())));
	}

}
