/** CardShopForm
 *  the ui for card shop
 */
package monopoly.ui;
import java.util.Scanner;
import monopoly.kernel.*;
public class CardShopForm
{
	//it will be generated every time 
	private static int[] sellingList;
	//the sum of cardAppearRate should be 1.0
	private static final double[] cardAppearRate={0.3,0.2,0.5};
	//the price of each card
	private static final int[] cardPrice={2,2,1};
	private static final int sizeOfShow=5;
	private static void generateSellingList()
	{
		sellingList=new int[sizeOfShow];
		for (int i=0;i<sizeOfShow;++i)
		{
			double t=Math.random();
			int choose=0;
			for (;choose<CardSystem.CARD_TOTAL;++choose)
			{
				if (t<=cardAppearRate[choose]) break;
				t-=cardAppearRate[choose];
			}
			sellingList[i]=choose;
		}
	}
	public static void showCardShopForm(int userId)
	{
		Scanner cin=new Scanner(System.in);
		User nowUser=MainController.userList[userId];
		System.out.println(nowUser.getName()+"，欢迎来到道具店，您可以用点券购买神奇的道具！");
		System.out.println("现在供应的道具有：");
		generateSellingList();
		for (int i=0;i<sizeOfShow;++i)
		{
			System.out.println("  "+i+"."+CardSystem.cardName[i]+
							" -> 点券"+cardPrice[i]+"张");
		}
		System.out.println("您有点券"+nowUser.getCoupons()+"张");
		System.out.println("请输入一个0～" + (sizeOfShow-1) + 
						"的数字选择购买的卡(其他放弃购买)：");
		int choose=cin.nextInt();
		if (choose<0 || choose>=sizeOfShow)
		{
			return ;
		}
		if (cardPrice[choose] > nowUser.getCoupons())
		{
			System.out.println("点券不足，无法购买！");
		}else
		{
			nowUser.modifyCoupons(-cardPrice[choose]);
			CardSystem.cardProperty[userId][choose]++;
		}
	}
}
