package scriptCreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.ScriptCategory;
import org.tbot.internal.event.events.MessageEvent;
import org.tbot.internal.event.listeners.MessageListener;
import org.tbot.internal.event.listeners.PaintListener;
import org.tbot.methods.Mouse;
import org.tbot.methods.Players;
import org.tbot.methods.Skills;
import org.tbot.methods.Time;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.Player;



@Manifest(name = "ScriptCreator", authors = "descry", category = ScriptCategory.OTHER, version = 0.6)
public class Main extends AbstractScript implements MessageListener, PaintListener, MouseListener {

	static gui frame = new gui();
	long startTime = 0;
	Point p;
	Player player = Players.getLocal();
	int mX, mY, angle, xpStart, lvStart, done, mouseSpeed;
	static int loopint = 1500;
	List<MousePathPoint> mousePath = new LinkedList<>();
	String status = null;
	static Boolean started = false;	
	public boolean onStart() {
		
			try {
				if(checkForUser()) {
					canStart = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(canStart) {
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						
						@Override
						public void run() {
							frame.setVisible(true);
							
						}
					});
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				while(!started) {
					sleep(100);
					if(started) {
						break;
					}
				}			
				mouseSpeed = 185;
				startTime = System.currentTimeMillis();
				xpStart = Skills.getTotalExperience();
				paintImage = getImage("http://i.imgur.com/Ej7CXx7.png");
				cur = getImage("http://i.imgur.com/PCfeJTo.png");	

				
			}
			
			if(!Inventory.isOpen()) {
				Inventory.openTab();
			}

		return true;
	}
	
	
	
	static ArrayList<GameEvent> iteration = new ArrayList<GameEvent>();
	static boolean running = true;
	boolean canStart = false;
	static int ii = 0;
	@Override
	public int loop() {
		
		if(running) {
			for(int i = 0; i < iteration.size(); i++) {
				GameEvent event = iteration.get(i);
				ii = i;	
				gui.liveUpdate();
				status = iteration.get(i).getMethod();
				event.execute();
				Time.sleep(loopint);
			}
		}
		
		return 10;
	
	}  
	
	public static int getRunning() {
		return ii;
	}
	
	

	Rectangle a = new Rectangle(151,316,30,20);
	@Override
	public void mouseClicked(MouseEvent e) {
		p = e.getPoint();
		e.consume();
		if(a.contains(p)) {
			if(frame.isVisible()) {
				frame.setVisible(false);
			}else{
				frame.setVisible(true);
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	Image paintImage = null;
	Image cur = null;
	public Image getImage(String url) {
	        try {
	            return ImageIO.read(new URL(url));
	        } catch(Exception e) {
	            System.err.println(e);
	            return null;
	        }
	    }
	
    public static String elapsedTimee(long elapsedTime) {
        String format = String.format("%%0%dd", 2);
        elapsedTime = elapsedTime / 1000;
        String seconds = String.format(format, elapsedTime % 60);
        String minutes = String.format(format, (elapsedTime % 3600) / 60);
      
        String hours = String.format(format, elapsedTime / 3600);
        String time =  hours + ":" + minutes + ":" + seconds;
        return time;
    }

	@Override
	public void onRepaint(final Graphics g1) {
		AffineTransform old = ((Graphics2D) g1).getTransform();
		Graphics2D g = (Graphics2D) g1; 
		long millis = System.currentTimeMillis() - startTime;
		int xpGained = (Skills.getTotalExperience()) - xpStart; 
		int perHour = (int) (xpGained * 3600000.0D / millis);
		if(started) {
			g.drawImage(paintImage, 0, 238, null);
			
			g.setColor(new Color(100,100,100));
			g.setFont(new Font("Tahoma", Font.PLAIN, 9)); 
			g.drawString("ScriptCreator, version " + super.getManifest().version(), 36, 257);
			g.drawString("" + elapsedTimee(System.currentTimeMillis()-startTime), 15, 275);
			g.drawString("Status: " + status, 15, 290);
			g.drawString("XP Gained: " + xpGained, 15, 305);
			g.drawString("XP/h: " + perHour, 15, 320);
			if(frame.isVisible()) {
				g.setColor(Color.red);
			}else{
				g.setColor(Color.green);
			}
			
			g.drawString("gui", 157, 325);
			
		}
		
		
		
		mX = Mouse.getLocation().x;
		mY = Mouse.getLocation().y;
		g.drawImage(cur, mX, mY, null);
		g.setTransform(old);

		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		Point clientCursor = Mouse.getLocation();
		MousePathPoint mpp = new MousePathPoint(clientCursor.x+5, clientCursor.y+10, 200);
		if (mousePath.isEmpty() || !((LinkedList<MousePathPoint>) mousePath).getLast().equals(mpp))
			mousePath.add(mpp);
		MousePathPoint lastPoint = null;
		for (MousePathPoint a : mousePath) {
			if (lastPoint != null) {
				g.setColor(new Color(200, 200, 200, a.getAlpha()));
				g.drawLine(a.x, a.y, lastPoint.x, lastPoint.y);
			}
			lastPoint = a;
		}
	}
	
	public int getExperience() {
		return Skills.getTotalExperience();
	}

	@Override
	public void messageReceived(MessageEvent arg0) {
	
		
	}
	
	

	private boolean checkForUser() throws Exception {
		
//		URL u = new URL("http://privatescripts.esy.es/users/nmz.txt");
//		BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
//		String inputLine;
//		while ((inputLine = in.readLine()) != null) {
//			if(inputLine.equalsIgnoreCase(Game.getCurrentUsername()) || inputLine.equalsIgnoreCase(TEnvironment.getForumUsername())) {
//				TBot.getBot().drawAnnouncement("Welcome, " + inputLine);
//				in.close();
//				return true;
//				
//			}
//		}
//				
//		System.out.println("Access denied!");
//		in.close();
//		return false;
		
		return true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	
	
}

class MousePathPoint extends Point
{
	private static final long serialVersionUID = 1L;
	private long finishTime;
	private double lastingTime;
	private int alpha = 255;

	public MousePathPoint(int x, int y, int lastingTime) {
		super(x, y);
		this.lastingTime = lastingTime;
		finishTime = System.currentTimeMillis() + lastingTime;
	}

	public int getAlpha()
	{
		int newAlpha = ((int) ((finishTime - System.currentTimeMillis()) / (lastingTime / alpha)));
		if (newAlpha > 255)
			newAlpha = 255;
		if (newAlpha < 0)
			newAlpha = 0;
		return newAlpha;
	}

	public boolean isUp()
	{
		return System.currentTimeMillis() >= finishTime;
	}

}