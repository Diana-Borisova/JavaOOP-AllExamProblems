package onlineShop.models.products;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral>peripherals;
    private double overallPerformance;
    private double totalPrice;
    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public double getOverallPerformance(){

        if (this.components.isEmpty()){
            return super.getOverallPerformance();
        }
        this.overallPerformance = super.getOverallPerformance() + this.components.stream().mapToDouble(Component::getOverallPerformance).sum() / this.components.size();
        return this.overallPerformance;
    }

    @Override
    public double getPrice(){
        double computerPrice = super.getPrice();
        this.totalPrice = computerPrice+ this.components.stream().mapToDouble(Component::getPrice).sum()
                +this.peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
        return this.totalPrice;
    }

    @Override
    public void addComponent(Component component) {
       if (this.components.contains(component)){
           throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, component.getModel(),
                   component.getClass().getSimpleName(), component.getId()));
       }
this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
Component component= null;
        for (Component component1 :this.components) {
            if (componentType.equals(component1.getModel())){
                component = component1;
                this.components.remove(component1);

            }
        }
        if (component == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType,
                    this.getComponents().getClass(), getId()));
        }
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (this.peripherals.contains(peripheral)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL, peripheral.getModel(),
                    peripheral.getClass().getSimpleName(), peripheral.getId()));
        }
        this.peripherals.add(peripheral);

    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral= null;
        for (Peripheral per :this.peripherals) {
            if (peripheralType.equals(per.getClass().getSimpleName())){
                peripheral = per;
                this.peripherals.remove(peripheral);
                return peripheral ;
            }

        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL, peripheralType,
                this.getComponents().getClass(), getId()));
    }
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)", this.getOverallPerformance(),
               this.getPrice(), super.getClass().getSimpleName(),
                        this.getManufacturer(), this.getModel(), this.getId())).append(System.lineSeparator());
                sb.append(String.format(" Components (%d):", this.getComponents().size())).append(System.lineSeparator());
        for (Component component : this.components) {
            sb.append(component).append(System.lineSeparator());
        }
       sb.append(String.format(" Peripherals (%d); ", this.peripherals.size()));
        if (peripherals.isEmpty()) {
            sb.append("Average Overall Performance (0.00):").append(System.lineSeparator());
           return sb.toString().trim();
        }
            double averagePerformance = this.peripherals.stream().mapToDouble(Peripheral::getOverallPerformance).sum() / this.peripherals.size();
        sb.append(String.format("Average Overall Performance (%.2f):", averagePerformance)).append(System.lineSeparator());
        for (Peripheral per :this.peripherals) {
            sb.append(per).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
