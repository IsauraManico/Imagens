# Imagens


Existem várias maneiras de carregar imagens no java2D, no java em Geral

Dentre os quais destaquei o BufferedImage, que carrega a imagem, a partir de um arquivo:

BufferedImage imagem = null;

File file = new File(arquivo);

imagem = ImageIO.read(file);

O ImageIcon

Image img;

img = new ImageIcon(getClass().getResource("caminho_imagem").getImage();

Podemos carregar tambem uma imagem com o VolatileImage();

O volatileImage, aumenta a rapidez, dos objectos no java2d, na class Desenho, explica detalhadamente, como carregar as Imagens!!!


