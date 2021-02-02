package com.sparrowwallet.sparrow.control;

import com.sparrowwallet.sparrow.AppServices;
import com.sparrowwallet.sparrow.Mode;
import com.sparrowwallet.sparrow.net.ServerType;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.HyperlinkLabel;
import org.controlsfx.control.StatusBar;
import org.controlsfx.control.ToggleSwitch;

public class WelcomeDialog extends Dialog<Mode> {
    private final HostServices hostServices;

    private ServerType serverType = ServerType.ELECTRUM_SERVER;

    public WelcomeDialog(HostServices services) {
        this.hostServices = services;

        final DialogPane dialogPane = getDialogPane();

        setTitle("Welcome to Sparrow-GRS");
        dialogPane.setHeaderText("Welcome to Sparrow-GRS!");
        dialogPane.getStylesheets().add(AppServices.class.getResource("app.css").toExternalForm());
        dialogPane.getStylesheets().add(AppServices.class.getResource("general.css").toExternalForm());
        AppServices.setStageIcon(dialogPane.getScene().getWindow());
        dialogPane.setPrefWidth(600);
        dialogPane.setPrefHeight(520);

        Image image = new Image("image/sparrow-small.png", 50, 50, false, false);
        if (!image.isError()) {
            ImageView imageView = new ImageView();
            imageView.setSmooth(false);
            imageView.setImage(image);
            dialogPane.setGraphic(imageView);
        }

        final ButtonType onlineButtonType = new javafx.scene.control.ButtonType("Configure Server", ButtonBar.ButtonData.OK_DONE);
        final ButtonType offlineButtonType = new javafx.scene.control.ButtonType("Later or Offline Mode", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogPane.getButtonTypes().addAll(onlineButtonType, offlineButtonType);

        final VBox content = new VBox(20);
        content.setPadding(new Insets(20, 20, 20, 20));
        content.getChildren().add(createParagraph("Sparrow-GRS can operate in both an online and offline mode. In the online mode it connects to Electrum-GRS server to display transaction history. In the offline mode it is useful as a transaction editor and as an airgapped multisig coordinator."));
        content.getChildren().add(createParagraph("With Sparrow-GRS you have access to a full blockchain explorer and your public keys are always encrypted on disk. Examples of Electru-GRS servers include ElectrumX and electrs."));
        content.getChildren().add(createStatusBar(onlineButtonType, offlineButtonType));

        dialogPane.setContent(content);

        setResultConverter(dialogButton -> dialogButton == onlineButtonType ? Mode.ONLINE : Mode.OFFLINE);
    }

    private Label createParagraph(String text) {
        Label label = new Label(text);
        label.setWrapText(true);

        return label;
    }

    private StatusBar createStatusBar(ButtonType onlineButtonType, ButtonType offlineButtonType) {
        StatusBar statusBar = new StatusBar();
        statusBar.setText("Online Mode");
        statusBar.getRightItems().add(createToggle(statusBar, onlineButtonType, offlineButtonType));

        return statusBar;
    }

    private ToggleSwitch createToggle(StatusBar statusBar, ButtonType onlineButtonType, ButtonType offlineButtonType) {
        ToggleSwitch toggleSwitch = new UnlabeledToggleSwitch();
        toggleSwitch.setStyle("-fx-padding: 1px 0 0 0");

        toggleSwitch.selectedProperty().addListener((observable, oldValue, newValue) -> {
            Button onlineButton = (Button) getDialogPane().lookupButton(onlineButtonType);
            onlineButton.setDefaultButton(newValue);
            Button offlineButton = (Button) getDialogPane().lookupButton(offlineButtonType);
            offlineButton.setDefaultButton(!newValue);

            if(!newValue) {
                serverType = (serverType == ServerType.BITCOIN_CORE ? ServerType.ELECTRUM_SERVER : ServerType.BITCOIN_CORE);

                if(serverType == ServerType.BITCOIN_CORE && !toggleSwitch.getStyleClass().contains("core-server")) {
                    toggleSwitch.getStyleClass().add("core-server");
                } else {
                    toggleSwitch.getStyleClass().remove("core-server");
                }
            }

            statusBar.setText(newValue ? "Online Mode: " + serverType.getName() : "Offline Mode");
        });

        toggleSwitch.setSelected(true);
        return toggleSwitch;
    }
}
