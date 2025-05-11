import java.lang.Math;
public class Planet {
   static final double G = 6.67e-11;
   public double xxPos;
   public double yyPos;
   public double xxVel;
   public double yyVel;
   public double mass;
   public String imgFileName;

   public Planet(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
   }
   public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
   }
   public double calcDistance(Planet p){
      double r = Math.sqrt((xxPos-p.xxPos) * (xxPos-p.xxPos) + (yyPos-p.yyPos) * (yyPos-p.yyPos));
      return r;
   }
   public double calcForceExertedBy(Planet p){
      double r = calcDistance(p);
      double force  = G *mass*p.mass/(r*r) ;
      return force;
   }
   public double calcForceExertedByX(Planet p){
      double dX  = p.xxPos - xxPos;
      double r = calcDistance(p);
      double forceExertedByX = calcForceExertedBy(p) * dX/r;
      return forceExertedByX;
   }
   public double calcForceExertedByY(Planet p){
      double dY  = p.yyPos - yyPos;
      double r = calcDistance(p);
      double forceExertedByY = calcForceExertedBy(p) * dY/r;
      return forceExertedByY;
   }
   public double calcNetForceExertedByX(Planet[] planets){
      double netForceExertedByX = 0;
      for(Planet p:planets){
         if(!this.equals(p)){
            netForceExertedByX += calcForceExertedByX(p);
         }
      }
      return netForceExertedByX;
   }
   public double calcNetForceExertedByY(Planet[] planets){
      double netForceExertedByY = 0;
      for(Planet p:planets){
         if(!this.equals(p)){
            netForceExertedByY += calcForceExertedByY(p);
         }
      }
      return netForceExertedByY;
   }
   public void update(double time, double forceX, double forceY){
      double aX = forceX/mass;
      double aY = forceY/mass;
      double newVx = xxVel + aX*time;
      double newVy = yyVel + aY*time;
      double newPx = xxPos + newVx*time;
      double newPy = yyPos + newVy*time;
      xxVel = newVx;
      yyVel = newVy;
      xxPos = newPx;
      yyPos = newPy;
   }
   public void draw(){
      String fullImgFileName = "images/" + imgFileName;
      StdDraw.picture(xxPos, yyPos, fullImgFileName);
      StdDraw.show();
   }
}