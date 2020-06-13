import java.util.ArrayList;
import java.util.List;

public class Box {
    public String classInfo;
    public List<String> fieldsInfo;
    public List<String> methodsInfo;
    public Position center;

    public Box(String c, Position center) {
        this.classInfo = c;
        this.center = center;
        this.fieldsInfo = new ArrayList<>();
        this.methodsInfo = new ArrayList<>();
    }

    public void drawBox(){


    }
}
