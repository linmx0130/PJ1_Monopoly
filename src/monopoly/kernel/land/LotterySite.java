/** LotterySite
 *  everything about lottery
 */
package monopoly.kernel.land;
import java.util.Scanner;
import monopoly.kernel.*;
import monopoly.ui.LotteryForm;
public class LotterySite extends AbstractLand
{
	public LotterySite(MapManager mapM,int landId)
	{
		super(mapM,5,landId);
	}
	public void arrivedAction(int userId) 
	{
		LotteryForm.showLotteryForm(userId);
	};
	public void passingAction(int userId) {};
	public static AbstractLand loader(MapManager mapM, Scanner fin,int landId)
	{
		LotterySite ret=new LotterySite(mapM,landId);
		return ret;
	}
};
