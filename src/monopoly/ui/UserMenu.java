/** UserMenu
 *  The class provided menu for users.
 */
package monopoly.ui;
import monopoly.kernel.*;
import monopoly.kernel.land.*;
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
		System.out.println("  3.查看所有玩家的资产");
		System.out.println("  4.使用效果卡");
		System.out.println("  0.心满意足，扔骰子前进！");
		System.out.print("请输入序号：");
		return cin.next();
	};
	/** Space
	 *  return n spaces
	 */
	private static String Space (int n)
	{
		String ret="";
		for (int i=0;i<n;++i) ret+=" ";
		return ret;
	}
	/** showAllProperty
	 *  show the list of all users' properties
	 */
	public static void showAllProperty()
	{
		System.out.println("===所有玩家资产信息===");
		System.out.println("玩家"+Space(8)+"现金"+Space(6)+"存款"+Space(6)+"地产"+Space(6)+"总额");
		for (int i=0;i<MainController.userTotal;++i)
		{
			User nowUser=MainController.userList[i];
			String buffer="";
			int total=0,landTotal=0;
			buffer+=nowUser.getName()+Space(12-nowUser.getName().length());
			buffer+=nowUser.getCash()+Space(10-(""+nowUser.getCash()).length());
			total+=nowUser.getCash();
			buffer+=BankSystem.savings[i]+Space(10-(""+BankSystem.savings[i]).length());
			total+=BankSystem.savings[i];
			for (int j=0;j<MainController.map.unitTotal;++j)
			{
				if (MainController.map.unitList[j].typeId==2)
				{
					NormalLand nowLand=(NormalLand)MainController.map.unitList[j];
					if (nowLand.owner==i)
					{
						landTotal+=nowLand.getBuyingPrice();
					}
				}
			}
			buffer+=landTotal+Space(10-(""+landTotal).length());
			total+=landTotal;
			buffer+=total;
			System.out.println(buffer);
		}
	}
};
