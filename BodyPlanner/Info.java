package BodyPlanner;

import java.util.*;

public class Info
{
	static Scanner sc=new Scanner(System.in);
	
    public static String InputName()
    {
        System.out.print("<<신상 정보 입력>>\n이름을 입력하세요 : ");
        String user_name=sc.nextLine();
        return user_name;
    }
    public static float InputHeight()
    {
        System.out.print("키(cm) : ");
        float user_height=sc.nextFloat();
        return user_height;
    }
    public static float InputWeight()
    {
        System.out.print("몸무게(kg) : ");
        float user_wight=sc.nextFloat();
        return user_wight;
    }
    public static int InputAge()
    {
        System.out.print("나이 : ");
        int user_name=sc.nextInt();
        return user_name;
    }
    public static int InputGender()
    {
        System.out.print("성별을 선택하세요 : 1. 남자 2. 여자 ");
        int user_gender=sc.nextInt();
        return user_gender;
    }
    public static double Harr(String name, double height, double weight, int age, int gender)
    {
    	double harris;
    	if(gender==1)
    		harris = 66.47 + (13.75*weight) + (5*height) - (6.76*age);
    	else
    		harris= 655.1 + (9.56*weight) + (1.85*height) - (4.68*age);
    	System.out.println(name+"님의 기초대사량은 "+String.format("%.2f", harris)+"kcal 입니다");
    	return harris;
    }
    public static double CalActive(double h, String name)
    {
    	System.out.println("본인의 운동강도를 입력해주세요.\n1.운동안함\n"
    			+ "2.2족보행\n3.주에 3일\n4.주에 5~6일\n5.난 선수임");
    	System.out.print("운동강도 : ");
    	int active=sc.nextInt();
    	double amount=0;
    	if(active==1)
    		 amount = h*0.2 + h;
    	else if(active==2)
    		amount = h*0.375 + h;
    	else if(active==3)
    		amount = h*0.555 + h;
    	else if(active==4)
    		amount = h*0.725 + h;
    	else if(active==5)
    		amount = h*0.895 + h;
    	System.out.println(name+"님의 활동대사량은 "+String.format("%.2f", amount)+"kcal입니다");
    	return amount;
    }
    public static int BodyPlan()
    {
    	System.out.println("Body Plan을 선택해주세요");
    	System.out.println("1.벌크업\r\n"
    			+ "2.린매스업\r\n"
    			+ "3.다이어트");
    	System.out.print("Body Plan : ");
    	int num=sc.nextInt();
    	if(num==1)
    		System.out.println("벌크업을 선택하셨습니다");
    	else if(num==2)
    		System.out.println("린매스업을 선택하셨습니다");
    	else if(num==3)
    		System.out.println("다이어트를 선택하셨습니다");
    	return num;
    }
    public static ArrayList<Double> Calculate(int m, double w, double admin)  //m이 1, 2, 3이 아닐때 a리턴중->수정필요 
    {
    	ArrayList<Double> a=new ArrayList<>();//벌크업
    	ArrayList<Double> b=new ArrayList<>();//린매스업
    	ArrayList<Double> c=new ArrayList<>();//다이어트
    	if(m==1)
    	{
    		double bulk_cal=admin+600;
    		double bulk_pro=w*2.15;
    		double bulk_fat =(bulk_cal*0.245)/9.1;
    		double bulk_carbo = (bulk_cal-(bulk_pro*4.1+bulk_fat*9.1))/4.1;
    		
    		a.add(bulk_cal);
    		a.add(bulk_carbo);
    		a.add(bulk_pro);
    		a.add(bulk_fat);
    		return a;
    	}
    	else if(m==2)
    	{
    	    double lean_cal = admin+250;
    	    double lean_pro = w*1.7;
    	    double lean_fat = (lean_cal*0.23)/9.1;
    	    double lean_carbo = (lean_cal-(lean_pro*4.1+lean_fat*9.1))/4.1;
    	    	    
    	    b.add(lean_cal);
    	    b.add(lean_carbo);
    	    b.add(lean_pro);
    	    b.add(lean_fat);
    	    return b;
    	}
    	else if(m==3)
    	{
    	    double diet_cal = admin-500;
    	    double diet_pro = w*1.6;
    	    double diet_fat = (diet_cal*0.2)/9.1;
    	    double diet_carbo = (diet_cal-(diet_pro*4.1+diet_fat*9.1))/4.1;
    	    
    	    c.add(diet_cal);
    	    c.add(diet_carbo);
    	    c.add(diet_pro);
    	    c.add(diet_fat);
    	    return c;
    	}
    	return a;
    }
    public static void MakeBody(ArrayList<Double>result)
    {
    	System.out.println("섭취해야 할 칼로리는 약 "+String.format("%.2f", result.get(0))+"kcal입니다");
    	System.out.println("섭취해야 할 탄수화물은 약 "+String.format("%.2f", result.get(1))+"kcal입니다");
    	System.out.println("섭취해야 할 단백질은 약 "+String.format("%.2f", result.get(2))+"kcal입니다");
    	System.out.println("섭취해야 할 지방은 약 "+String.format("%.2f", result.get(3))+"kcal입니다");
    }
}