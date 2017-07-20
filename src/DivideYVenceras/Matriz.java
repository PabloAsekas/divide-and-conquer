package DivideYVenceras;

/*
 *
 * @author Nacho y Pablo Bermejo
 *
 * https://github.com/PabloAsekas
 *
 */

public class Matriz {
    protected int tamaño;
    protected int[][] matriz;
    
    public Matriz() {}
    
    public Matriz(int tamaño) {
        this.tamaño = tamaño;
        this.matriz = new int[tamaño][tamaño];
    }
    
    public int getValor(int a, int b) {
        return this.matriz[a][b];
    }
    
    public int getTamaño() {
        return this.tamaño;
    }
    
    public final void setTamaño (int tamaño){
        this.tamaño = tamaño;
    }
    public final void setValor (int a, int b, int valor) {
        this.matriz[a][b] = valor;
    }
    
    public final void Mostrar() {
        for (int i = 0; i < this.tamaño; i++) {
            for (int j = 0; j < this.tamaño; j++) {
                System.out.print(this.getValor(i, j));
            }
            System.out.println();
        }
    }
    
    public Matriz Girar(Matriz a) {
        int n = a.getTamaño();
        Matriz b = new Matriz (n);
        int k;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                k = n - i - 1;
                b.setValor(j, k, a.getValor(i, j));
            }
        }
        return b;
    }
    
    public Matriz Copiar(Matriz a) {
        int n = a.getTamaño();
        for (int i = 0; i < n; i++) {
            for (int j=0; j < n; j++) {
                this.setValor(i, j, a.getValor(i, j));
            }
        }
        return this;
    }
    
    public Matriz DividirEnCuadrantes(Matriz matriz, int a, int b){
        int n = matriz.getTamaño();
        Matriz p = new Matriz(n/2);
        for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n/2; j++) {
                    p.setValor(i, j, matriz.getValor(i+a, j+b));
                }
            }
        return p;
            
    }
    
    public Matriz DivideYVenceras(Matriz a){
        int n = a.tamaño;
        Matriz girada = new Matriz(n);
        if (n==2){
            girada.setValor(0, 0, a.getValor(1, 0));
            girada.setValor(0, 1, a.getValor(0, 0));
            girada.setValor(1, 1, a.getValor(0, 1));
            girada.setValor(1, 0, a.getValor(1, 1));
        } else {
            Matriz p1 = new Matriz(n);
            p1 = p1.DividirEnCuadrantes(a, 0, 0);
            Matriz p2 = new Matriz(n);
            p2 = p2.DividirEnCuadrantes(a, 0, n/2);
            Matriz p3 = new Matriz(n);
            p3 = p3.DividirEnCuadrantes(a, n/2, n/2);
            Matriz p4 = new Matriz(n);
            p4 = p4.DividirEnCuadrantes(a, n/2, 0);
            Matriz aux = new Matriz(n/2);           
            aux.Copiar(p1);
            p1 = p1.DivideYVenceras(p4);
            p4 = p4.DivideYVenceras(p3);
            p3 = p3.DivideYVenceras(p2);
            p2 = p2.DivideYVenceras(aux);
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n/2; j++) {
                    girada.setValor(i, j, p1.getValor(i, j));
                    girada.setValor(i, j+n/2, p2.getValor(i, j));
                    girada.setValor(i+n/2, j+n/2, p3.getValor(i, j));
                    girada.setValor(i+n/2, j, p4.getValor(i, j));
                }
            }
        }
        return girada;
    }
}
