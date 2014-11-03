/** User class
 * Record the data and manage the actions of user.
 */

package monopoly.kernel;
import monopoly.ui.*;
public class User
{
	public static int userCount=0;
	private String name;
	private int id;
	private int cash;
	// used to support stay a night card
	public boolean stayANight;
	// the effect of turtleCard
	public boolean beTurtle;
	public User(String name)
	{
		this.name=name;
		this.id=userCount++;
		this.cash=0;
		stayANight=false;
		beTurtle=false;
	}
	public String getName()
	{
		return this.name;
	}
	public int getId()
	{
		return this.id;
	}
	public int getCash()
	{
		return this.cash;
	}
	public void modifyCash(int d)
	{
		this.cash+=d;
	}

	/**
	 * control() : call it when it's this user's turn
	 */
	public void control()
	{
		String userInput;
MENULOOP:
		do
		{
			userInput=UserMenu.showMenu(id);
			switch (userInput)
			{
				case "1":
					MapViewer.showBasicMap();
					break;
				case "2":
					MapViewer.showNowMap(id);
					break;
				case "3":
					UserMenu.showAllProperty();
					break;
				case "4":
					CardForm.useCardForm(id);
					break;
				case "0":
					int step=(int)(Math.random()*6+1);
					MainController.map.userWalk(id,step);
					break ;
			}
			//used to support stay a night 
			if (stayANight)
			{
				stayANight=false;
				MessageManager.showMessage(MessageManager.MESSAGE,"User",
								name+"滞留原地，并重新发动当地效果！");
				MainController.map.userWalk(id,0);
				break;
			}
		} while (!userInput.equals("0"));
	}
}
