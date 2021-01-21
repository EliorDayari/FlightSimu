package view;

import interpreter.AutoPilotParser;
import commands.DisconnectCommand;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import java.util.Observer;
import model.Model;
import viewmodel.ViewModel;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.application.Application;

public class MainWindowController extends Application
{
    public void start(final Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Flight.fxml"));
        final Parent root = (Parent)loader.load();
        final FlightController ctrl = (FlightController)loader.getController();
        final ViewModel viewModel = new ViewModel();
        final Model model = new Model();
        model.addObserver(viewModel);
        viewModel.setModel(model);
        viewModel.addObserver(ctrl);
        ctrl.setViewModel(viewModel);
        primaryStage.setTitle("Flight Gear Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            final DisconnectCommand command = new DisconnectCommand();
            final String[] disconnect = { "" };
            command.execute(disconnect);
            AutoPilotParser.thread1.interrupt();
            model.stopAll();
            System.out.println("Disconnected from server");
        });
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
