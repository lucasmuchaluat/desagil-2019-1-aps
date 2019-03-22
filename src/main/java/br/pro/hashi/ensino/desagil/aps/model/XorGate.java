package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nand;


    public XorGate() {
        super(2);

        nand = new NandGate();
    }

    @Override
    public boolean read() {
        return false;
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {

    }
}
