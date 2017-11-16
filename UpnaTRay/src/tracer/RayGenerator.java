package tracer;

import view.Camera;

public abstract class RayGenerator {
	
  final protected Camera camera; // Cámara para la que se generan los rayos  
  final protected float w;       // Anchura de la ventana de proyección
  final protected float h;       // Altura de la ventana de proyección
  final protected int W;         // Número de columnas de la imagen raster
  final protected int H;         // Número de filas de la imagen raster
  final protected float wW;      // Relación entre w y W
  final protected float hH;      // Relación entre h y H
  final protected float whalf;   // w / 2
  final protected float hhalf;   // h / 2

  protected RayGenerator (final Camera c, final int W, final int H) {
    System.out.println("Enter RayGenerator Abstract");    
    this.camera = new Camera(c);  
    this.w = c.getProjection().getWidth();
    this.h = c.getProjection().getHeight();
    this.W = W;
    this.H = H;
    this.wW = w / W;
    this.hH = h / H;
    this.whalf = w * 0.5f;
    this.hhalf = h * 0.5f;
    System.out.println("RayGenerator Data: "+c.getPosition().toString()+"\n"+
            w+" | "+h+" | "+W+" | "+H+" | "+wW+" | "+hH+" | "+whalf+" | "+hhalf);
  }

  /**
   * m y n son los indices columna y fila pixel a colorear
   * col index izq a der
   * filas index abajo hacia arriba
   * @param m
   * @param n
   * @return
   */
  public abstract Ray getRay (final int m, final int n);
	
}