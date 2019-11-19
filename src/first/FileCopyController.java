package first;

public class FileCopyController {
	private final FileCopyView view;
	private final FileCopyModel model;
	
	public FileCopyController(FileCopyView view, FileCopyModel model) {
		this.view = view;
		this.model = model;
		
		view.btnCopy.setOnAction( event -> {
			String fileNameIn = view.txtInput.getText();
			String fileNameOut = view.txtOutput.getText();
			
			model.FileCopy(fileNameIn, fileNameOut);
		});
	}
}
