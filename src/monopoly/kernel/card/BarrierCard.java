/** BarrierCard
 *  the card to set up barrier
 */

package monopoly.kernel.card;
import monopoly.kernel.MainController;
import java.util.Scanner;
public class BarrierCard extends AbstractCard
{
	public void endEffectAction(){};
	private int positionToPut;
	public boolean cardQuestion()
	{
		Scanner cin=new Scanner(System.in);
		System.out.println("可以在前后8步安放，如前2步应当输入-2，以当前行走方向为准。");
		System.out.print("请输入安放路障的位置(-8 ~ 8，其余为放弃)：");
		positionToPut=cin.nextInt();
		return (positionToPut>=-8 && positionToPut <=8);
	}
	public void effectAction()
	{
		positionToPut*=MainController.map.userDirection[this.subjectUserId];
		positionToPut+=MainController.map.userPosition[this.subjectUserId];
		if (positionToPut>=MainController.map.unitTotal)
			positionToPut-=MainController.map.unitTotal;
		if (positionToPut<0)
			positionToPut+=MainController.map.unitTotal;
		MainController.map.setBarrier(positionToPut);
	}
	public BarrierCard (int subjectUserId)
	{
		super("路障卡",4,0,subjectUserId,-1);
	}
}
