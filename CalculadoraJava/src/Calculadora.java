import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculadora extends JFrame implements ActionListener {

    JTextField display;
    JLabel labelOperacao;
    String operacao = "";
    double num1 = 0;
    boolean novaEntrada = false;

    ArrayList<String> historico = new ArrayList<>();
    JTextArea areaHistorico;
    JPanel painelHistorico;
    JPanel painelCientifico;

    Color COR_FUNDO     = new Color(28, 28, 30);
    Color COR_DISPLAY   = new Color(10, 10, 12);
    Color COR_BTN_NUM   = new Color(58, 58, 60);
    Color COR_BTN_OP    = new Color(255, 149, 0);
    Color COR_BTN_FUNC  = new Color(99, 99, 102);
    Color COR_BTN_CIENT = new Color(44, 44, 46);

    public Calculadora() {
        setTitle("Calculadora Pro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(COR_FUNDO);

        construirDisplay();
        construirCorpo();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void construirDisplay() {
        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(COR_DISPLAY);
        topo.setBorder(BorderFactory.createEmptyBorder(10, 12, 6, 12));

        JPanel barraTopos = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        barraTopos.setBackground(COR_DISPLAY);

        JButton btnHist  = criarBotaoTopo("Historico");
        JButton btnCient = criarBotaoTopo("Cientifico");
        btnHist.addActionListener(e -> {
            painelHistorico.setVisible(!painelHistorico.isVisible());
            pack();
        });
        btnCient.addActionListener(e -> {
            painelCientifico.setVisible(!painelCientifico.isVisible());
            pack();
        });
        barraTopos.add(btnHist);
        barraTopos.add(btnCient);

        labelOperacao = new JLabel(" ");
        labelOperacao.setFont(new Font("Arial", Font.PLAIN, 15));
        labelOperacao.setForeground(new Color(170, 170, 170));
        labelOperacao.setHorizontalAlignment(SwingConstants.RIGHT);

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 48));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(COR_DISPLAY);
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        topo.add(barraTopos,    BorderLayout.NORTH);
        topo.add(labelOperacao, BorderLayout.CENTER);
        topo.add(display,       BorderLayout.SOUTH);

        add(topo, BorderLayout.NORTH);
    }

    private void construirCorpo() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(COR_FUNDO);

        // Painel historico
        painelHistorico = new JPanel(new BorderLayout());
        painelHistorico.setBackground(new Color(20, 20, 22));
        painelHistorico.setPreferredSize(new Dimension(320, 150));
        painelHistorico.setVisible(false);

        JLabel titulo = new JLabel("  Historico de Calculos");
        titulo.setFont(new Font("Arial", Font.BOLD, 13));
        titulo.setForeground(new Color(200, 200, 200));
        titulo.setBorder(BorderFactory.createEmptyBorder(6, 0, 4, 0));

        areaHistorico = new JTextArea();
        areaHistorico.setEditable(false);
        areaHistorico.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaHistorico.setBackground(new Color(20, 20, 22));
        areaHistorico.setForeground(new Color(220, 220, 220));
        areaHistorico.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

        JScrollPane scroll = new JScrollPane(areaHistorico);
        scroll.setBorder(null);

        JButton btnLimpar = new JButton("Limpar Historico");
        btnLimpar.setFont(new Font("Arial", Font.PLAIN, 11));
        btnLimpar.setBackground(new Color(60, 20, 20));
        btnLimpar.setForeground(new Color(255, 80, 80));
        btnLimpar.setFocusPainted(false);
        btnLimpar.addActionListener(e -> { historico.clear(); areaHistorico.setText(""); });

        painelHistorico.add(titulo,    BorderLayout.NORTH);
        painelHistorico.add(scroll,    BorderLayout.CENTER);
        painelHistorico.add(btnLimpar, BorderLayout.SOUTH);

        // Painel cientifico
        painelCientifico = new JPanel(new GridLayout(3, 6, 4, 4));
        painelCientifico.setBackground(COR_FUNDO);
        painelCientifico.setBorder(BorderFactory.createEmptyBorder(6, 8, 2, 8));
        painelCientifico.setVisible(false);

        String[] cient = {
            "sin", "cos", "tan", "log", "ln", "sqrt",
            "asin", "acos", "atan", "10^x", "e^x", "x^2",
            "PI", "e", "x^y", "1/x", "n!", "|x|"
        };
        for (String t : cient) {
            painelCientifico.add(criarBotao(t, COR_BTN_CIENT, Color.WHITE, 12));
        }

        // Painel basico
        JPanel painelBasico = new JPanel(new GridLayout(5, 4, 5, 5));
        painelBasico.setBackground(COR_FUNDO);
        painelBasico.setBorder(BorderFactory.createEmptyBorder(6, 8, 8, 8));

        String[] basico = {
            "C", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "DEL", "="
        };
        for (String t : basico) {
            Color bg;
            if (t.equals("="))                                         bg = COR_BTN_OP;
            else if (t.equals("/") || t.equals("*") || t.equals("-") || t.equals("+")) bg = COR_BTN_OP;
            else if (t.equals("C") || t.equals("+/-") || t.equals("%"))               bg = COR_BTN_FUNC;
            else                                                       bg = COR_BTN_NUM;
            painelBasico.add(criarBotao(t, bg, Color.WHITE, 20));
        }

        JPanel corpo = new JPanel(new BorderLayout());
        corpo.setBackground(COR_FUNDO);
        corpo.add(painelCientifico, BorderLayout.NORTH);
        corpo.add(painelBasico,     BorderLayout.CENTER);

        container.add(painelHistorico, BorderLayout.NORTH);
        container.add(corpo,           BorderLayout.CENTER);
        add(container, BorderLayout.CENTER);
    }

    private JButton criarBotaoTopo(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.PLAIN, 11));
        btn.setBackground(new Color(55, 55, 60));
        btn.setForeground(new Color(200, 200, 200));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
        return btn;
    }

    private JButton criarBotao(String texto, Color bg, Color fg, int tamanho) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, tamanho));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        btn.addActionListener(this);

        Color hover = bg.brighter();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(bg); }
        });
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("C")) {
            display.setText("0");
            labelOperacao.setText(" ");
            operacao = "";
            num1 = 0;
            novaEntrada = false;

        } else if (cmd.equals("+/-")) {
            display.setText(fmt(-getVal()));

        } else if (cmd.equals("%")) {
            display.setText(fmt(getVal() / 100.0));

        } else if (cmd.equals("DEL")) {
            String s = display.getText();
            display.setText(s.length() > 1 ? s.substring(0, s.length() - 1) : "0");

        } else if (cmd.equals("/") || cmd.equals("*") || cmd.equals("-") || cmd.equals("+")) {
            num1 = getVal();
            operacao = cmd;
            labelOperacao.setText(fmt(num1) + " " + cmd);
            novaEntrada = true;

        } else if (cmd.equals("=")) {
            if (operacao.isEmpty()) return;
            double num2 = getVal();
            double res  = calcular(num1, num2, operacao);
            String expr = fmt(num1) + " " + operacao + " " + fmt(num2) + " = " + fmt(res);
            adicionarHistorico(expr);
            labelOperacao.setText(expr);
            display.setText(fmt(res));
            operacao = "";
            novaEntrada = true;

        } else if (cmd.equals(".")) {
            if (novaEntrada) { display.setText("0."); novaEntrada = false; return; }
            if (!display.getText().contains("."))
                display.setText(display.getText() + ".");

        } else if (cmd.equals("sin"))  { cientifica("sin",  Math.sin(Math.toRadians(getVal())));
        } else if (cmd.equals("cos"))  { cientifica("cos",  Math.cos(Math.toRadians(getVal())));
        } else if (cmd.equals("tan"))  { cientifica("tan",  Math.tan(Math.toRadians(getVal())));
        } else if (cmd.equals("asin")) { cientifica("asin", Math.toDegrees(Math.asin(getVal())));
        } else if (cmd.equals("acos")) { cientifica("acos", Math.toDegrees(Math.acos(getVal())));
        } else if (cmd.equals("atan")) { cientifica("atan", Math.toDegrees(Math.atan(getVal())));
        } else if (cmd.equals("log"))  { cientifica("log",  Math.log10(getVal()));
        } else if (cmd.equals("ln"))   { cientifica("ln",   Math.log(getVal()));
        } else if (cmd.equals("sqrt")) { cientifica("sqrt", Math.sqrt(getVal()));
        } else if (cmd.equals("x^2"))  { cientifica("x^2",  Math.pow(getVal(), 2));
        } else if (cmd.equals("10^x")) { cientifica("10^x", Math.pow(10, getVal()));
        } else if (cmd.equals("e^x"))  { cientifica("e^x",  Math.exp(getVal()));
        } else if (cmd.equals("1/x"))  { cientifica("1/x",  1.0 / getVal());
        } else if (cmd.equals("n!"))   { cientifica("n!",   fatorial((long) getVal()));
        } else if (cmd.equals("|x|"))  { cientifica("|x|",  Math.abs(getVal()));
        } else if (cmd.equals("PI"))   { display.setText(fmt(Math.PI)); novaEntrada = false;
        } else if (cmd.equals("e"))    { display.setText(fmt(Math.E));  novaEntrada = false;
        } else if (cmd.equals("x^y"))  {
            num1 = getVal(); operacao = "^";
            labelOperacao.setText(fmt(num1) + " ^");
            novaEntrada = true;

        } else {
            // digitos
            if (novaEntrada || display.getText().equals("0")) {
                display.setText(cmd);
                novaEntrada = false;
            } else if (display.getText().length() < 15) {
                display.setText(display.getText() + cmd);
            }
        }
    }

    private double getVal() {
        try { return Double.parseDouble(display.getText()); }
        catch (NumberFormatException ex) { return 0; }
    }

    private void cientifica(String nome, double resultado) {
        String expr = nome + "(" + fmt(getVal()) + ") = " + fmt(resultado);
        adicionarHistorico(expr);
        labelOperacao.setText(expr);
        display.setText(fmt(resultado));
        novaEntrada = true;
    }

    private double calcular(double a, double b, String op) {
        if (op.equals("/")) return b != 0 ? a / b : Double.NaN;
        if (op.equals("*")) return a * b;
        if (op.equals("-")) return a - b;
        if (op.equals("+")) return a + b;
        if (op.equals("^")) return Math.pow(a, b);
        return b;
    }

    private String fmt(double n) {
        if (Double.isNaN(n))      return "Erro";
        if (Double.isInfinite(n)) return "Infinito";
        if (n == (long) n)        return String.valueOf((long) n);
        return String.format("%.8f", n).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    private long fatorial(long n) {
        if (n < 0) return -1;
        long r = 1;
        for (long i = 2; i <= n; i++) r *= i;
        return r;
    }

    private void adicionarHistorico(String entrada) {
        historico.add(0, entrada);
        if (historico.size() > 50) historico.remove(historico.size() - 1);
        StringBuilder sb = new StringBuilder();
        for (String h : historico) sb.append(h).append("\n");
        areaHistorico.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}
