public class DataNode extends Node {
    private Object data;
    DataNode(Object object) {
        this.data = object;
    }
    public Object value() {
        return this.data;
    }
}
