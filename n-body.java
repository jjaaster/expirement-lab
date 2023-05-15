
package somemoregravity;

public class SomeMoreGravity
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Frame frame =  new Frame();
        frame.setVisible(true);
        frame.loop();
    }
}



package somemoregravity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Body
{
    private double xPos, yPos, mass, xVel, yVel, xFor, yFor;
    private static final double GRAVITY = 6.673e-11;
    private Color c;
    private int radius;
    private final int BOTTOM = 870;
    private final int LEFT_WALL = 0;
    private final int RIGHT_WALL = 1093;
    private final int TOP = 0;
    private int counter;
    private Shape shape;
    private boolean deleteMe;

    public Body(double xPos, double yPos, double mass, double xVel, double yVel, Color c)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.mass = mass;
        this.xVel = xVel;
        this.yVel = yVel;
        this.c = c;
        this.radius = this.calcRadius(this.mass);
        
        counter = 0;
        deleteMe = false;
    }
    
    public double getAngle()
    {
        double result = ((Math.atan2(yVel, xVel)) / (Math.PI/180));
        
        if(result < 0)
        {
            return 360 + result;
        }
        return result;
    }
    
    public double distTo(Body otherBody)
    {
        double dx = xPos - otherBody.getxPos();
        double dy = yPos - otherBody.getyPos();
        return Math.sqrt((dx * dx) + (dy * dy));
    }
    
    public void drawBody(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(c);
        shape = new Ellipse2D.Float((float)xPos - radius,(float) yPos - radius, radius, radius);
        
        g2d.fill(shape);
        g2d.dispose();
    }
    
    public double getArea()
    {
        return Math.PI * radius * radius;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Area: %f ", this.getArea()));
        sb.append(String.format("Angle: %f ", this.getAngle()));
        sb.append(String.format("x: %f, y: %f\n", xPos, yPos));
        return sb.toString();
    }
    
    public void update()
    {
        counter++;
        xVel += counter * xFor / mass;
        yVel += counter * yFor / mass;
        
        xPos -= counter * xVel;
        yPos -= counter * yVel;
    }
    
    public void resetForce()
    {
        xFor = 0;
        yFor = 0;
    }
    
    public void moveTo(double x, double y)
    {
        xFor = x - xPos;
        yFor = y - yPos;
    }
    
    public boolean isOut()
    {
        if(xPos > RIGHT_WALL || xPos < LEFT_WALL || yPos > BOTTOM || yPos < TOP)
        {
            return true;
        }
        return false;
    }
    
    public void addForce(Body otherBody)
    {
        double soften = 143;
        double dx = xPos - otherBody.getxPos();
        double dy = yPos - otherBody.getyPos();
        
        double dist = Math.sqrt((dx * dx) + (dy * dy));
        
        double force = (GRAVITY * this.mass * otherBody.getMass()) / (dist * dist + soften * soften);
        
        xFor += (force * dx / dist);
        yFor += force * dy / dist;
    }
    
    public void collide(Body otherBody)
    {   
        this.xVel = (((this.mass * this.xVel) + (otherBody.mass * otherBody.xVel)) / ( this.mass + otherBody.mass));
        this.yVel = (((this.mass * this.yVel) + (otherBody.mass * otherBody.yVel)) / ( this.mass + otherBody.mass));
        
        System.out.printf("Old mass: %.2f Old Radius: %d\n", this.mass, this.radius);
        this.mass += otherBody.mass;
        if(this.radius < 20)
        {
            this.radius = this.calcRadius(this.mass);
        }
        System.out.printf("New mass: %.2f New Radius: %d\n", this.mass, this.radius);
        System.out.printf("xVel: %f yVel: %f\n", this.xVel, this.yVel);
    }
    
    public void deleteThis()
    {
        System.out.println(this.toString());
        this.deleteMe = true;
    }
    
    public final int calcRadius(double mass)
    {
        int result = 0;
        
        if(mass < 100000)
        {
            result = 4;
        }else if(mass < 500000)
        {
            result = 6;
        }else if(mass < 1000000)
        {
            result = 8;
        }else if(mass < 5000000)
        {
            result = 10;
        }else if(mass < 10000000)
        {
            result = 12;
        }else if(mass < 20000000)
        {
            result = 14;
        }else
        {
            result = 16;
        }
        
        return result;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and setter crap">
    public void setDeleteMe(boolean deleteMe)
    {
        this.deleteMe = deleteMe;
    }
    
    public boolean getDeleteMe()
    {
        return deleteMe;
    }
    
    public void setShape(Shape shape)
    {
        this.shape = shape;
    }
    
    public Shape getShape()
    {
        return this.shape;
    }
    
    public double getxPos()
    {
        return xPos;
    }
    
    public void setxPos(double xPos)
    {
        this.xPos = xPos;
    }
    
    public double getyPos()
    {
        return yPos;
    }
    
    public void setyPos(double yPos)
    {
        this.yPos = yPos;
    }
    
    public double getMass()
    {
        return mass;
    }
    
    public void setMass(double mass)
    {
        this.mass = mass;
    }
    
    public double getxVel()
    {
        return xVel;
    }
    
    public void setxVel(double xVel)
    {
        this.xVel = xVel;
    }
    
    public double getyVel()
    {
        return yVel;
    }
    
    public void setyVel(double yVel)
    {
        this.yVel = yVel;
    }
    
    public double getxFor()
    {
        return xFor;
    }
    
    public void setxFor(double xFor)
    {
        this.xFor = xFor;
    }
    
    public double getyFor()
    {
        return yFor;
    }
    
    public void setyFor(double yFor)
    {
        this.yFor = yFor;
    }
    
    public Color getC()
    {
        return c;
    }
    
    public void setC(Color c)
    {
        this.c = c;
    }
    
    public int getRadius()
    {
        return radius;
    }
    
    public void setRadius(int radius)
    {
        this.radius = radius;
    }
    //</editor-fold>
}


package somemoregravity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.JFrame;

public class Frame extends JFrame
{

    private Canvas canvas;
    private BufferStrategy buffer;
    private Body b;
    private Space space;
    private boolean switcher;
    private int i = 0;

    public Frame()
    {
        super();
        switcher = false;
        space = new Space();
        final Random rand = new Random();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1100, 900));
        this.setResizable(false);
        this.setIgnoreRepaint(true);

        canvas = new Canvas();

        this.getContentPane().add(canvas);
        this.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        canvas.setBackground(Color.white);
        this.getContentPane().add(canvas);
        this.setVisible(true);

        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

        canvas.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getButton() == 1)
                {
                    for (int j = 0; j < 200; j++)
                    {
                        space.addBody(0, 0);
                    }
                } else if (e.getButton() == 3)
                {
                    if (switcher)
                    {
                        switcher = false;
                    } else
                    {
                        switcher = true;
                        i = 0;
                    }
                    System.out.println(switcher);
                } else
                {
                    space.delAll();
                    System.out.println(space.getSize());
                }
            }
        });
    }

    public void render()
    {
        do
        {
            do
            {
                Graphics2D g2d = (Graphics2D) buffer.getDrawGraphics();
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                renderParticles(g2d);
                g2d.dispose();
            } while (buffer.contentsRestored());
            buffer.show();
        } while (buffer.contentsLost());

    }

    public void renderParticles(Graphics2D g2d)
    {
        if (space.notEmpty())
        {
            space.drawAll(g2d);
        }
    }

    public void loop()
    {

        while (true)
        {


            if (switcher)
            {
                space.addForces();
            }
            space.update();
            render();

            space.resetAll();
            space.checkCollisionTwo();
            try
            {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e)
            {
                System.out.println("Interrupted Exception " + e);
            }
            space.deleteCrap();
        }
    }
}



package somemoregravity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Space
{
    private List safeBodies;
    private double largeX = 0, largeY = 0;
    
    public Space()
    {
        safeBodies = Collections.synchronizedList(new ArrayList<Body>());
        safeBodies.clear();
    }
    
    public void drawAll(Graphics2D g2d)
    {
        if(!this.safeBodies.isEmpty())
        {
            synchronized (safeBodies)
            {
                Iterator<Body> it = safeBodies.iterator();
                while(it.hasNext())
                {
                    Body body = it.next();
                    if(!body.getDeleteMe())
                    {
                        body.drawBody(g2d);
                        g2d.setColor(Color.red);
                        g2d.drawString(String.valueOf(largeX), 10, 10);
                        g2d.drawString(String.valueOf(largeY), 10, 30);
                    }
                }
            }
        }
    }
    
    public void addBody(int x, int y)
    {
        Random rand = new Random();
        synchronized (safeBodies)
        {
            safeBodies.add(new Body(rand.nextInt(1100), rand.nextInt(900), 10000, 0, 0, Color.WHITE));
        }
    }
    
    public boolean notEmpty()
    {
        if(safeBodies.isEmpty())
        {
            return false;
        }
        return true;
    }
    
    public void update()
    {
        if(!this.safeBodies.isEmpty())
        {
            synchronized (safeBodies)
            {
                Iterator<Body> it = safeBodies.iterator();
                while(it.hasNext())
                {
                    Body body = it.next();
                    if(!body.getDeleteMe())
                    {
                        body.update();
                        if(this.largeX <  body.getxVel())
                        {
                            this.largeX = body.getxVel();
                        }
                        if(this.largeY <  body.getyVel())
                        {
                            this.largeY = body.getyVel();
                        }
                    }
                    
                }
            }
        }
    }
    
    public void addForces()
    {
        synchronized(safeBodies)
        {
            for(Object a : safeBodies)
            {
                Body bodyOne = (Body) a;
                if(!bodyOne.getDeleteMe())
                {
                    for(Object b : safeBodies)
                    {
                        Body bodyTwo = (Body) b;
                        if(!bodyOne.equals(bodyTwo) && !bodyTwo.getDeleteMe())
                        {
                            bodyOne.addForce(bodyTwo);
                        }
                    }
                }
            }
        }
    }
    
    public int getSize()
    {
        return this.safeBodies.size();
    }
    
    public void delAll()
    {
        synchronized(safeBodies)
        {
            for (int i = 0; i < safeBodies.size(); i++)
            {
                safeBodies.remove(i);
            }
        }
    }
    
    public void resetAll()
    {
        synchronized(safeBodies)
        {
            for(Object a : safeBodies)
            {
                Body bodyOne = (Body) a;
                bodyOne.resetForce();
            }
        }
    }
    
    public void checkCollisionTwo()
    {
        Body bodyOne, bodyTwo;
        synchronized(safeBodies)
        {
            Iterator<Body> itOne = safeBodies.iterator();
            Iterator<Body> itTwo = safeBodies.iterator();
            
            for (int i = 0; i < safeBodies.size(); i++)
            {
                bodyOne = (Body) safeBodies.get(i);
                for (int j = 0; j < safeBodies.size(); j++)
                {
                    bodyTwo = (Body) safeBodies.get(j);
                    if(!bodyOne.equals(bodyTwo) && !bodyOne.getDeleteMe())
                    {
                        if((bodyOne.distTo(bodyTwo) < (bodyOne.getRadius() + bodyTwo.getRadius()) - 2) 
                                && bodyOne.getMass() > 0 
                                && bodyTwo.getMass() > 0)
                        {
                            if(bodyOne.getMass() > bodyTwo.getMass())
                            {
                                bodyOne.collide(bodyTwo);
                                bodyTwo.deleteThis();
                            }else
                            {
                                bodyTwo.collide(bodyOne);
                                bodyOne.deleteThis();
                            }
                        }
                    }
                }
            }
            
//            while(itOne.hasNext())
//            {
//                bodyOne = itOne.next();
//                while(itTwo.hasNext())
//                {
//                    bodyTwo = itTwo.next();
//                    if(!bodyOne.equals(bodyTwo))
//                    {
//                        if((bodyOne.distTo(bodyTwo) < bodyOne.getRadius() + bodyTwo.getRadius()) 
//                                && bodyOne.getMass() > 0 
//                                && bodyTwo.getMass() > 0)
//                        {
//                            bodyOne.collide(bodyTwo);
//                            bodyOne.deleteThis();
//                        }
//                    }
//                }
//            }
        }
    }
    
    public void deleteCrap()
    {
        synchronized(safeBodies)
        {
            for (int i = 0; i < safeBodies.size(); i++)
            {
                Body b = (Body) safeBodies.get(i);
                if(b.getDeleteMe())
                {
                    safeBodies.remove(i);
                }
            }
        }
    }
}
