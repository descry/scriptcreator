package scriptCreator;

import org.tbot.methods.Bank;
import org.tbot.methods.Camera;
import org.tbot.methods.Game;
import org.tbot.methods.GameObjects;
import org.tbot.methods.GroundItems;
import org.tbot.methods.Mouse;
import org.tbot.methods.Npcs;
import org.tbot.methods.Players;
import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.methods.tabs.Inventory;
import org.tbot.methods.walking.Path;
import org.tbot.methods.walking.Walking;
import org.tbot.wrappers.Area;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.GroundItem;
import org.tbot.wrappers.Item;
import org.tbot.wrappers.NPC;
import org.tbot.wrappers.Tile;

public class GameEvent {
	private String method;
	private boolean walkLocally;
	private String name;
	private String interaction;
	private int x;
	private int y;
	private int z;
	private int i;
	private boolean leftClick;
	private boolean combatCheck;
	private String condition;
	private String conditionType;
	public int cond1;
	public int cond2;
	public int cond3;
	public int cond4;
	
	public String toString() {
		return method + ":" +  walkLocally + ":" +  name + ":" +  interaction + ":" +  x + ":" +  y + ":" + z + ":" +  
				i + ":" +  leftClick + ":" +  combatCheck + ":" +  condition + ":" +  conditionType + ":" +  cond1 + ":" +  
				cond2 + ":" +  cond3 + ":" +  cond4;
	}
	
	public GameEvent(String method, boolean walkLocally, String name, String interaction, int x, int y, int z, int i, boolean leftClick, boolean combatCheck,
			String condition, String conditionType, int cond1, int cond2, int cond3, int cond4) {
		
		this.method = method;
		this.walkLocally = walkLocally;
		this.name = name;
		this.interaction = interaction;
		this.x = x;
		this.y = y;
		this.z = z;
		this.i = i;
		this.leftClick = leftClick;
		this.combatCheck = combatCheck;
		this.condition = condition;
		this.conditionType = conditionType;
		this.cond1 = cond1;
		this.cond2 = cond2;
		this.cond3 = cond3;
		this.cond4 = cond4;
	}
	
	
	/////////////2 STRINGS 1 BOOL
	public GameEvent(String method, String name, String interaction, boolean combatCheck, String conditionType, String condition) { //NPC/
		this.method = method;
		this.name = name;
		this.interaction = interaction;
		this.combatCheck = combatCheck;
		this.condition = condition;
		this.conditionType = conditionType;
	}
	/////////////NOTHING
	public GameEvent(String method, String conditionType, String condition) {
		this.method = method;
		this.condition = condition;
		this.conditionType = conditionType;
	}
	
	/////////////2 STINGS
	public GameEvent(String method, String name, String interaction, String conditionType, String condition) { //NPC/
		this.method = method;
		this.name = name;
		this.interaction = interaction;
		this.condition = condition;
		this.conditionType = conditionType;
	}
	
	
	/////////////1 INT
	public GameEvent(String method, int i, String conditionType, String condition) { //1 INT
		this.method = method;
		this.condition = condition;
		this.i = i;
		this.conditionType = conditionType;
	}
	
	/////////////3 INTS 1 BOOL
	public GameEvent(String method, int x, int y, int z, boolean walkLocally, String conditionType, String condition) { //WALKING
		this.method = method;
		this.x = x;
		this.y = y;
		this.z = z;
		this.walkLocally = walkLocally;
		this.condition = condition;
		this.conditionType = conditionType;
	}
	
	/////////////2 INTS
	public GameEvent(String method, int x, int y, String conditionType, String condition) { //MOUSE
		this.method = method;
		this.x = x;
		this.y = y;
		this.condition = condition;
		this.conditionType = conditionType;
		
	}
	/////////////BOOLEAN
	public GameEvent(String method, boolean leftClick, String conditionType, String condition) { //MOUSE CLICK
		this.method = method;
		this.leftClick = leftClick;
		this.condition = condition;
		this.conditionType = conditionType;
		
	}
	
	/////////////1 INT 1 STRING
	public GameEvent(String method, String name, int i, String conditionType, String condition) { //BANK
		this.method = method;
		this.name = name;
		this.condition = condition;
		this.i = i;
		this.conditionType = conditionType;
		
	}
	
	/////////////2 INTS 1 STRING
	public GameEvent(String method, int x, int y, String interaction, String conditionType, String condition) { //WIDGET
		this.method = method;
		this.x = x;
		this.condition = condition;
		this.y = y;
		this.interaction = interaction;
		this.conditionType = conditionType;
		
	}
	
	/////////////3 INTS 1 STRING
	public GameEvent(String method, int x, int y, int z, String interaction, String conditionType, String condition) { //WIDGET
		this.method = method;
		this.condition = condition;
		this.x = x;
		this.y = y;
		this.z = z;
		this.interaction = interaction;
		this.conditionType = conditionType;
		
	}

	private boolean dO() {
		if(condition.equals("null")) {
			return true;
		}else{
				if(condition.equalsIgnoreCase("!animating")) {
					if(Players.getLocal().getAnimation() == -1) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("animating")) {
					if(Players.getLocal().getAnimation() != -1) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("!moving")) {
					if(!Players.getLocal().isMoving()) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("moving")) {
					if(Players.getLocal().isMoving()) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("inventoryFull")) {
					if(Inventory.isFull()) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("!inventoryFull")) {
					if(!Inventory.isFull()) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("inventoryEmpty")) {
					if(Inventory.getEmptySlots() == 28) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("inventoryContains")) {
					if(Inventory.contains(cond1)) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("!inventoryContains")) {
					if(!Inventory.contains(cond1)) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("areaContains")) {
					Area a = new Area(cond1,cond2,cond3,cond4);
					if(a.contains(Players.getLocal().getLocation())) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("!areaContains")) {
					Area a = new Area(cond1,cond2,cond3,cond4);
					if(!a.contains(Players.getLocal().getLocation())) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("tileContains")) {
					Tile t = new Tile(cond1, cond2);
					if(t.equals(Players.getLocal().getLocation())) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("!tileContains")) {
					Tile t = new Tile(cond1, cond2);
					if(!t.equals(Players.getLocal().getLocation())) {
						return true;
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("groundItemOnScreen")) {
					GroundItem g = GroundItems.getNearest(cond1);
					if(g != null) {
						if(g.isOnScreen()) {
							return true;
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else if(condition.equalsIgnoreCase("!groundItemOnScreen")) {
					GroundItem g = GroundItems.getNearest(cond1);
					if(g != null) {
						if(g.isOnScreen()) {
							return false;
						}else{
							return true;
						}
					}else{
						return true;
					}
				}else{
					return true;
				}	
				
		}
	}
	
	private void walk() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					if (walkLocally) {
						Tile t = new Tile(x, y, z);
						while(!t.getLocation().equals(Players.getLocal().getLocation())) {
							Path p = Walking.findLocalPath(t);
							if (p != null) {
								p.traverse();
							}
						}
					} else {
						Tile t = new Tile(x, y, z);
						while(!t.getLocation().equals(Players.getLocal().getLocation())) {
							Path p = Walking.findPath(t);
							if (p != null) {
								p.traverse();
							}
						}
					}
				}
			

			}else if(conditionType.equals("If")) {
				if(dO()) {
					if (walkLocally) {
						Tile t = new Tile(x, y, z);
						while(!t.getLocation().equals(Players.getLocal().getLocation())) {
							Path p = Walking.findLocalPath(t);
							if (p != null) {
								p.traverse();
							}
						}
					} else {
						Tile t = new Tile(x, y, z);
						while(!t.getLocation().equals(Players.getLocal().getLocation())) {
							Path p = Walking.findPath(t);
							if (p != null) {
								p.traverse();
							}
						}
					}
				}
			}
		}else{
			if (walkLocally) {
				Tile t = new Tile(x, y, z);
				while(!t.getLocation().equals(Players.getLocal().getLocation())) {
					Path p = Walking.findLocalPath(t);
					if (p != null) {
						p.traverse();
					}
				}
			} else {
				Tile t = new Tile(x, y, z);
				while(!t.getLocation().equals(Players.getLocal().getLocation())) {
					Path p = Walking.findPath(t);
					if (p != null) {
						p.traverse();
					}
				}
			}
		}
		
	}
	
	public void execute() {
		if (method.equalsIgnoreCase("Walking")) {
			walk();//
		} else if (method.equalsIgnoreCase("Sleep")) {
			sleep();
		} else if (method.equalsIgnoreCase("CheckRun")) {
			checkRun();//
		} else if (method.equalsIgnoreCase("NPC")) {
			NPCc();//
		} else if (method.equalsIgnoreCase("GameObject")) {
			GameObjectt();//
		} else if (method.equalsIgnoreCase("GroundItem")) {
			GroundItemm();//
		} else if (method.equalsIgnoreCase("Move mouse")) {
			mouseMove();//
		} else if (method.equalsIgnoreCase("Click mouse")) {
			mouseClickVoid();//
		} else if (method.equalsIgnoreCase("Move camera randomly")) {
			moveCameraRandomly();//
		} else if (method.equalsIgnoreCase("Change camera pitch")) {
			setPitch();//
		} else if (method.equalsIgnoreCase("Change camera angle")) {
			setAngle();//
		} else if (method.equalsIgnoreCase("Open bank")) {
			bankOpen();//
			Time.sleep(200);
		} else if (method.equalsIgnoreCase("Close bank")) {
			bankClose();//
		} else if (method.equalsIgnoreCase("Deposit")) {
			deposit();//
		} else if (method.equalsIgnoreCase("Deposit all")) {
			depositAll();//
		} else if (method.equalsIgnoreCase("Withdraw")) {
			withdraw();//
		} else if (method.equalsIgnoreCase("Widget")) {
			doWidget();//
		} else if (method.equalsIgnoreCase("Child widget")) {
			doWidgetChild();//
		} else if (method.equalsIgnoreCase("WorldHop (P2P)")) {
			worldHopP2P();//
		} else if (method.equalsIgnoreCase("WorldHop (F2P)")) {
			worldHopF2P();//
		} else if (method.equalsIgnoreCase("WorldHop")) {
			worldHop();//
		} else if (method.equalsIgnoreCase("Logout")) {
			logout();//
		} else if (method.equalsIgnoreCase("Equip")) {
			equip();//
		} else if (method.equalsIgnoreCase("Interact")) {
			interact();//
		} else if (method.equalsIgnoreCase("Drop")) {
			drop();//
		} else if (method.equalsIgnoreCase("Drop all")) {
			dropAll();//
		} else if (method.equalsIgnoreCase("Interact item")) {
			interactItem();//
		}
		
	}
	
	private void interactItem() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				if(dO()) {
					while(dO()) {
						Item a = Inventory.getFirst(x);
						Item b = Inventory.getFirst(y);
						a.interact("Use");
						Time.sleep(250);
						b.interact("Use " + a.getName() + " -> " + b.getName());
					}
				}
			}else if(conditionType.equals("If")) {
				Item a = Inventory.getFirst(x);
				Item b = Inventory.getFirst(y);
				a.interact("Use");
				Time.sleep(250);
				b.interact("Use " + a.getName() + " -> " + b.getName());
			}
		}else{
			Item a = Inventory.getFirst(x);
			Item b = Inventory.getFirst(y);
			a.interact("Use");
			Time.sleep(250);
			b.interact("Use " + a.getName() + " -> " + b.getName());
		}
		
	}
	
	private void dropAll() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				if(dO()) {
					while(dO()) {
						for(Item item : Inventory.getItems()) {
							item.interact("Drop");
						}
					}
				}
			}else if(conditionType.equals("If")) {
				for(Item item : Inventory.getItems()) {
					item.interact("Drop");
				}
			}
		}else{
			for(Item item : Inventory.getItems()) {
				item.interact("Drop");
			}
		}
		
	}
	
	private void drop() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				if(dO()) {
					while(dO()) {
						Inventory.drop(i);
					}
				}
			}else if(conditionType.equals("If")) {
				Inventory.drop(i);
			}
		}else{
			Inventory.drop(i);
		}
		
	}
	
	private void interact() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				if(dO()) {
					while(dO()) {
						Inventory.getFirst(i).interact(name);
					}
				}
			}else if(conditionType.equals("If")) {
				Inventory.getFirst(i).interact(name);
			}
		}else{
			Inventory.getFirst(i).interact(name);
		}
		
	}
	
	private void equip() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				if(dO()) {
					while(dO()) {
						Inventory.getFirst(i).click();
					}
				}
			}else if(conditionType.equals("If")) {
				Inventory.getFirst(i).click();
			}
		}else{
			Inventory.getFirst(i).click();
		}
		
	}

	private void checkRun() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				if(dO()) {
					while(dO()) {
						if (Walking.getRunEnergy() >= i && !Walking.isRunEnabled()) {
							Walking.setRun(true);
						}
					}
				}
			}else if(conditionType.equals("If")) {
				if (Walking.getRunEnergy() >= i && !Walking.isRunEnabled()) {
					Walking.setRun(true);
				}
			}
		}else{
			if (Walking.getRunEnergy() >= i && !Walking.isRunEnabled()) {
				Walking.setRun(true);
			}
		}
		
	}
	
	private void logout() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Game.logout();
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Game.logout();
			}
		}else{
			Game.logout();
		}
		
	}
	
	private void sleep() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Time.sleep(i);
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Time.sleep(i);
			}
		}else{
			Time.sleep(i);
		}
		
	}
	
	private void GroundItemm() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					GroundItem item = GroundItems.getNearest(name);
					if (item != null) {
						item.interact(interaction);
					}	
				}
			

			}else if(conditionType.equals("If")) {
				if(dO()) {
					GroundItem item = GroundItems.getNearest(name);
					if (item != null) {
						item.interact(interaction);
					}
				}
			}
		}else{
			GroundItem item = GroundItems.getNearest(name);
			if (item != null) {
				item.interact(interaction);
			}
		}
		
	}
	
	private void GameObjectt() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					GameObject object = GameObjects.getNearest(name);
					if (object != null) {
						object.interact(interaction);
					}
				}
			

			}else if(conditionType.equals("If")) {
				if(dO()) {
					GameObject object = GameObjects.getNearest(name);
					if (object != null) {
						object.interact(interaction);
					}
				}
			}
		}else{
			GameObject object = GameObjects.getNearest(name);
			if (object != null) {
				object.interact(interaction);
			}
		}
		
	}
	
	private void NPCc() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					NPC npc = Npcs.getNearest(name);
					if (npc != null && !npc.isInteractingWithLocalPlayer()) {
						if (!checkCombat()) {
							npc.interact(interaction);
						} else {
							if (!npc.isHealthBarVisible()) {
								npc.interact(interaction);
							}
						}

					}
				}
			

			}else if(conditionType.equals("If")) {
				if(dO()) {
					NPC npc = Npcs.getNearest(name);
					if (npc != null && !npc.isInteractingWithLocalPlayer()) {
						if (!checkCombat()) {
							npc.interact(interaction);
						} else {
							if (!npc.isHealthBarVisible()) {
								npc.interact(interaction);
							}
						}
					}
				}
				
			}
		}else{
			NPC npc = Npcs.getNearest(name);
			if (npc != null && !npc.isInteractingWithLocalPlayer()) {
				if (!checkCombat()) {
					npc.interact(interaction);
				} else {
					if (!npc.isHealthBarVisible()) {
						npc.interact(interaction);
					}
				}
			}
		}
		
	}
	
	private void setAngle() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Camera.setAngle(i);
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Camera.setAngle(i);
			}
		}else{
			Camera.setAngle(i);
		}
		
	}
	
	private void setPitch() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Camera.setPitch(i);
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Camera.setPitch(i);
			}
		}else{
			Camera.setPitch(i);
		}
		
	}
	
	private void worldHopP2P() {
		
		if(Game.isLoggedIn())
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Game.instaHopRandomP2P();
					Time.sleep(1000);
				}
			}else if(conditionType.equals("If")) {
				if(dO())
				Game.instaHopRandomP2P();
				Time.sleep(1000);
			}
		}else{
			Game.instaHopRandomP2P();
			Time.sleep(1000);
		}
		
	}
	
	private void worldHopF2P() {
		
		if(Game.isLoggedIn())
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Game.instaHopRandomF2P();
					Time.sleep(1000);
				}
			}else if(conditionType.equals("If")) {
				if(dO())
				Game.instaHopRandomF2P();
				Time.sleep(1000);
			}
		}else{
			Game.instaHopRandomF2P();
			Time.sleep(1000);
		}
		
	}
	
	private void worldHop() {
		
		if(Game.isLoggedIn())
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Game.instaHop(i);
					Time.sleep(1000);
				}
			}else if(conditionType.equals("If")) {
				if(dO())
					Game.instaHop(i);
				Time.sleep(1000);
			}
		}else{
			Game.instaHop(i);
			Time.sleep(1000);
		}
		
	}
	
	private void mouseClickVoid() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					if (leftClick) {
						Mouse.click(true);
					} else {
						Mouse.click(false);
					}
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				if (leftClick) {
					Mouse.click(true);
				} else {
					Mouse.click(false);
				}
			}
		}else{
			if (leftClick) {
				Mouse.click(true);
			} else {
				Mouse.click(false);
			}
		}
		
	}
	
	private void mouseMove() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Mouse.move(x, y);
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Mouse.move(x, y);
			}
		}else{
			Mouse.move(x, y);
		}
		
	}
	
	private void doWidgetChild() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					if (Widgets.getWidget(x, y).getChild(z).isVisible()) {
						Widgets.getWidget(x, y).getChild(z).interact(interaction);
					}
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				if (Widgets.getWidget(x, y).getChild(z).isVisible()) {
					Widgets.getWidget(x, y).getChild(z).interact(interaction);
				}
			}
		}else{
			if (Widgets.getWidget(x, y).getChild(z).isVisible()) {
				Widgets.getWidget(x, y).getChild(z).interact(interaction);
			}
		}
		
	}
	
	private void doWidget() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					if (Widgets.getWidget(x, y).isVisible()) {
						Widgets.getWidget(x, y).interact(interaction);
					}
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				if (Widgets.getWidget(x, y).isVisible()) {
					Widgets.getWidget(x, y).interact(interaction);
				}
			}
		}else{
			if (Widgets.getWidget(x, y).isVisible()) {
				Widgets.getWidget(x, y).interact(interaction);
			}
		}
		
	}
	
	private void bankOpen() {
		if(!conditionType.equals("null")) {
			if(conditionType.equalsIgnoreCase("While")) {
				while(dO()) {
					Bank.open();
				}
			

			}else if(conditionType.equalsIgnoreCase("If")) {
				if(dO())
				Bank.open();
			}
		}else{
			Bank.open();
		}
		
	}
	
	private void bankClose() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Bank.close();
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Bank.close();
			}
		}else{
			Bank.close();
		}
		
	}
	
	
	private void deposit() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					int j = Integer.parseInt(name);
					Bank.deposit(j, i);
				}
			

			}else if(conditionType.equals("If")) {
				if(dO()) {
					int j = Integer.parseInt(name);
					Bank.deposit(j, i);
				}
				
			}
		}else{
			int j = Integer.parseInt(name);
			Bank.deposit(j, i);
		}
		
	}
	
	private void withdraw() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					int j = Integer.parseInt(name);
					Bank.withdraw(j, i);
				}
			

			}else if(conditionType.equals("If")) {
				if(dO()) {
					int j = Integer.parseInt(name);
					Bank.withdraw(j, i);
				}
			}
		}else{
			int j = Integer.parseInt(name);
			Bank.withdraw(j, i);
		}
		
	}
	
	private void moveCameraRandomly() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Camera.rotateRandomly();
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Camera.rotateRandomly();
			}
		}else{
			Camera.rotateRandomly();
		}
		
	}
	
	private void depositAll() {
		if(!conditionType.equals("null")) {
			if(conditionType.equals("While")) {
				while(dO()) {
					Bank.depositAll();
				}
			

			}else if(conditionType.equals("If")) {
				if(dO())
				Bank.depositAll();
			}
		}else{
			Bank.depositAll();
		}
		
	}

	public String getConditionType() {
		return conditionType;
	}
	
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	
	public String mouseClick() {
		if(leftClick) {
			return "left";
		}else{
			return "right";
		}
	}
	
	public boolean checkCombat() {
		return combatCheck;
	}
	
	public int getSleep() {
		return i;
	}

	public String getMethod() {
		return method;
	}

	public boolean isWalkLocally() {
		return walkLocally;
	}

	public String getName() {
		return name;
	}

	public String getInteraction() {
		return interaction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	
	
}
