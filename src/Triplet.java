import java.util.Objects;

public class Triplet{
    private String src;
    private String des;
    private DependEnum type;

    public Triplet(String src, String des, DependEnum type) {
        this.src = src;
        this.des = des;
        this.type = type;
    }

    public Triplet(String src) {
        this.src = src;
    }

    public Triplet() {
    }


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public DependEnum getType() {
        return type;
    }

    public void setType(DependEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "src='" + src + '\'' +
                ", des='" + des + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet triplet = (Triplet) o;
        return Objects.equals(src, triplet.src) &&
                Objects.equals(des, triplet.des) &&
                type == triplet.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, des, type);
    }
}