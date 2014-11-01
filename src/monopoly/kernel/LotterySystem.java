/** LotterySystem 
 *  deal with everything about lottery
 */
package monopoly.kernel;
import monopoly.ui.MessageManager;
public class LotterySystem
{
	public static final int oneTimeBetPrice=100;
	public static final int oneTimeBetBonus=233;
	public static final int normalBetPrice=100;
	public static int[] normalBetPool={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	public static int normalBetBonus=233;

	/** oneTimeBet
	 *  the user can get money right now!
	 *  true -> get money
	 */
	public static boolean oneTimeBet(int userId)
	{
		if (MainController.userList[userId].getCash()<oneTimeBetPrice) return false;
		MainController.userList[userId].modifyCash(-oneTimeBetPrice);
		if (Math.random()<0.5)
		{
			BankSystem.savings[userId]+=oneTimeBetBonus;
			return true;
		}
		return false;
	}
	/** normalBet
	 *  the user can get money at the end of the month!
	 *  true -> pay success 
	 */
	public static boolean normalBet(int userId,int choose)
	{
		if (MainController.userList[userId].getCash()<normalBetPrice) return false;
		if (normalBetPool[choose]!=-1) return false;
		MainController.userList[userId].modifyCash(-normalBetPrice);
		normalBetPool[choose]=userId;
		return true;
	}
	public static void publishNormalBet()
	{
		int choose=(int)(Math.random()*normalBetPool.length);
		if (normalBetPool[choose]==-1)
		{
			MessageManager.showMessage(MessageManager.MESSAGE,"LotterySystem",
				"  ===月底彩票开奖===\n"+
				TimeManager.getNowDate()[1]+"月开奖情况：本次号码为"+choose+"。\n无人中奖，奖池保留，累积至"
				+normalBetBonus+"元!");
			return;
		}
		MessageManager.showMessage(MessageManager.MESSAGE,"LotterySystem",
			"  ===月底彩票开奖===\n"+
			TimeManager.getNowDate()[1]+"月开奖情况：本次号码为"+choose+"。\n大奖由"
			+MainController.userList[normalBetPool[choose]].getName()+
			"获得，奖金为"+normalBetBonus+"元!恭喜！");
		BankSystem.savings[normalBetPool[choose]]+=normalBetBonus;
		for (int i=0;i<normalBetPool.length;++i)
		{
			normalBetPool[i]=-1;
		}
		normalBetBonus=233;
	}
}
