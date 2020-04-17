package htmlpull;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Listener implements ActionListener {

	TableModel table;
	
	Listener (TableModel tab) {
		table = tab;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "News - Official - Deutsch") {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			    try {
					Desktop.getDesktop().browse(new URI("https://borderlands.com/de-DE/news/#-"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (e.getActionCommand() == "News - reddit - Englisch") {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			    try {
					Desktop.getDesktop().browse(new URI("https://www.reddit.com/r/borderlands3/?f=flair_name%3A%22%5B%20News%20%5D%20%F0%9F%A7%BE%22"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			
		} else if (e.getActionCommand() == "Patch Notes") {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			    try {
					Desktop.getDesktop().browse(new URI("https://www.reddit.com/r/borderlands3/?f=flair_name%3A%22%5B%20Patch%20Notes%20%5D%20%F0%9F%93%96%20%22"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}

		
		}
	}


