package view;

import tracer.RayGenerator;
import primitives.Point3D;
import tracer.Ray;

public class Perspective extends Projection {

  public Perspective (final float fov, final float aspect) {
    super((float) (2.0 * Math.tan(Math.toRadians(0.5 * fov))) * aspect,
          (float) (2.0 * Math.tan(Math.toRadians(0.5 * fov))));
  }

  @Override
  public RayGenerator getRayGenerator(final Camera c, final int W, final int H) {
    return new PerspectiveRayGenerator(c, W, H);
  }

  static private class PerspectiveRayGenerator extends RayGenerator {
      @Override
    public Ray getRay (final int m, final int n){
        final float x=(m+0.5f)*w/W-w*0.5f;
        final float y=(n+0.5f)*h/H-h*0.5f;
        final float z=0;
        final Point3D R = new Point3D(x, y, z);
        camera.toSceneCoord(R);
        return new Ray(R, camera.getLook()); 
    }
    public PerspectiveRayGenerator(final Camera c, final int W, final int H){
     super(c, W, H);
    }

  }

}