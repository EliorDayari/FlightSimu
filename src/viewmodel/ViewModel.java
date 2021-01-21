package viewmodel;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.Model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import java.util.Observer;
import java.util.Observable;

public class ViewModel extends Observable implements Observer
{
    public DoubleProperty throttle;
    public DoubleProperty rudder;
    public DoubleProperty aileron;
    public DoubleProperty elevator;
    public StringProperty ip;
    public StringProperty port;
    public DoubleProperty airplaneX;
    public DoubleProperty airplaneY;
    public DoubleProperty startX;
    public DoubleProperty startY;
    public DoubleProperty offset;
    public StringProperty script;
    public DoubleProperty heading;
    public DoubleProperty markSceneX;
    public DoubleProperty markSceneY;
    public BooleanProperty path;
    private int[][] data;
    private Model model;

    public void setData(final int[][] data) {
        this.data = data;
        this.model.GetPlane(this.startX.getValue(), this.startY.doubleValue(), this.offset.getValue());
    }

    public ViewModel() {
        this.throttle = (DoubleProperty)new SimpleDoubleProperty();
        this.rudder = (DoubleProperty)new SimpleDoubleProperty();
        this.aileron = (DoubleProperty)new SimpleDoubleProperty();
        this.elevator = (DoubleProperty)new SimpleDoubleProperty();
        this.ip = (StringProperty)new SimpleStringProperty();
        this.port = (StringProperty)new SimpleStringProperty();
        this.airplaneX = (DoubleProperty)new SimpleDoubleProperty();
        this.airplaneY = (DoubleProperty)new SimpleDoubleProperty();
        this.startX = (DoubleProperty)new SimpleDoubleProperty();
        this.startY = (DoubleProperty)new SimpleDoubleProperty();
        this.offset = (DoubleProperty)new SimpleDoubleProperty();
        this.script = (StringProperty)new SimpleStringProperty();
        this.heading = (DoubleProperty)new SimpleDoubleProperty();
        this.markSceneX = (DoubleProperty)new SimpleDoubleProperty();
        this.markSceneY = (DoubleProperty)new SimpleDoubleProperty();
        this.path = (BooleanProperty)new SimpleBooleanProperty();
    }

    public void setModel(final Model model) {
        this.model = model;
    }

    public void setThrottle() {
        final String[] data = { "set /controls/engines/current-engine/throttle " + this.throttle.getValue() };
        this.model.send(data);
    }

    public void setRudder() {
        final String[] data = { "set /controls/flight/rudder " + this.rudder.getValue() };
        this.model.send(data);
    }

    public void setJoystick() {
        final String[] data = { "set /controls/flight/aileron " + this.aileron.getValue(), "set /controls/flight/elevator " + this.elevator.getValue() };
        this.model.send(data);
    }

    public void connect() {
        this.model.connectManual(this.ip.getValue(), Integer.parseInt(this.port.getValue()));
    }

    public void parse() {
        final Scanner scanner = new Scanner(this.script.getValue());
        final ArrayList<String> list = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        final String[] tmp = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            tmp[i] = list.get(i);
        }
        this.model.parse(tmp);
    }

    public void execute() {
        this.model.execute();
    }

    public void stopAutoPilot() {
        this.model.stopAutoPilot();
    }

    public void findPath(final double h, final double w) {
        if (!this.path.getValue()) {
            this.model.connectPath(this.ip.getValue(), Integer.parseInt(this.port.getValue()));
        }
        this.model.findPath((int)(this.airplaneY.getValue() / -1.0), (int)(this.airplaneX.getValue() + 15.0), Math.abs((int)(this.markSceneY.getValue() / h)), Math.abs((int)(this.markSceneX.getValue() / w)), this.data);
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (o == this.model) {
            final String[] tmp = (String[])arg;
            if (tmp[0].equals("plane")) {
                double x = Double.parseDouble(tmp[1]);
                double y = Double.parseDouble(tmp[2]);
                x = x - this.startX.getValue() + this.offset.getValue();
                x /= this.offset.getValue();
                y = (y - this.startY.getValue() + this.offset.getValue()) / this.offset.getValue();
                this.airplaneX.setValue((Number)x);
                this.airplaneY.setValue((Number)y);
                this.heading.setValue((Number)Double.parseDouble(tmp[3]));
                this.setChanged();
                this.notifyObservers();
            }
            else if (tmp[0].equals("path")) {
                this.setChanged();
                this.notifyObservers(tmp);
            }
        }
    }
}
