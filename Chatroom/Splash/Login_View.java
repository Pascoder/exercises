package Splash;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login_View {

protected Menu options;
protected Stage stage;
private MenuBar menubar;
private Login_Model model;
protected Label username, password, placeholder, status;
protected TextField txtusername;
protected PasswordField pwpassword;//TextField to insert a pw

public Button btnlogin, btnerstellen;

	public Login_View(Stage primary, Login_Model model) {
		this.stage = primary;
		this.model = model;
		
		
		
		this.username = new Label();
		this.txtusername = new TextField();
		this.password = new Label();
		this.pwpassword = new PasswordField();
		this.btnlogin = new Button();
		this.btnerstellen = new Button();
		this.placeholder = new Label();
		this.status = new Label();
		
		
		
		//MenuBar
		this.options = new Menu();
		this.menubar = new MenuBar();
		
		
		this.menubar.getMenus().add(options);
		
		updateTexts();
		
		GridPane root = new GridPane();
		root.getStyleClass().add("hbox");
		root.setPadding(new Insets(10));
		root.setHgap(10);
		root.setVgap(10);
		
		root.add(menubar, 3, 0);
		root.add(username, 0, 1);
		root.add(txtusername, 1, 1);
		root.add(password, 0, 2);
		root.add(pwpassword, 1, 2);
		root.add(placeholder, 0, 3);
		root.add(btnlogin, 1, 3);
		root.add(btnerstellen, 2, 3);
		root.add(status, 1, 4);
		
		Scene scene = new Scene(root,400,150);
		stage.setScene(scene);
		
		stage.show();
		
	}

	private void updateTexts() {
		
        
        // The menu entries
		options.setText("Options");
       username.setText("Username:	");
      password.setText("Password:	");
       btnlogin.setText("Login");
       
       // Top Controls
       btnerstellen.setText("Create");
      
       
       stage.setTitle("Login Menu");
       status.setText("-");
		
	}
	public Button getButton() {
		return this.btnlogin;
	}

}
