/** TimeManager
 *  manage everything about time.
 */
package monopoly.kernel;
public class TimeManager
{
	private static final int[] dayOfMonth={31,28,31,30,31,30,31,31,30,31,30,31};

	public static int nowDay=0;

	public static int startYear=2014;
	private static int dayOfThisYear(int year)
	{
		return ((year % 4 ==0) ^(year%100==0))?366:365;
	}
	
	/** getNowDate
	 *  return a int[3], [1]-Year, [2]-Month, [3]-Day
	 */
	public static int[] getNowDate()
	{
		int nowYear=startYear;
		int leftDay=nowDay;

		while (leftDay>dayOfThisYear(nowYear))
		{
			leftDay-=dayOfThisYear(nowYear);
			nowYear++;
		}
		int nowMonth;
		for (nowMonth=0;nowMonth<12;nowMonth++)
		{
			if (nowMonth==2 && dayOfThisYear(nowYear)==366)
			{
				if (leftDay>29) leftDay-=29;
				continue;
			}else if (leftDay>dayOfMonth[nowMonth])
			{
				leftDay-=dayOfMonth[nowMonth];
				continue;
			}
			break;
		}
		int []ret=new int[3];
		ret[0]=nowYear;ret[1]=nowMonth+1;ret[2]=leftDay;
		return ret;
	}
	/** endMonthAction
	 *  When it's the last day of a month, it will be involked.
	 */
	public static void endMonthAction()
	{
		BankSystem.generateInterest();
		LotterySystem.publishNormalBet();
	};
	/** newDay
	 *  Only be called by MainController to start a new day
	 */
	public static void newDay()
	{
		nowDay++;
		int [] nowDate=getNowDate();
		if (nowDate[2]>=dayOfMonth[nowDate[1]])
		{
			endMonthAction();
		}
	}
};
