public class Testrp{
    public static void main(String[] args){
        String filename = "./data/planets.txt";
        Planet[] planets = NBody.readPlanets(filename);
        for(Planet p : planets){
            System.out.println(p.xxPos+" "+p.yyPos+" "+p.xxVel+" "+p.yyVel+" "+p.mass+" "+p.imgFileName);
        }
    }
}