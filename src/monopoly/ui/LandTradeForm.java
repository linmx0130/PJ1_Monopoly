/** LandTradeForm
 *  the ui for the dirtiest land trade :)
 */
package monopoly.ui;
import monopoly.kernel.*;
import monopoly.kernel.land.NormalLand;
import java.util.Scanner;
public class LandTradeForm
{
	public static final int LEVEL_LIMIT=6;
	/** buyLandDialog
	 *  Dealing with the request of buying land
	 *  true -> bought it
	 *  false -> didn't buy it
	 */
	public static boolean buyLandDialog(int userId, int landId)
	{
		if (MainController.map.unitList[landId].typeId!=2)
		{
			LogManager.log(LogManager.ERROR,"UI::LandTradeForm",
						"use buyLandDialog on wrong land!");
			System.exit(1);
		}
		NormalLand nowLand=(NormalLand)MainController.map.unitList[landId];
		if (nowLand.owner!=-1)
		{
			LogManager.log(LogManager.ERROR,"UI::LandTradeForm",
						"use buyLandDialog on wrong land!");
			System.exit(1);
		}
		Scanner cin= new Scanner(System.in);
		System.out.println("您到了还没有被人购买的"+nowLand.name+
						"！这里风景秀丽，游客众多，投资它一定能赚大钱，只要"
						+nowLand.getBuyingPrice()+"元即可购买!");
		System.out.print("您是否需要购买？(Y->买，否则不买)");
		String userCommand=cin.next();
		if (userCommand.equals("Y") || userCommand.equals("y"))
		{
			if (MainController.userList[userId].getCash()<nowLand.getBuyingPrice())
			{
				System.out.println("您财力不足，还是好好回去搬砖赚钱比较好，不要来这里投机～");
				return false;
			}
			MainController.userList[userId].modifyCash(-nowLand.getBuyingPrice());
			nowLand.owner=userId;
			System.out.println("恭喜！您已经买下了"+nowLand.name+"！");
			return true;
		}
		return false;
	}
	/** payArrivedCost
	 *  the method provided a way to pay arrived cost
	 */
	public static void payArrivedCost(int userId,int landId)
	{
		if (MainController.map.unitList[landId].typeId!=2)
		{
			LogManager.log(LogManager.ERROR,"UI::LandTradeForm",
						"use buyLandDialog on wrong land!");
			System.exit(1);
		}
		NormalLand nowLand=(NormalLand)MainController.map.unitList[landId];
		if (nowLand.owner==-1)
		{
			LogManager.log(LogManager.ERROR,"UI::LandTradeForm",
						"use buyLandDialog on wrong land!");
			System.exit(1);
		}
		User nowUser=MainController.userList[userId];
		while (nowUser.getCash()<nowLand.getArrivedPrice())
		{
			//use the savings
			if (BankSystem.savings[userId]>0)
			{
				if (BankSystem.savings[userId]+nowUser.getCash()
					<nowLand.getArrivedPrice())
				{
					BankSystem.withdraw(userId,BankSystem.savings[userId]);
				}
				else
				{
					BankSystem.withdraw(userId,nowLand.getArrivedPrice()-nowUser.getCash());
				}
				continue;
			}
			if (!mortgageLand(userId,true))
			{
				break;
			}
		}
		if (nowUser.getCash()<nowLand.getArrivedPrice())
		{
			MainController.endGame(userId);
		}
		nowUser.modifyCash(-nowLand.getArrivedPrice());
		MainController.userList[nowLand.owner].modifyCash(nowLand.getArrivedPrice());
		MessageManager.showMessage(MessageManager.MESSAGE,"UI::LandTradeForm",
			nowUser.getName()+"到达"+nowLand.name+"，向地主"+MainController.userList[nowLand.owner].getName()+
			"支付参观费"+nowLand.getArrivedPrice()+"元");
	}
	/** mortgageLand
	 *  dying -> true means the user is try to deal with his debt
	 *  return true means finishing a mortgage action
	 *  return false means give up.
	 */
	public static boolean mortgageLand(int userId, boolean dying)
	{
		Scanner cin=new Scanner(System.in);
		System.out.println("请选择您想要出售的土地：");
		for (int i=0;i<MainController.map.unitTotal;++i)
		{
			if (MainController.map.unitList[i].typeId==2)
			{
				if (((NormalLand)MainController.map.unitList[i]).owner==userId)
				{
					System.out.println("  "+i+"."
						+((NormalLand)MainController.map.unitList[i]).name+"  -> "
						+(int)((((NormalLand)MainController.map.unitList[i]).getBuyingPrice()*0.5))
						+"元");
				}
			}
		}
		if (dying)
		{
			System.out.println("  -1.放弃治疗，认输！");
		}else
		{
			System.out.println("  -1.放弃本次卖地融资");
		}
		int choose;
		do 
		{		
			System.out.print("请输入您选择出售土地的编号（数字）:");
			choose=cin.nextInt();
			if (choose==-1) return false;
			if (((NormalLand)MainController.map.unitList[choose]).owner!=userId)
			{
				System.out.println("非法选择！请重新选择！");
				continue;
			}
			MainController.userList[userId].modifyCash(
				(int)(((NormalLand)MainController.map.unitList[choose]).getBuyingPrice()*0.5));
			((NormalLand)MainController.map.unitList[choose]).owner=-1;
			return true;
		}while (true);
	}
	/** levelUpLand
	 *  level up a land
	 */
	public static void levelUpLand(int userId,int landId)
	{
		if (MainController.map.unitList[landId].typeId!=2)
		{
			MessageManager.showMessage(MessageManager.ERROR,"UI::LandTradeForm",
							"Illegal request on land level up!");
			System.exit(1);
		}
		if (((NormalLand)MainController.map.unitList[landId]).owner!=userId)
		{
			MessageManager.showMessage(MessageManager.ERROR,"UI::LandTradeForm",
							"Illegal request on land level up!");
			System.exit(1);
		}
		NormalLand nowLand=(NormalLand)MainController.map.unitList[landId];
		if (nowLand.level==LEVEL_LIMIT)
		{
			System.out.println("这块土地已经到达最高级，无法继续升级！");
			return ;
		}
		Scanner cin=new Scanner(System.in);
		System.out.print("这块土地属于您！\n"+
						"升级土地"+nowLand.name+"需要支付"+nowLand.basicPrice/2+
						"元，是否升级？(Y升级，其他放弃)：");
		String command=cin.next();
		if (command.equals("Y") || command.equals("y")) 
		{
			int needMoney=nowLand.basicPrice/2;
			if (MainController.userList[userId].getCash()<needMoney)
			{
				System.out.println("您现金不足，无法升级土地！");
				return;
			}
			MainController.userList[userId].modifyCash(needMoney);
			nowLand.level++;
			System.out.println("升级成功！土地"+nowLand.name+"现在是"+nowLand.level+"级。");
		}
		else
		{
			return ;
		}
	}
}
