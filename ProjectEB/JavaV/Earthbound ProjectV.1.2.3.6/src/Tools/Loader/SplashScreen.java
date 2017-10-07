package Tools.Loader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow{
	
	private BorderLayout borderLayout;
	private JPanel southPanel;
	private JLabel imageLabel;
	private FlowLayout southFlow;
	private JProgressBar progressBar;
	private ImageIcon imgIcon;
	
	public SplashScreen(ImageIcon texture){
		this.imgIcon = new ImageIcon(texture.getImage());
		southPanel = new JPanel();
		borderLayout = new BorderLayout();
		imageLabel = new JLabel();
		southFlow = new FlowLayout();
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		try{
			init();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private void init() throws Exception{
		imageLabel.setIcon(imgIcon);
		getContentPane().setLayout(borderLayout);
		southPanel.setLayout(southFlow);
		southPanel.setBackground(Color.black);
		getContentPane().add(imageLabel, BorderLayout.CENTER);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.add(progressBar, null);
		pack();
	}
	
	public void setMaxProgess(int maxprogress){
		progressBar.setMaximum(maxprogress);
	}
	
	public void setProgress(int progress){
		setProgress(progress, null);
	}
	
	
	public void setProgress(int progress, String message){
		float percentage = ((float)progress/(float)progressBar.getMaximum()) * 100;
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				progressBar.setValue(progress);
				if(message == null){
					if(percentage>=100)
						progressBar.setString("Loading: 100%");
					else
						progressBar.setString("Loading: " + percentage + "%");
				}
				else
				{
					progressBar.setString(message);
				}
			}
		});
	}
}
