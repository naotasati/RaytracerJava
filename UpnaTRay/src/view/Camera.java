package view;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;

import tracer.RayGenerator;
import primitives.Point3D;
import primitives.Vector3D;

public class Camera {

  // ATRIBUTOS
  private final Point3D position;
  private final Vector3D up; // Vector Up
  private final Vector3D lookAt; // Vector LookAt
  private final Matrix4f camera2scene;
  private Projection optics;

  // CONSTRUCTORES
  //Terminar los constructores
  //Que datos recibe OPTICS?
  //Traslacion
  //Alineamiento eje Z
  public Camera (final Point3D V, final Point3D C, final Vector3D up) {      
      this.position = new Point3D(V);
      this.up = new Vector3D(up);
      Vector3D u = new Vector3D(up);//Vector UP
      this.lookAt = new Vector3D(V,C); //Vector Vista
      Vector3D w = new Vector3D(V,C);
      w=w.opposite();
      w.normalize();
      //System.out.println("w normalizado: "+w.toString());
      
      float s = u.dot(w);
      float s2= s*s;
      System.out.println("s: "+s);
      float t = invSqrt(1-s2);
      System.out.println("t: "+t);
      System.out.println("u: "+u.x+u.y+u.z);
      
      this.camera2scene = new Matrix4f(
      t*((u.y*w.z)-(u.z*w.y)),t*(u.x-s*w.x),w.x,this.position.x,
      t*((u.z*w.x)-(u.x*w.z)),t*(u.y-s*w.y),w.y,this.position.y,
      t*((u.x*w.y)-(u.y*w.x)),t*(u.z-s*w.z),w.z,this.position.z,
      0f,0f,0f,1f);
      /*      this.camera2scene = new Matrix4f();
      camera2scene.setRow(0, 0.7071067f, -0.40824825f, 0.57735026f, 20f);
      camera2scene.setRow(1, 0f, 0.81649655f, 0.57735026f, 20f);
      camera2scene.setRow(2, -0.7071067f, -0.40824825f, 0.57735026f, 20f);
      camera2scene.setRow(3, 0f, 0f, 0f, 1f);*/
           
      System.out.println("Camara Matriz4f: \n"+this.camera2scene.toString()+"\n");      
      
  }

  public Camera (final Camera c) {
    this.position = new Point3D(c.position);
    this.up = new Vector3D(c.up);
    this.lookAt = new Vector3D(c.lookAt);
    this.camera2scene = new Matrix4f(c.camera2scene);
    this.optics = c.optics;
  }
  //Metodo fast Inverse Squareroot
  public static float invSqrt(float x) {
    float xhalf = 0.5f * x;
    int i = Float.floatToIntBits(x);
    i = 0x5f3759df - (i >> 1);
    x = Float.intBitsToFloat(i);
    x *= (1.5f - xhalf * x * x);
    return x;
}

  public final void toSceneCoord (final Vector3D v) {
    camera2scene.transform(v);
  }

  public final void toSceneCoord (final Point3D p) {
    camera2scene.transform(p);
  }

  public final Point3D getPosition () {
    return this.position;
  }
  
   public final Vector3D getLook () {
    return this.lookAt;
  }
    
  public final Projection getProjection () {
    return this.optics;
  }
  public final void setProjection (final Projection p) {
    this.optics = p;
  }

  public final RayGenerator getRayGenerator (final int W, final int H) {
    return this.optics.getRayGenerator(this, W, H);
  }
}