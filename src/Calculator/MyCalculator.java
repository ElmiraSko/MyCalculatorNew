package Calculator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MyCalculator extends JFrame{

    private JButton button_Plus, button_Minus, button_Mult, button_Div, button_Exp, button_C, button_Eq;
    private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    private JPanel panelSimbols, panelField, panelAll, panel;
    private  JTextField field;
    private JLabel label;
    private String textFild = "";
    private String operation = "_";
    private StringBuilder bildNumber = new StringBuilder("");
    int n1 = 0;
    int n2 = 0;

    public MyCalculator(){ //Конструктор
        super("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocation(150, 150);
        setResizable(false);//запретили изменять размер окна калькулятора

        label = new JLabel("Введите число:");
        panelField = new JPanel(); //панель для текстового поля
        panelField.add(label);
        //рамки, чтобы улучшить внешний вид
        EmptyBorder border = new EmptyBorder(10, 0, 10, 0);
        LineBorder line = new LineBorder(Color.gray, 1, true);
        CompoundBorder compound = new CompoundBorder(line, border);
        // Создание текстового поля field и задание шрифта
        field = new JTextField(30);
        field.setFont(new Font("Dialog", Font.PLAIN, 14)); //public Font(String name, int style, int size)
        field.setBorder(compound);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {//слушатель поля, код выполняется, когда поле получает фокус
                label.setForeground(Color.BLACK);
                label.setText("Введите число:");
            }
        });


        EmptyBorder borderPanelNumBer = new EmptyBorder(15, 5, 5, 5);
        EmptyBorder borderPan = new EmptyBorder(15, 0, 35, 0);
        panelField.setBorder(borderPan);
        panelField.add(field);

        panelSimbols = new JPanel();
        panelSimbols.setLayout(new GridLayout(4,4));
        panelSimbols.setBorder(borderPanelNumBer);

        button_Plus = new JButton("+");
        button_Plus.addActionListener(new ButtonListener());//зарегистрировали слушателя кнопок ButtonListener
        button_Minus = new JButton("-");
        button_Minus.addActionListener(new ButtonListener());
        button_Mult = new JButton("*");
        button_Mult.addActionListener(new ButtonListener());
        button_Div = new JButton("/");
        button_Div.addActionListener(new ButtonListener());
        button_Exp = new JButton("n^2");
        button_Exp.addActionListener(new ButtonListener());
        button_C = new JButton("C");
        button_C.setActionCommand("clean");
        button_C.addActionListener(new CleanListener());
        button_Eq = new JButton("=");
        button_Eq.addActionListener(new ButtonListener());

        NumberListener numbListener = new NumberListener();
        b0 = new JButton("0");
        b0.addActionListener(numbListener);
        b1 = new JButton("1");
        b1.addActionListener(numbListener);
        b2 = new JButton("2");
        b2.addActionListener(numbListener);
        b3 = new JButton("3");
        b3.addActionListener(numbListener);
        b4 = new JButton("4");
        b4.addActionListener(numbListener);
        b5 = new JButton("5");
        b5.addActionListener(numbListener);
        b6 = new JButton("6");
        b6.addActionListener(numbListener);
        b7 = new JButton("7");
        b7.addActionListener(numbListener);
        b8 = new JButton("8");
        b8.addActionListener(numbListener);
        b9 = new JButton("9");
        b9.addActionListener(numbListener);

        panelSimbols.add(b1);
        panelSimbols.add(b2);
        panelSimbols.add(b3);
        panelSimbols.add(button_Plus);

        panelSimbols.add(b4);
        panelSimbols.add(b5);
        panelSimbols.add(b6);
        panelSimbols.add(button_Minus);

        panelSimbols.add(b7);
        panelSimbols.add(b8);
        panelSimbols.add(b9);
        panelSimbols.add(button_Mult);

        panelSimbols.add(b0);
        panelSimbols.add(button_Exp);
        panelSimbols.add(button_Div);
        panelSimbols.add(button_Eq);

        panelAll = new JPanel();
        panelAll.setLayout(new BorderLayout());

        panelAll.add(panelField, "North");
        panelAll.add(panelSimbols, "Center");
        panelAll.add(button_C, "South");

        Container container = getContentPane();
        container.add(panelAll);
        setVisible(true);
    }

    public String getTextFild() {
        return textFild;
    }

    public void setTextFild(String textFild) {
        this.textFild = textFild;
    }

    //создадим слушателя кнопок ButtonListener
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if (ae.getActionCommand().equals("+")){
                operation("+");
            }
            if (ae.getActionCommand().equals("-")){
                operation("-");
            }
            if (ae.getActionCommand().equals("*")){
                operation("*");
            }
            if (ae.getActionCommand().equals("/")){
                operation("/");
            }
            if (ae.getActionCommand().equals("n^2")){
                operation("n^2");
                field.setText(String.valueOf(n1*n1));
            }
            if (ae.getActionCommand().equals("=")){//если нажата кнопка "="
                String result = "";
                textFild = field.getText().trim();
                try {
                    n2 = Integer.parseInt(textFild);
                    if (operation.equals("+")){
                        result = String.valueOf(n1 + n2);
                    }
                    if (operation.equals("-")){
                        result = String.valueOf(n1 - n2);
                    }
                    if (operation.equals("*")){
                        result = String.valueOf(n1 * n2);
                    }
                    if (operation.equals("/")){
                        if (n2 != 0){
                            result = String.valueOf(n1 / n2);
                        }else {
                            label. setForeground(Color.RED);
                            label.setText("На ноль делить нельзя!");}
                    }
                    field.setText(result);
                }catch (NumberFormatException e){
                    field.requestFocus();
                }
            }

        }
    }
    //Создаем слушателя для кнопки clean
    class CleanListener implements ActionListener{
        public void actionPerformed(ActionEvent ex){
            if(ex.getActionCommand().equals("clean")) {
                field.setText(""); //стераем содержимое поля field
                field.requestFocus(); //возвращаем фокус полю
                label.setForeground(Color.BLACK);
                label.setText("Введите число:");
                bildNumber.delete(0, bildNumber.length());
            }
        }
    }
    //вспомогательный метод, используется в слушателе кнопок ButtonListener
    public void operation(String operation){ //определяет число и арифметическое действие
        textFild = field.getText().trim(); //содержимое поля, (методом trim() отбрасываем пробелы)
        try {
            n1 = Integer.parseInt(textFild); //метод parseInt - переводит строку в целое число
            this.operation = operation;
            bildNumber.setLength(0);
            field.setText("");
            field.requestFocus(); //возвращает фокус на поле
        }catch (NumberFormatException e){
            label.setText("Вы ничего не ввели или это не число");
        }
    }
    //Создаем слушателей для числовых кнопок
    public class NumberListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if (ae.getActionCommand().equals("1")){
                bildNumber.append("1");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("2")){
                bildNumber.append("2");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("3")){
                bildNumber.append("3");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("4")){
                bildNumber.append("4");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("5")){
                bildNumber.append("5");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("6")){
                bildNumber.append("6");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("7")){
                bildNumber.append("7");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("8")){
                bildNumber.append("8");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("9")){
                bildNumber.append("9");
                field.setText(bildNumber.toString());
            }
            if (ae.getActionCommand().equals("0")){
                bildNumber.append("0");
                field.setText(bildNumber.toString());
            }
        }
    }
}
