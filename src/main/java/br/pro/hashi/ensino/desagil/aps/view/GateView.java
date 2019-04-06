package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class GateView extends JPanel implements ActionListener {

    private final Gate gate;

    private final JCheckBox[] inputFields;

    private final JCheckBox outputField;

    private final Switch[] switches;

    public GateView(Gate gate) {
        this.gate = gate;

        // Nada de especial na construção dos campos.
        inputFields = new JCheckBox[gate.getInputSize()];
        outputField = new JCheckBox();
        switches = new Switch[gate.getInputSize()];

        // A classe JLabel representa um rótulo, ou seja,
        // um texto não-editável que queremos colocar na
        // interface para identificar alguma coisa. Não
        // precisa ser atributo, pois não precisamos mais
        // mexer nesses objetos depois de criar e adicionar.
        JLabel inputLabel = new JLabel("Entrada:");
        JLabel outputLabel = new JLabel("Saída:");

        // Um JPanel tem um layout, ou seja, um padrão para
        // organizar as componentes dentro dele. A linha abaixo
        // estabelece um dos padrões mais simples: simplesmente
        // colocar uma componente debaixo da outra, alinhadas.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Colocamos todas componentes aqui no contêiner.
        add(inputLabel);

        // Uma campo de texto tem uma lista de observadores que
        // reagem quando o usuário dá Enter depois de digitar.
        // Usamos o método addActionListener para adicionar esta
        // instância de CalculatorView, ou seja "this", nessa
        // lista. Só que addActionListener espera receber um objeto
        // do tipo ActionListener como parâmetro. É por isso que
        // adicionamos o "implements ActionListener" lá em cima.
        for (int i = 0; i < gate.getInputSize(); i++) {
            inputFields[i] = new JCheckBox();
            switches[i] = new Switch();
            add(inputFields[i]);
            inputFields[i].addActionListener(this);
            gate.connect(i, switches[i]);
        }

        add(outputLabel);
        add(outputField);
        // O último campo de texto não pode ser editável, pois é
        // só para exibição. Logo, configuramos como desabilitado.
        outputField.setEnabled(false);


        // Update é o método que definimos abaixo para atualizar o
        // último campo de acordo com os valores dos primeiros.
        // Precisamos chamar esse método no final da construção
        // para garantir que a interface não nasce inconsistente.
        update();
    }

    private void update() {

        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputFields[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        // O contrário também vale! Para colocar um double no
        // campo, precisamos antes converter ele para String.
        outputField.setSelected(gate.read());
}

    // O que esta componente deve fazer quando o usuário der um
    // Enter depois de digitar? Apenas chamar o update, é claro!
    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
