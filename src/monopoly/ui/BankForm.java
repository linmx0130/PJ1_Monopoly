/** BankForm
 *  the form for bank site
 */
package monopoly.ui;
import monopoly.kernel.*;
import java.util.Scanner;

public class BankForm
{
	private static void showStoreForm(Scanner cin,int userId)
	{
		User nowUser=MainController.userList[userId];
		System.out.print("您有现金"+nowUser.getCash()+"，想要存多少?");
		int toStore=cin.nextInt();
		if (toStore<0)
		{
			System.out.println("输入非法！");
		}
		if (toStore>nowUser.getCash())
		{
			System.out.println("您现金不足！");
			return ;
		}
		nowUser.modifyCash(-toStore);
		BankSystem.savings[userId]+=toStore;
		System.out.println("您现在有现金"+nowUser.getCash()+"元，存款"+BankSystem.savings[userId]+"元。");
	}
	private static void showWithdrawForm(Scanner cin,int userId)
	{
		User nowUser=MainController.userList[userId];
		System.out.print("您有存款"+BankSystem.savings[userId]+"，想要取多少?");
		int toWithdraw=cin.nextInt();
		if (toWithdraw<0)
		{
			System.out.println("输入非法！");
		}
		if (toWithdraw>=BankSystem.savings[userId])
		{
			System.out.println("您存款不足！");
			return ;
		}
		nowUser.modifyCash(toWithdraw);
		BankSystem.savings[userId]+=toWithdraw;
		System.out.println("您现在有现金"+nowUser.getCash()+"元，存款"+BankSystem.savings[userId]+"元。");
	}

	public static void showBankForm(int userId)
	{
		User nowUser=MainController.userList[userId];
		System.out.println(nowUser.getName()+"，欢迎来到银行！请问您要做什么？");
		String choose;
		Scanner cin=new Scanner(System.in);
		do
		{
			System.out.println("  1.存款");
			System.out.println("  2.取款");
			System.out.println("  0.退出");
			System.out.print("请输入操作编号：");
			choose=cin.next();
			if (choose.equals("1")) 
			{
				showStoreForm(cin,userId);
				continue;
			}
			if (choose.equals("2")) 
			{
				showWithdrawForm(cin,userId);
				continue;
			}
			if (!choose.equals("0"))
			{
				System.out.println("输入又误，请重新输入！");
			}
		}while (!choose.equals("0"));
	}
};
