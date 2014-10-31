/** MainController
 *  the main controller of monopoly kernel
 */
package monopoly.kernel;
import monopoly.ui.*;
public class MainController
{
	public static MapManager map=new MapManager();
	public static User[] userList;
	public static int userTotal;
	
	public static void buildUsers(int userTotal)
	{
		MainController.userTotal=userTotal;
		userList=new User[userTotal];
		BankSystem.savings=new int[userTotal];
		map.userPosition=new int[userTotal];
	}
	public static void mainLoop() throws Exception
	{
		InitGame.showInitGameDialog();
		while (true) 
		{
			TimeManager.newDay();
			for (int i=0;i<userTotal;++i)
			{
				userList[i].control();
			}
		}
	};
};

