package client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
    
    String ipAddr, msg;
    int portNo;
    Networking ob = new Networking();
    
    @Override
    public void start(Stage primaryStage) {
        
        //Initialisation of all Components
        Label l1 = new Label("IP Address:");
        Label l2 = new Label("Port Number:");
        Label l3 = new Label("Message:");
        TextField ip = new TextField();
        TextField port = new TextField();
        TextField message = new TextField();
        Button btn = new Button("Send");
        StackPane root = new StackPane();
        VBox vbox = new VBox();
        Scene scene = new Scene(root);
        
        //Adding Element At required Place
        root.getChildren().add(vbox);
        vbox.getChildren().addAll(l1, ip, l2, port, l3, message, btn);
        
        //Displating Elements
        primaryStage.setScene(scene);
        primaryStage.setTitle("Client Program");
        primaryStage.show();
        
        //Adding Button Functionality
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                ipAddr=ip.getText();
                portNo=Integer.parseInt(port.getText());
                msg= message.getText();
                ob.runClient(ipAddr, portNo, msg);
            }
            
        });
        
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
