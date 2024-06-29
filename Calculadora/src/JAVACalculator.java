import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase principal que define la calculadora
public class JAVACalculator {
    private JTextField textField1;  // Campo de texto para mostrar la entrada y salida
    private JButton clear;  // Botón para borrar la entrada
    private JButton a3Button, division, a7Button, a8Button, a4Button, a1Button, coma, a9Button, a5Button, a2Button, a0Button, suma, resta, raiz, a6Button, multiplicacion, potencia, senButton, cosButton, tanButton, igual; // Botones de la calculadora
    private JPanel JAVACalculator;

    private double tempFirst = 0; // Variable para almacenar el primer operando
    private double tempSecond = 0; // Variable para almacenar el segundo operando
    private String operator = ""; // Variable para almacenar el operador

    public JAVACalculator() {
        // ACTIONLISTENER PARA LOS BOTONES NUMERICOS
        a7Button.addActionListener(new OyenteNumero("7"));
        a8Button.addActionListener(new OyenteNumero("8"));
        a9Button.addActionListener(new OyenteNumero("9"));
        a0Button.addActionListener(new OyenteNumero("0"));
        a1Button.addActionListener(new OyenteNumero("1"));
        a2Button.addActionListener(new OyenteNumero("2"));
        a3Button.addActionListener(new OyenteNumero("3"));
        a4Button.addActionListener(new OyenteNumero("4"));
        a5Button.addActionListener(new OyenteNumero("5"));
        a6Button.addActionListener(new OyenteNumero("6"));
        coma.addActionListener(new OyenteNumero("."));

        // ACTIONLISTENER PARA LAS OPERACIONES BASICAS
        suma.addActionListener(new OyenteOperador("+"));
        resta.addActionListener(new OyenteOperador("-"));
        multiplicacion.addActionListener(new OyenteOperador("*"));
        division.addActionListener(new OyenteOperador("/"));

        // ACTIONLISTENER PARA OPERACIONES ESPECIALES
        raiz.addActionListener(new OyenteOperacionEspecial("sqrt"));
        potencia.addActionListener(new OyenteOperacionEspecial("^"));
        senButton.addActionListener(new OyenteOperacionEspecial("sin"));
        cosButton.addActionListener(new OyenteOperacionEspecial("cos"));
        tanButton.addActionListener(new OyenteOperacionEspecial("tan"));

        igual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                tempFirst = 0;
                tempSecond = 0;
                operator = "";
            }
        });
    }

    // Clase interna para manejar los eventos de los botones numéricos
    private class OyenteNumero implements ActionListener {
        private String value;  // Valor del botón presionado

        public OyenteNumero(String value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            textField1.setText(textField1.getText() + value);  // Agregar el valor al campo de texto
        }
    }

    // Clase interna para manejar los eventos de los operadores
    private class OyenteOperador implements ActionListener {
        private String op;  // Operador

        public OyenteOperador(String op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tempFirst = Double.parseDouble(textField1.getText());  // Almacenar el primer operando
            operator = op;  // Almacenar el operador
            textField1.setText("");  // Limpiar el campo de texto
        }
    }

    // Clase interna para manejar los eventos de las operaciones especiales
    private class OyenteOperacionEspecial implements ActionListener {
        private String op;  // Operación especial

        public OyenteOperacionEspecial(String op) {
            this.op = op;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            double result = 0;
            double value = Double.parseDouble(textField1.getText());  // Obtener el valor del campo de texto

            switch (op) {
                case "sqrt":
                    result = Math.sqrt(value);  // Calcular la raíz cuadrada
                    break;
                case "^":
                    tempFirst = value;  // Almacenar el primer operando para la potencia
                    operator = "^";  // Almacenar el operador de potencia
                    textField1.setText("");
                    return;
                case "sin":
                    result = Math.sin(Math.toRadians(value));  // Calcular el seno
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(value));  // Calcular el coseno
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(value));  // Calcular la tangente
                    break;
            }

            textField1.setText(String.format("%.2f", result));  // Mostrar el resultado en el campo de texto
        }
    }

    // Método para calcular el resultado de la operación
    private void calculateResult() {
        tempSecond = Double.parseDouble(textField1.getText());  // Obtener el segundo operando
        double result = 0;

        switch (operator) {
            case "+":
                result = tempFirst + tempSecond;  // Sumar
                break;
            case "-":
                result = tempFirst - tempSecond;  // Restar
                break;
            case "*":
                result = tempFirst * tempSecond;  // Multiplicar
                break;
            case "/":
                if (tempSecond == 0) {
                    JOptionPane.showMessageDialog(null, "Error: División por cero", "Error", JOptionPane.ERROR_MESSAGE);
                    textField1.setText("");  // Limpiar el campo de texto
                    return;
                } else {
                    result = tempFirst / tempSecond;  // Dividir
                }
                break;
            case "^":
                result = Math.pow(tempFirst, tempSecond);  // Calcular la potencia
                break;
        }

        textField1.setText(String.format("%.2f", result));  // Mostrar el resultado en el campo de texto
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        JFrame frame = new JFrame("JAVACalculator");
        frame.setContentPane(new JAVACalculator().JAVACalculator);  // Configurar el panel principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Configurar la operación de cierre
        frame.pack();  // Ajustar el tamaño del frame
        frame.setVisible(true);  // Hacer visible el frame
    }
}
