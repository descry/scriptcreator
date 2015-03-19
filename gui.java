package scriptCreator;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.tbot.methods.Players;


public class gui extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String condition;
	public static boolean doClose = false;
	public static byte conditionByte;
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	static JList<String> list = new JList<String>(listModel);
		public gui() {
				
		new JFrame();
		setBounds(100, 100, 300, 300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnNewMenu = new JMenu("Script");
		menuBar.add(mnNewMenu);
		JMenuItem mntmStart = new JMenuItem("Start");
		final JMenu mnEditEvent = new JMenu("Edit event");
		final JMenu mnEvent = new JMenu("Add event");
		final JMenu mnDo = new JMenu("Condition");
		mntmStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.started = true;
				update();
				Main.running = true;
				mnEvent.setEnabled(false);
				mnEditEvent.setEnabled(false);
				mnDo.setEnabled(false);
			}
		});
		mnNewMenu.add(mntmStart);
		
		
		JMenuItem mnSetSpeed = new JMenuItem("Set loop interval");
		mnSetSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("New speed (Recommended between 1000->5000, depends on your PC, though.):");
				int x = Integer.parseInt(name);
				Main.loopint = x;
				update();
			}
		});
		mnNewMenu.add(mnSetSpeed);
		
		JMenuItem mntmPause = new JMenuItem("Pause");
		mntmPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				Main.running = false;
				mnEvent.setEnabled(true);
				mnEditEvent.setEnabled(true);
				mnDo.setEnabled(true);
			}
		});
		mnNewMenu.add(mntmPause);
		
		JMenuItem mntmSave = new JMenuItem("Save script");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Parser.write(Main.iteration);
			}
		});
		mnNewMenu.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load script");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Parser.load();
			}
		});
		mnNewMenu.add(mntmLoad);
		
		JMenuItem mntmRef = new JMenuItem("Refresh");
		mntmRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		mnNewMenu.add(mntmRef);
		
		
		menuBar.add(mnEvent);
		
		
		menuBar.add(mnDo);
		
		JMenuItem mntmIf = new JMenuItem("If");
		mntmIf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = list.getSelectedIndex();
				conditionGui cGui = new conditionGui();
				cGui.conditionType = "If";
				cGui.value = x;
				cGui.setVisible(true);
				
			}
		});
		mnDo.add(mntmIf);
		
		
		JMenuItem mntmWhile = new JMenuItem("While");
		mntmWhile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = list.getSelectedIndex();
				conditionGui cGui = new conditionGui();
				cGui.conditionType = "While";
				cGui.value = x;
				cGui.setVisible(true);
				
			}
		});
		mnDo.add(mntmWhile);		
		
		JMenu mnWalking = new JMenu("Walking");
		mnEvent.add(mnWalking);
		
		JMenuItem mntmWalkToTile = new JMenuItem("Walk to tile locally");
		mntmWalkToTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xS = JOptionPane.showInputDialog("X Coords:");
				String yS = JOptionPane.showInputDialog("Y Coords:");
				String zS = JOptionPane.showInputDialog("Z Coords:");
				int x = Integer.parseInt(xS);
				int y = Integer.parseInt(yS);
				int z = Integer.parseInt(zS);
				Main.iteration.add(new GameEvent("Walking", x,y,z,true,"null","null"));
				update();
			}
		});
		mnWalking.add(mntmWalkToTile);
		
		JMenuItem mntmWalkToTileCurrent = new JMenuItem("Walk to current tile locally");
		mntmWalkToTileCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Players.getLocal().getLocation().getX();
				int y = Players.getLocal().getLocation().getY();
				int z = Players.getLocal().getLocation().getPlane();
				Main.iteration.add(new GameEvent("Walking", x,y,z,true,"null","null"));
				update();
			}
		});
		mnWalking.add(mntmWalkToTileCurrent);
		
		JMenuItem mntmWebWalkTo = new JMenuItem("Web walk to tile");
		mntmWebWalkTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xS = JOptionPane.showInputDialog("X Coords:");
				String yS = JOptionPane.showInputDialog("Y Coords:");
				String zS = JOptionPane.showInputDialog("Z Coords:");
				int x = Integer.parseInt(xS);
				int y = Integer.parseInt(yS);
				int z = Integer.parseInt(zS);
				Main.iteration.add(new GameEvent("Walking", x,y,z,false,"null","null"));
				update();
			}
		});
		mnWalking.add(mntmWebWalkTo);
		
		JMenuItem mntmEnableRunIf = new JMenuItem("Enable run if energy");
		mntmEnableRunIf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zS = JOptionPane.showInputDialog("Run energy higher or equal to:");
				int x = Integer.parseInt(zS);
				Main.iteration.add(new GameEvent("CheckRun", x,"null","null"));
				update();
			}
		});
		mnWalking.add(mntmEnableRunIf);
		
		JMenu mnInteraction = new JMenu("Interaction");
		mnEvent.add(mnInteraction);
		
		JMenuItem mntmGameobject = new JMenuItem("GameObject");
		mntmGameobject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("GameObject name (CASE SENSITIVE):");
				String interaction = JOptionPane.showInputDialog("Interaction (CASE SENSITIVE):");
				Main.iteration.add(new GameEvent("GameObject", name, interaction,"null","null"));
				update();
			}
		});
		mnInteraction.add(mntmGameobject);
		
		JMenuItem mntmNpc = new JMenuItem("NPC");
		mntmNpc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("NPC name (CASE SENSITIVE):");
				String interaction = JOptionPane.showInputDialog("Interaction (CASE SENSITIVE):");
				String combatCheck = JOptionPane.showInputDialog("Check if NPC is in combat (1 = YES/0 = NO):");
				byte combatCheckk = Byte.parseByte(combatCheck);
				if(combatCheckk == 0) {
					Main.iteration.add(new GameEvent("NPC", name, interaction, false,"null","null"));
				}else if(combatCheckk == 1) {
					Main.iteration.add(new GameEvent("NPC", name, interaction, true,"null","null"));
				}else{
					System.out.println("Wrong input");
				}
				
				update();
			}
		});
		mnInteraction.add(mntmNpc);
		
		JMenuItem mntmGrounditems = new JMenuItem("GroundItem");
		mntmGrounditems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("GroundItem name (CASE SENSITIVE):");
				String interaction = JOptionPane.showInputDialog("Interaction (CASE SENSITIVE):");
				Main.iteration.add(new GameEvent("GroundItem", name, interaction,"null","null"));
				update();
			}
			
		});
		mnInteraction.add(mntmGrounditems);
		
		JMenu mnWidget = new JMenu("Widget");
		mnInteraction.add(mnWidget);
		
		JMenuItem mntmSimpleWidget = new JMenuItem("Simple widget");
		mntmSimpleWidget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String widget1 = JOptionPane.showInputDialog("Widget ID (1st):");
				int wid1 = Integer.parseInt(widget1);
				String widget2 = JOptionPane.showInputDialog("Widget ID (2nd):");
				int wid2 = Integer.parseInt(widget2);
				String interaction = JOptionPane.showInputDialog("Interaction (CASE SENSITIVE):");

				Main.iteration.add(new GameEvent("Widget", wid1, wid2, interaction,"null","null"));
				update();
			}
		});
		mnWidget.add(mntmSimpleWidget);
		
		JMenuItem mntmChildWidget = new JMenuItem("Child widget");
		mntmChildWidget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String widget1 = JOptionPane.showInputDialog("Widget ID (1st):");
				int wid1 = Integer.parseInt(widget1);
				String widget2 = JOptionPane.showInputDialog("Widget ID (2nd):");
				int wid2 = Integer.parseInt(widget2);
				String widget3 = JOptionPane.showInputDialog("Child ID:");
				int wid3 = Integer.parseInt(widget3);
				String interaction = JOptionPane.showInputDialog("Interaction (CASE SENSITIVE):");

				Main.iteration.add(new GameEvent("Child widget", wid1, wid2, wid3, interaction,"null","null"));
				update();
			}
		});
		mnWidget.add(mntmChildWidget);
		
		JMenu mnInv = new JMenu("Inventory");
		mnEvent.add(mnInv);
		
		JMenuItem mntmEquip = new JMenuItem("Equip");
		mntmEquip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("Item ID: ");
				int i = Integer.parseInt(s);
				Main.iteration.add(new GameEvent("Equip",i,"null","null"));
				update();
			}
		});
		mnInv.add(mntmEquip);
		
		JMenuItem mntmInteract = new JMenuItem("Interact");
		mntmInteract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("Item ID: ");
				int i = Integer.parseInt(s);
				String s1 = JOptionPane.showInputDialog("Interaction (CASE SENSITIVE): ");
				Main.iteration.add(new GameEvent("Interact",s1,i,"null","null"));
				update();
			}
		});
		mnInv.add(mntmInteract);
		
		JMenuItem mntmInteractW = new JMenuItem("Interact with another inventory item");
		mntmInteractW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("Item ID: ");
				int i = Integer.parseInt(s);
				String s2 = JOptionPane.showInputDialog("Item ID: (to interact with): ");
				int i2 = Integer.parseInt(s2);

				Main.iteration.add(new GameEvent("Interact item",i,i2,"null","null"));
				update();
			}
		});
		mnInv.add(mntmInteractW);
		
		JMenuItem mntmDrop = new JMenuItem("Drop");
		mntmDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("Item ID: ");
				int i = Integer.parseInt(s);
				Main.iteration.add(new GameEvent("Drop",i,"null","null"));
				update();
			}
		});
		mnInv.add(mntmDrop);
		
		JMenuItem mntmDropAll = new JMenuItem("Drop all");
		mntmDropAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Drop all","null","null"));
				update();
			}
		});
		mnInv.add(mntmDropAll);
		
		JMenu mnBank = new JMenu("Bank");
		mnEvent.add(mnBank);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Open bank","null","null"));
				update();
			}
		});
		mnBank.add(mntmOpen);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Close bank","null","null"));
				update();
			}
		});
		mnBank.add(mntmClose);
		
		JMenuItem mntmDeposit = new JMenuItem("Deposit");
		mntmDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Item ID:");
				String interaction = JOptionPane.showInputDialog("Amount:");
				int i = Integer.parseInt(interaction);
				Main.iteration.add(new GameEvent("Deposit", name, i,"null","null"));
				update();
			}
		});
		mnBank.add(mntmDeposit);
		
		JMenuItem mntmDepositAll = new JMenuItem("Deposit all");
		mntmDepositAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Deposit all","null","null"));
				update();
			}
		});
		mnBank.add(mntmDepositAll);
		
		JMenuItem mntmWithdraw = new JMenuItem("Withdraw");
		mntmWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Item ID:");
				String interaction = JOptionPane.showInputDialog("Amount:");
				int i = Integer.parseInt(interaction);
				Main.iteration.add(new GameEvent("Withdraw", name, i,"null","null"));
				update();
			}
		});
		mnBank.add(mntmWithdraw);
		
		JMenu mnSleep = new JMenu("Sleep");
		mnEvent.add(mnSleep);
		
		JMenuItem mntmNormalSleep = new JMenuItem("Normal sleep");
		mntmNormalSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sleepS = JOptionPane.showInputDialog("Sleep duration in ms");
				int sleep = Integer.parseInt(sleepS);
				Main.iteration.add(new GameEvent("Sleep", sleep,"null","null"));
				update();
			}
		});
		mnSleep.add(mntmNormalSleep);
		
		JMenuItem mntmConditionalSleep = new JMenuItem("Conditional sleep");
		mntmConditionalSleep.setEnabled(false);
		mnSleep.add(mntmConditionalSleep);
		
		JMenu mnMouse = new JMenu("Mouse");
		mnEvent.add(mnMouse);
		
		JMenuItem mntmMoveMouseTo = new JMenuItem("Move mouse to");
		mntmMoveMouseTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xS = JOptionPane.showInputDialog("X Coordinate:");
				int x = Integer.parseInt(xS);
				String yS = JOptionPane.showInputDialog("Y Coordinate:");
				int y = Integer.parseInt(yS);
				Main.iteration.add(new GameEvent("Move mouse", x, y,"null","null"));
				update();
			}
		});
		mnMouse.add(mntmMoveMouseTo);
		
		JMenuItem mntmLeftClick = new JMenuItem("Left click");
		mntmLeftClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Click mouse", true,"null","null"));
				update();
			}
		});
		mnMouse.add(mntmLeftClick);
		
		JMenuItem mntmRightClick = new JMenuItem("Right click");
		mntmRightClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Click mouse", false,"null","null"));
				update();
			}
		});
		mnMouse.add(mntmRightClick);
		
		JMenu mnCamera = new JMenu("Camera");
		mnEvent.add(mnCamera);
		
		JMenuItem mntmMoveCameraRandomly = new JMenuItem("Move camera randomly");
		mntmMoveCameraRandomly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Move camera randomly","null","null"));
				update();
			}
		});
		mnCamera.add(mntmMoveCameraRandomly);
		
		JMenuItem mntmSetPitch = new JMenuItem("Set pitch");
		mntmSetPitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String yS = JOptionPane.showInputDialog("Pitch:");
				int y = Integer.parseInt(yS);
				Main.iteration.add(new GameEvent("Change camera pitch", y,"null","null"));
				update();
			}
		});
		mnCamera.add(mntmSetPitch);
		
		JMenuItem mntmSetAngle = new JMenuItem("Set angle");
		mntmSetAngle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String yS = JOptionPane.showInputDialog("Angle:");
				int y = Integer.parseInt(yS);
				Main.iteration.add(new GameEvent("Change camera angle", y,"null","null"));
				update();
			}
		});
		mnCamera.add(mntmSetAngle);
		
		JMenu mnGame = new JMenu("Game");
		mnEvent.add(mnGame);
		
		JMenuItem mntmInstantWorldHop = new JMenuItem("Instant world hop random P2P worlds");
		mntmInstantWorldHop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("WorldHop (P2P)","null","null"));
				update();
			}
		});
		mnGame.add(mntmInstantWorldHop);
		
		
		JMenuItem mntmInstantWorldHop1 = new JMenuItem("Instant world hop random F2P worlds");
		mntmInstantWorldHop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("WorldHop (F2P)","null","null"));
				update();
			}
		});
		mnGame.add(mntmInstantWorldHop1);
		
		JMenuItem mntmInstantWorldHopw = new JMenuItem("Instant world hop to a specific world");
		mntmInstantWorldHopw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = JOptionPane.showInputDialog("Enter world:" );
				int x1 = Integer.parseInt(s1);
				Main.iteration.add(new GameEvent("WorldHop",x1,"null","null"));
				update();
			}
		});
		mnGame.add(mntmInstantWorldHopw);
		
		JMenuItem mntmLogout = new JMenuItem("Log out");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.add(new GameEvent("Logout","null","null"));
				update();
			}
		});
		mnGame.add(mntmLogout);
		
		
		menuBar.add(mnEditEvent);
		
		
		
		
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Action ID:");
				int x = Integer.parseInt(name);
				Main.iteration.remove(x);
				update();
			}
		});
		mnEditEvent.add(mntmDelete);
		
		JMenuItem mntmDeleteSel = new JMenuItem("Delete selected event");
		mntmDeleteSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = list.getSelectedIndex();
				Main.iteration.remove(x);
				update();
			}
		});
		mnEditEvent.add(mntmDeleteSel);
		
		JMenuItem mntmDeleteAll = new JMenuItem("Delete all");
		mntmDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.iteration.clear();
				update();
			}
		});
		mnEditEvent.add(mntmDeleteAll);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(list);
		list.setFocusable(false);
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
			
		});
		}
	
	public static void liveUpdate() {
		int i = Main.getRunning();
		list.setSelectedIndex(i);		
	}
	
	public static String getCondition(int i) {
		if(!Main.iteration.get(i).getCondition().equalsIgnoreCase("null")) {
			return " : " + Main.iteration.get(i).getConditionType() + " " + Main.iteration.get(i).getCondition();
		}else{
			return "";
		}
	}
	
	public static void update(){
		
		listModel.clear();
		Main.running = false;
		
		if(Main.iteration.size() > 0) {
			for(int i = 0; i < Main.iteration.size(); i++) {
				GameEvent g = Main.iteration.get(i);
				if(g.getMethod().equalsIgnoreCase("Walking")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getX() + "," + g.getY() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Sleep") || g.getMethod().equalsIgnoreCase("CheckRun") || g.getMethod().equalsIgnoreCase("Change camera pitch") || g.getMethod().equalsIgnoreCase("Change camera angle")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getSleep() + getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("NPC")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getName() + " - " + g.getInteraction() + " - " + g.checkCombat() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("GameObject") || g.getMethod().equalsIgnoreCase("GroundItem")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getName() + " - " + g.getInteraction() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Move mouse")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getX() + " - " + g.getY() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Click mouse")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.mouseClick() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Move camera randomly") || g.getMethod().equalsIgnoreCase("Open bank") || g.getMethod().equalsIgnoreCase("Close bank") || g.getMethod().equalsIgnoreCase("Deposit all")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Withdraw") || g.getMethod().equalsIgnoreCase("Deposit") || g.getMethod().equalsIgnoreCase("Interact")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getName() + "," + g.getSleep() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Child widget")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getX() + "," + g.getY() + "," + g.getZ() + " - " + g.getInteraction() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Widget")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getX() + "," + g.getY() + " - " + g.getInteraction() +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("WorldHop (P2P)") || g.getMethod().equalsIgnoreCase("WorldHop (F2P)") || g.getMethod().equalsIgnoreCase("Logout")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Drop") || g.getMethod().equalsIgnoreCase("Equip")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getSleep() + " - " +getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Interact")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getSleep() + " - " + g.getName() + getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Drop all")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("Interact item")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " Use " + g.getX() + " on " + g.getY() + getCondition(i));
				}else if(g.getMethod().equalsIgnoreCase("WorldHop")) {
					listModel.addElement("[" + i + "] - " + g.getMethod() + " - " + g.getSleep() + getCondition(i));
				}
			}
		}
		
	}
}