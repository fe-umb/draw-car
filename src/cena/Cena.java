package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Fernanda P. Umberto - 20943426
 */

public class Cena implements GLEventListener {

    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private TextRenderer textRenderer;

    public float angulo = 0, tx = 0, ty = 0, tz = 0;
    GLU glu;

    @Override
    public void init(GLAutoDrawable drawable) {
        textRenderer = new TextRenderer(new Font("Segoe UI", Font.PLAIN, 38));

        //dados iniciais da cena
        glu = new GLU();
        GL2 gl = drawable.getGL().getGL2();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;

        //Habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        GLUT glut = new GLUT();

        gl.glClearColor(0, 0, 0, 1);

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); //ler a matriz identidade

        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        Color color = new Color(0.6f, 0, 0, 1);
        desenhaTexto(225, 570, color, "Carro! =)");

        gl.glRotatef(angulo, 0, 1, 0);

        desenhaParteBaixoCarro(gl, glut); // Desenha parte de cima do carro.
        desenhaParteCimaCarro(gl, glut); // Desenha parte de baixo do carro.

        desenhaRodaEsquerda(gl, glut, 50f); // Desenha roda da frente (esquerda).
        desenhaRodaEsquerda(gl, glut, -50f); // Desenha roda traseira (esquerda).

        desenhaRodaDireita(gl, glut, -50f); // Desenha roda da frente (direita).
        desenhaRodaDireita(gl, glut, 50f); // Desenha roda traseira (direita).

        desenhaFarol(gl, glut, 15f); // Desenha farol (esquerdo).
        desenhaFarol(gl, glut, -15f); // Desenha farol (direito).

        desenhaLanternaTraseira(gl, glut, 15f); // Desenha lanterna (esquerda).
        desenhaLanternaTraseira(gl, glut, -15f); // Desenha lanterna (direita).

        gl.glFlush();
    }

    public void desenhaParteBaixoCarro(GL2 gl, GLUT glut) {
        gl.glColor3f(0.6f, 0, 0);
        gl.glPushMatrix();
        gl.glScalef(1, 0.30f, 0.5f);
        glut.glutSolidCube(100);
        gl.glPopMatrix();
    }

    public void desenhaParteCimaCarro(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3f(0.4f, 0, 0);
        gl.glTranslatef(25f, 25f, 0);
        gl.glScalef(0.5f, 0.25f, 0.5f);
        glut.glutSolidCube(100);
        gl.glPopMatrix();
    }

    public void desenhaRodaEsquerda(GL2 gl, GLUT glut, float xTranslate) {
        gl.glPushMatrix();
        gl.glColor3f(0.94f, 0.94f, 0.96f);
        gl.glTranslatef(xTranslate, -15f, 29f);
        glut.glutSolidTorus(4, 12, 23, 15);
        gl.glPopMatrix();
    }

    public void desenhaRodaDireita(GL2 gl, GLUT glut, float xTranslate) {
        gl.glPushMatrix();
        gl.glColor3f(0.94f, 0.94f, 0.96f);
        gl.glTranslatef(xTranslate, -15f, -29f);
        glut.glutSolidTorus(4, 12, 23, 15);
        gl.glPopMatrix();
    }

    public void desenhaFarol(GL2 gl, GLUT glut, float zTranslate) {
        gl.glPushMatrix();
        gl.glColor3f(1f, 1f, 0);
        gl.glTranslatef(-50f, 0, zTranslate);
        glut.glutSolidSphere(5, 30, 30);
        gl.glPopMatrix();
    }

    public void desenhaLanternaTraseira(GL2 gl, GLUT glut, float zTranslate) {
        gl.glPushMatrix();
        gl.glColor3f(1f, 0.30f, 0.30f);
        gl.glTranslatef(50f, 0, zTranslate);
        glut.glutSolidSphere(5, 30, 30);
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();

        //evita a divisao por zero
        if (height == 0) {
            height = 1;
        }
        //calcula a proporcao da janela (aspect ratio) da nova janela
        //float aspect = (float) width / height;

        //seta o viewport para abranger a janela inteira
        //gl.glViewport(0, 0, width, height);
        //ativa a matriz de projecao
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //ler a matriz identidade

        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
//        if(width >= height)            
//            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
//        else        
//            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
        //projecao ortogonal sem a correcao do aspecto
        gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //ler a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    public void desenhaTexto(int xDraw, int yDraw, Color cor, String mensagem) {
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer.setColor(cor);
        textRenderer.draw(mensagem, xDraw, yDraw);
        textRenderer.endRendering();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}
