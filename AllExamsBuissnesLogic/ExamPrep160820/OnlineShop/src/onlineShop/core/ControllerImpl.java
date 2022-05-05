package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.*;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private List<Computer> computers;
    private List<Component> components;
    private List<Peripheral>peripherals;


    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();

    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;
        if (computerType.equals("DesktopComputer")){
            computer = new DesktopComputer(id,manufacturer,model,price);

        } else if (computerType.equals("Laptop")){
            computer = new Laptop(id,manufacturer,model,price);

        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }
        for (Computer comp :this.computers) {
            if ((comp.getId() == id)) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
            }
        }
        this.computers.add(computer);


        return String.format(OutputMessages.ADDED_COMPUTER, computer.getId());
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        if (this.computers.stream().noneMatch(idCurr ->idCurr.getId()==computerId)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);

        }
        Peripheral peripheral;
        if (peripheralType.equals("Headset")){
            peripheral = new Headset(id,manufacturer,model,price,overallPerformance,connectionType);
        } else if (peripheralType.equals("Keyboard")){
            peripheral = new Keyboard(id,manufacturer,model,price,overallPerformance,connectionType);
        }else if (peripheralType.equals("Monitor")){
            peripheral = new Monitor(id,manufacturer,model,price,overallPerformance,connectionType);
        }else if (peripheralType.equals("Mouse")){
            peripheral = new Mouse(id,manufacturer,model,price,overallPerformance,connectionType);
        }        else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }
        for (Peripheral peri :this.peripherals) {
            if ((peri.getId() == id)) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
            }
        }
        this.peripherals.add(peripheral);

        Computer computer;
        for (int i = 0; i <this.computers.size() ; i++) {

              Computer currentComputer =  this.computers.get(i);
              if (currentComputer.getId()==computerId){

                currentComputer.addPeripheral(peripheral);
computer = currentComputer;
                  //this.computers.add(computer);
        }
    }

        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheral.getClass().getSimpleName(), id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (this.computers.stream().noneMatch(idCurr ->idCurr.getId()==computerId)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        StringBuilder sb = new StringBuilder();
        int currentID = 0;
        if (this.peripherals.stream().anyMatch(peripheral -> peripheral.getClass().getSimpleName().equals(peripheralType))){
        for (Computer computer :this.computers) {
            if (computer.getId()==computerId){
                for (Peripheral per :this.peripherals) {
                    if (per.getClass().getSimpleName().equals(peripheralType)){
                       sb.append(per).append(System.lineSeparator());
                        computer.removePeripheral(peripheralType);
                        this.peripherals.remove(per);
currentID= per.getId();

                        return String.format(OutputMessages.REMOVED_PERIPHERAL, per.getClass().getSimpleName(), currentID);
                    }
                }

            }
        }
    }
   return null;
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if (this.computers.stream().noneMatch(idCurr ->idCurr.getId()==computerId)){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);

        }
        Component component;
        if (componentType.equals("CentralProcessingUnit")){
            component = new CentralProcessingUnit(id,manufacturer,model,price,overallPerformance,generation);
        } else if (componentType.equals("Motherboard")){
            component = new Motherboard(id,manufacturer,model,price,overallPerformance,generation);
        }else if (componentType.equals("PowerSupply")){
            component = new PowerSupply(id,manufacturer,model,price,overallPerformance,generation);
        }else if (componentType.equals("RandomAccessMemory")){
            component = new RandomAccessMemory(id,manufacturer,model,price,overallPerformance,generation);
        } else if (componentType.equals("SolidStateDrive")){
            component = new SolidStateDrive(id,manufacturer,model,price,overallPerformance,generation);
        } else if (componentType.equals("VideoCard")){
            component = new VideoCard(id,manufacturer,model,price,overallPerformance,generation);
        }
        else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }
        for (Component component2 :this.components) {
            if (component2.getId() == id){
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
            }
        }
        this.components.add(component);

        int currentID = 0;
        for (Computer computer :this.computers) {
        if (computer.getId()==computerId){
            for (Component component1 :this.components) {
                if (component1.getClass().getSimpleName().equals(componentType) && computer.getId()==computerId){
                    currentID = computer.getId();
                    computer.addComponent(component1);
                //    this.computers.add(computer);

                }
            }

        }}
        return String.format(OutputMessages.ADDED_COMPONENT, componentType, component.getId(),currentID);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (this.computers.stream().noneMatch(idCurr -> idCurr.getId() == computerId)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        int currentID = 0;
        //if (this.components.stream().anyMatch(component -> component.getModel().getClass().getSimpleName().equals(componentType))) {
            for (Computer computer : this.computers) {
                if (computer.getId() == computerId) {
                   // if (this.components.stream().anyMatch(component -> component.getClass().getSimpleName().equals(componentType))){


                    for (int i = 0; i <this.components.size() ; i++) {
                        String currentComponent = this.components.get(i).getClass().getSimpleName();
                        if (componentType.equals(currentComponent)) {
                            currentID = this.components.get(i).getId();

                           Component forRemove =  this.components.get(i);
                            this.computers.remove(forRemove);
                           this.components.remove(forRemove);
                            return String.format(OutputMessages.REMOVED_COMPONENT, componentType, currentID);
                        }
                    }


                }
            }

      Computer computer = this.computers.stream().filter(idCurr -> idCurr.getId() == computerId).findFirst().orElse(null);

        return String.format("Component %s does not exist in %s with Id %d.", componentType, computer.getClass().getSimpleName(),computerId);
    }
    @Override
    public String buyComputer(int id) {
        if (this.computers.stream().noneMatch(idCurr -> idCurr.getId() == id)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
StringBuilder sb = new StringBuilder();
       List<Computer> computerToRemove = new ArrayList<>();
       computerToRemove= this.computers.stream().filter(computer -> computer.getId()== id).collect(Collectors.toList());
       Computer computer1 = this.computers.stream().filter(computer -> computer.getId()== id).findFirst().orElse(null);
      sb.append(computer1).append(System.lineSeparator());
       this.computers.removeAll(computerToRemove);

        return computerToRemove.stream().map(Computer::toString).collect(Collectors.joining(System.lineSeparator()));


    }

    @Override
    public String BuyBestComputer(double budget) {
        StringBuilder sb = new StringBuilder();
        double highestPerformance =0;
        double bestPrice = 0;
        boolean noMatches = true;
String model = "";
int id = 0;
        String manufacture = "";
        String per = "";
        Computer computerToRemove= null;
        for (Computer computer :this.computers) {
            double currentPrice = computer.getOverallPerformance();
            if (computer.getOverallPerformance()>highestPerformance && computer.getPrice()<=budget){
                highestPerformance = computer.getOverallPerformance();
                bestPrice = computer.getPrice();
                model = computer.getModel();
                manufacture = computer.getManufacturer();
                id = computer.getId();
                per = computer.getComponents().toString();
               computerToRemove = computer;
                noMatches = false;
            }
        }
        if (computerToRemove !=null){
            sb.append(computerToRemove);
            this.computers.remove(computerToRemove);
        }
        if (noMatches){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }

      //  return String.format(OutputMessages.PRODUCT_TO_STRING, highestPerformance, bestPrice, model, manufacture, per,id);
    return sb.toString().trim();
    }

    @Override
    public String getComputerData(int id) {
        if (this.computers.stream().noneMatch(idCurr -> idCurr.getId() == id)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }

        /*StringBuilder sb = new StringBuilder();
        Computer computer = this.computers.stream().filter(idCurr -> idCurr.getId() == id).findFirst().orElse(null);
        sb.append(String.format(OutputMessages.PRODUCT_TO_STRING, computer.getOverallPerformance(), computer.getPrice(),
                        computer.getClass().getSimpleName(), computer.getManufacturer(),
                        computer.getModel(), computer.getId())).append(System.lineSeparator());
        int count = 0;

        for (int i = 0; i <this.computers.size() ; i++) {
            count ++;
                    this.computers.get(i).getComponents();
        }
        sb.append(String.format(OutputMessages.COMPUTER_COMPONENTS_TO_STRING, count)).append(System.lineSeparator());

        for (Component component : this.components) {
            sb.append(component).append(System.lineSeparator());
        }
        double averagePerformance = this.peripherals.stream().mapToDouble(Peripheral::getOverallPerformance).sum() / this.peripherals.size();
        sb.append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING, this.peripherals.size(),averagePerformance)).append(System.lineSeparator());

        for (Peripheral per :this.peripherals) {
            sb.append(per).append(System.lineSeparator());
        }*/

        return this.computers.stream().map(Computer::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
