package controller;

import java.sql.SQLException;
import javafx.stage.Stage;
import model.dao.ReadWriteDatabase;
import model.data.User;

public class ButtonEventHandeler{

    public static void userRemoverButton(String username, Stage stage, AdminPageController mainController){
        System.out.println("try delete : " + username);
        try{
            ReadWriteDatabase database = new ReadWriteDatabase();
            User user = User.loadUserObject();
            System.out.println(user.getUsername());
            if(!user.getUsername().equals(username)){
                if(new PopupYoNController().showPopupYoN(stage, "Voulez vous supprimer l'utilisateur '" + username + "' ?")){
                    database.deleteUserByUsername(username);
                    new PopupInfoController().showPopupInfo(stage, "L'utilisateur '" + username + "' a bien été supprimer !");
                }
            }
            else{
                new PopupInfoController().showPopupInfo(stage, "L'utilisateur '" + username + "' n'a pas été supprimer !\nVous ne pouvez pas vous supprimer vous meme !");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            new PopupInfoController().showPopupInfo(stage, "Erreur de base de donnée:\nL'utilisateur '" + username + "' n'a pas été supprimer !");
        }
        mainController.loadUserTableAdmin();
    }
}