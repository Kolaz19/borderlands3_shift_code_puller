package htmlpull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ShiftCodePuller {

	public static void main(String[] args) throws IOException {
		//See, if save.csv exists, otherwise create one
		File tmpFile = new File("save.csv");
		if (!tmpFile.exists()) {
			FileWriter writer = new FileWriter("save.csv",false);
			writer.close();
		}
		
		int iter = 0;
		//Only get array size (for later)
		Scanner scanner = new Scanner (new File("save.csv"));

		scanner.useDelimiter(";");
		while (scanner.hasNext()) {
			iter++;
			scanner.next();
		}
		scanner.close();
		
		Scanner scanner2 = new Scanner(new File("save.csv"));
		scanner2.useDelimiter(";");
		//create and fill Array
		String[] records = new String[iter];
		for (int x = 0; x < iter; x++) {
			records[x] = scanner2.next();
		}
			
		scanner2.close();
		
		//Get HTML from URL into String
		URL shift = new URL("https://shift.orcicorn.com/tags/borderlands3/index.json");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(shift.openStream()));
        
        String url = in.readLine();
        in.close();
        
        String code_string = "\"code\":";
        String date_string = "\"archived\":\"";
        String reward_string = "\"reward\":\"";
        String reward_endstring = "\",\"archived\"";

        int loops = -1;
        int index = 0;
        int index_end = 0;

       
        //Get Size of array with amount of appearence of 'code_string'
        while (index != -1) {
                        index+= 1;
                        index = url.indexOf(code_string,index);
                        loops++;
        }

        TableModel tmodel = new TableModel("Aktive SHiFT-Codes","Datum/Zeit","Belohnung","Eingelöst",loops);
        
        index = 0;
        // Add to array after every 'code_string'
        for (int x = 0; x != loops; x++) {
                        index = url.indexOf(code_string,index);
                        index++;
                        tmodel.data[x][0] = url.substring(index+7,index+36);  
                        
                        for (int y = 0; y < records.length; y++) {
                        	if ((tmodel.getValueAt(x,0)).equals(records[y])) {
                        		tmodel.setValueAt(true,x,3);
                        	}
                        }
        }
       
        index = 0;
        //Add to array after every 'date_string'
        for (int x = 0; x != loops; x++) {
                        index = url.indexOf(date_string,index);
                        index++;
                        tmodel.data[x][1] = url.substring(index+11,index+28);                              
        }
   
        index = 0;
        //Add to array after every 'reward_string' -> dynamic end of string, because reward length differs
        for (int x = 0; x != loops; x++) {
                        index = url.indexOf(reward_string,index);
                        index_end = url.indexOf(reward_endstring,index_end);
                        index++;
                        index_end++;
                        tmodel.data[x][2] = url.substring(index+9,index_end-1);                                         
        }   
		
        
        Listener listen = new Listener(tmodel);
        //Configure Menu bar and add listener 
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu ("ONLINE");
		JMenuItem menuitem1 = new JMenuItem ("News - Official - Deutsch");
		menuitem1.addActionListener(listen);
		JMenuItem menuitem2 = new JMenuItem ("News - reddit - Englisch");
		menuitem2.addActionListener(listen);
		JMenuItem menuitem3 = new JMenuItem ("Patch Notes");
		menuitem3.addActionListener(listen);
		menu.add(menuitem1);
		menu.add(menuitem2);
		menu.add(menuitem3);
		bar.add(menu);
        
		//Configure table 
		JFrame frame = new JFrame();		
		JTable table = new JTable(tmodel);
		table.getColumnModel().getColumn(0).setMinWidth(235);
		table.getColumnModel().getColumn(1).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.setCellSelectionEnabled(true);
		table.setBounds(500,500,300,400);
		JScrollPane scrollPane = new JScrollPane(table);
		
		//Set icon from local or from URL
		File icon = new File("icon.png");
		if (icon.exists()) {
		frame.setIconImage(ImageIO.read(new File("icon.png")));
		} else {
		frame.setIconImage(ImageIO.read(new URL("https://www.seekpng.com/png/full/148-1486747_borderlands-symbol-borderlands-symbols-tattoo-ideas-borderlands-vault.png")));
		}
		
		//Configure frame
		WindowsListen wlisten = new WindowsListen(tmodel);
		frame.addWindowListener(wlisten);
		frame.setJMenuBar(bar);
		frame.add(scrollPane);
		frame.setSize(550,85 + loops * 16);
		frame.setVisible(true);
		
	}
}
