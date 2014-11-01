/** NewsForm
 *  the ui of news site
 */
package monopoly.ui;
import java.util.Scanner;
public class NewsForm
{
	public static void publishNews(String newsContent)
	{
		System.out.println("  ===新闻点大新闻===");
		System.out.println(newsContent);
		System.out.println("  ===香港记者提供===");
		System.out.print("输入任意内容回车后继续。");
		Scanner cin=new Scanner(System.in);
		cin.next();
	}
};
