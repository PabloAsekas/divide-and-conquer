package DivideYVenceras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 *
 * @author Nacho y Pablo Bermejo
 *
 * https://github.com/PabloAsekas
 *
 */
 
public class Archivo {
    protected String url;
    protected FileReader fr;
    protected BufferedReader br;
    protected FileWriter fw;
    protected PrintWriter pw;
    
    public Archivo(boolean a, String ruta) throws FileNotFoundException, IOException {
        if (a) {
            this.url = ruta;
            this.fr = new FileReader (url);
            this.br = new BufferedReader (fr);
        } else {
            this.fw = new FileWriter (ruta + "-girada.txt");
            this.pw = new PrintWriter (this.fw);
        }
    }
    
    public Matriz LeerMatriz (Matriz matriz) throws IOException {
        String s2, s3;
        for (int i = 0; i < matriz.tamaño; i++){
            s2 = this.br.readLine();
            int numTokens = 0;
            StringTokenizer st = new StringTokenizer (s2);
            while (st.hasMoreTokens()) {
                s3 = st.nextToken();
                matriz.setValor(i, numTokens, Integer.parseInt(s3));
                //matriz[i][numTokens] = Integer.parseInt(s3);
                numTokens++;
            }
        }
        return matriz;
    }
    
    public final void GuardarMatriz (Matriz matriz) {
        int a = matriz.getTamaño();
        this.pw.println(a);
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                this.pw.print(matriz.getValor(i, j) + " ");
            }
            this.pw.println();
        }
    }
}
