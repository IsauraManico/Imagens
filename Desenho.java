package BufferedImageVolatilImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author isaura
 */
@SuppressWarnings("serial")
public class Desenho extends JPanel
{

    
    private BufferedImage yuri;
    private BufferedImage sempre;

    private VolatileImage volatileImage; // imagem para desenhar em memória

    // main só pra testar
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Teste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(640, 480));
        frame.add(new Desenho(), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // método pra carregar imagem sem precisar ficar botando try-catch
    private static BufferedImage carregarImagem(String caminho) 
    {
        try 
        {
            InputStream input = Desenho.class.getResourceAsStream(caminho);
            
            return ImageIO.read(input);
        } 
        catch (IOException ex)
        {
            System.err.println("Error:");
            ex.printStackTrace();
            return null;
        }
    }

    public Desenho() 
    {
        //yuri = carregarImagem("/sara/bola.png");
        sempre = carregarImagem("imagem.jpg");
        volatileImage = createVolatileImage(getWidth(), getHeight());

        addComponentListener(new ComponentAdapter() 
        {

            // recriar a VolatileImage caso o DrawPanel seja redimensionado
            @Override
            public void componentResized(ComponentEvent e)
            {
                Dimension size = e.getComponent().getSize();
                volatileImage = newVolatileImage(size.width, size.height, Transparency.TRANSLUCENT);
            }
        });

        //setBackground(Color.GRAY);

        Timer tempo = new Timer(9, event -> repaint());
        tempo.start();
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        int width = volatileImage.getWidth();
        int height = volatileImage.getHeight();

        // obtém o Graphics da VolatileImage
        Graphics2D grafico = volatileImage.createGraphics();

        // desenha nele
        grafico.setBackground(Color.GRAY);
        grafico.fillRect(0, 0, width, height);
        grafico.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grafico.drawImage(yuri, 0, 0, null);
        grafico.drawImage(sempre, 50, 50, null);
       
        // agora transfere a VolatileImage para o Graphics deste componente
        g.drawImage(volatileImage, 0, 0, width, height, null);
        
         grafico.dispose();

    }

    private VolatileImage newVolatileImage(int width, int height, int transparency) 
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
        VolatileImage volatileImage = gc.createCompatibleVolatileImage(width, height, transparency);
        int valid = volatileImage.validate(gc);
        if (valid == VolatileImage.IMAGE_INCOMPATIBLE)
        {
            return newVolatileImage(width, height, transparency);
        }
        return volatileImage;
    }
}