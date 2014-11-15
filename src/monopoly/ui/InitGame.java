/** InitGame
 *  Provided UI for initialize the game config.
 */
package monopoly.ui;
import monopoly.kernel.*;
import java.util.Scanner;
public class InitGame
{
	public static void showInitGameDialog(String mapFileName) throws Exception
	{
		Scanner cin=new Scanner(System.in);
		System.out.println("			$$$ MonoPoly $$$");
		System.out.println("\t欢迎来到大富翁游戏，在游戏开始之前，您需要做一些设定。");

		//Input users
		MainController.buildUsers(UserMenu.SafeInputInteger(cin,"游戏人数："));
		MainController.map.loadMap(mapFileName);
		for (int i=0;i<MainController.userTotal;++i)
		{
			System.out.print("请输入玩家"+i+"的名字：");
			MainController.userList[i]=new User(cin.nextLine());
		}
		
		//Input cash at first 
		int cashAtFirst=UserMenu.SafeInputInteger(cin,"请输入每个玩家的起始现金金额：");
		for (int i=0;i<MainController.userTotal;++i)
		{
			MainController.userList[i].modifyCash(cashAtFirst);
		}
		System.out.println("配置结束，游戏即将开始！");
		CardSystem.Init();
	}
}
