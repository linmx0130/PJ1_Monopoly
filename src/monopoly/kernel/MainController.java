/** MainController
 *  the main controller of monopoly kernel
 */
package monopoly.kernel;
import monopoly.ui.*;
import monopoly.kernel.land.NormalLand;
import monopoly.ui.MessageManager;
import monopoly.ui.UserMenu;
public class MainController
{
	public static MapManager map=new MapManager();
	public static User[] userList;
	public static int userTotal;
	public static boolean[] inGame;

	/** buildUsers
	 *  build users for init game.
	 */
	public static void buildUsers(int userTotal)
	{
		MainController.userTotal=userTotal;
		userList=new User[userTotal];
		BankSystem.savings=new int[userTotal];

		map.initMapUserPart(userTotal);	

		inGame=new boolean[userTotal];
		for (int i=0;i<userTotal;++i) inGame[i]=true;;
		CardSystem.cardProperty=new int[userTotal][CardSystem.CARD_TOTAL];
	}
	/** endGame
	 *  a user give up game
	 */
	public static void endGame(int userId)
	{
		inGame[userId]=false;
		//release all land
		for (int i=0;i<map.unitTotal;++i)
		{
			if (map.unitList[i].typeId==2)
			{
				if (((NormalLand)map.unitList[i]).owner==userId)
				{
					((NormalLand)map.unitList[i]).owner=-1;
				}
			}
		}
		int leftUser=0;
		for (int i=0;i<userTotal;++i)
		{
			if (inGame[i]) leftUser++;
		}
		if (leftUser==1)
		{	
			for (int i=0;i<userTotal;++i)
			{
				if (inGame[i]) winGame(i);
			}
		}
	}
	/* winGame
	 * a user won the game
	 */
	public static void winGame(int userId)
	{
		MessageManager.showMessage(MessageManager.MESSAGE,"MainController","恭喜玩家"+userList[userId].getName()+"赢得了游戏！");	
		UserMenu.haveAPause();
		System.exit(0);
	}
	public static void mainLoop(String mapFileName) throws Exception
	{
		InitGame.showInitGameDialog(mapFileName);
		while (true) 
		{
			TimeManager.newDay();
			for (int i=0;i<userTotal;++i)
			{
				if (inGame[i]) userList[i].control();
			}
		}
	};
};

