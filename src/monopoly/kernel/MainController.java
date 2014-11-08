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
	public static boolean[] inGame;

	/** buildUsers
	 *  build users for init game.
	 */
	public static void buildUsers(int userTotal)
	{
		MainController.userTotal=userTotal;
		userList=new User[userTotal];
		BankSystem.savings=new int[userTotal];

		map.userPosition=new int[userTotal];
		map.userDirection=new int[userTotal];
		for (int i=0;i<userTotal;++i)
		{
			map.userDirection[i]=1;
		}
		
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

