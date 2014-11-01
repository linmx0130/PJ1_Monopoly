/** LotteryForm
 *  the form for lottery
 */
package monopoly.ui;
import monopoly.kernel.*;
import java.util.Scanner;
public class LotteryForm
{
	private static void oneTimeBetForm(Scanner cin,int userId)
	{
		if (MainController.userList[userId].getCash()<LotterySystem.oneTimeBetPrice)
		{
			System.out.println("您没钱买彩票！好好搬砖去吧！");
			return ;
		}
		if (LotterySystem.oneTimeBet(userId))
		{
			System.out.println("恭喜！您中奖了！奖金已发到您到银行账户上。");
		}
		else 
		{
			System.out.println("真遗憾，您没中奖！欢迎继续投注！");
		}
	}
	private static void normalBetForm(Scanner cin,int userId)
	{
		if (MainController.userList[userId].getCash()<LotterySystem.normalBetPrice)
		{
			System.out.println("您没钱买彩票！好好搬砖去吧！");
			return ;
		}
		System.out.println("可下注的号码为：");
		int count=0;
		for (int i=0;i<LotterySystem.normalBetPool.length;++i)
		{
			if (LotterySystem.normalBetPool[i]!=-1)
			{
				System.out.print(i+"\t");
				if (++count ==5)
				{
					System.out.print("\n");
					count=0;
				}
			}
		}
		if (count !=0) System.out.print("\n");
		System.out.print("请输入您选择的号码：");
		int choose=cin.nextInt();
		if (LotterySystem.normalBet(userId,choose))
		{
			System.out.println("下注成功！欢迎继续购买彩票！");
		}
		else 
		{
			System.out.println("非法下注！");
		}
	}

	public static void showLotteryForm(int userId)
	{
		System.out.println("欢迎来到彩票站！");
		String choose;
		Scanner cin=new Scanner(System.in);
		do 
		{
			System.out.println("请选择您打算购买的彩票：");
			System.out.println("  1.刮刮彩：每注"+LotterySystem.oneTimeBetPrice+
							"元，立即开奖，中奖可获"+LotterySystem.oneTimeBetBonus);
			System.out.println("  2.月末开奖彩：每注"+LotterySystem.normalBetPrice+
							"元，月末开奖，中奖可得奖池中全部奖金。");
			System.out.println("  0.离开彩票站");
			System.out.println("请输入您的选择:");
			choose=cin.next();
			if (choose.equals("1"))
			{
				oneTimeBetForm(cin,userId);
				continue;
			}
			if (choose.equals("2"))
			{
				normalBetForm(cin,userId);
				continue;
			}
			if (!choose.equals("0"))
			{
				System.out.println("输入非法！");
			}
		}while (!choose.equals("0"));
	}
};
