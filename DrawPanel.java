
package BufferedImageVolatilImage;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;



import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author isaura
 */
public class DrawPanel extends JPanel implements ActionListener
{
    private Timer tempo;
    
    private BufferedImage roda = null, ball = null;
    
    private BufferedImage  yuri;
    
    private Image sempre;
    
    InputStream in = null;/* Arquivo que você quer carregar */;
    
    
    
    private VolatileImage image;
    
    private int width,height;
    
   
    
    
    
    
    
    public DrawPanel()
    {
        
        
            this.setBackground(Color.gray);
          
                tempo = new Timer(9,this);
                
                tempo.start();
               
            try
            {
                yuri= ImageIO.read(new File("sara/bola.png"));
                
                
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
                
                System.out.println("Error"+ex);
                
            }
            
            try
            {
                  in = getClass().getResourceAsStream("img/imagem.jpg");
                sempre = ImageIO.read(in);
            
            }
            
             catch (IOException ex)
            {
                ex.printStackTrace();
                
                System.out.println("Error"+ex);
                
            }
            
            
            
          
        /*    try
            {
                  in =new File("sara/imagem.jpg") ;
            
                sempre = ImageIO.read(in);
          
        
                
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
                
                System.out.println("Error:"+ex);
            }*/
            
          
        
    }
    
    public VolatileImage createVolatileImage( int width,int height,int transparency)
    {
        GraphicsEnvironment ge  = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
        
        VolatileImage image = gc.createCompatibleVolatileImage(width, height, transparency);
        
        int valid = image.validate(gc);
        
        if( valid == VolatileImage.IMAGE_INCOMPATIBLE)
        {
            return createVolatileImage(width,height,transparency);
        }
        
        
        return image;
    }
    
    
    @Override
    public void paintComponent( Graphics g)
    {
        super.paintComponent(g);
        Graphics2D grafico = (Graphics2D)g;
        
        grafico.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
         
       // grafico.drawImage(ball, 200,100,100,100,Color.GRAY ,this);
       
        
         grafico.drawImage(yuri, 0,0, null);
      
            // desenhando no VolatileImage
       //int width = sempre.getWidth(null);
       //int height = sempre.getHeight(null);
       
       VolatileImage volatileImage = createVolatileImage(100, 200, Transparency.TRANSLUCENT);
       Graphics graphics = volatileImage.getGraphics();
       graphics.drawImage(sempre, 50, 50, width, height, null);

       
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        this.repaint();
        
    }
    
}
