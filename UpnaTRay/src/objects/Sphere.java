package objects;

import java.awt.Color;

import primitives.Point3D;
import primitives.Vector3D;
import tracer.Hit;
import tracer.Ray;

public class Sphere extends Object3D {
  
  private final Point3D centro;
  private final float radio;
  private final float inv_radio;
   
  private Sphere (final Point3D centro, final float radio) {
    this.centro = new Point3D(centro);
    this.radio = radio;
    this.inv_radio = (float) (1.0 / radio);
  }    

  public Sphere (final Point3D centro, final float radio, final Color color) {
    this(centro, radio);
    this.color = color;
  } 

  @Override
  protected Hit _intersects (final Ray ray) {
      float r2= radio*radio;
      Point3D origen = new Point3D(ray.getStartingPoint());
      Vector3D r = new Vector3D(ray.getDirection());
      float a = ray.getDirection().dot(ray.getDirection());
      float b= 2*ray.getStartingPoint().sub(centro).dot(ray.getDirection());
      float b2= b*b;
      float c= ray.getStartingPoint().sub(centro).dot(ray.getStartingPoint().sub(centro))-r2;
      // b*b-4*a*c
      float discriminant = b2-4*a*c;
      
      float x=(float)Math.sqrt(discriminant);
                    float t =(-b-x)/(2*a);
      Hit sp = new Hit(t,origen , r, this);
                        return sp;
                        
                        
      
//      if(discriminant < 0.0){
//                    //return Hit.NOHIT;
//                }else{
//                    //negativo es el valor mas cercano
//                    float x=(float)Math.sqrt(discriminant);
//                    float t =(-b-x)/(2*a);
//                    //t= (-b - Math.sqrt(discriminant))/(2*a);                    
//                    if (t < 10E-9){
//                        
//                        System.out.println("SPHERE!!! "+ray.toString());
//                        Hit sp = new Hit(t,origen , r, this);
//                        return sp;
//                    }else{
//                        //no hubo interseccion
//                        return Hit.NOHIT;
//                    }
//                }
  }

}