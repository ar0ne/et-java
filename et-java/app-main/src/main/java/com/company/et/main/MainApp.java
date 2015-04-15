package com.company.et.main;

//
//import com.company.et.domain.Professor;
//import com.company.et.domain.Task;
//import com.company.et.service.ProfessorService;
//import com.company.et.service.JsonService;
//import java.io.IOException;
//import java.text.ParseException;
//
//public class MainApp {
//
//
//    /**
//     * The main() method is ignored in correctly deployed JavaFX application.
//     * main() serves only as fallback in case the application can not be
//     * launched through deployment artifacts, e.g., in IDEs with limited FX
//     * support. NetBeans ignores main().
//     *
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws IOException, ParseException {
//        Professor professor = new Professor();
//        //test example
//        ProfessorService.addTaskToProfessor(professor, new Task("dfsdfadfad"));
//        System.out.println(professor.getProfessorTasks().get(0));
//        
//        System.out.println(JsonService.objectToString(professor));
//        
//    }
//
//}

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainApp extends Application {
    
 
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Работа преподавателей");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               
        launch(args);
    }
    
    

}