package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;

    private final Light light;
    private final Image image;
    private Color color;

    public GateView(Gate gate) {


        super(245, 346);


        light = new Light();
        light.setR(255);
        light.setG(0);
        light.setB(0);


        light.connect(0, gate);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }


        JLabel inputLabel = new JLabel("Input");
        JLabel outputLabel = new JLabel("Output");


        int y = 125;

        for (JCheckBox inputBox : inputBoxes) {
            if (inputBoxes.length == 1) {
                add(inputBox, 20, 185, 25, 25);

            } else {
                add(inputBox, 20, y, 25, 25);
                y += 105;
            }
        }


        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);


        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        addMouseListener(this);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        int r = light.getR();
        int g = light.getG();
        int b = light.getB();
        color = new Color(r, g, b);
        repaint();


    }


    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }


    @Override
    public void mouseClicked(MouseEvent event) {

        int x = event.getX();
        int y = event.getY();

        double difx = x - 222.5;
        double dify = y - 192.5;
        double radius = 12.5;


        if ((Math.pow(difx, 2) + Math.pow(dify, 2)) <= Math.pow(radius, 2)) {


            color = JColorChooser.showDialog(this, null, color);
            if (color != null) {
                light.setR(color.getRed());
                light.setG(color.getGreen());
                light.setB(color.getBlue());
            }
            repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(image, 10, 80, 221, 221, this);


        g.setColor(color);
        g.fillOval(210, 180, 25, 25);


        getToolkit().sync();
    }


}
