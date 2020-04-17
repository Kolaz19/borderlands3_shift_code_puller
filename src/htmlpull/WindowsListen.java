package htmlpull;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileWriter;
import java.io.IOException;

public class WindowsListen implements WindowListener {
	
	TableModel table;
	
	WindowsListen(TableModel tab) {
		table = tab;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {

		try {
			//Overwrite file
			FileWriter writer = new FileWriter("save.csv",false);
			
			for (int y = 0; y != table.data.length ; y++) {	
				String lv_hold = String.valueOf((table.getValueAt(y,3)));
				if (lv_hold == "true") {
					writer.append((table.getValueAt(y,0)).toString());
					writer.append(";");
				}
			}
			writer.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
