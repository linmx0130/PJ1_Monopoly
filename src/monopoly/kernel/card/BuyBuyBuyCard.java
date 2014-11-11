/** BuyBuyBuyCard
 *  buy the land where the user set
 */
package monopoly.kernel.card;
import monopoly.kernel.MainController;
import monopoly.kernel.land.NormalLand;
public class BuyBuyBuyCard extends AbstractCard
{
	public void endEffectAction(){};
	public boolean cardQuestion()
	{
		int pos=MainController.map.userPosition[subjectUserId];
		if (MainController.map.unitList[pos].typeId!=2)
		{
			System.out.println("购地卡发动失败：这块地不能购买！");
			return false;
		}
		NormalLand nowLand=(NormalLand)MainController.map.unitList[pos];
		if (nowLand.getBuyingPrice()> MainController.userList[subjectUserId].getCash())
		{
			System.out.println("购地卡发动失败：您的钱不够！");
			return false;
		}
		return true;
	}
	public void effectAction()
	{
		int pos=MainController.map.userPosition[subjectUserId];
		NormalLand nowLand=(NormalLand)MainController.map.unitList[pos];
		MainController.userList[subjectUserId].modifyCash(-nowLand.getBuyingPrice());
		if (((NormalLand)MainController.map.unitList[pos]).owner!=-1)
		{
			MainController.userList[((NormalLand)MainController.map.unitList[pos]).owner]
					.modifyCash(nowLand.getBuyingPrice());
		}
		((NormalLand)MainController.map.unitList[pos]).owner=subjectUserId;
	}
	public BuyBuyBuyCard (int subjectUserId)
	{
		super("购地卡",5,0,subjectUserId,-1);
	}
}
