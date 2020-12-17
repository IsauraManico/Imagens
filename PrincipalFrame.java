
package BufferedImageVolatilImage;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author isaura
 */
public class PrincipalFrame extends JFrame
{
    
    
    public PrincipalFrame()
    {
        this.setTitle("Carregamento de Imagens");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setResizable(true);
        
        this.add(new DrawPanel());
        
        this.setSize( new Dimension(900,700));
        
        this.setLocationRelativeTo(null);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        
        new PrincipalFrame();
    }
}
