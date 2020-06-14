public class Arrow {

    Position position;
    String color; // line color
    String type;  // solid or not

    public Arrow (Position p, String c, String t){
            this.position = p;
            this.color = c;
            this.type = t;
    }
    public void setPosition (int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
    public void setColor (String color){
        this.color = color;
    }
    public void setType (String type){
        this.type = type;
    }
    public Position getPosition (){
        return this.position;
    }
    public String getColor () {
        return this.color;
    }
    public String getType () {
        return this.type;
    }




}
