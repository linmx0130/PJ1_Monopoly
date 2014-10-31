/** LandTradeForm
 *  the ui for the dirtiest land trade :)
 */
package monopoly.ui;
import monopoly.kernel.*;
import monopoly.kernel.land.NormalLand;
import java.util.Scanner;
public class LandTradeForm
{
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
		if (userCommand.equals("Y"))
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
	/** payArrivedCose
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
		{//TODO
		}
		nowUser.modifyCash(-nowLand.getArrivedPrice());
		MessageManager.showMessage(MessageManager.MESSAGE,"UI::LandTradeForm",
			nowUser.getName()+"到达"+nowLand.name+"，向地主"+MainController.userList[nowLand.owner].getName()+
			"支付参观费"+nowLand.getArrivedPrice()+"元");
	}
}
