import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import user.States;

public class Controller implements Initializable {

    public TextField txtFirstName;
    public TextField txtLastName;
    public DatePicker datePickerBirthday;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtZipCode;
    public TextField txtCountry;

    public ComboBox cmbStates;
    public ComboBox cmbSex;

    public TextField txtACTScoreValue;
    public TextField txtACTScoreTotal;
    public TextField txtSATScoreValue;
    public TextField txtSATScoreTotal;
    public TextField txtGPAScoreValue;
    public TextField txtGPAScoreTotal;

    public CheckBox chckBoxIsAnyFelony;


    private Date birthday;

    private DialogMessage alert = new DialogMessage();

    private void clearLayout(){
        txtFirstName.setText("");
        txtLastName.setText("");
        datePickerBirthday.getEditor().clear();

        txtAddress.setText("");
        txtCity.setText("");

        txtZipCode.setText("");
        txtCountry.setText("");

        cmbStates.getSelectionModel().clearSelection();
        cmbSex.getSelectionModel().clearSelection();

        txtACTScoreValue.setText("");
        txtACTScoreTotal.setText("");
        txtSATScoreValue.setText("");
        txtSATScoreTotal.setText("");
        txtGPAScoreValue.setText("");
        txtGPAScoreTotal.setText("");

        chckBoxIsAnyFelony.setSelected(false);

    }

    public void btnSubmitFormOnAction(ActionEvent actionEvent) {
        if(fieldValidation()){

        }
    }

    public void btnCleanFormOnAction(ActionEvent actionEvent) {
        clearLayout();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addDataValidators();
        clearLayout();

        cmbSex.getItems().addAll("MALE","FEMALE");
        cmbStates.getItems().addAll(States.values());
        txtCountry.setText("United States");

    }


    private Boolean fieldValidation(){

        if(txtFirstName.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"First Name is Missing","First Name is required","Capture your First Name in the Field");
            txtFirstName.requestFocus();
            return false;
        }

        if(txtLastName.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"Last Name is Missing","Last Name is required","Capture your Last Name in the Field");
            txtLastName.requestFocus();
            return false;
        }

        if(cmbSex.getSelectionModel().getSelectedIndex() == -1){
            alert.displayMessage(Alert.AlertType.ERROR,"Missing Sex","Sex is required","Capture your Sex in the Field");
            cmbSex.requestFocus();
            return false;
        }

        if( datePickerBirthday.getEditor().getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"Missing Birthday Info","Birthday is required","Capture your Birtday Date in the Picker");
            txtGPAScoreTotal.requestFocus();
            return false;
        }else{
            try {
                String birthdayInPicker = datePickerBirthday.getEditor().getText();

                if(!birthdayInPicker.contains(":")){
                    birthdayInPicker = birthdayInPicker + " 00:00:00";
                }
                Timestamp lastCalibrationDate = Timestamp.valueOf(birthdayInPicker);
                birthday = new Date(lastCalibrationDate.getTime());

            }catch(Exception err){
                err.printStackTrace();
            }
        }


        if(txtAddress.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"Address is Missing","Address is required","Capture your Address in the Field");
            txtAddress.requestFocus();
            return false;
        }

        if(txtCity.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"City is Missing","City is required","Capture your City in the Field");
            txtCity.requestFocus();
            return false;
        }

        if(cmbStates.getSelectionModel().getSelectedIndex() == -1){
            alert.displayMessage(Alert.AlertType.ERROR,"Missing State","State is required","Capture your Address State in the Field");
            cmbStates.requestFocus();
            return false;
        }

        if(txtZipCode.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"Zip Code is Missing","Zip Code is required","Capture your Zip Code in the Field");
            txtZipCode.requestFocus();
            return false;
        }else{
            if(txtZipCode.getText().length() != 5){
                alert.displayMessage(Alert.AlertType.ERROR,"Zip Code is Incorrect","Zip Code should be a 5 digits size, please correct the Zip Code"
                        ,"Zip Code in United States are a 5 digit number");
                txtZipCode.setText("");
                txtZipCode.requestFocus();
                return false;
            }
        }


        //Score
        if(txtACTScoreValue.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"ACT Score Result is Missing","Applicant ACT Score Result is required","Capture your ACT Score Result in the Field");
            txtACTScoreValue.requestFocus();
            return false;
        }
        if(txtACTScoreTotal.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"ACT Test value is Missing","ACT Test value is required","Capture your ACT Total Result in the Field");
            txtACTScoreTotal.requestFocus();
            return false;
        }else{
            double result = Double.valueOf(txtACTScoreValue.getText());
            double total = Double.valueOf(txtACTScoreTotal.getText());

            if(result > total){
                alert.displayMessage(Alert.AlertType.ERROR,"ACT values are incorrect","Applicant ACT Test result is greater than the actual test total value","Applicant ACT Test result must be equal or less than the Test total value");
                txtACTScoreTotal.requestFocus();
                return false;
            }

        }

        if(txtSATScoreValue.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"SAT Score Result is Missing","Applicant SAT Score result is required","Capture your SAT Score Result in the Field");
            txtSATScoreValue.requestFocus();
            return false;
        }
        if(txtSATScoreTotal.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"SAT Test value is Missing","SAT test value is required","Capture your SAT Total Result in the Field");
            txtSATScoreTotal.requestFocus();
            return false;
        }else{
            double result = Double.valueOf(txtSATScoreValue.getText());
            double total = Double.valueOf(txtSATScoreTotal.getText());

            if(result > total){
                alert.displayMessage(Alert.AlertType.ERROR,"SAT values are incorrect","Applicant SAT Test result is greater than the actual test total value","Applicant SAT Test result must be equal or less than the Test total value");
                txtSATScoreTotal.requestFocus();
                return false;
            }

        }

        if(txtGPAScoreValue.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"High School GPA Total Value is Missing","Applicant High School GPA Score result is required","Capture your High School GPA Total Result in the Field");
            txtGPAScoreValue.requestFocus();
            return false;
        }
        if(txtGPAScoreTotal.getText().equals("")) {
            alert.displayMessage(Alert.AlertType.ERROR, "High School GPA Test value is Missing", "High School GPA test value is required", "Capture your High School GPA Total Result in the Field");
            txtGPAScoreTotal.requestFocus();
            return false;
        }else{
            double result = Double.valueOf(txtGPAScoreValue.getText());
            double total = Double.valueOf(txtGPAScoreTotal.getText());

            if(result > total){
                alert.displayMessage(Alert.AlertType.ERROR,"High School GPA values are incorrect","Applicant High School GPA Test result is greater than the actual test total value","Applicant High School GPA Test result must be equal or less than the Test total value");
                txtGPAScoreTotal.requestFocus();
                return false;
            }

        }


        if(!chckBoxIsAnyFelony.isSelected()){
            if (!alert.displayMessageYesNo(Alert.AlertType.WARNING,"Felony in your record","Having a felony in the last 5 years is not Checked, is this correct ?"
            ,"Not having marked this question means you didn't have a felony in the last 5 years")){
                chckBoxIsAnyFelony.requestFocus();
                return false;
            }

        }


        return true;
    }


    private void addDataValidators(){

        txtZipCode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*?")) {
                txtZipCode.setText(oldValue);
            }
        });

        txtACTScoreValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtACTScoreValue.setText(oldValue);
            }
        });

        txtACTScoreTotal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtACTScoreTotal.setText(oldValue);
            }
        });

        txtSATScoreValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtSATScoreValue.setText(oldValue);
            }
        });


        txtSATScoreTotal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtSATScoreTotal.setText(oldValue);
            }
        });


        txtGPAScoreValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtGPAScoreValue.setText(oldValue);
            }
        });

        txtGPAScoreTotal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtGPAScoreTotal.setText(oldValue);
            }
        });

        datePickerBirthday.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                datePickerBirthday.setPromptText(pattern);
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

    }
}
