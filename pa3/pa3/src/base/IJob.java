public interface IJob {
    int getPatience();
    int getPid();
    void serve();
    boolean isDone();
}
