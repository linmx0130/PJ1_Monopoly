/** CardForm
 *  the form to use card
 */
package monopoly.ui;
import monopoly.kernel.*;
import java.util.Scanner;
public class CardForm
{
	/** useCardForm
	 *  the form to use card
	 */
	public static void useCardForm(int userId)
	{
		System.out.println(MainController.userList[userId].getName()+"，您拥有的卡有：\n======");
		for (int i=0;i<CardSystem.CARD_TOTAL;++i)
		{
			if (CardSystem.cardProperty[userId][i]>0)
			{
				System.out.println("   "+i+"."+CardSystem.cardName[i]+" "+
								CardSystem.cardProperty[userId][i]+"张");
			}
		}
		System.out.println("======");
		System.out.print("请输入您想用的卡的编号，其他数字为放弃使用：");
		Scanner cin=new Scanner(System.in);
		int choose=cin.nextInt();
		if (choose<0 || choose>=CardSystem.CARD_TOTAL) return ;
		if (CardSystem.cardProperty[userId][choose]==0) return ;

		if (CardSystem.needObject[choose])
		{
			System.out.println("请选择受用者：");
			for (int i=0;i<MainController.userTotal;++i)
			{
				System.out.println("   "+i+"."+MainController.userList[i].getName());
			}
			System.out.print("请输入受用者的ID(错误ID为放弃使用)：");
			int objectId=cin.nextInt();
			if (objectId<0||objectId>=MainController.userTotal) return;
			useCardAtLast(userId,objectId,choose);
		}else
		{
			useCardAtLast(userId,-1,choose);
		}
	}
	private static void useCardAtLast(int userId,int objectId,int cardId)
	{
		if (CardSystem.useCard(userId,objectId,cardId))
		{
			System.out.println("使用成功！");
		}else
		{
			System.out.println("使用失败！可能是您没有这张卡或者效果发动条件不满足。");
		}
	}
}
