package Source;



import static java.lang.Math.PI;

import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.cos;
import static java.lang.Math.abs;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

import javax.swing.JPanel;

public class CalculationEngine   {

	double Intensity[][];
	
	public CalculationEngine() {
		// TODO Auto-generated constructor stub
		
		Intensity = new double[300][300];	
	}
	
	static double bessj1( double x )
	/*------------------------------------------------------------*/
	/* PURPOSE: Evaluate Bessel function of first kind and order  */
	/*          1 at input x                                      */
	/*------------------------------------------------------------*/
	{
	   double ax,z;
	   double xx,y,ans,ans1,ans2;

	   if ((ax=abs(x)) < 8.0) 
	   {
	      y=x*x;
	      ans1=x*(72362614232.0+y*(-7895059235.0+y*(242396853.1
	         +y*(-2972611.439+y*(15704.48260+y*(-30.16036606))))));
	      ans2=144725228442.0+y*(2300535178.0+y*(18583304.74
	         +y*(99447.43394+y*(376.9991397+y*1.0))));
	      ans=ans1/ans2;
	   } 
	   else 
	   {
	      z=8.0/ax;
	      y=z*z;
	      xx=ax-2.356194491;
	      ans1=1.0+y*(0.183105e-2+y*(-0.3516396496e-4
	         +y*(0.2457520174e-5+y*(-0.240337019e-6))));
	      ans2=0.04687499995+y*(-0.2002690873e-3
	         +y*(0.8449199096e-5+y*(-0.88228987e-6
	         +y*0.105787412e-6)));
	      ans=sqrt(0.636619772/ax)*(cos(xx)*ans1-z*sin(xx)*ans2);
	      if (x < 0.0) ans = -ans;
	   }
	   return ans;
	}
	
	void Intensity_CIRC(Pixel p)
	{
		
		double k = 2*PI/p.lambda;
		double r;
		
		for(int i=0;i<300;i++) 
		{
			
			for(int j=0;j<300;j++) 
			{
				r = sqrt((j-p.center)*(j-p.center)+(i-p.center)*(i-p.center));
				
				if(i-p.xCenter==0 && j-p.yCenter==0)
					p.Intensity[i][j] = 1;
				
				else if (i-p.xCenter==0)
					p.Intensity[i][j]= pow(2*( bessj1(k*p.R*(j-p.center)/p.z)/(k*p.R*(j-p.center)/p.z) ),2 ) ;
					
				else if(j-p.yCenter==0)
					p.Intensity[i][j]= pow(2* ( bessj1(k*p.R*(i-p.center)/p.z)/(k*p.R*(i-p.center))/p.z),2 ) ;
					
				else
				{
					p.Intensity[i][j]=  pow(2* ( bessj1(k*p.R*r/p.z)/(k*p.R*r/p.z) ),2 ) ;
				
				}
			
			}
			
		}
	}
		
	void Intensity_RECT(Pixel p)
	{
		for(int i=0;i<300;i++) 
		{
			
			for(int j=0;j<300;j++) 
			{
				if(i-p.xCenter==0 && j-p.yCenter==0)
					p.Intensity[i][j] = 1;
				else if (i-p.xCenter==0)
					p.Intensity[i][j]= pow(sin (PI*p.ly* ((j-p.center)) / (p.lambda* p.z)) /(PI*p.ly*((j-p.center)) / (p.lambda*p.z)) , 2 ) ;
				else if(j-p.yCenter==0)
					p.Intensity[i][j]=pow(sin (PI*p.lx*((i-p.center)) / (p.lambda* p.z))/(PI*p.lx* ((i-p.center)) / (p.lambda* p.z)) , 2 );
				else
					p.Intensity[i][j]=pow(sin (PI*p.lx*((i-p.center))/(p.lambda*p.z)) /(PI*p.lx*((i-p.center)) / (p.lambda* p.z)) , 2 )* 
					pow(sin (PI*p.ly* ((j-p.center))/(p.lambda* p.z)) /(PI*p.ly* ((j-p.center)) / (p.lambda* p.z)) , 2 ) ;
					
			}
		}
	}
	
	
	void SetLambda(Pixel p,double lambdaLenght)
	{
		p.lambda = lambdaLenght; 
	}
	
	void SetLx(Pixel p,double xLenght)
	{
		p.lx = xLenght; 
	}
	
	void SetLy(Pixel p,double yLenght)
	{
		p.ly = yLenght; 
	}
	
	void SetZ(Pixel p,double zLenght)
	{
		p.z = zLenght; 
	}
	
	void SetR(Pixel p,double rLenght)
	{
		p.R = rLenght; 
	}

}

