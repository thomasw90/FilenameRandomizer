package application;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller {
	
	/** Dialog to select directory */
	private DirectoryChooser dirChooser;
	
	/** Stage the Pane/Scene is located */
	private Stage primaryStage;
	
	@FXML
	TextField path;
	
	public void init(Stage primaryStage) {		
		this.primaryStage = primaryStage;
		dirChooser = new DirectoryChooser();
	}

	public void openFolderDialog() {
		if(!path.getText().isEmpty()) {
			dirChooser.setInitialDirectory(new File(path.getText()));
		}

		File file = dirChooser.showDialog(primaryStage);
		if(file != null) {
			path.setText(file.getAbsolutePath());
		}
	}
	
	public void start() {
		File folder = new File(path.getText());
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if(file.isFile()) {
				file.renameTo(newFileName(file));			
			}
		}
	}
	
	private File newFileName(File file) {
		int randomNum = ThreadLocalRandom.current().nextInt(111111111, 999999999);
		return new File(file.getParent() + File.separator + Integer.toString(randomNum) + file.getName().substring(file.getName().lastIndexOf('.')));
	}
}
