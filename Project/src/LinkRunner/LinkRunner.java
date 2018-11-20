package LinkRunner;

import java.io.*;
import java.util.*;

public class LinkRunner
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		HashMap<String, String> h = new HashMap<String, String>();
		
		while(true)
		{
			System.out.println("1.�����ϱ�\n2.�����ϱ�");
			System.out.print("������ �ɼ��� �����ϼ���>>");
			int n = scanner.nextInt();
			
			if(n==1)
			{
				System.out.print("������ ���α׷��� �Է��ϼ���>>");
				String s = scanner.next();
				if(h.containsKey(s))
				{	
					try	
					{
						Process oProcess = new ProcessBuilder(h.get(s)).start();
					}
					catch (IOException e)
					{
				  	   System.err.println("����! �ܺ� ��� ���࿡ �����߽��ϴ�.\n" + e.getMessage());
				  	   System.exit(-1);}
					}
				}
			
			if(n==2)
			{
				System.out.print("������ ���ϸ�� ��ũ�� �Է��ϼ���>>");
				String name = scanner.next();
				if(name.equals("stop"))
					continue;
			
				String link = scanner.nextLine();
				link = link.trim();
			
				h.put(name, link);
			}
		}
	}
}
