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
		System.out.println("  4.使用道具卡");
		System.out.println("  5.前方示警");
		System.out.println("  6.查看土地详细信息");
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
		System.out.println("玩家"+Space(8)+"点券"+Space(6)+"现金"+Space(6)+"存款"+Space(6)+"地产"+Space(6)+"总额");
		for (int i=0;i<MainController.userTotal;++i)
		{
			User nowUser=MainController.userList[i];
			String buffer="";
			int total=0,landTotal=0;
			buffer+=nowUser.getName()+Space(12-nowUser.getName().length());
			buffer+=nowUser.getCoupons()+Space(10-(""+nowUser.getCoupons()).length());
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
	//the warning function: to show barrier forward
	public static void showWaring(int userId)
	{
		int nowPos=MainController.map.userPosition[userId];
		boolean foundBarrier=false;
		for (int i=1;i<=10;++i)
		{
			nowPos+=MainController.map.userDirection[userId];
			if (nowPos>=MainController.map.unitTotal)
			{
				nowPos-=MainController.map.unitTotal;
			}
			if (nowPos<0)
			{
				nowPos+=MainController.map.unitTotal;
			}
			if (MainController.map.getBarrier(nowPos)>0)
			{
				System.out.println("前方"+i+"步有"+
					MainController.map.getBarrier(nowPos)+
					"个路障！");
				foundBarrier=true;
			}
		}
		if (!foundBarrier)
		{
			System.out.println("前方10步范围内没有路障！");
		}
	}
	// show a land
	public static void showLand(int userId)
	{
		Scanner cin=new Scanner(System.in);
		System.out.print("请输入您要查询的位置与您的相对距离（反向用负数，x放弃）:");
		String command=cin.next();
		if (command.equals("x")) return ;
		int quesPos=Integer.parseInt(command);
		
		//get correct position
		quesPos*=MainController.map.userDirection[userId];
		quesPos+=MainController.map.userPosition[userId];
		quesPos%=MainController.map.unitTotal;
		if (quesPos<0) quesPos+=MainController.map.unitTotal;
		
		switch (MainController.map.unitList[quesPos].typeId)
		{
			case 1: System.out.println("类型：空土地");break;
			case 3: System.out.println("类型：银行");break;
			case 4: System.out.println("类型：新闻点");break;
			case 5: System.out.println("类型：彩票站");break;
			case 6: System.out.println("类型：赠卡点");break;
			case 7: System.out.println("类型：赠券点");break;
			case 8: System.out.println("类型：道具店");break;
			case 2: 
				System.out.println("类型：地产");
				NormalLand tmp=(NormalLand) MainController.map.unitList[quesPos];
				System.out.println("名称："+tmp.name);
				System.out.println("等级："+tmp.level);
				System.out.println("当前购买价："+tmp.getBuyingPrice());
				System.out.println("街道号："+tmp.street);
				if (tmp.owner==-1) System.out.println("所有者：<可供购买>");
				else 
				{
					System.out.println("所有者："+MainController.userList[tmp.owner].getName());
					System.out.println("当前过路费："+tmp.getArrivedPrice());
				}
				break;
		}
	}
};
