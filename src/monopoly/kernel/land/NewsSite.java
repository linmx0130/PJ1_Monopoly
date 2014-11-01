/** NewsSite
 *  the actions with news
 */
package monopoly.kernel.land;
import monopoly.kernel.*;
import monopoly.ui.*;
import java.util.Scanner;

public class NewsSite extends AbstractLand
{
	//the posibility of each news, the sum must be 1.0
	private final static double[] newsPosibility=
	{
		0.6,0.1,0.1,0.1,0.1
	};
	/** newsOne
	 *  give bonus to who has most land
	 */
	private void newsOne()
	{
		int maxLandValue=-1;
		int [] landValueList=new int[MainController.userTotal];
		for (int i=0;i<MainController.userTotal;++i)
		{
			int count=0;
			for (int j=0;j<MainController.map.unitTotal;++j)
			{
				if (MainController.map.unitList[j].typeId==2)
				{
					if (((NormalLand)(MainController.map.unitList[j])).owner==i)
					{
						count+=((NormalLand)(MainController.map.unitList[j])).getBuyingPrice();
					}
				}
			}
			if (count>maxLandValue) maxLandValue=count; 
			landValueList[i]=count;
		}
		String buffer="";
		int bonus=(int)(Math.random()*10000);
		for (int i=0;i<MainController.userTotal;++i)
		{
			if (landValueList[i]==maxLandValue)
			{
				buffer+="奖励土地最多的"+MainController.userList[i].getName()+"现金"+bonus+"元。\n";
				MainController.userList[i].modifyCash(bonus);
			}
		}
		NewsForm.publishNews(buffer);
	}
	/** newsTow
	 *  give bonus to who has least land
	 */
	private void newsTwo()
	{
		int minLandValue=0x7fffffff;
		int [] landValueList=new int[MainController.userTotal];
		for (int i=0;i<MainController.userTotal;++i)
		{
			int count=0;
			for (int j=0;j<MainController.map.unitTotal;++j)
			{
				if (MainController.map.unitList[j].typeId==2)
				{
					if (((NormalLand)(MainController.map.unitList[j])).owner==i)
					{
						count+=((NormalLand)(MainController.map.unitList[j])).getBuyingPrice();
					}
				}
			}
			if (count<minLandValue) minLandValue=count; 
			landValueList[i]=count;
		}
		String buffer="";
		int bonus=(int)(Math.random()*10000);
		for (int i=0;i<MainController.userTotal;++i)
		{
			if (landValueList[i]==minLandValue)
			{
				buffer+="补助土地最少的"+MainController.userList[i].getName()+"现金"+bonus+"元。\n";
				MainController.userList[i].modifyCash(bonus);
			}
		}
		NewsForm.publishNews(buffer);
	}
	/** newsThree
	 *  add 10% money to everyone's bank account
	 */
	private void newsThree()
	{
		for (int i=0;i<MainController.userTotal;++i)
		{
			BankSystem.savings[i]+=(int)(0.1*BankSystem.savings[i]);
		}
		NewsForm.publishNews("经济景气，银行加发储金红利，每个人得到存款10%！");
	}
	/** newsFour
	 *  decrease 10% money to everyone's bank account
	 */
	private void newsFour()
	{
		for (int i=0;i<MainController.userTotal;++i)
		{
			BankSystem.savings[i]-=(int)(0.1*BankSystem.savings[i]);
		}
		NewsForm.publishNews("征收财产税！所有人缴纳存款的10%。");
	}
	private void makeBigNews(int newsId,int userId)
	{
		if (newsId==0) //no news
		{
			NewsForm.publishNews("\"我看你们新闻界还是要努力提高自身姿势水平，不要搞大新闻！\"");
		}
		if (newsId==1) newsOne();
		if (newsId==2) newsTwo();
		if (newsId==3) newsThree();
		if (newsId==4) newsThree();
	}
	NewsSite(MapManager map,int landId)
	{
		super(map,4,landId);
	}
	public void arrivedAction(int userId) 
	{
		double newsRandomValue=Math.random();
		for (int i=0;i<newsPosibility.length;++i)
		{
			if (newsRandomValue>newsPosibility[i])
			{
				newsRandomValue-=newsPosibility[i];
				continue;
			}
			makeBigNews(i,userId);
			break;
		}
	};
	public void passingAction(int userId) {};
	public static AbstractLand loader(MapManager mapM, Scanner fin,int landId)
	{
		NewsSite ret=new NewsSite(mapM,landId);
		return ret;
	}
}
