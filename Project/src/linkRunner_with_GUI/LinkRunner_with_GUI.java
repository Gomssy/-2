package linkRunner_with_GUI;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class NorthPanel extends JPanel
{
	public static HashMap<String, String> h = new HashMap<String, String>();
	
	static File Saved = new File("Saved.txt");
	static String s;
	static String sArray[];
	
	
	public static void showList()
{
	StringBuilder sb = new StringBuilder();	
	Set key = h.keySet();
	Iterator<String> itr = key.iterator();
	while(itr.hasNext())
	{
		String keyName = itr.next();
		sb.append(keyName).append("\n");
	}
	
	(CenterPanel.showList).setText(sb.toString());
}
	
	
	public NorthPanel()
	{		
		setBackground(Color.LIGHT_GRAY);
		setLayout(new FlowLayout());
		
		JButton Save = new JButton("저장하기");
		add(Save);
		Save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
					String name = JOptionPane.showInputDialog("저장할 프로그램의 이름을 입력하세요.");
					String link = JOptionPane.showInputDialog("프로그램의 링크를 입력하세요.");
					if(h.containsKey(name))
					{
						h.put(name, link);
						StringBuilder sb = new StringBuilder();
						Set key = h.keySet();
						Iterator<String> itr = key.iterator();
						while(itr.hasNext())
						{
							String keyName = itr.next();
							String keyNameLink = h.get(keyName);
							sb.append(keyName).append(" ").append(keyNameLink).append("\r\n");
							try {
								PrintWriter NewWriter = new PrintWriter(new FileWriter(Saved), true);
								NewWriter.printf(sb.toString());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						showList();
					}
					else
					{
					if(name.length()!=0 && link.length()!=0)
					{
						if(h.isEmpty())
						{
							try
							{					
								PrintWriter Writer = new PrintWriter(new FileWriter(Saved));
								Writer.println(name+" "+link);
								Writer.flush();
								Writer.close();
							}							
							catch(Exception e1)
							{
								e1.printStackTrace();
							}
						}
						else
						{
						try
						{					
							PrintWriter Writer = new PrintWriter(new FileWriter(Saved, true));
							Writer.println(name+" "+link);
							Writer.flush();
							Writer.close();
						}							
						catch(Exception e1)
						{
							e1.printStackTrace();
						}	
						}
						try
						{
							BufferedReader Reader = new BufferedReader(new FileReader(Saved));
							while((s=Reader.readLine())!=null)
							{
								sArray=s.split(" ");
								h.put(sArray[0],sArray[1]);
								
							}
						}
						catch(Exception e1)
						{							
							e1.printStackTrace();
						}
					}
					showList();
			}
			}
		});
		
		JButton Delete = new JButton("삭제하기");
		add(Delete);
		Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = JOptionPane.showInputDialog("삭제할 프로그램의 이름을 입력하세요.");
				h.remove(name);	
				if(h.isEmpty())
				{
					try {
						PrintWriter NewWriter = new PrintWriter(new FileWriter(Saved), true);
						NewWriter.println();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				else
				{
				Set key = h.keySet();
				Iterator<String> itr = key.iterator();
			
				StringBuilder sb = new StringBuilder();
				while(itr.hasNext())
				{
					String keyName = itr.next();
					String keyNameLink = h.get(keyName);
					sb.append(keyName).append(" ").append(keyNameLink).append("\r\n");
					try 
					
					{						
						PrintWriter NewWriter = new PrintWriter(new FileWriter(Saved),true);
						NewWriter.printf(sb.toString());
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				}
				showList();
			}
		});		
	}
}

class CenterPanel extends JPanel
{
	public static JTextArea showList = new JTextArea(7,25);
	
	public CenterPanel() 
	{		
		JTextField tf = new JTextField(25);
		add(tf);
		add(new JScrollPane(showList));
		showList.setEditable(false);
		tf.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTextField Input = (JTextField)e.getSource();
							
				
				if((NorthPanel.h).containsKey(Input.getText()))
				{
					try	
					{
						Process oProcess = new ProcessBuilder((NorthPanel.h).get(Input.getText())).start();
					}
					catch (IOException e1)
					{
				  	   System.err.println("에러! 외부 명령 실행에 실패했습니다.\n" + e1.getMessage());
				  	   System.exit(-1);
				  	}
				}
				Input.setText("");
			}
		});
	}
}

public class LinkRunner_with_GUI extends JFrame {
	public LinkRunner_with_GUI()
	{
		setTitle("김상언의 win+R 대체기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		NorthPanel NPan = new NorthPanel();
		CenterPanel CPan = new CenterPanel();
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(NPan, BorderLayout.NORTH);
		contentPane.add(CPan, BorderLayout.CENTER);
		
		setSize(300,250);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		try
		{
			BufferedReader Reader = new BufferedReader(new FileReader(NorthPanel.Saved));
			while((NorthPanel.s=Reader.readLine())!=null)
			{
				NorthPanel.sArray=NorthPanel.s.split(" ");
				NorthPanel.h.put(NorthPanel.sArray[0],NorthPanel.sArray[1]);
			}
		}
		catch(Exception e1)
		{							
			e1.printStackTrace();
		}
	
		NorthPanel.showList();
		new LinkRunner_with_GUI();	
		
	}
}
