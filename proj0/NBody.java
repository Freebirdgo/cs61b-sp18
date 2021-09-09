public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int N = in.readInt();
		Planet[] Planets = new Planet[N];
		double s = in.readDouble();
		for(int i = 0; i < N; i += 1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			Planets[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		
		return Planets;
	}
	
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = NBody.readPlanets(filename);
		int n = planets.length;
		double radius = NBody.readRadius(filename);
		//
		StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering();
		double t = 0.0;
		
		
		while(t < T){
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			
			for(int i = 0; i<n; i+=1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i<n; i+=1){
				planets[i].update(t, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			for(int i = 0; i< n; i += 1){
				planets[i].draw();
			}
			
			StdDraw.show();
			StdDraw.pause(10);
			
			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
							planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
							planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

		
		
		//StdDraw.show();
	//	StdDraw.pause(2000);
		
	}
}