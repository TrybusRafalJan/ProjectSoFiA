package Source;





import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class Display extends JFrame {

	static CalculationEngine c = new CalculationEngine();
	
	public Display() 
	{
		setSize(935,520);
		setTitle("Simulation of Fraunhofer Difractive Images (SoFiA) - v.1.0 ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new MigLayout()); 
		setResizable(false);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		
	JFrame f = new Display();
	
	//Section of screens creation
	JPanel pixel_log = new Pixel(220,200,10,700,70);
	JPanel Screen1 = new JPanel(new MigLayout());
	Screen1.add(pixel_log,"width 300:300:300,height 300:300:300, gapx 10 , gapy 5");
	
	TitledBorder Screen1_border;
	Screen1_border = BorderFactory.createTitledBorder("Logarythmic Scale Plot:");
	Screen1.setBorder(Screen1_border);
	
	JPanel pixel_lin = new Pixel(220,200,10,700,70);
	JPanel Screen2 = new JPanel(new MigLayout());
	Screen2.add(pixel_lin,"width 300:300:300,height 300:300:300,gapx 10 , gapy 5");
	
	TitledBorder Screen2_border;
	Screen2_border = BorderFactory.createTitledBorder("Linear Scale Plot:");
	Screen2.setBorder(Screen2_border);
	
	
	f.add(Screen1,"width 350:350:350,height 350:350:350");
	f.add(Screen2,"width 350:350:350,height 350:350:350");
	
	JPanel sidePanel = new JPanel(new MigLayout());
	
	TitledBorder sidePanel_border;
	sidePanel_border = BorderFactory.createTitledBorder("Aperture choice:");
	sidePanel.setBorder(sidePanel_border);
	
	JLabel apertures_l =  new JLabel("Select an aperture:");
	String[] apertures = {"RECTANGLE","CIRCLE"};
	JComboBox apertures_c = new JComboBox(apertures);
	sidePanel.add(apertures_l,"width 200:200:200,wrap");
	sidePanel.add(apertures_c,"width 180:180:180,wrap");
	
	JPanel opt1 = new JPanel(new MigLayout());
	JSlider j1 = new JSlider(40,300,220);
	JLabel l1 = new JLabel("x-lenght[mm]");
	opt1.add(l1,"width 150:150:150,wrap");
	opt1.add(j1,"width 150:150:150,wrap");
	sidePanel.add(opt1,"width 180:180:180,wrap");
	
	JPanel opt2 = new JPanel(new MigLayout());
	JSlider j2 = new JSlider(40,300,220);
	JLabel l2 = new JLabel("y-lenght[mm]");
	opt1.add(l2,"width 150:150:150,wrap");
	opt1.add(j2,"width 150:150:150,wrap");
	sidePanel.add(opt2,"width 180:180:180,wrap");
	
	JPanel opt3 = new JPanel(new MigLayout());
	JSlider j3 = new JSlider(1,20,10);
	JLabel l3 = new JLabel("z-lenght[m]");
	opt1.add(l3,"width 150:150:150,wrap");
	opt1.add(j3,"width 150:150:150,wrap");
	sidePanel.add(opt3,"width 180:180:180,wrap");
	
	JPanel opt4 = new JPanel(new MigLayout());
	JSlider j4 = new JSlider(100,800,550);
	JLabel l4 = new JLabel("lambda-lenght[nm]");
	opt1.add(l4,"width 150:150:150,wrap");
	opt1.add(j4,"width 150:150:150,wrap");
	sidePanel.add(opt4,"width 180:180:180,wrap");
	
	JButton exec_b = new JButton("EXECUTE");
	sidePanel.add(exec_b,"width 180:180:180");
	f.add(sidePanel,"width 200:200:200,height 350:350:350,span,wrap");
	
	//Section of Properities Panel creation
	
	JPanel PropertiesPanel = new JPanel(new MigLayout());
	
	TitledBorder txtField;
	txtField = BorderFactory.createTitledBorder("Experiment properities:");
	PropertiesPanel.setBorder(txtField);
	
	
	JPanel Descriptions = new JPanel(new MigLayout());
	JLabel d1 = new JLabel("x-lenght:[mm]");
	JLabel d2 = new JLabel("y-lenght[mm]:");
	JLabel d3 = new JLabel("z-lenght[m]:");
	JLabel d4 = new JLabel("lambda-lenght[nm]:");
	Descriptions.add(d1,"width 150:150:150");
	Descriptions.add(d2,"width 150:150:150");
	Descriptions.add(d3,"width 150:150:150");
	Descriptions.add(d4,"width 150:150:150");
	
	JPanel txtFields = new JPanel(new MigLayout());
	JTextField t1 = new JTextField("220");
	JTextField t2 = new JTextField("200");
	JTextField t3 = new JTextField("10");
	JTextField t4 = new JTextField("700");
	txtFields.add(t1,"width 150:150:150");
	txtFields.add(t2,"width 150:150:150");
	txtFields.add(t3,"width 150:150:150");
	txtFields.add(t4,"width 150:150:150");
	
	
	j1.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			t1.setText(Integer.toString(j1.getValue()));
			
		}});

	j2.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			t2.setText(Integer.toString(j2.getValue()));
			
		}});
	
	j3.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			t3.setText(Integer.toString(j3.getValue()));
	
		}});
	
	j4.addChangeListener(new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			t4.setText(Integer.toString(j4.getValue()));
			
		}});
	
	apertures_c.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(apertures_c.getSelectedItem().equals("CIRCLE"))
			{
				t1.disable();
				d1.setText("----");
				d2.setText("r - lenght [mm]");
				d1.disable();

				Descriptions.repaint();
				txtFields.repaint();
				
				l1.setText("----");
				l2.setText("r - lenght [mm]");
				l1.disable();
				j1.disable();
				opt1.repaint();					
			}
	
			if(apertures_c.getSelectedItem().equals("RECTANGLE"))
			{
				t1.enable();
				d1.setText("x-lenght:[mm]");
				d2.setText("y-lenght:[mm]");
				d1.enable();

				Descriptions.repaint();
				txtFields.repaint();
				
				l1.setText("x-lenght:[mm]");
				l2.setText("y-lenght:[mm]");
				l1.enable();
				j1.enable();
				opt1.repaint();
				
			}
			
		}});
	
exec_b.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			j1.setValue((int) Double.parseDouble(t1.getText()));
			j2.setValue((int) Double.parseDouble(t2.getText()));
			j3.setValue((int) Double.parseDouble(t3.getText()));
			j4.setValue((int) Double.parseDouble(t4.getText()));
			
			
			
			if (t1.getText().matches("[0-9]+") && t2.getText().matches("[0-9]+") && t3.getText().matches("[0-9]+") && t4.getText().matches("[0-9]+") && apertures_c.getSelectedItem().equals("RECTANGLE"))
			{
				
				System.out.println("rectangle");
				((CalculationEngine)c).SetLx((Pixel) pixel_log,Double.parseDouble(t1.getText()));
				((CalculationEngine)c).SetLy((Pixel)pixel_log,Double.parseDouble(t2.getText()));
				((CalculationEngine) c).SetZ((Pixel)pixel_log,Double.parseDouble(t3.getText()));
				((CalculationEngine) c).SetLambda((Pixel)pixel_log,Double.parseDouble(t4.getText()));
				((CalculationEngine) c).Intensity_RECT((Pixel)pixel_log);
				((Pixel) pixel_log).Scale_log();
				
	
				((CalculationEngine)c).SetLx((Pixel) pixel_lin,Double.parseDouble(t1.getText()));
				((CalculationEngine)c).SetLy((Pixel)pixel_lin,Double.parseDouble(t2.getText()));
				((CalculationEngine) c).SetZ((Pixel)pixel_lin,Double.parseDouble(t3.getText()));
				((CalculationEngine) c).SetLambda((Pixel)pixel_lin,Double.parseDouble(t4.getText()));
				((CalculationEngine) c).Intensity_RECT((Pixel)pixel_lin);
				((Pixel) pixel_lin).Scale_lin();
				
				pixel_log.repaint();
				pixel_lin.repaint();
			
			}
			else if (t1.getText().matches("[0-9]+") && t2.getText().matches("[0-9]+") && t3.getText().matches("[0-9]+") && t4.getText().matches("[0-9]+") && apertures_c.getSelectedItem().equals("CIRCLE"))
			{
				
				System.out.println("circle");
				
				((CalculationEngine)c).SetR((Pixel)pixel_log,Double.parseDouble(t2.getText()));
				((CalculationEngine) c).SetZ((Pixel)pixel_log,Double.parseDouble(t3.getText()));
				((CalculationEngine) c).SetLambda((Pixel)pixel_log,Double.parseDouble(t4.getText()));
				((CalculationEngine) c).Intensity_CIRC((Pixel)pixel_log);
				((Pixel) pixel_log).Scale_log();
				
		
				((CalculationEngine)c).SetR((Pixel)pixel_lin,Double.parseDouble(t2.getText()));
				((CalculationEngine) c).SetZ((Pixel)pixel_lin,Double.parseDouble(t3.getText()));
				((CalculationEngine) c).SetLambda((Pixel)pixel_lin,Double.parseDouble(t4.getText()));
				((CalculationEngine) c).Intensity_CIRC((Pixel)pixel_lin);
				((Pixel) pixel_lin).Scale_lin();
				
				pixel_log.repaint();
				pixel_lin.repaint();

			}
			else
			{
				
			JOptionPane.showMessageDialog(f, "Some imput field does not contain a number...","NaN",JOptionPane.ERROR_MESSAGE);
			}
	
		}
	} );


t1.addKeyListener(new KeyAdapter() {

	  public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
	    	SwingUtilities.getRootPane(exec_b).setDefaultButton(exec_b);
	    	
	    }
	  }
	});
t2.addKeyListener(new KeyAdapter() {

	  public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
	    	SwingUtilities.getRootPane(exec_b).setDefaultButton(exec_b);
	    }
	  }
	});
t3.addKeyListener(new KeyAdapter() {

	  public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
	    	SwingUtilities.getRootPane(exec_b).setDefaultButton(exec_b);
	    }
	  }
	});
t4.addKeyListener(new KeyAdapter() {

	  public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
	    	SwingUtilities.getRootPane(exec_b).setDefaultButton(exec_b);
	    }
	  }
	});

	
	PropertiesPanel.add(Descriptions,"width 700:700:700,wrap");
	PropertiesPanel.add(txtFields,"width 700:700:700");


	f.add(PropertiesPanel,"width 700:700:700,span 2");
	
	JPanel AnimatePanel = new JPanel(new GridBagLayout()); // AnimationPanel
	
	TitledBorder txtField2;
	txtField2 = BorderFactory.createTitledBorder("Conduct animation:");
	AnimatePanel.setBorder(txtField2);
	
	JButton anim_butt = new JButton("ANIMATE");
	AnimatePanel.add(anim_butt);
	
	f.add(AnimatePanel, "width 200:200:200, height 100:100:100,wrap,span 2");
	JLabel credits = new JLabel();
	credits.setText("--- Developed by Rafał Jan Trybus & Szymon Fiderkiewicz - Warsaw University of Technology 17'");
	//credits.setSize(new Dimension(700,50));
	
	credits.setVisible(true);
	//credits.setFont(new Font("Serif",0,15));
	
	f.add(credits, "width 900:900:900,span,gapx 205");

	f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	f.setVisible(true);
	
	
	
		anim_butt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame AnimationWindow = new AnimationWindow();
				AnimationWindow.setVisible(true);
				
			}
		});
		
	}

}
