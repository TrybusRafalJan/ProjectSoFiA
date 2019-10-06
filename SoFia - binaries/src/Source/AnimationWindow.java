package Source;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class AnimationWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	static CalculationEngine c = new CalculationEngine();
	
	public AnimationWindow() 
	{
		setTitle("Animation Window");
		setSize(605,680);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new MigLayout());
		setLocationRelativeTo(null);
		setResizable(false);

	JPanel apertures_panel = new JPanel(new MigLayout());
	
	TitledBorder apertures_border;
	apertures_border = BorderFactory.createTitledBorder("Aperture choice:");
	apertures_panel.setBorder(apertures_border);
	
	String[] apertures1 = {"RECTANGLE","CIRCLE"};
	JComboBox apertures_c1 = new JComboBox(apertures1);
	apertures_panel.add(apertures_c1,"width 180:180:180,wrap");
	add(apertures_panel, "wrap");
	
	JPanel PropertiesPanel1 = new JPanel(new MigLayout());
	
	TitledBorder txtField1;
	txtField1 = BorderFactory.createTitledBorder("Select which properity to animate:");
	PropertiesPanel1.setBorder(txtField1);
	
	JButton x_lenght = new JButton("Plot x_lenght");
	JButton y_lenght = new JButton("Plot y_lenght ");
	JButton lambda_lenght = new JButton("Plot lambda_lenght ");
	JButton z_lenght = new JButton("Plot z_lenght");
	JButton radious = new JButton("Plot radius");
	PropertiesPanel1.add(x_lenght); PropertiesPanel1.add(y_lenght); 
	PropertiesPanel1.add(z_lenght); PropertiesPanel1.add(lambda_lenght);
	
	JTextField start = new JTextField("1");
	JTextField stop = new JTextField("200");
	
	apertures_c1.addActionListener(new ActionListener(){

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(apertures_c1.getSelectedItem().equals("CIRCLE"))
			{	
				PropertiesPanel1.remove(x_lenght);
				PropertiesPanel1.remove(y_lenght);
				PropertiesPanel1.add(radious);
				PropertiesPanel1.add(z_lenght);
				PropertiesPanel1.add(lambda_lenght);
				PropertiesPanel1.repaint();
				PropertiesPanel1.validate();
			}
	
			if(apertures_c1.getSelectedItem().equals("RECTANGLE"))
			{
				PropertiesPanel1.remove(radious);
				PropertiesPanel1.add(x_lenght);
				PropertiesPanel1.add(y_lenght);
				PropertiesPanel1.add(z_lenght);
				PropertiesPanel1.add(lambda_lenght);
				PropertiesPanel1.repaint();
				PropertiesPanel1.validate();
			}
			
		}});

	JPanel Des = new JPanel(new MigLayout());
	JLabel d10 = new JLabel("Start value");
	JLabel d20 = new JLabel("Final value");
	Des.add(d10,"width 250:250:250");
	Des.add(d20,"width 250:250:250");
				
	JPanel txt = new JPanel(new MigLayout());

	txt.add(start,"width 200:200:200");
	txt.add(stop,"width 200:200:200");
	
	JPanel animate = new JPanel (new MigLayout());
	animate.setSize(315,315);
	JPanel sss = new JPanel(new MigLayout());
	TitledBorder txtField3;
	txtField3 = BorderFactory.createTitledBorder("Values of animation:");
	sss.setBorder(txtField3);
	
	JProgressBar xBar = new JProgressBar(0,100);
	TitledBorder txtField4;
	txtField4 = BorderFactory.createTitledBorder("Progress of calculations:");
	xBar.setBorder(txtField4);
	
	JProgressBar xBar1 = new JProgressBar(0,100);
	TitledBorder txtField5;
	txtField5 = BorderFactory.createTitledBorder("Progress of animation:");
	xBar1.setBorder(txtField5);

	sss.add(Des, "width 475:475:475 ,wrap"); sss.add(txt,"width 475:475:475" );

	add(sss,"width 475:475:475,wrap");
	add(PropertiesPanel1,"width 580:580:580,wrap");
	add(xBar,"width 580:580:580,wrap");
	add(animate,"width 310:310:310,height 310:310:310,center, wrap");
	add(xBar1,"width 580:580:580,wrap");
	
	x_lenght.addActionListener(new ActionListener()
	{
	
		public void actionPerformed(ActionEvent e) 
		{
			y_lenght.setEnabled(false);
			z_lenght.setEnabled(false);
			lambda_lenght.setEnabled(false);
			apertures_c1.setEnabled(false);
			repaint();
			if (start.getText().matches("[0-9]+") && stop.getText().matches("[0-9]+") &&
					((Integer.parseInt(stop.getText())) - (Integer.parseInt(start.getText()))) < 700)
			{
			JPanel[] pixel_anim = new Pixel[(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()))];
			
			SwingWorker <Void, Void > worker2 = new SwingWorker <Void,Void>() 
			{
				@Override
				protected Void doInBackground() throws Exception {
					
					
					// TODO Auto-generated method stub
					for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
					{
							pixel_anim[ii] = new Pixel(Integer.parseInt(start.getText())+ii,200,10,700,70);
							((CalculationEngine) c).Intensity_RECT((Pixel)pixel_anim[ii]);
							((Pixel) pixel_anim[ii]).Scale_log();
							int range;
							range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
							xBar.setValue(100*(ii+1)/range);
							xBar.setStringPainted(true);
					}
					
					return null;
				}
				
				protected void done()
				{
					
					SwingWorker <Void, Void > worker = new SwingWorker <Void,Void>() 
					{

						@Override
						protected Void doInBackground() throws Exception {
							// TODO Auto-generated method stub
							animate.repaint();
							animate.validate();
							validate();
							animate.setVisible(true);
							getContentPane();
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
								int range, a;
								range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
								a=100*(ii+1)/range;
								xBar1.setValue(a);
								xBar1.setStringPainted(true);
								if(a==100){JOptionPane.showMessageDialog(PropertiesPanel1, "Animation has been completed","Animation",JOptionPane.INFORMATION_MESSAGE);}
								
								if(ii == 0)
								{
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
									try {Thread.sleep(80);} 
									catch (InterruptedException e1) {e1.printStackTrace();}
								}
								else
								{
									try {Thread.sleep(80);} 
									catch (InterruptedException e1) {e1.printStackTrace();}
									
									animate.remove(pixel_anim[ii-1]);
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
								
								}
								
								animate.setVisible(true);
								animate.repaint();					
							}
						
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
									animate.remove(pixel_anim[ii]);
									pixel_anim[ii] = null;
									animate.validate();
							}
							
						
							animate.repaint();
							repaint();
							y_lenght.setEnabled(true);
							z_lenght.setEnabled(true);
							lambda_lenght.setEnabled(true);
							apertures_c1.setEnabled(true);
							xBar.setValue(0);
							xBar1.setValue(0);
							return null;
						}
						
						
					};
					worker.execute();
					System.out.println("Skończyłem");
					
				}
				
			};
			worker2.execute();
			
		}
				else
				{	
				JOptionPane.showMessageDialog(PropertiesPanel1, "Some imput field does not contain a number or given one is too big to conduct...","NaN",JOptionPane.ERROR_MESSAGE);
				}
		
		}
		
	
	});
	
y_lenght.addActionListener(new ActionListener()
{
	public void actionPerformed(ActionEvent e) 
	{
		x_lenght.setEnabled(false);
		z_lenght.setEnabled(false);
		lambda_lenght.setEnabled(false);
		apertures_c1.setEnabled(false);	
		if (start.getText().matches("[0-9]+") && stop.getText().matches("[0-9]+") &&
				((Integer.parseInt(stop.getText())) - (Integer.parseInt(start.getText()))) < 700)
		{
			JPanel[] pixel_anim = new Pixel[(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()))];
		
		SwingWorker <Void, Void > worker2 = new SwingWorker <Void,Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
				{
						int range;
						range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
						pixel_anim[ii] = new Pixel(220,Integer.parseInt(start.getText())+ii,10,700,70);
						((CalculationEngine) c).Intensity_RECT((Pixel)pixel_anim[ii]);
						((Pixel) pixel_anim[ii]).Scale_log();
						xBar.setValue(100*(ii+1)/range);
						xBar.setStringPainted(true);
				}
				
				return null;
			}
			
			protected void done()
			{
				SwingWorker <Void, Void > worker = new SwingWorker <Void,Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						// TODO Auto-generated method stub
						animate.repaint();
						animate.validate();
						validate();
						getContentPane();
						for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
						{
							int range, a;
							range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
							a=100*(ii+1)/range;
							xBar1.setValue(a);
							xBar1.setStringPainted(true);
							if(a==100){JOptionPane.showMessageDialog(PropertiesPanel1, "Animation has been completed","Animation",JOptionPane.INFORMATION_MESSAGE);}
							if(ii == 0)
							{
								animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
								animate.validate();
								try {Thread.sleep(80);}
								catch (InterruptedException e1) {e1.printStackTrace();}
							}
							else
							{
								try {Thread.sleep(80);} 
								catch (InterruptedException e1) {e1.printStackTrace();}
								animate.remove(pixel_anim[ii-1]);
								animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
								animate.validate();
			
							}
		
							animate.setVisible(true);
							animate.repaint();
							
						}
					
						for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
						{
					
								animate.remove(pixel_anim[ii]);
								pixel_anim[ii] = null;
								animate.validate();
								
						}
						animate.repaint();
						repaint();
						x_lenght.setEnabled(true);
						z_lenght.setEnabled(true);
						lambda_lenght.setEnabled(true);
						apertures_c1.setEnabled(true);
						xBar.setValue(0);
						xBar1.setValue(0);
						return null;
					}
					
					
				};
				worker.execute();

				System.out.println("Skończyłem");	
			}
			
		};
		worker2.execute();
		
		}
		else
		{	
			JOptionPane.showMessageDialog(PropertiesPanel1, "Some imput field does not contain a number or given one is too big to conduct...","NaN",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	});
	lambda_lenght.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{	
			x_lenght.setEnabled(false);
			y_lenght.setEnabled(false);
			z_lenght.setEnabled(false);
			radious.setEnabled(false);
			apertures_c1.setEnabled(false);
			if (start.getText().matches("[0-9]+") && stop.getText().matches("[0-9]+")
					&& ((Integer.parseInt(stop.getText())) - (Integer.parseInt(start.getText()))) < 700)
			{
			JPanel[] pixel_anim = new Pixel[(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()))];
			
			SwingWorker <Void, Void > worker2 = new SwingWorker <Void,Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					// TODO Auto-generated method stub
					for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
					{
							int range;
							range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
							pixel_anim[ii] = new Pixel(220,200,10,Integer.parseInt(start.getText())+ii,70);
							if(apertures_c1.getSelectedItem().equals("RECTANGLE"))
							((CalculationEngine) c).Intensity_RECT((Pixel)pixel_anim[ii]);
							else
								((CalculationEngine) c).Intensity_CIRC((Pixel)pixel_anim[ii]);	
							((Pixel) pixel_anim[ii]).Scale_log();
							xBar.setValue(100*(ii+1)/range);
							xBar.setStringPainted(true);
					}
					
					return null;
				}
				
				protected void done()
				{
					SwingWorker <Void, Void > worker = new SwingWorker <Void,Void>() {

						@Override
						protected Void doInBackground() throws Exception {
							// TODO Auto-generated method stub
							animate.repaint();
							animate.validate();
							validate();
							getContentPane();
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
								int range, a;
								range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
								a=100*(ii+1)/range;
								xBar1.setValue(a);
								xBar1.setStringPainted(true);
								if(a==100){JOptionPane.showMessageDialog(PropertiesPanel1, "Animation has been completed","Animation",JOptionPane.INFORMATION_MESSAGE);}
								if(ii == 0)
								{
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
									try {Thread.sleep(80);}
									catch (InterruptedException e1) {e1.printStackTrace();}
								}
								else
								{
									try {Thread.sleep(80);} 
									catch (InterruptedException e1) {e1.printStackTrace();}
									animate.remove(pixel_anim[ii-1]);
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
								
								}
								animate.setVisible(true);
								animate.repaint();
								
							}
						
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
						
									animate.remove(pixel_anim[ii]);
									pixel_anim[ii] = null;
									animate.validate();
									
								}
							
						
							animate.repaint();
							repaint();
							x_lenght.setEnabled(true);
							radious.setEnabled(true);
							y_lenght.setEnabled(true);
							z_lenght.setEnabled(true);
							apertures_c1.setEnabled(true);
							xBar.setValue(0);
							xBar1.setValue(0);
							return null;
						}
						
						
					};
					worker.execute();

					System.out.println("Skończyłem");	
				}
				
			};
			worker2.execute();
				
		}
		else
			{	
			JOptionPane.showMessageDialog(PropertiesPanel1, "Some imput field does not contain a number or given one is too big to conduct...","NaN",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	});
	
	z_lenght.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			x_lenght.setEnabled(false);
			y_lenght.setEnabled(false);
			lambda_lenght.setEnabled(false);
			radious.setEnabled(false);
			apertures_c1.setEnabled(false);
			if (start.getText().matches("[0-9]+") && stop.getText().matches("[0-9]+") &&
					((Integer.parseInt(stop.getText())) - (Integer.parseInt(start.getText()))) < 700)
			{
			JPanel[] pixel_anim = new Pixel[(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()))];
			
			SwingWorker <Void, Void > worker2 = new SwingWorker <Void,Void>() {

				@Override
				protected Void doInBackground() throws Exception {
					// TODO Auto-generated method stub
					for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
					{
							int range;
							range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
							pixel_anim[ii] = new Pixel(220,200,Integer.parseInt(start.getText())+ii,700,70);
							if(apertures_c1.getSelectedItem().equals("RECTANGLE"))
							((CalculationEngine) c).Intensity_RECT((Pixel)pixel_anim[ii]);
							else
								((CalculationEngine) c).Intensity_CIRC((Pixel)pixel_anim[ii]);	
							((Pixel) pixel_anim[ii]).Scale_log();
							xBar.setValue(100*(ii+1)/range);
							xBar.setStringPainted(true);
					}
					
					return null;
				}
				
				protected void done()
				{
					SwingWorker <Void, Void > worker = new SwingWorker <Void,Void>() {

						@Override
						protected Void doInBackground() throws Exception {
							// TODO Auto-generated method stub
							animate.repaint();
							animate.validate();
							validate();
							getContentPane();
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
								int range, a;
								range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
								a=100*(ii+1)/range;
								xBar1.setValue(a);
								xBar1.setStringPainted(true);
								if(a==100){JOptionPane.showMessageDialog(PropertiesPanel1, "Animation has been completed","Animation",JOptionPane.INFORMATION_MESSAGE);}
								if(ii == 0)
								{
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
									try {Thread.sleep(140);} 
									catch (InterruptedException e1) {e1.printStackTrace();}
								}
								else
								{
									try {Thread.sleep(140);}
									catch (InterruptedException e1) {e1.printStackTrace();}	
									animate.remove(pixel_anim[ii-1]);
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
								
								}
								animate.setVisible(true);
								animate.repaint();
								
							}
						
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
						
								animate.remove(pixel_anim[ii]);
								pixel_anim[ii] = null;
								animate.validate();
									
							}
							animate.repaint();
							repaint();
							x_lenght.setEnabled(true);
							y_lenght.setEnabled(true);
							lambda_lenght.setEnabled(true);
							radious.setEnabled(true);
							apertures_c1.setEnabled(true);
							xBar.setValue(0);
							xBar1.setValue(0);
							return null;
						}
						
						
					};
					worker.execute();

					System.out.println("Skończyłem");	
				}
				
			};
			worker2.execute();
				
			}
			else
			{	
			JOptionPane.showMessageDialog(PropertiesPanel1, "Some imput field does not contain a number or given one is too big to conduct...","NaN",JOptionPane.ERROR_MESSAGE);
			}
		}
	
	});
	
	radious.addActionListener(new ActionListener()
	{
	
		public void actionPerformed(ActionEvent e) 
		{
		
			z_lenght.setEnabled(false);
			lambda_lenght.setEnabled(false);
			apertures_c1.setEnabled(false);
			if (start.getText().matches("[0-9]+") && stop.getText().matches("[0-9]+")&&
					((Integer.parseInt(stop.getText())) - (Integer.parseInt(start.getText()))) < 700)
			{
			JPanel[] pixel_anim = new Pixel[(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()))];
			
			SwingWorker <Void, Void > worker2 = new SwingWorker <Void,Void>() {

				@Override
				protected Void doInBackground() throws Exception {
					// TODO Auto-generated method stub
					for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
					{

							pixel_anim[ii] = new Pixel(220,200,10,700,Integer.parseInt(start.getText())+ii);
							int range;
							range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());

							((CalculationEngine) c).Intensity_CIRC((Pixel)pixel_anim[ii]);	
							((Pixel) pixel_anim[ii]).Scale_log();
							xBar.setValue(100*(ii+1)/range);
							xBar.setStringPainted(true);
					}
					
					return null;
				}
				
				protected void done()
				{
					SwingWorker <Void, Void > worker = new SwingWorker <Void,Void>() {

						@Override
						protected Void doInBackground() throws Exception {
							// TODO Auto-generated method stub
							animate.repaint();
							animate.validate();
							validate();
							getContentPane();
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
								int range, a;
								range = Integer.parseInt(stop.getText())-Integer.parseInt(start.getText());
								a=100*(ii+1)/range;
								xBar1.setValue(a);
								xBar1.setStringPainted(true);
								if(a==100){JOptionPane.showMessageDialog(PropertiesPanel1, "Animation has been completed","Animation",JOptionPane.INFORMATION_MESSAGE);}
								if(ii == 0)
								{
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
									try {Thread.sleep(80);} 
									catch (InterruptedException e1) {e1.printStackTrace();}
								}
								else
								{
									try {Thread.sleep(80);} 
									catch (InterruptedException e1) {e1.printStackTrace();}
									animate.remove(pixel_anim[ii-1]);
									animate.add(pixel_anim[ii],"width 300:300:300,height 300:300:300");
									animate.validate();
								}
							
								animate.setVisible(true);
								animate.repaint();
								
							}
						
							for(int ii=0;ii<(int)(Integer.parseInt(stop.getText())-Integer.parseInt(start.getText()));ii++)
							{
						
									animate.remove(pixel_anim[ii]);
									pixel_anim[ii] = null;
									animate.validate();
									
								}
							
							animate.repaint();
							repaint();
							z_lenght.setEnabled(true);
							lambda_lenght.setEnabled(true);
							apertures_c1.setEnabled(true);
							xBar.setValue(0);
							xBar1.setValue(0);
							return null;
						}
						
					};
					worker.execute();

					System.out.println("Skończyłem");	
				}
				
			};
			worker2.execute();
				
		}
		else
		{	
		JOptionPane.showMessageDialog(PropertiesPanel1, "Some imput field does not contain a number or given one is too big to conduct...","NaN",JOptionPane.ERROR_MESSAGE);
		}
		}
	
	});
	
	}
	
}
