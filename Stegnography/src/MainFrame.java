import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
public class MainFrame extends JFrame implements ActionListener,DocumentListener
{
	/**
	 * @wbp.nonvisual location=-12,139
	 */
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private int max_string_size;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() 
	{
		super("Steganography");
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-8, -19, 704, 457);
		getContentPane().setLayout(null);
		setLocationRelativeTo(this);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel label = new JLabel("Image:");
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		label.setBounds(37, 141, 57, 18);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(91, 142, 235, 20);
		textField.setBackground(null);
		getContentPane().add(textField);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(331, 140, 89, 23);
		btnBrowse.setFocusPainted(false);
		getContentPane().add(btnBrowse);
		
		lblNewLabel = new JLabel("                 No Image Available");
		lblNewLabel.setBounds(458, 136, 211, 183);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		lblNewLabel.setBorder(border);
		getContentPane().add(lblNewLabel);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblText.setBounds(37, 181, 46, 14);
		getContentPane().add(lblText);
		
		textArea = new JTextArea();
		JScrollPane sp = new JScrollPane(textArea);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setBounds(38, 213, 380, 169);
		sp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(sp);
		
		JButton btnNewButton = new JButton("Encode");
		btnNewButton.setBounds(452, 347, 108, 36);
		btnNewButton.setFocusPainted(false);
		getContentPane().add(btnNewButton);
		
		JButton btnDecode = new JButton("Decode");
		btnDecode.setFocusPainted(false);
		btnDecode.setBounds(570, 347, 108, 36);
		getContentPane().add(btnDecode);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Yu Gothic Light", Font.BOLD, 55));
		lblNewLabel_2.setBounds(10, 11, 668, 113);
		Image img;
		try 
		{
			img = ImageIO.read(getClass().getResource("/2.png"));
			lblNewLabel_2.setIcon(new ImageIcon(img.getScaledInstance(lblNewLabel_2.getWidth(),lblNewLabel_2.getHeight(),Image.SCALE_SMOOTH)));
			getContentPane().add(lblNewLabel_2);
		} 
		catch (IOException e) 
		{
			//e.printStackTrace();
		}
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setBounds(357, 180, 57, 20);
		getContentPane().add(textField_1);
		textField_1.setEditable(false);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.white);
		textField_1.setColumns(10);
		
		JLabel lblLeft = new JLabel("Char Left:");
		lblLeft.setBounds(287, 182, 72, 14);
		getContentPane().add(lblLeft);
		lblLeft.setForeground(Color.RED);
		lblLeft.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(4096, 556, 101, 22);
		getContentPane().add(menuBar);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(458, 326, 211, 14);
		getContentPane().add(progressBar);
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnFile = new JMenu("File");
		menuBar_1.add(mnFile);
		
		JMenuItem mntmClear = new JMenuItem("Clear");
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmClear);
		mnFile.add(mntmExit);
		
		mntmClear.addActionListener(this);
		mntmExit.addActionListener(this);
		btnBrowse.addActionListener(this);
		btnDecode.addActionListener(this);
		btnNewButton.addActionListener(this);
		textArea.getDocument().addDocumentListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand()=="Browse")
		{
			// create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.setFileFilter(new FileNameExtensionFilter(".png,.jpg", "PNG","JPG"));
 
            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);
 
            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)
            {
                textField.setText(j.getSelectedFile().getAbsolutePath());
                File input_file = new File(j.getSelectedFile().getAbsolutePath());
                BufferedImage image;
				try 
				{
					image = ImageIO.read(input_file);
					max_string_size = InsertText.stringSize(image);
					textField_1.setText(max_string_size+"");
					Image img = ImageIO.read(new File(j.getSelectedFile().getAbsolutePath()));
	                lblNewLabel.setIcon(new ImageIcon(img.getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(),Image.SCALE_SMOOTH)));
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(this,"File Type Not Supported","Error",JOptionPane.ERROR_MESSAGE);
				}
                
                
            }
		}
		else if(e.getActionCommand()=="Decode")
		{
			String path=textField.getText();
			if(path.isEmpty())
				JOptionPane.showMessageDialog(this,"No Image File Selected","Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				    File input_file = new File(path);
	                BufferedImage image;
					try 
					{
						image = ImageIO.read(input_file);
						String msg = InsertText.extractText(image);
						if(msg.equalsIgnoreCase("error: No Message Is Encoded In This Image"))
						{
							JOptionPane.showMessageDialog(this,"No Message Is Encoded","Result",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						 textArea.setText(msg);
					} 
					catch (IOException e1) 
					{
						JOptionPane.showMessageDialog(this,"File Type Not Supported","Error",JOptionPane.ERROR_MESSAGE);
					}
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(this,"Unknown Error Occured","Error",JOptionPane.ERROR_MESSAGE);
					}
			}
		}
		else if(e.getActionCommand()=="Encode")
		{
			String path=textField.getText();
			if(path.isEmpty())
				JOptionPane.showMessageDialog(this,"No Image File Selected","Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				j.setFileSelectionMode(JFileChooser.CUSTOM_DIALOG);
				j.setApproveButtonText("Save");
				j.setAcceptAllFileFilterUsed(false);
			
				j.setDialogTitle("Destination");
		
				int r = j.showSaveDialog(this);
				
				if(r==JFileChooser.APPROVE_OPTION)
				{
					
					String dest_path = j.getSelectedFile().getAbsolutePath();
				
				    File input_file = new File(path);
	                BufferedImage image;
					try 
					{	
						image = ImageIO.read(input_file);
						image = InsertText.insertText(image,textArea.getText());
						File output_file = new File(dest_path+".png");
				        ImageIO.write(image, "png", output_file);
				        
					      
				        JOptionPane.showMessageDialog(this,"Message Encrypted Successfully!","Successful",JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (IOException e1) 
					{
						JOptionPane.showMessageDialog(this,"File Type Not Supported","Error",JOptionPane.ERROR_MESSAGE);
					}
					catch( Exception e1)
					{
						JOptionPane.showMessageDialog(this,"Unknown Error Occured:\n"+e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}	
				}
			}
		}
		else if(e.getActionCommand()=="Clear")
		{
			textField.setText(null);
			textField_1.setText(null);
			lblNewLabel.setIcon(null);
			textArea.setText(null);
		}
		else if(e.getActionCommand()=="Exit")
		{
			System.exit(0);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) 
	{
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) 
	{
		String text = textArea.getText();
		int len = text.length();
		textField_1.setText((max_string_size - len)+"");
	}

	@Override
	public void removeUpdate(DocumentEvent e) 
	{
		textField_1.setText((max_string_size - textArea.getText().length())+"");
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
