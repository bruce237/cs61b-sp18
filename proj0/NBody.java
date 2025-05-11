public class NBody {
    public static double readRadius(String fileName) {
        // 1.根据传入参数找到file
        In in = new In(fileName);
        // 2.怎么拿到半径
        int firstItemInFile = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double r = in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            planets[i] = planet;
        }
        return planets;

    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        Planet[] planets = readPlanets(fileName);
        double r = readRadius(fileName);

        StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);

        String imageToDraw = "images/starfield.jpg";
        StdDraw.picture(0, 0, imageToDraw);
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.enableDoubleBuffering();
        for (int i = 0; i < T + 1; i += dt) {
            int num = planets.length;
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int j = 0; j < num; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int k = 0; k < num; k++) {
                planets[k].update(dt, xForces[k], yForces[k]);
            }
            StdDraw.picture(0, 0, imageToDraw);
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (Planet p:planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p.xxPos, p.yyPos, p.xxVel,
                    p.yyVel, p.mass, p.imgFileName);
        }
    }
}