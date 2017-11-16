package view;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import tracer.RayGenerator;
import primitives.Point3D;
import tracer.Ray;

public class Orthographic extends Projection {

  public Orthographic (final float h, final float aspect) {
    super(h * aspect, h);
  }

@Override
  public RayGenerator getRayGenerator (final Camera c, final int W, final int H) {
    return new OrtographicRayGenerator(c, W, H);
  }

  static private class OrtographicRayGenerator extends RayGenerator{
      public OrtographicRayGenerator(final Camera c, final int W, final int H){
     super(c, W, H);
                  System.out.println("Ortographic Ray Gen: "+c+W+H);
    }
    @Override
    public Ray getRay (final int m, final int n){
        final float x=(m+0.5f)*w/W-w*0.5f;
        final float y=(n+0.5f)*h/H-h*0.5f;
        final float z=0;
        final Point3D R = new Point3D(x, y, z);
        camera.toSceneCoord(R);
        //System.out.println("Ortographic Ray: "+x+y);
        return new Ray(R, camera.getLook());        
    }
   
  }

}