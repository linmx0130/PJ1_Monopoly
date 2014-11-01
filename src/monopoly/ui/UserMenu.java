/** UserMenu
 *  The class provided menu for users.
 */
package monopoly.ui;
import monopoly.kernel.*;
import java.util.Scanner;

public class UserMenu
{
	/** showMenu
	 *  show the menu for user and return user's input
	 */
	public static String showMenu(int userId)
	{
		Scanner cin=new Scanner(System.in);
		User nowUser=MainController.userList[userId];
		int[] date=TimeManager.getNowDate();
		System.out.println("\n========================分割线========================");
		System.out.println(nowUser.getName()+"，您好！现在是"+date[0]+"年"+date[1]+"月"+date[2]+"日。您想要做什么?");
		System.out.println("  1.查看原始地图");
		System.out.println("  2.查看当前地图");
		System.out.println("  0.心满意足，扔骰子前进！");
		System.out.print("请输入序号：");
		return cin.next();
	};
	/** showAllProperty
	 *  show the list of all users' properties
	 */
	public static void showAllProperty()
	{
		//TODO
	}
};
