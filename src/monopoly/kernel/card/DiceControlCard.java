/** DiceControlCard
 *  the card to control the dice
 */
/* ***ATTENTION***
 * This module didn't move UI part out of itself!!!
 */
package monopoly.kernel.card;
import monopoly.kernel.MapManager;
import java.util.Scanner;
public class DiceControlCard extends AbstractCard
{
	public DiceControlCard(int subjectUserId)
	{
		super("遥控骰子",3,0,subjectUserId,-1);
	}
	public void effectAction()
	{
		int nextStep=0;
		Scanner cin=new Scanner(System.in);
		do
		{
			System.out.println("请输入一个1到6的数字表示想要的骰子的值：");
			nextStep=cin.nextInt();
			if(nextStep<=0 || nextStep>6) System.out.println("输入错误！");
		}while (nextStep>0 && nextStep<=6);
	}
	public void endEffectAction(){};
};
