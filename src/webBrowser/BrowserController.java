package webBrowser;

public class BrowserController {

	final private BrowserModel model;
	final private BrowserView view;

	protected BrowserController(BrowserModel model, BrowserView view) {
		this.model = model;
		this.view = view;

		// register ourselves to listen for button clicks
		view.btnGo.setOnAction(e -> {
			String ipAddress = view.txtIP.getText();
			Integer port = new Integer(view.txtPort.getText());
			String webPage = model.browse(ipAddress, port);
			view.txtWebPage.setText(webPage);
		});
	}
}
