/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author user
 */
public class Point {
    private int x;
    private int y;

    
    public Point(int x, int y) {
        if( x >= 0)
            this.x = x;
        else
            x = 0;
        
        if( y >= 0 )
            this.y = y;
        else
            y = 0;
    }
    
    /**
     * Renvoie la distance entre le point p et l'instance.
     * Renvoie -1 si p vaut null.
     * @param p 
     */
    public int getDistance(Point p)
    {
        if( p == null)
            return -1;
        
        return (int) Math.sqrt( Math.pow(this.x - p.x, 2) + Math.pow(this.y-p.y, 2)  );
    }
    
    public Point(Point p)
    {
        if( p != null )
        {
            this.x = p.x;
            this.y = p.y;
        }
        else
        {
            this.x = 0;
            this.y = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
    
    
    public static void main(String[] args) {
        // TP8 Q21
        /*
        Point p = new Point(2,300);
        System.out.println(p.toString());
        Point p2 = new Point(p);
        p = null;
        System.out.println(p2.toString());
        */
    }
    
    
    

    
    
    
    
    
    
    
    
}
