/** MainController
 *  the main controller of monopoly kernel
 */
package monopoly.kernel;
import monopoly.ui.*;
public class MainController
{
	public static MapManager map;
	public static User[] userList;
	public static int userTotal;
	public static void buildUsers(int userTotal)
	{
		MainController.userTotal=userTotal;
		userList=new User[userTotal];
	}
	public static void mainLoop()
	{
		monopoly.ui.InitGame.showInitGameDialog();
	};
};

