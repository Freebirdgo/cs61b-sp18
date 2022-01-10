public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private final static double G = 6.67e-11;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
					this.xxPos = xP;
					this.yyPos = yP;
					this.xxVel = xV;
					this.yyVel = yV;
					this.mass = m;
					this.imgFileName = img;
				}
				
	public Planet(Planet b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
		
	}
	
	public double calcDistance(Planet p){
		double dX, dY, result;
		dX = this.xxPos - p.xxPos;
		dY = this.yyPos - p.yyPos;
		result  = Math.sqrt(dX*dX + dY*dY);
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
		Fx = force * dX / r;
		return Fx;
		
	}
	
	public double calcForceExertedByY(Planet p){
		double dY, Fy, r, force;
		force = this.calcForceExertedBy(p);
		r = this.calcDistance(p);
		dY = p.yyPos - this.yyPos;
		Fy = force * dY / r;   // there might be sign issues
		return Fy;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double Fnetx = 0.0;
		for(int i=0; i < allPlanets.length; i += 1){
			if (!this.equals(allPlanets[i])){
				Fnetx += this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return Fnetx;
		
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double Fnety = 0.0;
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
		vX = this.xxVel + aX*time;
		vY = this.yyVel + aY*time;
		pX = this.xxPos + vX*time;
		pY = this.yyPos + vY*time;
		this.xxVel = vX;
		this.yyVel = vY;
		this.xxPos = pX;
		this.yyPos = pY;
	}
	
	public  void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
		
	}

}