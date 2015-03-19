//package scriptCreator;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JToolBar;
//
//import java.awt.BorderLayout;
//
//import javax.swing.JMenu;
//import javax.swing.JCheckBoxMenuItem;
//import javax.swing.JMenuBar;
//import javax.swing.JOptionPane;
//import javax.swing.JPopupMenu;
//
//import java.awt.Component;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.JMenuItem;
//import javax.swing.JScrollPane;
//import javax.swing.JList;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class wdb {
//
//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					wdb window = new wdb();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public wdb() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		
//		frame = new JFrame();
//		frame.setBounds(100, 100, 256, 308);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		JMenuBar menuBar = new JMenuBar();
//		frame.setJMenuBar(menuBar);
//		
//		
//		
//		
//		JMenu mnNewMenu = new JMenu("Script");
//		menuBar.add(mnNewMenu);
//		
//		JMenuItem mntmStart = new JMenuItem("Start");
//		mntmStart.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnNewMenu.add(mntmStart);
//		
//		JMenu mnEvent = new JMenu("Add event");
//		menuBar.add(mnEvent);
//		
//		JMenu mnDo = new JMenu("Condition");
//		mnEvent.add(mnDo);
//		
//		JMenuItem mntmIf = new JMenuItem("If");
//		mntmIf.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				conditionGui cGui = new conditionGui();
//				cGui.setVisible(true);
//			}
//		});
//		mnDo.add(mntmIf);
//		
//		JMenu mnWalking = new JMenu("Walking");
//		mnEvent.add(mnWalking);
//		
//		JMenuItem mntmWalkToTile = new JMenuItem("Walk to tile locally");
//		mntmWalkToTile.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		mnWalking.add(mntmWalkToTile);
//		
//		JMenuItem mntmWebWalkTo = new JMenuItem("Web walk to tile");
//		mntmWebWalkTo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnWalking.add(mntmWebWalkTo);
//		
//		JMenuItem mntmEnableRunIf = new JMenuItem("Enable run if energy");
//		mntmEnableRunIf.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnWalking.add(mntmEnableRunIf);
//		
//		JMenu mnInteraction = new JMenu("Interaction");
//		mnEvent.add(mnInteraction);
//		
//		JMenuItem mntmGameobject = new JMenuItem("GameObject");
//		mntmGameobject.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnInteraction.add(mntmGameobject);
//		
//		JMenuItem mntmNpc = new JMenuItem("NPC");
//		mntmNpc.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnInteraction.add(mntmNpc);
//		
//		JMenuItem mntmGrounditems = new JMenuItem("GroundItem");
//		mntmGrounditems.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnInteraction.add(mntmGrounditems);
//		
//		JMenu mnWidget = new JMenu("Widget");
//		mnInteraction.add(mnWidget);
//		
//		JMenuItem mntmSimpleWidget = new JMenuItem("Simple widget");
//		mntmSimpleWidget.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnWidget.add(mntmSimpleWidget);
//		
//		JMenuItem mntmChildWidget = new JMenuItem("Child widget");
//		mntmChildWidget.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnWidget.add(mntmChildWidget);
//		
//		JMenu mnBank = new JMenu("Bank");
//		mnEvent.add(mnBank);
//		
//		JMenuItem mntmOpen = new JMenuItem("Open");
//		mntmOpen.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnBank.add(mntmOpen);
//		
//		JMenuItem mntmClose = new JMenuItem("Close");
//		mntmClose.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnBank.add(mntmClose);
//		
//		JMenuItem mntmDeposit = new JMenuItem("Deposit");
//		mntmDeposit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnBank.add(mntmDeposit);
//		
//		JMenuItem mntmDepositAll = new JMenuItem("Deposit All");
//		mntmDepositAll.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnBank.add(mntmDepositAll);
//		
//		JMenuItem mntmWithdraw = new JMenuItem("Withdraw");
//		mntmWithdraw.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnBank.add(mntmWithdraw);
//		
//		JMenu mnSleep = new JMenu("Sleep");
//		mnEvent.add(mnSleep);
//		
//		JMenuItem mntmNormalSleep = new JMenuItem("Normal sleep");
//		mntmNormalSleep.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnSleep.add(mntmNormalSleep);
//		
//		JMenuItem mntmConditionalSleep = new JMenuItem("Conditional sleep");
//		mntmConditionalSleep.setEnabled(false);
//		mnSleep.add(mntmConditionalSleep);
//		
//		JMenu mnMouse = new JMenu("Mouse");
//		mnEvent.add(mnMouse);
//		
//		JMenuItem mntmMoveMouseTo = new JMenuItem("Move mouse to");
//		mntmMoveMouseTo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnMouse.add(mntmMoveMouseTo);
//		
//		JMenuItem mntmLeftClick = new JMenuItem("Left click");
//		mntmLeftClick.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnMouse.add(mntmLeftClick);
//		
//		JMenuItem mntmRightClick = new JMenuItem("Right click");
//		mntmRightClick.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnMouse.add(mntmRightClick);
//		
//		JMenu mnCamera = new JMenu("Camera");
//		mnEvent.add(mnCamera);
//		
//		JMenuItem mntmMoveCameraRandomly = new JMenuItem("Move camera randomly");
//		mntmMoveCameraRandomly.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnCamera.add(mntmMoveCameraRandomly);
//		
//		JMenuItem mntmSetPitch = new JMenuItem("Set pitch");
//		mntmSetPitch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnCamera.add(mntmSetPitch);
//		
//		JMenuItem mntmSetAngle = new JMenuItem("Set angle");
//		mntmSetAngle.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		mnCamera.add(mntmSetAngle);
//		
//		JMenu mnGame = new JMenu("Game");
//		mnEvent.add(mnGame);
//		
//		JMenuItem mntmInstantWorldHop = new JMenuItem("Instant world hop all worlds");
//		mnGame.add(mntmInstantWorldHop);
//		
//		JMenu mnEditEvent = new JMenu("Edit event");
//		menuBar.add(mnEditEvent);
//		
//		JMenuItem mntmDelete = new JMenuItem("Delete");
//		mnEditEvent.add(mntmDelete);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
//		
//		JList list = new JList();
//		scrollPane.setViewportView(list);
//	}
//
//	private static void addPopup(Component component, final JPopupMenu popup) {
//		component.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			public void mouseReleased(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//			private void showMenu(MouseEvent e) {
//				popup.show(e.getComponent(), e.getX(), e.getY());
//			}
//		});
//	}
//}
