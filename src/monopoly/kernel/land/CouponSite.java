/** CouponSite
 *  the site give user free coupons
 */
package monopoly.kernel.land;
import monopoly.kernel.*;
import java.util.Scanner;
import monopoly.ui.MessageManager;
public class CouponSite extends AbstractLand
{
	private final int MAX_COUPON=4;
	private CouponSite(MapManager map, int landId)
	{
		super(map,7,landId);
	}
	public void arrivedAction(int userId)
	{
		int give=(int)(MAX_COUPON*Math.random()+1);
		MainController.userList[userId].modifyCoupons(give);
		MessageManager.showMessage(MessageManager.MESSAGE,"CouponSite",
			"玩家"+MainController.userList[userId].getName()+"幸运的得到了"+give+"张点券，其他玩家可要小心咯~");
	}
	public void passingAction(int userId) {};
	public static AbstractLand loader(MapManager mapM, Scanner fin,int landId)
	{
		CouponSite ret=new CouponSite(mapM,landId);
		return ret;
	}
};
