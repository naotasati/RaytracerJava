package upnaTRay;

import java.io.File;
import java.io.FileNotFoundException;

import view.Camera;
import gui.Image;
import javafx.geometry.Point3D;
import objects.Group3D;
import parser.Parser;

public final class UpnaTRay {
  /**
   * Main.
   * Sergio Tohaquiza Intriago
   * @param args
   * @throws FileNotFoundException
   * @throws Exception
   */
    
    //camara
    //proyecciones
    //get ray
    //Conseguir scene0.xml
  public static void main (final String[] args)
          throws FileNotFoundException, Exception {
      final String path = System.getProperty("user.dir") + File.separator
            + "scenes" + File.separator;
      final File inputFile = new File(path + args[0]);
        System.out.println(inputFile);
        
        final Parser parser = new Parser(inputFile);
        final Image image = parser.getViewport(); //Dando valor al obj clase imagen
        final Camera camera = parser.getCamera();
        final Group3D scene = parser.getScene();
        
        final long t0 = System.nanoTime();
        image.synthesis(camera, scene); //Solo la camara y la escena se llaman
        final float t1 = (float) ((System.nanoTime() - t0) / 1E+9);
        
        System.out.println("Rendering time: " + t1 + " seconds");
        
        image.show();
  }
}