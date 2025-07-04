package VendingMachine.Entities;

import VendingMachine.VendingStates.State;

import java.util.List;

public class VendingMachine {

    private State state;
    List<Item> inventory;

    public void setVendingMachineState(State state) {
        this.state= state;
    }

    public State getVendingMachineState() {
        return this.state;
    }
}
