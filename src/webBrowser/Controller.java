package webBrowser;

public class Controller {

	final private Model model;
	final private View view;

	protected Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		// register ourselves to listen for button clicks
		view.btnGo.setOnAction(e -> {
			Integer port = new Integer(view.txtPort.getText());
			model.setPort(port);
			model.setDaemon(true); // End when the program closes
			model.start();
		});
	}
}
