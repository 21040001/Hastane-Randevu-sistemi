package com.Hastane.RezerveSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class RezerveSystemApplication extends Application {

	public static void main(String[] args) {
		SpringApplication.run(RezerveSystemApplication.class, args);
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

    	Parent root = FXMLLoader.load(getClass().getResource("/com/Hastane/RezerveSystem/View/Main.fxml"));
    	Scene scene = new Scene(root );
    
    	stage.setScene(scene);
    	stage.setTitle("My Title"); //ekran titlesini ayarlar
        stage.show();
	}

}