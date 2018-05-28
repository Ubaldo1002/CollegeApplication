import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import user.Address;
import user.ApplicantData;
import user.Score;
import user.States;
import utilities.ParserHelper;

public class Controller implements Initializable {

    public TextField txtFirstName;
    public TextField txtLastName;
    public DatePicker datePickerBirthday;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtZipCode;
    public TextField txtCountry;

    public Label lblAge;
    public ComboBox<States> cmbStates;
    public ComboBox cmbSex;

    public TextField txtACTScoreValue;
    public TextField txtSATScoreValue;
    public TextField txtGPAScoreValue;
    public TextField txtGPAScoreTotal;

    public CheckBox chckBoxIsAnyFelony;


    private Date birthday;

    private DialogMessage alert = new DialogMessage();

    int ATCScore;
    int SATScore;
    Score GPAScoreResult;

    private void clearLayout(){

        lblAge.setText("");
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
        txtSATScoreValue.setText("");
        txtGPAScoreValue.setText("");
        txtGPAScoreTotal.setText("");

        chckBoxIsAnyFelony.setSelected(false);

    }

    public void btnSubmitFormOnAction(ActionEvent actionEvent) {
        if(fieldValidation()) {
            Address myAddress = new Address(txtAddress.getText(), txtCity.getText(), cmbStates.getSelectionModel().getSelectedItem(), Integer.parseInt(txtZipCode.getText()));

            boolean hasFelonies = false;
            if (chckBoxIsAnyFelony.isSelected()) {
                hasFelonies = true;
            }

            ApplicantData applicantData = new ApplicantData(txtFirstName.getText(), txtLastName.getText(), birthday, myAddress
                    , SATScore, ATCScore, GPAScoreResult, hasFelonies);

            ApplicationEvaluation applicationEvaluation = new ApplicationEvaluation(applicantData);
            boolean qualifyForInstantReject = applicationEvaluation.qualifyForInstantReject();

            if (qualifyForInstantReject) {
                alert.displayMessage(Alert.AlertType.INFORMATION, "Application Result", "Your application was rejected based on 'Instant Reject' Rules"
                        , "Your application was automatically Rejected");
            } else {

                boolean isApproved = applicationEvaluation.qualifyForInstantAccept(qualifyForInstantReject);
                if (isApproved) {
                    alert.displayMessage(Alert.AlertType.INFORMATION, "Application Result", "Your application was accepted based on 'Instant Accept' Rules"
                            , "Your application was automatically Accepted");
                } else {
                    alert.displayMessage(Alert.AlertType.INFORMATION, "Application Result", "Your application was marked for Further Review based on 'Instant Reject/Instant Accept' Rules"
                            , "Your application was automatically marked to Further Review");
                }
            }
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
            alert.displayMessage(Alert.AlertType.ERROR,"Missing Birthday Info","Birthday is required, Choose the date from the Picker","Capture your Birthday Date clicking the Date Picker calendar and select the date");
            txtGPAScoreTotal.requestFocus();
            return false;
        }else{
            try {
                String birthdayInPicker = datePickerBirthday.getEditor().getText();

                if(!birthdayInPicker.contains(":")){
                    birthdayInPicker = birthdayInPicker + " 00:00:00";
                }
                Timestamp birthDayPicked = Timestamp.valueOf(birthdayInPicker);
                birthday = new Date(birthDayPicked.getTime());

                lblAge.setText("Your Age is : "+String.valueOf(ParserHelper.calculateAge(birthday)));

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
                alert.displayMessage(Alert.AlertType.ERROR,"Zip Code is Incorrect","Zip Code must be a 5 digits number, please type the correct the Zip Code"
                        ,"Zip Code in United States is a 5 digit number");
                txtZipCode.setText("");
                txtZipCode.requestFocus();
                return false;
            }
        }

        //can be only one of these of both
        if(txtACTScoreValue.getText().equals("") && txtSATScoreValue.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"ACT or SAT Score Result are Missing","ApplicantData ACT Score or SAT Score Result is required","Capture your ApplicantData ACT Score or SAT Score Result in the correct Field");
            txtACTScoreValue.requestFocus();
            return false;
        }


        if(txtGPAScoreValue.getText().equals("")){
            alert.displayMessage(Alert.AlertType.ERROR,"High School GPA Total Value is Missing","ApplicantData High School GPA Score result is required","Capture your High School GPA Total Result in the Field");
            txtGPAScoreValue.requestFocus();
            return false;
        }
        if(txtGPAScoreTotal.getText().equals("")) {
            alert.displayMessage(Alert.AlertType.ERROR, "High School GPA Test value is Missing", "High School GPA test value is required", "Capture your High School GPA Total Result in the Field");
            txtGPAScoreTotal.requestFocus();
            return false;
        }else{
            float result = Float.valueOf(txtGPAScoreValue.getText());
            float total = Float.valueOf(txtGPAScoreTotal.getText());

            if(result > total){
                alert.displayMessage(Alert.AlertType.ERROR,"High School GPA values are incorrect","ApplicantData High School GPA Test result: " + txtGPAScoreValue.getText()
                        + " is greater than the actual test total value: "+ txtGPAScoreTotal.getText(),"ApplicantData High School GPA Test result must be equal or less than the Test total value");
                txtGPAScoreTotal.requestFocus();
                return false;
            }else{
                GPAScoreResult = new Score(result, total);
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

        txtSATScoreValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtSATScoreValue.setText(oldValue);
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
