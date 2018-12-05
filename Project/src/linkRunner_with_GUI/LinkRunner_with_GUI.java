package linkRunner_with_GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


class NorthPanel extends JPanel
{
	public static HashMap<String, String> h = new HashMap<String, String>();
	
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
		
		JButton Save = new JButton("�����ϱ�");
		add(Save);
		Save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
					String name = JOptionPane.showInputDialog("������ ���α׷��� �̸��� �Է��ϼ���.");
					String link = JOptionPane.showInputDialog("���α׷��� ��ũ�� �Է��ϼ���.");
					if(name.length()!=0)
						h.put(name, link);
					showList();
			}
		});
		
		JButton Delete = new JButton("�����ϱ�");
		add(Delete);
		Delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = JOptionPane.showInputDialog("������ ���α׷��� �̸��� �Է��ϼ���.");
				h.remove(name);
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
				  	   System.err.println("����! �ܺ� ��� ���࿡ �����߽��ϴ�.\n" + e1.getMessage());
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
		setTitle("������ win+R ��ü��");
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
		new LinkRunner_with_GUI();
	}
}
