package br.pro.hashi.ensino.desagil.aps.view;
// NÃO É NECESSÁRIO NEM PERMITIDO MODIFICAR ESTA CLASSE!

import javax.swing.*;
import java.awt.*;


public class FixedPanel extends JPanel {


    protected FixedPanel(int width, int height) {

        setLayout(null);


        setPreferredSize(new Dimension(width, height));
    }


    protected Component add(Component comp, int x, int y, int width, int height) {


        super.add(comp);


        comp.setBounds(x, y, width, height);

        return comp;
    }
}
