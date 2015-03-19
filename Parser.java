package scriptCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Parser {
	public static void write(ArrayList<GameEvent> iteration) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    try {
				FileWriter fw = new FileWriter(selectedFile);
				PrintWriter pw = new PrintWriter(fw);
				
				for(int i = 0; i < iteration.size(); i++) {
					pw.println(iteration.get(i).toString());
				}
				pw.close();
			}catch(IOException e) {
				System.out.println(e);
			} 
		}
	}
	
	public static void load() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    try {
				FileReader fr = new FileReader(selectedFile);
				BufferedReader br = new BufferedReader(fr);
				
				String str;
				Main.iteration.clear();
				while((str = br.readLine()) != null) {
					String[] ar = str.split(":");
					if(ar.length > 1) {
						System.out.println(ar.length);
						String method = ar[0];
						boolean walkLocally = Boolean.parseBoolean(ar[1]);
						String name = ar[2];
						String interaction = ar[3];
						int x = Integer.parseInt(ar[4]);
						int y = Integer.parseInt(ar[5]);
						int z = Integer.parseInt(ar[6]);
						int i = Integer.parseInt(ar[7]);
						boolean leftClick = Boolean.parseBoolean(ar[8]);
						boolean combatCheck = Boolean.parseBoolean(ar[9]);
						String condition = ar[10];
						String conditionType = ar[11];
						int cond1 = Integer.parseInt(ar[12]);
						int cond2 = Integer.parseInt(ar[13]);
						int cond3 = Integer.parseInt(ar[14]);
						int cond4 = Integer.parseInt(ar[15]);
						
						Main.iteration.add(new GameEvent(method,walkLocally,name,interaction,x,y,z,i,leftClick,combatCheck,condition,conditionType,cond1,cond2,cond3,cond4));
						System.out.println(str);
					}
					
				}
				gui.update();
				br.close();
				
			}catch(IOException e) {
				System.out.println(e);
			} 
		}
	}
}
