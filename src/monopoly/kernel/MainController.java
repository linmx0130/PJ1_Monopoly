/** MainController
 *  the main controller of monopoly kernel
 */
package monopoly.kernel;
import monopoly.ui.*;
import monopoly.kernel.land.NormalLand;
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
	}
	public static void mainLoop() throws Exception
	{
		InitGame.showInitGameDialog();
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

