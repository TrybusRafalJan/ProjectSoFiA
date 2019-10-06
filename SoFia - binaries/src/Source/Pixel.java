package Source;




import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.*;
import java.awt.Graphics2D;
import java.awt.PageAttributes;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pixel extends JPanel {

double lx;
double ly;
double R;
public double lambda;
double z;
double Intensity[][];
double xCenter; 
double yCenter;
int center=150;
int min;
int max;
int Gcolor[][];

//Default Constructor
	public Pixel() {}
//Main Constructor
	
	public Pixel(double xLenght,double yLenght,double zLenght,double lambdaLenght,double er) 
	{
		Intensity = new double[300][300];
		lx = xLenght;
		ly = yLenght;
		z = zLenght;
		lambda = lambdaLenght;
		int center =150;
		Gcolor = new int[300][300];
		R = er;
		
		//Intenisty Calculation // -300 in code stands for centralizing the picture half of the display
		
		for(int i=0;i<300;i++) 
		{
			
			for(int j=0;j<300;j++) 
			{
					Intensity[i][j] = 0;
			}
		}
		

		 //Color calculation

		for(int i=0;i<300;i++) 
		{
			
			for(int j=0;j<300;j++) 
			{	
					Gcolor[i][j] = 0;	
			}
		}
	
	}
	
	void Scale_log()
	{
		
		
		for(int i=0;i<300;i++) 
		{
			
			for(int j=0;j<300;j++) 
			{
				if(Intensity[i][j]==0)
					Gcolor[i][j] = 0;
				else
				{
				Gcolor[i][j] = (int) Math.abs((255*(1/Math.log(Intensity[i][j]))));
				
				}
			}
		}
	}
	
	void Scale_lin()
	{
		
		for(int i=0;i<300;i++) 
		{
			
			for(int j=0;j<300;j++) 
			{
	
				Gcolor[i][j] = (int) Math.abs((255*30*(Intensity[i][j])));
			}
		}
	}
	 // Overriden painting function
		@Override
		public void paint(Graphics g) {
	        super.paint(g);
	        

	        for(int x = 0; x < 300; x++) {
	            for(int y = 0; y < 300; y++) {
	            	if(Gcolor[x][y]<0)
	            	{
	            		g.setColor(new Color(0,0,0));
	            		g.drawLine(x, y, x, y);
	            	}
	            	else if (Gcolor[x][y]>255)
	            	{
	            		g.setColor(new Color(255,255,255));
	            		g.drawLine(x, y, x, y);
	            	}
	            	else
	            	{
	                g.setColor(new Color(Gcolor[x][y],Gcolor[x][y],Gcolor[x][y]));
	                g.drawLine(x, y, x, y);
	            	}
	            
	        }
	    }

		}
		
		double getlx() { return lx;}	

}


