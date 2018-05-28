import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by ureyes on 3/3/2017.
 */
public class DialogMessage {

    public DialogMessage() {
    }

    public void displayMessage(Alert.AlertType alertType, String titleText, String headerText, String contestText) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // log.info(alertType.name() + ":  titleText: "+titleText + " --- headerText:" + headerText + " --- contestText:" + contestText);
                Alert alert = new Alert(alertType);
                alert.setTitle(titleText);
                alert.setHeaderText(headerText);
                alert.setContentText(contestText);

                alert.show();
            }
        });

    }


    public boolean displayMessageOkCancel(Alert.AlertType alertType, String titleText, String headerText, String contestText) {

        Alert alert =
                new Alert(alertType, contestText,
                        ButtonType.OK,
                        ButtonType.CANCEL);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    public boolean displayMessageYesNo(Alert.AlertType alertType, String titleText, String headerText, String contestText) {

        Alert alert =
                new Alert(alertType, contestText,
                        ButtonType.YES,
                        ButtonType.NO);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }

    }
}
