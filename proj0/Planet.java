public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	final static double G = 6.67e-11;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
					xxPos = xP;
					yyPos = yP;
					xxVel = xV;
					yyVel = yV;
					mass = m;
					imgFileName = img;
				}
				
	public Planet(Planet b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
		
	}
	
	public double calcDistance(Planet p){
		double dX, dY, result;
		dX = Math.abs(this.xxPos - p.xxPos);
		dY = Math.abs(this.yyPos - p.yyPos);
		result  = Math.pow(dX*dX + dY*dY, 0.5);
		return result;
	}
	
	public double calcForceExertedBy(Planet p){
		double force, r;
		r = this.calcDistance(p);
		force = (G * this.mass * p.mass) / (r*r);
		return force;
	}
	
	public double calcForceExertedByX(Planet p){
		double dX, Fx, r, force;
		force = this.calcForceExertedBy(p);
		r = this.calcDistance(p);
		dX = p.xxPos - this.xxPos;
		Fx = (force * dX) / r;
		return Fx;
		
	}
	
	public double calcForceExertedByY(Planet p){
		double dY, Fy, r, force;
		force = this.calcForceExertedBy(p);
		r = this.calcDistance(p);
		dY = p.yyPos - this.yyPos;
		Fy = (force * dY) / r;   // there might be sign issues
		return Fy;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double Fnetx = 0;
		for(int i=0; i < allPlanets.length; i += 1){
			if (!this.equals(allPlanets[i])){
				Fnetx += this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return Fnetx;
		
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double Fnety = 0;
		for(int i=0; i < allPlanets.length; i += 1){
			if (!this.equals(allPlanets[i])){
				Fnety += this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return Fnety;
		
	}
	
	public void update(double time, double fX, double fY){
		double aX, aY, vX, vY, pX, pY;
		aX = fX / this.mass;
		aY = fY / this.mass;
		vX = xxVel + aX*time;
		vY = yyVel + aY*time;
		pX = xxPos + vX*time;
		pY = yyPos + vY*time;
		xxVel = vX;
		yyVel = vY;
		xxPos = pX;
		yyPos = pY;
	}
	
	public  void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
		
	}

}