package scriptCreator;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;



public class conditionGui extends JFrame {
	public int value;
	public String conditionType;
	private static final long serialVersionUID = 1L;
		@SuppressWarnings({"unchecked", "rawtypes"})
		public conditionGui() {
			new JFrame();
			setTitle("Condition selector");
			setBounds(300, 300, 300, 300);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			final JList list = new JList();
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String condition = null;
					if(list.getSelectedIndex() == 0) {
						condition = "animating";
						addCondition(value, condition, conditionType,0,0,0,0);
					}else if(list.getSelectedIndex() == 1) {
						condition = "!animating";
						addCondition(value, condition, conditionType,0,0,0,0);
					}else if(list.getSelectedIndex() == 2) {
						condition = "moving";
						addCondition(value, condition, conditionType,0,0,0,0);
					}else if(list.getSelectedIndex() == 3) {
						condition = "!moving";
						addCondition(value, condition, conditionType,0,0,0,0);
					}else if(list.getSelectedIndex() == 4) {
						condition = "inventoryFull";
						addCondition(value, condition, conditionType,0,0,0,0);
					}else if(list.getSelectedIndex() == 5) {
						condition = "!inventoryFull";
						addCondition(value, condition, conditionType,0,0,0,0);
					}else if(list.getSelectedIndex() == 6) {
						condition = "inventoryEmpty";
						addCondition(value, condition, conditionType,0,0,0,0);
					}else if(list.getSelectedIndex() == 7) {
						condition = "inventoryContains";
						String s = JOptionPane.showInputDialog("Item ID:");
						int si = Integer.parseInt(s);
						addCondition(value, condition, conditionType,si,0,0,0);
					}else if(list.getSelectedIndex() == 8) {
						condition = "!inventoryContains";
						String s = JOptionPane.showInputDialog("Item ID:");
						int si = Integer.parseInt(s);
						addCondition(value, condition, conditionType,si,0,0,0);
					}else if(list.getSelectedIndex() == 9) {
						condition = "areaContains";
						String s1 = JOptionPane.showInputDialog("Area UPPER LEFT X:");
						int x1 = Integer.parseInt(s1);
						String s2 = JOptionPane.showInputDialog("Area UPPER LEFT Y:");
						int x2 = Integer.parseInt(s2);
						String s3 = JOptionPane.showInputDialog("Area LOWER RIGHT X:");
						int x3 = Integer.parseInt(s3);
						String s4 = JOptionPane.showInputDialog("Area LOWER RIGHT Y:");
						int x4 = Integer.parseInt(s4);
						addCondition(value, condition, conditionType,x1,x2,x3,x4);
					}else if(list.getSelectedIndex() == 10) {
						condition = "!areaContains";
						String s1 = JOptionPane.showInputDialog("Area UPPER LEFT X:");
						int x1 = Integer.parseInt(s1);
						String s2 = JOptionPane.showInputDialog("Area UPPER LEFT Y:");
						int x2 = Integer.parseInt(s2);
						String s3 = JOptionPane.showInputDialog("Area LOWER RIGHT X:");
						int x3 = Integer.parseInt(s3);
						String s4 = JOptionPane.showInputDialog("Area LOWER RIGHT Y:");
						int x4 = Integer.parseInt(s4);
						addCondition(value, condition, conditionType,x1,x2,x3,x4);
					}else if(list.getSelectedIndex() == 11) {
						condition = "tileContains";
						String s1 = JOptionPane.showInputDialog("Tile X:");
						int x1 = Integer.parseInt(s1);
						String s2 = JOptionPane.showInputDialog("Tile Y:");
						int x2 = Integer.parseInt(s2);
						addCondition(value, condition, conditionType,x1,x2,0,0);
					}else if(list.getSelectedIndex() == 12) {
						condition = "!tileContains";
						String s1 = JOptionPane.showInputDialog("Tile X:");
						int x1 = Integer.parseInt(s1);
						String s2 = JOptionPane.showInputDialog("Tile Y:");
						int x2 = Integer.parseInt(s2);
						addCondition(value, condition, conditionType,x1,x2,0,0);
					}else if(list.getSelectedIndex() == 13) {
						condition = "groundItemOnScreen";
						String s1 = JOptionPane.showInputDialog("Item ID:");
						int x1 = Integer.parseInt(s1);
						addCondition(value, condition, conditionType,x1,0,0,0);
					}else if(list.getSelectedIndex() == 14) {
						condition = "!groundItemOnScreen";
						String s1 = JOptionPane.showInputDialog("Item ID:");
						int x1 = Integer.parseInt(s1);
						addCondition(value, condition, conditionType,x1,0,0,0);
					}
					
					dispose();
					gui.update();
				}
			});
			
			
			
			list.setModel(new AbstractListModel() {
				private static final long serialVersionUID = 1L;
				String[] values = new String[] {
						"Animating", "Not animating", "Moving", "Not moving", "Inventory full","Inventory not full", "Inventory empty", "Inventory contains",
						"Inventory doesn't contain", "Player in area", "Player not in area", "Player on tile", "Player not on tile",
						"Ground item on screen", "Ground item not on screen"
				
				};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			scrollPane.setViewportView(list);
		}
		
		public static void addCondition(int x, String condition, String conditionType, int cond1, int cond2, int cond3, int cond4) {
			Main.iteration.get(x).cond1 = cond1;
			Main.iteration.get(x).cond2 = cond2;
			Main.iteration.get(x).cond3 = cond3;
			Main.iteration.get(x).cond4 = cond4;
			
			Main.iteration.get(x).setConditionType(conditionType);
			Main.iteration.get(x).setCondition(condition);
		}
	
	}