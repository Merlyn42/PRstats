package havocx42;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.JSplitPane;
import javax.swing.JPanel;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;

public class Gui implements ActionListener {

	private JFrame		frame;
	private JTextField	txtTargetpath;
	private JTextField	txtSourcepath;
	private String		targetPath	= "";
	private String		sourcePath	= "";

	/**
	 * Create the application.
	 */
	public Gui(String sourcePath_in,String targetPath_in) {
		sourcePath=sourcePath_in;
		targetPath=targetPath_in;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 517, 122);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);

		JButton runBtn = new JButton("Run");
		runBtn.addActionListener(this);
		runBtn.setActionCommand("run");
		panel_2.add(runBtn);

		JPanel targetPanel = new JPanel();
		frame.getContentPane().add(targetPanel, BorderLayout.CENTER);
		targetPanel.setLayout(new BoxLayout(targetPanel, BoxLayout.X_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Target:");
		targetPanel.add(lblNewLabel_1);

		txtTargetpath = new JTextField();
		txtTargetpath.setText(targetPath);
		targetPanel.add(txtTargetpath);
		txtTargetpath.setColumns(28);

		JButton targetBtn = new JButton("Choose Target File");
		targetBtn.addActionListener(this);
		targetBtn.setActionCommand("target");
		targetPanel.add(targetBtn);

		JPanel sourcePanel = new JPanel();
		frame.getContentPane().add(sourcePanel, BorderLayout.NORTH);
		sourcePanel.setLayout(new BoxLayout(sourcePanel, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("Source:");
		sourcePanel.add(lblNewLabel);

		txtSourcepath = new JTextField();
		txtSourcepath.setText(sourcePath);
		sourcePanel.add(txtSourcepath);
		txtSourcepath.setColumns(28);

		JButton sourceBtn = new JButton("Choose Source Directory");
		sourceBtn.addActionListener(this);
		sourceBtn.setActionCommand("source");
		sourcePanel.add(sourceBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Open Save Folder
		if ("source".equals(e.getActionCommand())) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			File sourceFile;
			if(sourcePath!=null){
				sourceFile = new File(sourcePath);
			}else{
				sourceFile = new File("");
			}
			fc.setSelectedFile(sourceFile);
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				if (f.exists()) {
					sourcePath = f.getAbsolutePath();
					txtSourcepath.setText(sourcePath);
				}
			}
		}

		// new version open a patch file
		if ("target".equals(e.getActionCommand())) {
			final JFileChooser fc = new JFileChooser();
			File targetFile;
			if(targetPath!=null){
				targetFile = new File(targetPath);
			}else{
				targetFile = new File("");
			}
			if(!targetFile.isFile()){
				targetFile=new File(targetFile,"PRStats.xls");
			}
			fc.setSelectedFile(targetFile);
			
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				targetPath = f.getAbsolutePath();
				txtTargetpath.setText(targetPath);

			}
		}
		if ("run".equals(e.getActionCommand())) {
			System.out.println("run");
			File source = new File(sourcePath);
			if (!source.exists() || !source.isDirectory()) {
				System.err.println("Source invalid");
				return;
			}
			File target = new File(targetPath);
			try {
				if(!target.canWrite()&&!target.createNewFile()){
					System.err.println("Can't write to target");
					return;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				return;
			}
			Controller controller = new Controller();
			controller.run(source, target);
			WindowEvent winClosingEvent = new WindowEvent( frame, WindowEvent.WINDOW_CLOSING );
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent( winClosingEvent );
		}

	}

}
